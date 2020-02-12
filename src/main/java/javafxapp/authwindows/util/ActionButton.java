package javafxapp.authwindows.util;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafxapp.authwindows.config.AuthConfig;

public class ActionButton extends Button {
    public ActionButton(String s) {
        super(s);

        this.setMinSize(AuthConfig.BUTTON_WIDTH, AuthConfig.BUTTON_HEIGHT);
        this.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        this.setAlignment(Pos.CENTER);
        this.setCursor(Cursor.HAND);
        this.setDefaultButton(true);
    }
}
