package javafxapp.mainwindow;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxapp.api.model.GameModel;
import javafxapp.api.model.GameResultType;
import javafxapp.api.model.PlayerModel;
import javafxapp.config.Config;
import javafxapp.mainwindow.config.MainWindowConfig;
import javafxapp.mainwindow.panes.MainWindowCenterPane;
import javafxapp.mainwindow.panes.MainWindowLeftPane;
import javafxapp.mainwindow.panes.MainWindowRightPane;
import javafxapp.mainwindow.windows.settingswindow.SettingsWindow;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class MainWindow extends Application {

    BorderPane createMainWindowPanes(double width, double height) {
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(0));

        bp.setMinSize(width, height);
        bp.setMaxSize(bp.getMinWidth(), bp.getMinHeight());

        return bp;
    }

    MenuBar createMenuBar(Stage primaryStage /*, Parent parent*/) {
        MenuBar mb = new MenuBar();

        Menu mProfileMenu = new Menu("Profile");
        Menu mAboutMenu = new Menu("About");
//        mAboutMenu.setOnAction(e -> {
//        });

        MenuItem profileMenuSettings = new MenuItem("Settings");
        profileMenuSettings.setOnAction(e -> {
            Scene settingsScene = new Scene(new SettingsWindow(400, 400));

            Stage settingsWindow = new Stage();
            settingsWindow.setTitle("Settings");
            settingsWindow.setScene(settingsScene);
            settingsWindow.setResizable(false);

            settingsWindow.initModality(Modality.WINDOW_MODAL);
            settingsWindow.initOwner(primaryStage);

            settingsWindow.show();
        });
        profileMenuSettings.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));

        MenuItem profileMenuLogOut = new MenuItem("Log out");
//        profileMenuLogOut.setOnAction(e -> {
//        });
//        profileMenuLogOut.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));

        mProfileMenu.getItems().addAll(profileMenuSettings,
                new SeparatorMenuItem(),
                profileMenuLogOut);

        mb.getMenus().addAll(mProfileMenu, mAboutMenu);

        mb.setMinHeight(MainWindowConfig.HEADER_HEIGHT);

        return mb;
    }

    @Override
    public void start(Stage primaryStage) {
        MenuBar mb = createMenuBar(primaryStage);

        double width = Config.WINDOW_WIDTH;
        double height = Config.WINDOW_HEIGHT - mb.getMaxHeight();

        BorderPane bp = createMainWindowPanes(width, height);

        PlayerModel player = new PlayerModel(1L, 1L, "Developer", 9999L, 0.99d, true);

        MainWindowLeftPane leftPane = new MainWindowLeftPane(player, (width * 0.2d) - 30, (height));


        PlayerModel participant1 = new PlayerModel(1L, 1L, "Joaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaara", 129L, 0.64d, false);
        PlayerModel participant2 = new PlayerModel(2L, 2L, "XXXDESTROYERXXX", 13L, 0.29d, true);
        PlayerModel participant3 = new PlayerModel(3L, 3L, "CCmasterC", 1181L, 0.48d, false);
        PlayerModel participant4 = new PlayerModel(4L, 4L, "kiskaaaaaaaaa", 22L, 0.78d, true);
        PlayerModel participant5 = new PlayerModel(5L, 5L, "sasha_112", 65L, 0.45d, true);
        PlayerModel participant6 = new PlayerModel(6L, 6L, "noname-1", 2210L, 0.54d, false);


        List<GameModel> gameModelList = List.of(
            new GameModel(1L, Date.from(Instant.now()), player, participant2, GameResultType.BLACK_WINS),
            new GameModel(2L, Date.from(Instant.now()), participant3, player, GameResultType.WHITE_WINS),
            new GameModel(3L, Date.from(Instant.now()), participant5, player, GameResultType.DRAW),
            new GameModel(4L, Date.from(Instant.now()), participant1, player, GameResultType.BLACK_WINS),
            new GameModel(5L, Date.from(Instant.now()), player, participant4, GameResultType.DRAW),
            new GameModel(6L, Date.from(Instant.now()), participant5, player, GameResultType.WHITE_WINS),
            new GameModel(7L, Date.from(Instant.now()), participant1, player, GameResultType.WHITE_WINS),
            new GameModel(8L, Date.from(Instant.now()), participant3, player, GameResultType.WHITE_WINS),
            new GameModel(9L, Date.from(Instant.now()), player, participant6, GameResultType.BLACK_WINS),
            new GameModel(10L, Date.from(Instant.now()), player, participant2, GameResultType.DRAW),
            new GameModel(11L, Date.from(Instant.now()), player, participant4, GameResultType.WHITE_WINS),
            new GameModel(12L, Date.from(Instant.now()), player, participant4, GameResultType.WHITE_WINS),
            new GameModel(13L, Date.from(Instant.now()), player, participant2, GameResultType.DRAW),
            new GameModel(14L, Date.from(Instant.now()), player, participant6, GameResultType.BLACK_WINS),
            new GameModel(15L, Date.from(Instant.now()), participant5, player, GameResultType.BLACK_WINS),
            new GameModel(16L, Date.from(Instant.now()), participant1, player, GameResultType.BLACK_WINS),
            new GameModel(17L, Date.from(Instant.now()), player, participant4, GameResultType.DRAW),
            new GameModel(18L, Date.from(Instant.now()), participant5, player, GameResultType.WHITE_WINS),
            new GameModel(19L, Date.from(Instant.now()), participant1, player, GameResultType.WHITE_WINS),
            new GameModel(20L, Date.from(Instant.now()), participant3, player, GameResultType.WHITE_WINS),
            new GameModel(21L, Date.from(Instant.now()), player, participant6, GameResultType.BLACK_WINS),
            new GameModel(22L, Date.from(Instant.now()), player, participant2, GameResultType.DRAW),
            new GameModel(23L, Date.from(Instant.now()), player, participant4, GameResultType.WHITE_WINS),
            new GameModel(24L, Date.from(Instant.now()), player, participant6, GameResultType.WHITE_WINS)
        );

        MainWindowCenterPane centerPane = new MainWindowCenterPane(primaryStage, player, gameModelList, (width * 0.4d) - 30, (height));

        List<PlayerModel> onlineList = List.of(
                participant1,
                participant2,
                participant3,
                participant4,
                participant5,
                participant6
                )
                .stream()
                .filter(PlayerModel::getOnline).collect(Collectors.toList());

        MainWindowRightPane rightPane = new MainWindowRightPane(primaryStage, player, onlineList, (width * 0.4d) - 30, (height));


        bp.setCenter(centerPane);
        bp.setLeft(leftPane);
        bp.setRight(rightPane);

        bp.setTop(mb);
        bp.setBottom(new Pane());


        Scene scene = new Scene(bp, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT, Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(Config.WINDOW_WIDTH);
        primaryStage.setMinHeight(Config.WINDOW_HEIGHT);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
