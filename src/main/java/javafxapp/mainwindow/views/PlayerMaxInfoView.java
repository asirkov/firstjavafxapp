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
        ImageView playerAvatarRoot = new ImageView(new Image("https://karate.moscow/image/cache/data/Karate/Kimono/bs-14-two-160x160.png",true));
        playerAvatarRoot.setFitWidth(width - 20);
        playerAvatarRoot.setFitHeight(width - 20);
//        playerAvatarRoot.setTranslateX(playerAvatarRoot.getTranslateX());

        Label lblPlayerName = new BigLabel(player.getPlayerName(), width, MainWindowConfig.HEADER_HEIGHT);

        Tooltip tooltipPlayerName = new Tooltip(player.getPlayerName());
        tooltipPlayerName.setShowDelay(Duration.seconds(0.5));
        tooltipPlayerName.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        lblPlayerName.setTooltip(tooltipPlayerName);

        this.getChildren().addAll(
                playerAvatarRoot,
                lblPlayerName,
                (player.getOnline() ? new RegularLabel("Status:\tonline", width, Paint.valueOf(MainWindowConfig.FIRST_FONT_COLOR)) :
                                      new RegularLabel("Status:\toffline", width, Paint.valueOf(MainWindowConfig.SECOND_FONT_COLOR))),
                new RegularLabel("Games:\t" + player.getGames(), width, Paint.valueOf(MainWindowConfig.FIRST_FONT_COLOR)),
                new RegularLabel("Wins:\t" + (player.getRate() * 100) + "%", width, Paint.valueOf(MainWindowConfig.FIRST_FONT_COLOR))
        );
    }
}
