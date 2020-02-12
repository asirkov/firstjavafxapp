package javafxapp.authwindows.util.links;

import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafxapp.authwindows.config.AuthConfig;

public class RegularLink extends Hyperlink {
    public RegularLink(String s) {
        super(s);
        this.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.ITALIC, 14));
        this.setAlignment(Pos.CENTER);
        this.setPrefWidth(AuthConfig.SMALL_LABEL_WIDTH);
        this.setPrefHeight(AuthConfig.LABELS_HEIGHT);
    }
}
