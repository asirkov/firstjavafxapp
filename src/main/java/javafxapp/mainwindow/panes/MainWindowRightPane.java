package javafxapp.mainwindow.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafxapp.api.model.PlayerModel;
import javafxapp.mainwindow.config.MainWindowConfig;
import javafxapp.mainwindow.separators.HSeparator;
import javafxapp.mainwindow.separators.VSeparator;
import javafxapp.mainwindow.views.PlayerMinInfoView;
import javafxapp.mainwindow.labels.*;

import java.util.List;

public class MainWindowRightPane extends VBox {

    private List<PlayerModel> onlineList = List.of(
            new PlayerModel(13L, 13L, "<aaaaa>", 1229L, 0.68d, true),
            new PlayerModel(14L, 14L, "no!checrs", 929L, 0.4d, true),
            new PlayerModel(15L, 15L, "prafessiOnal", 297L, 0.56d, true),
            new PlayerModel(16L, 16L, "Yoyo", 129L, 0.64d, true),
            new PlayerModel(17L, 17L, "<aa>", 1229L, 0.68d, true),
            new PlayerModel(18L, 18L, "no!help", 99L, 0.41d, true),
            new PlayerModel(19L, 19L, "xexexexe", 267L, 0.16d, true),
            new PlayerModel(20L, 20L, "OHNOOOOOO", 109L, 0.34d, true),
            new PlayerModel(21L, 13L, "<aaaaa>", 1229L, 0.68d, true),
            new PlayerModel(22L, 14L, "no!checrs", 929L, 0.4d, true),
            new PlayerModel(23L, 15L, "prafessiOnal", 297L, 0.56d, true),
            new PlayerModel(24L, 16L, "Yoyo", 129L, 0.64d, true),
            new PlayerModel(25L, 17L, "<aa>", 1229L, 0.68d, true),
            new PlayerModel(26L, 18L, "no!help", 99L, 0.41d, true),
            new PlayerModel(27L, 19L, "xexexexe", 267L, 0.16d, true),
            new PlayerModel(28L, 20L, "OHNOOOOOO", 109L, 0.34d, true)
            );

    private List<PlayerModel> friendsList = List.of(
            new PlayerModel(16L, 16L, "Yoyo", 129L, 0.64d, true),
            new PlayerModel(17L, 17L, "HalloP", 129L, 0.64d, false),
            new PlayerModel(18L, 18L, "PUT IN", 129L, 0.64d, false)
            );


    private ScrollPane createPlayersList(List<PlayerModel> playersList, double width, double height) {
        VBox vb = new VBox();
        vb.setPadding(new Insets(0));
        vb.setSpacing(5);

        ScrollPane sp = new ScrollPane();
        sp.setPadding(new Insets(0));
        sp.setContent(vb);

        playersList.forEach(player -> {
            PlayerMinInfoView view = new PlayerMinInfoView(player, width);
            vb.getChildren().add(view);
            VBox.setVgrow(view, Priority.ALWAYS);
        });

        sp.setMinHeight(height);
//        sp.setMaxSize(width, height);

        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setFitToWidth(true);

        return sp;
    }

    public MainWindowRightPane(PlayerModel player, List<PlayerModel> playersList, double width, double height) {
        super();
        this.setPadding(new Insets(0, 10, 0, 0));
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(0);

        TabPane tabPane = new TabPane();
        VBox.setVgrow(tabPane, Priority.ALWAYS);


        Tab tabOnline = new Tab("Online");
        tabOnline.setClosable(false);
        Tab tabFriends = new Tab("Friends");
        tabFriends.setClosable(false);


        Label lblOnlineHeader = new BigLabel("Online: " + onlineList.size(), width / 2);
        ScrollPane spOnline = createPlayersList(onlineList, width, (height - MainWindowConfig.HEADER_HEIGHT * 2) - VSeparator.SEPARATOR_HEIGHT);
        VBox.setVgrow(lblOnlineHeader, Priority.NEVER);
        VBox.setVgrow(spOnline, Priority.ALWAYS);
        tabOnline.setContent(new VBox(lblOnlineHeader, new VSeparator(width), spOnline));

        Label lblFriendsHeader = new BigLabel("Friends: " + friendsList.size(), width / 2);
        ScrollPane spFriends = createPlayersList(friendsList, width, (height - MainWindowConfig.HEADER_HEIGHT * 2) - VSeparator.SEPARATOR_HEIGHT);
        VBox.setVgrow(lblFriendsHeader, Priority.NEVER);
        VBox.setVgrow(spFriends, Priority.ALWAYS);
        tabFriends.setContent(new VBox(lblFriendsHeader, new VSeparator(width), spFriends));

        tabPane.getTabs().addAll(tabOnline, tabFriends);

        this.getChildren().add(tabPane);

//        this.setMinWidth(width);
//        this.setMaxWidth(width);
    }

    public void update() {}
}
