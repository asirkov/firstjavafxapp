package javafxapp.authwindows.util.labels;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafxapp.authwindows.config.AuthConfig;

public class BigLabel extends Label {
    public BigLabel(String s) {
        super(s);
        this.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 32));
        this.setAlignment(AuthConfig.LABELS_POSITION);
        this.setMinWidth(AuthConfig.BIG_LABEL_WIDTH);
        this.setMaxWidth(AuthConfig.BIG_LABEL_WIDTH);
        this.setMinHeight(AuthConfig.LABELS_HEIGHT);
        this.setPadding(new Insets(0, 0, 0, 0));
    }


    public BigLabel(String s, Paint paint) {
        this(s);
        this.setTextFill(paint);
    }
}
