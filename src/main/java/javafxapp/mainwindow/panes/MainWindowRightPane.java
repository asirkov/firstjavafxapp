package javafxapp.mainwindow.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafxapp.api.model.PlayerModel;
import javafxapp.mainwindow.config.MainWindowConfig;
import javafxapp.util.separators.VSeparator;
import javafxapp.mainwindow.views.PlayerMinInfoView;

import java.util.List;

public class MainWindowRightPane extends VBox {

    private List<PlayerModel> onlineList = List.of(
            new PlayerModel(13L, 13L, "Stepa", 1229L, 0.68d, true),
            new PlayerModel(14L, 14L, "Petro", 929L, 0.4d, true),
            new PlayerModel(15L, 15L, "Kesha", 297L, 0.56d, true),
            new PlayerModel(16L, 16L, "Serega", 129L, 0.64d, true),
            new PlayerModel(17L, 17L, "Kolia", 1229L, 0.68d, true),
            new PlayerModel(18L, 18L, "Misha", 99L, 0.41d, true),
            new PlayerModel(19L, 19L, "Andrey", 267L, 0.16d, true),
            new PlayerModel(20L, 20L, "Zina", 109L, 0.34d, true),
            new PlayerModel(21L, 13L, "Alina", 1229L, 0.68d, true),
            new PlayerModel(22L, 14L, "Olena", 929L, 0.4d, true),
            new PlayerModel(23L, 15L, "Nikita", 297L, 0.56d, true),
            new PlayerModel(24L, 16L, "Liosha", 129L, 0.64d, true),
            new PlayerModel(25L, 17L, "Aleksei", 1229L, 0.68d, true),
            new PlayerModel(26L, 18L, "Lionia", 99L, 0.41d, true),
            new PlayerModel(27L, 19L, "Semen", 267L, 0.16d, true),
            new PlayerModel(28L, 20L, "Inokentii", 109L, 0.34d, true)
            );

    private List<PlayerModel> friendsList = List.of(
            new PlayerModel(16L, 16L, "Serega", 129L, 0.64d, true),
            new PlayerModel(29L, 29L, "Nikolay", 129L, 0.64d, false),
            new PlayerModel(30L, 30L, "Sergii", 129L, 0.64d, false)
            );


    private ScrollPane createPlayersList(Stage primaryStage, List<PlayerModel> playersList, double width, double height) {
        VBox vb = new VBox();
        vb.setPadding(new Insets(0));
        vb.setSpacing(5);

        ScrollPane sp = new ScrollPane();
        sp.setPadding(new Insets(0));
        sp.setContent(vb);

        playersList.forEach(player -> {
            PlayerMinInfoView view = new PlayerMinInfoView(primaryStage, player, width);
            view.setBorder(new Border(new BorderStroke(Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY,
                    BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
                    CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));

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

    public MainWindowRightPane(Stage primaryStage, PlayerModel player, List<PlayerModel> playersList, double width, double height) {
        super();
        this.setPadding(new Insets(0, 10, 0, 0));
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(0);

        TabPane tabPane = new TabPane();
        VBox.setVgrow(tabPane, Priority.ALWAYS);


        Tab tabOnline = new Tab("Online: " + onlineList.size());
        tabOnline.setStyle("-fx-font-size:14;");
        tabOnline.setClosable(false);

        Tab tabFriends = new Tab("Friends: " + friendsList.size());
        tabFriends.setStyle("-fx-font-size:14;");
        tabFriends.setClosable(false);


        ScrollPane spOnline = createPlayersList(primaryStage, onlineList, width,
                (height - MainWindowConfig.HEADER_HEIGHT * 2) - VSeparator.SEPARATOR_HEIGHT - tabPane.getTabMinHeight());
        spOnline.setPadding(new Insets(VSeparator.SEPARATOR_HEIGHT,0,0,0));
        VBox.setVgrow(spOnline, Priority.ALWAYS);
        tabOnline.setContent(spOnline);

        ScrollPane spFriends = createPlayersList(primaryStage, friendsList, width,
                (height - MainWindowConfig.HEADER_HEIGHT * 2) - VSeparator.SEPARATOR_HEIGHT - tabPane.getTabMinHeight());
        spFriends.setPadding(new Insets(VSeparator.SEPARATOR_HEIGHT,0,0,0));
        VBox.setVgrow(spFriends, Priority.ALWAYS);
        tabFriends.setContent(spFriends);

        tabPane.setTabDragPolicy(TabPane.TabDragPolicy.FIXED);
        tabPane.setTabMinHeight(MainWindowConfig.HEADER_HEIGHT);
        tabPane.setTabMinWidth(width / 2);
        tabPane.getTabs().addAll(tabOnline, tabFriends);
        tabPane.setPadding(new Insets(0));

        this.getChildren().add(tabPane);
    }

    public void update() {}
}
