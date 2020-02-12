package javafxapp.authwindows.util.textfields;

import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafxapp.authwindows.config.AuthConfig;

public class RegularTextField extends TextField {
    public RegularTextField() {
        this.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        this.setPrefHeight(AuthConfig.TEXT_HEIGHT);
        this.setMinWidth(AuthConfig.TEXT_WIDTH);
    }
}
