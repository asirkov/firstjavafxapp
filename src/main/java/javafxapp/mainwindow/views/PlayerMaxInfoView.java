package javafxapp.mainwindow.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import javafxapp.mainwindow.config.MainWindowConfig;

public class PlayerMaxInfoView extends VBox {

    public PlayerMaxInfoView(String playerName, Long playerGames, Double playerRate, double width) {
        Image imgPlayerAvatar = new Image("https://mundolatino.ru/forum/php/download/file.php?avatar=52889_1473972254.jpg",
                width - 20, width - 20, true, true);

        ImageView playerAvatarRoot = new ImageView();
        playerAvatarRoot.setFitWidth(width - 20);
        playerAvatarRoot.setFitHeight(width - 20);
        playerAvatarRoot.setTranslateX(playerAvatarRoot.getTranslateX());
        playerAvatarRoot.setImage(imgPlayerAvatar);


        Label lblPlayerName = new Label(playerName);
        lblPlayerName.setPadding(new Insets(0, 0, 20, 20));
        lblPlayerName.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 22));

        Tooltip tooltipPlayerName = new Tooltip(playerName);
        tooltipPlayerName.setTextAlignment(TextAlignment.CENTER);
        tooltipPlayerName.setShowDelay(Duration.seconds(0.5));
        tooltipPlayerName.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 14));
        lblPlayerName.setTooltip(tooltipPlayerName);


        Label lblPlayerGames = new Label("Games:\t" + String.valueOf(playerGames) + "");
        lblPlayerGames.setPadding(new Insets(0, 0, 0, 20));
        lblPlayerGames.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        lblPlayerGames.setTextFill(Paint.valueOf(MainWindowConfig.SECOND_FONT_COLOR));

        Label lblPlayerRate = new Label("Wins:\t" + (playerRate * 100) + "%");
        lblPlayerRate.setPadding(new Insets(0, 0, 0, 20));
        lblPlayerRate.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        lblPlayerRate.setTextFill(Paint.valueOf(MainWindowConfig.SECOND_FONT_COLOR));

        this.getChildren().addAll(playerAvatarRoot, lblPlayerName, lblPlayerGames, lblPlayerRate);
    }
}
