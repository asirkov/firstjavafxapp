package javafxapp.util;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafxapp.authwindows.config.AuthConfig;
import javafxapp.config.Config;
import javafxapp.mainwindow.MainWindow;
import javafxapp.mainwindow.config.MainWindowConfig;

public class ActionButton extends Button {
    public ActionButton(String s, double width) {
        super(s);

        this.setMinSize(width, MainWindowConfig.BUTTON_HEIGHT);
        this.setFont(Font.font("helvetica neue", FontWeight.EXTRA_BOLD, 24));
        this.setAlignment(Pos.CENTER);
        this.setCursor(Cursor.HAND);
        this.setDefaultButton(true);
    }
}
