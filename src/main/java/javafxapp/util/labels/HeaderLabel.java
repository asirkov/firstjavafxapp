package javafxapp.util.labels;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafxapp.mainwindow.config.MainWindowConfig;

public class HeaderLabel extends Label {
    public HeaderLabel(String s, double width) {
        super(s);
        this.setFont(Font.font("helvetica neue", FontWeight.EXTRA_BOLD, 24));

        this.setAlignment(Pos.CENTER_LEFT);
        this.setMinWidth(width);
        this.setMaxWidth(width);
        this.setMinHeight(MainWindowConfig.HEADER_HEIGHT);
        this.setMaxHeight(MainWindowConfig.HEADER_HEIGHT);
        this.setPadding(new Insets(0));
    }
}
