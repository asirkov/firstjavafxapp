package javafxapp.mainwindow.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxapp.api.model.PlayerModel;
import javafxapp.mainwindow.views.icons.PlayerOnlineIcon;
import javafxapp.labels.*;
import javafxapp.mainwindow.windows.PlayerMaxInfoWindow;

import java.util.List;

public class PlayerMinInfoView extends HBox {
    List<String> imagesUrlList = List.of("https://cdn.shortpixel.ai/client/q_glossy,ret_img,w_900/https://robymgood.com/wp-content/uploads/2019/08/avatar.jpg",
            "https://i0.wp.com/www.cordcuttersnews.com/wp-content/uploads/2019/12/Baby-Yoda.jpg?ssl=1",
            "https://static-resource.np.community.playstation.net/avatar/WWS_J/J0039.png",
            "https://store.playstation.com/store/api/chihiro/00_09_000/container/TH/en/19/UP4396-CUSA04449_00-ASIA000000000014/image?w=240&h=240&bg_color=000000&opacity=100&_version=00_09_000",
            "https://s01.riotpixels.net/data/88/82/88825b62-ae51-4b34-88d3-bd6a50c39fd3.jpg/avatar.witcher-3-wild-hunt.600x600.2014-06-08.280.jpg",
            "https://www.sp-studio.de/wp-content/uploads/start_supportme.png",
            "https://resizing.flixster.com/P8vqZ14SyFhsabGbZRdciwohp50=/1000x1500/v1.bjs1MjIwNTk7ajsxODMyMTsxMjAwOzEwMDA7MTUwMA",
            "https://i1.wp.com/slovami.net/wp-content/uploads/2018/04/1-36-1024x1024.jpg");


    public PlayerMinInfoView(Stage primaryStage, PlayerModel playerModel, double width) {
        this.setSpacing(0);
        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER);

        ImageView playerAvatarRoot = new ImageView(new Image(imagesUrlList.get((int) (Math.random() * imagesUrlList.size())),true));
        playerAvatarRoot.setFitWidth(40);
        playerAvatarRoot.setFitHeight(40);
        playerAvatarRoot.setClip(new Circle(20, 20, 20));


        Label lblPlayerName = new RegularLabel(playerModel.getPlayerName(), width - this.getSpacing() - PlayerOnlineIcon.ICON_RADIUS * 2 - 25);
        lblPlayerName.setPadding(new Insets(5));

        PlayerOnlineIcon onlineIcon = new PlayerOnlineIcon(playerModel.getOnline());

        this.getChildren().addAll(playerAvatarRoot, lblPlayerName, onlineIcon);
//        this.setBorder(new Border(new BorderStroke(
//                Color.GRAY,
//                BorderStrokeStyle.SOLID,
//                new CornerRadii(3),
//                new BorderWidths(1))));



        this.setCursor(Cursor.HAND);
        this.setOnMouseClicked(e -> {
            Scene playerMaxInfoScene = new Scene(new PlayerMaxInfoWindow(playerModel, 400, 400));

            Stage playerMaxInfoWindow = new Stage();
            playerMaxInfoWindow.setTitle("Player \"" + playerModel.getPlayerName() + "\" info.");
            playerMaxInfoWindow.setScene(playerMaxInfoScene);
            playerMaxInfoWindow.setResizable(false);

            playerMaxInfoWindow.initModality(Modality.WINDOW_MODAL);
            playerMaxInfoWindow.initOwner(primaryStage);

            playerMaxInfoWindow.show();
        });
    }

}
