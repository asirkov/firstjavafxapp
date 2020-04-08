package javafxapp.mainwindow.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxapp.api.dto.UsrDto;
import javafxapp.mainwindow.views.icons.PlayerOnlineIcon;
import javafxapp.util.labels.*;
import javafxapp.mainwindow.windows.PlayerMaxInfoWindow;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class PlayerMinInfoView extends HBox {
    public PlayerMinInfoView(Stage primaryStage, UsrDto usrDto, byte[] avatarData, double width) {
        this.setSpacing(0);
        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER);

        ImageView playerAvatarRoot = new ImageView(new Image(new ByteArrayInputStream(avatarData)));
        playerAvatarRoot.setFitWidth(40);
        playerAvatarRoot.setFitHeight(40);
        playerAvatarRoot.setClip(new Circle(20, 20, 20));


        Label lblPlayerName = new RegularLabel(usrDto.getPlayerName(), width - this.getSpacing() - PlayerOnlineIcon.ICON_RADIUS * 2 - 25);
        lblPlayerName.setPadding(new Insets(5));

        PlayerOnlineIcon onlineIcon = new PlayerOnlineIcon(usrDto.isOnline());

        this.getChildren().addAll(playerAvatarRoot, lblPlayerName, onlineIcon);
//        this.setBorder(new Border(new BorderStroke(
//                Color.GRAY,
//                BorderStrokeStyle.SOLID,
//                new CornerRadii(3),
//                new BorderWidths(1))));



        this.setCursor(Cursor.HAND);
        this.setOnMouseClicked(e -> {
            Scene playerMaxInfoScene = new Scene(new PlayerMaxInfoWindow(usrDto, 400, 400));

            Stage playerMaxInfoWindow = new Stage();
            playerMaxInfoWindow.setTitle("Player \"" + usrDto.getPlayerName() + "\" info.");
            playerMaxInfoWindow.setScene(playerMaxInfoScene);
            playerMaxInfoWindow.setResizable(false);

            playerMaxInfoWindow.initModality(Modality.WINDOW_MODAL);
            playerMaxInfoWindow.initOwner(primaryStage);

            playerMaxInfoWindow.show();
        });
    }

}
