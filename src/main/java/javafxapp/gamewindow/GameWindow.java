package javafxapp.gamewindow;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafxapp.config.Config;
import javafxapp.gamewindow.checker.Checker;
import javafxapp.gamewindow.checker.CheckerType;
import javafxapp.gamewindow.tile.BorderType;
import javafxapp.gamewindow.tile.Tile;
import javafxapp.gamewindow.move.MoveResult;
import javafxapp.gamewindow.move.MoveType;
import javafxapp.util.separators.HSeparator;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class GameWindow extends Application {
//    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    public static final int TILE_SIZE = (int) (Config.WINDOW_HEIGHT / 10);
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Group tileGroup = new Group();
    private Group checkersGroup = new Group();

    private Pane createGamePane() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.setMinSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);

        root.getChildren().addAll(tileGroup, checkersGroup);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile(this,(x + y) % 2 == 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);

                Checker checker = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    checker = makeChecker(tile, CheckerType.BLACK, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    checker = makeChecker(tile, CheckerType.WHITE, x, y);
                }

                if (checker != null) {
                    tile.setChecker(checker);
                    checkersGroup.getChildren().add(checker);
                }
            }
        }

        return root;
    }

    private Checker makeChecker(Tile parent, CheckerType type, int x, int y) {
        Checker checker = new Checker(parent, type, x, y);

        checker.setOnMouseReleased(e -> {
            int newX = toBoardCoords(checker.getLayoutX());
            int newY = toBoardCoords(checker.getLayoutY());

            MoveResult result;
            if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                result = new MoveResult(MoveType.NONE);
            } else {
                result = tryMove(checker, newX, newY);
            }

            int x0 = toBoardCoords(checker.getOldX());
            int y0 = toBoardCoords(checker.getOldY());

            switch (result.getType()) {
                case NONE:
                    checker.abortMove();
                    break;
                case MOVE:
                    checker.move(newX, newY);
                    board[x0][y0].setChecker(null);
                    board[newX][newY].setChecker(checker);
                    break;
                case KILL:
                    board[x0][y0].setChecker(null);
                    board[newX][newY].setChecker(checker);

                    Checker otherChecker = result.getChecker();
                    board[toBoardCoords(otherChecker.getOldX())][toBoardCoords(otherChecker.getOldY())].setChecker(null);
                    checkersGroup.getChildren().remove(otherChecker);
                    break;
            }
        });

        return checker;
    }

    private int toBoardCoords(double pixelCoord) {
        return (int) (pixelCoord + TILE_SIZE / 2) / TILE_SIZE;
    }

    private MoveResult tryMove(Checker checker, int newX, int newY) {
//        if (board[newX][newY].hasChecker() || (newX + newY) % 2 == 0) {
//            return new MoveResult(MoveType.NONE);
//        }

        int x0 = toBoardCoords(checker.getOldX());
        int y0 = toBoardCoords(checker.getOldY());

        try {
            // TODO: Make queen check
            if (Math.abs(newX - x0) == 1 && newY - y0 == checker.getCheckerType().moveDirection) {
                return new MoveResult(MoveType.MOVE);
            } else if (Math.abs(newX - x0) == 2 && newY - y0 == checker.getCheckerType().moveDirection * 2) {
                int x1 = x0 + (newX - x0) / 2;
                int y1 = y0 + (newY - y0) / 2;

                if (board[x1][y1].hasChecker() && board[x1][y1].getChecker().getCheckerType() != checker.getCheckerType()) {
                    return new MoveResult(MoveType.KILL, board[x1][y1].getChecker());
                }
            }
        } catch (Exception ex) {
            return new MoveResult(MoveType.NONE);
        }

        return new MoveResult(MoveType.NONE);
    }

    public void showMoves(double mouseX, double mouseY) {
        int rootX = toBoardCoords(mouseX);
        int rootY = toBoardCoords(mouseY);
        Checker root = board[rootX][rootY].getChecker();
        boolean haveMoves = false;
        boolean haveKills = false;

        cleanTileBorders();


//        for (int y = rootY; y < HEIGHT && y >= 0; y += root.getCheckerType().moveDirection) {
////            for (int x = rootX; x < WIDTH && x >= 0; x++) {
////                if ((x + y) % 2 != 0) {
////                    if (!board[x][y].hasChecker())
////                        board[x][y].setTileBorder(BorderType.BLUE, root);
////                    else if (board[x][y].getChecker().getCheckerType().moveDistance != root.getCheckerType().moveDistance) {
////                        board[x][y].setTileBorder(BorderType.RED, root);
////                        int frontX = x - rootX > 0 ? x + 1 : x - 1;
////                        int frontY = y - rootY > 0 ? y + 1 : y - 1;
////                        board[frontX][frontY].setTileBorder(BorderType.BLUE, root);
////                    } else {
////                        board[x][y].setTileBorder(BorderType.NONE, null);
////                    }
////                }
////            }

////            for (int i = 0; i < WIDTH; i++) {
////                if (rootX - i < WIDTH) {
////                    int x = rootX - i;
////                    if ((x + y) % 2 != 0) {
////                        if (board[x][y].getChecker().getCheckerType().moveDistance != root.getCheckerType().moveDistance) {
////                            int frontX = x - rootX > 0 ? x + 1 : x - 1;
////                            int frontY = y - rootY > 0 ? y + 1 : y - 1;
////                            if (frontX >= 0 && frontX < WIDTH && frontY >= 0 && frontY < HEIGHT) {
////                                board[frontX][frontY].setTileBorder(BorderType.BLUE, root);
////                                board[x][y].setTileBorder(BorderType.RED, root);
////                                haveKills = true;
////                            }
////                        }
////                    }
////                }
////                if (rootX - i < WIDTH) {
////                    int x = rootX + i;
////                    if ((x + y) % 2 != 0) {
////                        if (board[x][y].getChecker().getCheckerType().moveDistance != root.getCheckerType().moveDistance) {
////                            int frontX = x - rootX > 0 ? x + 1 : x - 1;
////                            int frontY = y - rootY > 0 ? y + 1 : y - 1;
////                            if (frontX >= 0 && frontX < WIDTH && frontY >= 0 && frontY < HEIGHT) {
////                                board[frontX][frontY].setTileBorder(BorderType.BLUE, root);
////                                board[x][y].setTileBorder(BorderType.RED, root);
////                                haveKills = true;
////                            }
////                        }
////                    }
////                }
////
////            }

//            if (!haveKills) {
//                for (int i = 0; i < WIDTH; i++) {
//                    if (rootX - i < WIDTH) {
//                        int x = rootX - i;
//                        if ((x + y) % 2 != 0) {
//                            if (!board[x][y].hasChecker() && x - rootX <= root.getCheckerType().moveDistance && y - rootY <= root.getCheckerType().moveDistance) {
//                                board[x][y].setTileBorder(BorderType.BLUE, root);
//                            }
//                        }
//                    }
//                    if (rootX + i < WIDTH) {
//                        int x = rootX + i;
//                        if ((x + y) % 2 != 0) {
//                            if (!board[x][y].hasChecker() && x - rootX <= root.getCheckerType().moveDistance && y - rootY <= root.getCheckerType().moveDistance) {
//                                board[x][y].setTileBorder(BorderType.BLUE, root);
//                            }
//                        }
//                    }
//                }
//            }
//        }

        //

        rootX--;
        rootY--;
        System.err.println("check moves from rootX=" + rootX + ", rootY=" + rootY);
        if ((rootX + rootY) % 2 != 0) {
            for (int i = 0; Math.abs(i) <= root.getCheckerType().moveDistance; i += root.getCheckerType().moveDirection) {
                haveMoves = setTileBordersToMoves(rootX + i, rootY - i, root);
                haveMoves = setTileBordersToMoves(rootX - i, rootY - i, root);
            }
        }
//            if (y >= 0 && y < HEIGHT){
//                if (x >= 0 && x < WIDTH){
//                    if (!board[x][y].hasChecker()) {
//                        board[x][y].setTileBorder(BorderType.BLUE, root);
//                    } else if (board[x][y].getChecker().getCheckerType().moveDirection != root.getCheckerType().moveDirection) {
//                        board[x][y].setTileBorder(BorderType.RED, root);
//                    }
//                }
//            }


    }

    private boolean setTileBordersToKill(int x, int y, Checker root) {
        boolean resVal = false;

        if (x >= 0 && x < WIDTH){
            if (!board[x][y].hasChecker()) {
                board[x][y].setTileBorder(BorderType.BLUE, root);
            } else if (board[x][y].getChecker().getCheckerType().moveDirection != root.getCheckerType().moveDirection) {
                board[x][y].setTileBorder(BorderType.RED, root);
            }
        }

        return resVal;
    }

    private boolean setTileBordersToMoves(int x, int y, Checker root) {
        boolean resVal = false;
        System.err.print("check moves to X=" + x + ", Y=" + y + " ");

        if (board[x][y].getChecker().equals(root)) {
            board[x][y].setTileBorder(BorderType.BLUE, null);
            System.err.println("checked checker is root checker\n\n");
            return false;
        } else if (y < HEIGHT && x < WIDTH) {
//            if (!board[x][y].hasChecker()) {
                board[x][y].setTileBorder(BorderType.BLUE, root);
                resVal = true;
//            }
        }

        System.err.println((resVal?"move is correct":"can`t move to this direction") + "\n\n");
        return resVal;
    }

    private void cleanTileBorders() {
        for (int y = 0; y < HEIGHT; y++){
            for (int x = 0; x < WIDTH; x++){
                if ((x + y) % 2 != 0) {
                    board[x][y].setTileBorder(BorderType.NONE, null);
                }
            }
        }
    }

    private HBox createGameWindowPanes(double width, double height) {
        HBox hb = new HBox();
        hb.setMinSize(width, height);

        Pane gamePane = createGamePane();

        hb.getChildren().addAll(gamePane, new HSeparator(height));
        hb.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        return hb;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox mainWindowPanes = createGameWindowPanes(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        Scene scene = new Scene(mainWindowPanes, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        primaryStage.setTitle("CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}