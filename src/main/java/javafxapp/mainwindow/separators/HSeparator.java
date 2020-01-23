package javafxapp.mainwindow.separators;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;

public class HSeparator extends Separator {
    public static final double SEPARATOR_WIDTH = 10;

    public HSeparator(double height) {
        super();
        this.setOrientation(Orientation.VERTICAL);
        this.setPadding(new Insets(0, SEPARATOR_WIDTH/2, 0, SEPARATOR_WIDTH/2));
        this.setPrefHeight(height);
    }
}
