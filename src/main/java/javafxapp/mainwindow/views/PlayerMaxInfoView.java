package javafxapp.mainwindow.views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import javafxapp.api.dto.UsrDto;
import javafxapp.api.model.PlayerModel;
import javafxapp.authwindows.config.AuthConfig;
import javafxapp.mainwindow.config.MainWindowConfig;
import javafxapp.util.labels.*;

public class PlayerMaxInfoView extends VBox {

    public PlayerMaxInfoView(UsrDto currentUsr, double width) {
        this.setPadding(new Insets(0, 10, 0, 10));

        ImageView playerAvatarRoot = new ImageView(new Image(AuthConfig.DEFAULT_AVATAR_PATH,true));
        playerAvatarRoot.setFitWidth(width - 20);
        playerAvatarRoot.setFitHeight(width - 20);
        playerAvatarRoot.setClip(new Circle((width) / 2, (width) / 2, (width) / 2));

        Label lblPlayerName = new BigLabel(currentUsr.getPlayerName(), width, MainWindowConfig.HEADER_HEIGHT);

        Tooltip tooltipPlayerName = new Tooltip(currentUsr.getPlayerName());
        tooltipPlayerName.setShowDelay(Duration.seconds(0.5));
        tooltipPlayerName.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        lblPlayerName.setTooltip(tooltipPlayerName);

        this.getChildren().addAll(
                playerAvatarRoot,
                lblPlayerName,
                (currentUsr.isOnline() ? new RegularLabel("Status:\tonline", width, Paint.valueOf(MainWindowConfig.FIRST_FONT_COLOR)) :
                                      new RegularLabel("Status:\toffline", width, Paint.valueOf(MainWindowConfig.SECOND_FONT_COLOR))),
                new RegularLabel("Games:\t" + currentUsr.getGamesCount(), width, Paint.valueOf(MainWindowConfig.FIRST_FONT_COLOR)),
                new RegularLabel("Wins:\t" + (currentUsr.getGamesCount() == 0 ? 100 : currentUsr.getGamesCount() / currentUsr.getWinsCount() * 100) + "%",
                        width, Paint.valueOf(MainWindowConfig.FIRST_FONT_COLOR))
        );
    }
}
