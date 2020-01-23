package javafxapp.mainwindow.views.icons;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;

public class PlayerOnlineIcon extends Ellipse {
    public static final double ICON_RADIUS = 5;

    public PlayerOnlineIcon(Boolean playerOnline) {
        super(ICON_RADIUS, ICON_RADIUS);

        if (playerOnline) {
            this.setFill(Paint.valueOf("#7CFC00"));
        } else {
            this.setFill(Paint.valueOf("#808080"));
        }

    }
}
