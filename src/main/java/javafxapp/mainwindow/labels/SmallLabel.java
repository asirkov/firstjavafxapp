package javafxapp.mainwindow.labels;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SmallLabel extends Label {
    public SmallLabel(String s, double width, double height) {
        super(s);
        this.setFont(Font.font("Arial", FontWeight.LIGHT, 11));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        this.setPadding(new Insets(0, 0, 0, 0));
    }

    public SmallLabel(String s, double width, double height, Paint paint) {
        this(s, width, height);
        this.setTextFill(paint);
    }
}
