package javafxapp.gamewindow;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafxapp.gamewindow.checker.Checker;
import javafxapp.gamewindow.checker.CheckerType;
import javafxapp.gamewindow.tile.Tile;
import javafxapp.gamewindow.move.MoveResult;
import javafxapp.gamewindow.move.MoveType;

public class GameWindow extends Application {
    public static final int TILE_SIZE = 50;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Group tileGroup = new Group();
    private Group checkersGroup = new Group();

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tileGroup, checkersGroup);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);

                Checker checker = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    checker = makeChecker(CheckerType.BLACK, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    checker = makeChecker(CheckerType.WHITE, x, y);
                }

                if (checker != null) {
                    tile.setChecker(checker);
                    checkersGroup.getChildren().add(checker);
                }
            }
        }

        return root;
    }

    private Checker makeChecker(CheckerType type, int x, int y) {
        Checker checker = new Checker(type, x, y);

        checker.setOnMouseReleased(e -> {
            int newX = toBoardCoords(checker.getLayoutX());
            int newY = toBoardCoords(checker.getLayoutY());

            MoveResult result;
            if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                result = new MoveResult(MoveType.NONE);
            } else {
                result = this.tryMove(checker, newX, newY);
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
        if (board[newX][newY].hasChecker() || (newX + newY) % 2 == 0) {
            return new MoveResult(MoveType.NONE);
        }

        int x0 = toBoardCoords(checker.getOldX());
        int y0 = toBoardCoords(checker.getOldY());

        // TODO: Make queen check
        if (Math.abs(newX - x0) == 1 && newY - y0 == checker.getCheckerType().moveDirection) {
            return new MoveResult(MoveType.MOVE);
        } else if (Math.abs(newX - x0) == 2 && newY - y0 == checker.getCheckerType().moveDirection * 2) {
            int x1 = x0 - (newX - x0) / 2;
            int y1 = y0 = (newY - y0) / 2;

            if (board[x1][y1].hasChecker() && board[x1][y1].getChecker().getCheckerType() != checker.getCheckerType()) {
                return new MoveResult(MoveType.KILL, board[x1][y1].getChecker());
            }
        }

        return new MoveResult(MoveType.NONE);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}