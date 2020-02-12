package javafxapp.authwindows.util.labels;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafxapp.authwindows.config.AuthConfig;

public class SmallLabel extends Label {
    public SmallLabel(String s) {
        super(s);
        this.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        this.setAlignment(AuthConfig.LABELS_POSITION);
        this.setMinWidth(AuthConfig.SMALL_LABEL_WIDTH);
        this.setMaxWidth(AuthConfig.SMALL_LABEL_WIDTH);
        this.setMinHeight(AuthConfig.LABELS_HEIGHT);
        this.setPadding(new Insets(0, 0, 0, 0));
    }


    public SmallLabel(String s, Paint paint) {
        this(s);
        this.setTextFill(paint);
    }
}
