package javafxapp.gamewindow.checker;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafxapp.gamewindow.config.CheckerConfig;
import lombok.Getter;
import lombok.Setter;

import static javafxapp.gamewindow.GameWindow.TILE_SIZE;

@Getter
@Setter
public class Checker extends StackPane {
    private final CheckerConfig checkerConfig = new CheckerConfig();

    private CheckerType checkerType;

    private double mouseX, mouseY;
    private double oldX, oldY;

    public Checker(CheckerType checkerType, int x, int y) {
        this.checkerType = checkerType;

        this.move(x, y);

        // Creating background
        Ellipse bg = new Ellipse(checkerConfig.getCHECKER_X_RADIUS() * TILE_SIZE,
                                 checkerConfig.getCHECKER_Y_RADIUS() * TILE_SIZE);
        bg.setFill(Color.BLACK);
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(TILE_SIZE * checkerConfig.getCHECKER_STROKE_WIDTH());

        bg.setTranslateX((TILE_SIZE - TILE_SIZE * checkerConfig.getCHECKER_X_RADIUS() * 2) / 2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * checkerConfig.getCHECKER_Y_RADIUS() * 2) / 2 + TILE_SIZE * 0.07);

        // Create inner circle
        Ellipse inbg = new Ellipse(checkerConfig.getCHECKER_X_RADIUS() * TILE_SIZE * 0.75,
                                   checkerConfig.getCHECKER_Y_RADIUS() * TILE_SIZE * 0.75);
        inbg.setFill(checkerType == CheckerType.BLACK ?
                Color.valueOf(checkerConfig.getBLACK_CHECKER_COLOR()) :
                Color.valueOf(checkerConfig.getWHITE_CHECKER_COLOR()));

        inbg.setStroke(Color.BLACK);
        inbg.setStrokeWidth(TILE_SIZE * checkerConfig.getCHECKER_STROKE_WIDTH());

        inbg.setTranslateX((TILE_SIZE - TILE_SIZE * checkerConfig.getCHECKER_X_RADIUS() * 2) / 2);
        inbg.setTranslateY((TILE_SIZE - TILE_SIZE * checkerConfig.getCHECKER_Y_RADIUS() * 2) / 2);

        // Creating front size
        Ellipse fg = new Ellipse(checkerConfig.getCHECKER_X_RADIUS() * TILE_SIZE,
                                 checkerConfig.getCHECKER_Y_RADIUS() * TILE_SIZE);
        fg.setFill(checkerType == CheckerType.BLACK ?
                Color.valueOf(checkerConfig.getBLACK_CHECKER_COLOR()) :
                Color.valueOf(checkerConfig.getWHITE_CHECKER_COLOR()));

        fg.setStroke(Color.BLACK);
        fg.setStrokeWidth(TILE_SIZE * checkerConfig.getCHECKER_STROKE_WIDTH());

        fg.setTranslateX((TILE_SIZE - TILE_SIZE * checkerConfig.getCHECKER_X_RADIUS() * 2) / 2);
        fg.setTranslateY((TILE_SIZE - TILE_SIZE * checkerConfig.getCHECKER_Y_RADIUS() * 2) / 2);

        this.getChildren().addAll(bg, fg, inbg);

        this.setOnMousePressed(e -> {
            this.mouseX = e.getSceneX();
            this.mouseY = e.getSceneY();
        });

        this.setOnMouseDragged(e -> {
            this.relocate(e.getSceneX() - this.mouseX + oldX, e.getSceneY() - this.mouseY + oldY);
        });
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
