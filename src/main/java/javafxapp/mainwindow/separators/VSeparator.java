package javafxapp.mainwindow.separators;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;

public class VSeparator extends Separator {
    public VSeparator(double width) {
        super();
        this.setOrientation(Orientation.HORIZONTAL);
        this.setPadding(new Insets(10, 0, 10, 0));
        this.setPrefWidth(width);
    }
}
