package javafxapp.gamewindow.checker;


import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafxapp.gamewindow.config.CheckerConfig;
import javafxapp.gamewindow.tile.BorderType;
import javafxapp.gamewindow.tile.Tile;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static javafxapp.gamewindow.GameWindow.TILE_SIZE;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false, exclude = {"checkerParent"})
@ToString(exclude="checkerParent")
public class Checker extends StackPane {
    private Tile checkerParent;
    private CheckerType checkerType;
    private boolean isQueen;

    private double mouseX, mouseY;
    private double oldX, oldY;

    public Checker(Tile checkerParent, CheckerType checkerType, int x, int y) {
        this.checkerParent = checkerParent;
        this.checkerType = checkerType;

        this.move(x, y);

        // Creating background
        Ellipse bg = new Ellipse(CheckerConfig.CHECKER_X_RADIUS * TILE_SIZE,
                                CheckerConfig.CHECKER_Y_RADIUS * TILE_SIZE);
        bg.setFill(Color.BLACK);
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(TILE_SIZE * CheckerConfig.CHECKER_STROKE_WIDTH);

        bg.setTranslateX((TILE_SIZE - TILE_SIZE * CheckerConfig.CHECKER_X_RADIUS * 2) / 2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * CheckerConfig.CHECKER_Y_RADIUS * 2) / 2 + TILE_SIZE * 0.07);

        // Creating front size
        Ellipse fg = new Ellipse(CheckerConfig.CHECKER_X_RADIUS * TILE_SIZE,
                                CheckerConfig.CHECKER_Y_RADIUS * TILE_SIZE);
        fg.setFill(checkerType == CheckerType.BLACK ?
                Color.valueOf(CheckerConfig.BLACK_CHECKER_COLOR) :
                Color.valueOf(CheckerConfig.WHITE_CHECKER_COLOR));

        fg.setStroke(Color.BLACK);
        fg.setStrokeWidth(TILE_SIZE * CheckerConfig.CHECKER_STROKE_WIDTH);

        fg.setTranslateX((TILE_SIZE - TILE_SIZE * CheckerConfig.CHECKER_X_RADIUS * 2) / 2);
        fg.setTranslateY((TILE_SIZE - TILE_SIZE * CheckerConfig.CHECKER_Y_RADIUS * 2) / 2);

        this.getChildren().addAll(bg, fg);

        this.setOnMousePressed(e -> {
            this.mouseX = e.getSceneX();
            this.mouseY = e.getSceneY();

            System.err.println(checkerParent.toString());
            checkerParent.showMoves(this.mouseX, this.mouseY);
        });

//        this.setOnMouseDragged(e -> {
//            this.relocate(e.getSceneX() - this.mouseX + oldX, e.getSceneY() - this.mouseY + oldY);
//        });
    }

    public void move(int x, int y) {
        oldX = x * TILE_SIZE;
        oldY = y * TILE_SIZE;

        this.relocate(oldX, oldY);
    }

    public void abortMove() {
        this.relocate(oldX, oldY);
    }
}
