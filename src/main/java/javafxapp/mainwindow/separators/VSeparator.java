package javafxapp.mainwindow.separators;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;

public class VSeparator extends Separator {
    public static final double SEPARATOR_HEIGHT = 10;

    public VSeparator(double width) {
        super();
        this.setOrientation(Orientation.HORIZONTAL);
        this.setPadding(new Insets(SEPARATOR_HEIGHT/2, 0, SEPARATOR_HEIGHT/2, 0));

        this.setMinWidth(width);
        this.setMaxWidth(this.getMinWidth());
    }
}
