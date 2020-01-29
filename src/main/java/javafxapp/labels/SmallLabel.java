package javafxapp.labels;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafxapp.mainwindow.config.MainWindowConfig;

public class SmallLabel extends Label {
    public SmallLabel(String s, double width) {
        super(s);
        this.setFont(Font.font("Arial", FontWeight.LIGHT, 11));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setMinWidth(width);
        this.setMaxWidth(width);
        this.setMinHeight(MainWindowConfig.HEADER_HEIGHT);
        this.setPadding(new Insets(0, 0, 0, 0));
    }

    public SmallLabel(String s, double width, Paint paint) {
        this(s, width);
        this.setTextFill(paint);
    }

    public SmallLabel(String s, double width, double height) {
        this(s, width);
        this.setMinHeight(height);
        this.setMaxHeight(height);
    }

    public SmallLabel(String s, double width, double height, Paint paint) {
        this(s, width, height);
        this.setTextFill(paint);
    }

}
