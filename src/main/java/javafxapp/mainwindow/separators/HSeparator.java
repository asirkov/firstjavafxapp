package javafxapp.mainwindow.separators;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;

public class HSeparator extends Separator {
    public HSeparator(double height) {
        super();
        this.setOrientation(Orientation.VERTICAL);
        this.setPadding(new Insets(0, 10, 0, 10));
        this.setPrefHeight(height);
    }
}
