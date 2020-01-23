package javafxapp.mainwindow.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafxapp.api.model.PlayerModel;
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
            new PlayerModel(20L, 20L, "OHNOOOOOO", 109L, 0.34d, true)
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

        playersList.forEach(playerModel -> vb.getChildren().add(new PlayerMinInfoView(playerModel, width - 6) ));

        sp.setMinSize(width, height);
        sp.setMaxSize(width, height);

        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        return sp;
    }

    public MainWindowRightPane(PlayerModel player, List<PlayerModel> playersList, double width, double height) {
        super();
        this.setPadding(new Insets(0, 10, 0, 0));
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(0);

        Label lblOnlineHeader = new BigLabel("Online: " + onlineList.size(), width, (height * 0.07d));
        ScrollPane spOnline = createPlayersList(onlineList, width, (height * 0.93d / 2) - VSeparator.SEPARATOR_HEIGHT * 2);


        Label lblFriendsHeader = new BigLabel("Friends: " + friendsList.size(), width, (height * 0.07d));
        ScrollPane spFriends = createPlayersList(friendsList, width, (height * 0.93d / 2) - VSeparator.SEPARATOR_HEIGHT * 2);

        this.getChildren().addAll(
                lblOnlineHeader,
                new VSeparator(width),
                spOnline,
                new VSeparator(width),
                lblFriendsHeader,
                new VSeparator(width),
                spFriends
        );

        this.setMinWidth(width);
        this.setMaxWidth(width);
    }

    public void update() {}
}
