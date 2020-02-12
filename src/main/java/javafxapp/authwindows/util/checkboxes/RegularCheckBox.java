package javafxapp.authwindows.util.checkboxes;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafxapp.authwindows.config.AuthConfig;

public class RegularCheckBox extends CheckBox {
    public RegularCheckBox(String s) {
        super(s);
        this.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        this.setMinWidth(AuthConfig.BIG_LABEL_WIDTH);
        this.setAlignment(Pos.CENTER_RIGHT);
    }
}
