package javafxapp.mainwindow.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafxapp.api.model.PlayerModel;
import javafxapp.mainwindow.views.icons.PlayerOnlineIcon;
import javafxapp.mainwindow.labels.*;

public class PlayerMinInfoView extends HBox {
    public PlayerMinInfoView(PlayerModel playerModel, double width) {
        this.setSpacing(0);
        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER);

        PlayerOnlineIcon onlineIcon = new PlayerOnlineIcon(playerModel.getOnline());

        Label lblPlayerName = new RegularLabel(playerModel.getPlayerName(), width - this.getSpacing() - PlayerOnlineIcon.ICON_RADIUS * 2 - 25);
        lblPlayerName.setPadding(new Insets(5));


        this.getChildren().addAll(lblPlayerName, onlineIcon);
        this.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

//        this.setMinWidth(width);
//        this.setMaxWidth(width);

        this.setCursor(Cursor.HAND);
//        this.setOnMouseClicked(e -> {
//
//        });
    }

}
