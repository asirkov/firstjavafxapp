package javafxapp.mainwindow.views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import javafxapp.api.model.PlayerModel;
import javafxapp.mainwindow.config.MainWindowConfig;
import javafxapp.mainwindow.labels.*;

public class PlayerMaxInfoView extends VBox {

    public PlayerMaxInfoView(PlayerModel player, double width) {
        String playerName = player.getPlayerName();
        Long playerGames = player.getGames();
        Double playerRate = player.getRate();
        Boolean playerOnline = player.getOnline();

        Image imgPlayerAvatar = new Image("https://karate.moscow/image/cache/data/Karate/Kimono/bs-14-two-160x160.png",true);

        ImageView playerAvatarRoot = new ImageView();
        playerAvatarRoot.setFitWidth(width - 20);
        playerAvatarRoot.setFitHeight(width - 20);
        playerAvatarRoot.setTranslateX(playerAvatarRoot.getTranslateX());
        playerAvatarRoot.setImage(imgPlayerAvatar);
        playerAvatarRoot.setSmooth(true);


        Label lblPlayerName = new BigLabel(playerName, width, MainWindowConfig.HEADER_HEIGHT);

        Tooltip tooltipPlayerName = new Tooltip(playerName);
        tooltipPlayerName.setShowDelay(Duration.seconds(0.5));
        tooltipPlayerName.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        lblPlayerName.setTooltip(tooltipPlayerName);


        Label lblPlayerGames = new RegularLabel("Games:\t" + String.valueOf(playerGames),
                width, Paint.valueOf(MainWindowConfig.FIRST_FONT_COLOR));

        Label lblPlayerRate = new RegularLabel("Wins:\t" + (playerRate * 100) + "%",
                width, Paint.valueOf(MainWindowConfig.FIRST_FONT_COLOR));

        Label lblPlayerStatus = new RegularLabel("Status:\tnull",
                width, Paint.valueOf(MainWindowConfig.FIRST_FONT_COLOR));

        if (playerOnline) {
            lblPlayerStatus.setText("Status:\tonline");
            lblPlayerStatus.setTextFill(Paint.valueOf(MainWindowConfig.FIRST_FONT_COLOR));
        } else {
            lblPlayerStatus.setText("Status:\toffline");
            lblPlayerStatus.setTextFill(Paint.valueOf(MainWindowConfig.SECOND_FONT_COLOR));
        }

        this.getChildren().addAll(playerAvatarRoot, lblPlayerName, lblPlayerStatus, lblPlayerGames, lblPlayerRate);
    }
}
