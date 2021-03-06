package javafxapp.mainwindow;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafxapp.api.dao.UsrDataApiDao;
import javafxapp.api.dto.AuthResponseDto;
import javafxapp.api.dto.UsrDto;
import javafxapp.api.model.GameModel;
import javafxapp.api.model.GameResultType;
import javafxapp.api.model.PlayerModel;
import javafxapp.authwindows.loginwindow.LoginWindow;
import javafxapp.config.Config;
import javafxapp.mainwindow.config.MainWindowConfig;
import javafxapp.mainwindow.panes.MainWindowCenterPane;
import javafxapp.mainwindow.panes.MainWindowLeftPane;
import javafxapp.mainwindow.panes.MainWindowRightPane;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainWindow extends Parent {
    private final UsrDataApiDao usrDataApiDao;
    private final UsrDto currentUsr;

    private List<UsrDto> onlineList1;


    PlayerModel player = new PlayerModel(1L, 1L, "Developer", 9999L, 0.99d, true);


    PlayerModel participant1 = new PlayerModel(1L, 1L, "Jora", 129L, 0.64d, false);
    PlayerModel participant2 = new PlayerModel(2L, 2L, "Petya", 13L, 0.29d, true);
    PlayerModel participant3 = new PlayerModel(3L, 3L, "Zhenia", 1181L, 0.48d, false);
    PlayerModel participant4 = new PlayerModel(4L, 4L, "Olya", 22L, 0.78d, true);
    PlayerModel participant5 = new PlayerModel(5L, 5L, "Sasha", 65L, 0.45d, true);
    PlayerModel participant6 = new PlayerModel(6L, 6L, "Ira", 2210L, 0.54d, false);


    List<GameModel> gameModelList = List.of();

    List<PlayerModel> onlineList = List.of();


    //###################################################################################3

    private void loadSettingsWindow(Stage primaryStage) {

    }

    private void showConfirmation(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to log out?");
        alert.setResizable(false);

        Optional<ButtonType> option = alert.showAndWait();
        option.ifPresent(op -> {
            if (option.get() == ButtonType.OK) {
                loadLoginWindow(primaryStage);
                alert.close();
            } else if (option.get() == ButtonType.CANCEL) {
                alert.close();
            }
        });
    }

    private void loadLoginWindow(Stage primaryStage) {
        Scene loginWindowScene = new Scene(new LoginWindow(primaryStage));

        primaryStage.setTitle("Login");
        primaryStage.setScene(loginWindowScene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    MenuBar createMenuBar(Stage primaryStage) {
        MenuBar mb = new MenuBar();
        mb.setMinHeight(Config.MENU_HEIGHT);

        Menu mProfileMenu = new Menu("Profile");
        Menu mAboutMenu = new Menu("About");
//        mAboutMenu.setOnAction(e -> {
//        });

        MenuItem profileMenuSettings = new MenuItem("Settings");
        profileMenuSettings.setOnAction(e -> {
//            Scene settingsScene = new Scene(new SettingsWindow(400, 400));
//
//            Stage settingsWindow = new Stage();
//            settingsWindow.setTitle("Settings");
//            settingsWindow.setScene(settingsScene);
//            settingsWindow.setResizable(false);
//
//            settingsWindow.initModality(Modality.WINDOW_MODAL);
//            settingsWindow.initOwner(primaryStage);
//
//            settingsWindow.show();
        });
        profileMenuSettings.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));

        MenuItem profileMenuLogOut = new MenuItem("Log out");
        profileMenuLogOut.setOnAction(e -> showConfirmation(primaryStage));

        mProfileMenu.getItems().addAll(profileMenuSettings,
                new SeparatorMenuItem(),
                profileMenuLogOut);

        mb.getMenus().addAll(mProfileMenu, mAboutMenu);
        return mb;
    }

    private BorderPane createMainWindowPanes(Stage primaryStage, double width, double height) {
        MenuBar mb = createMenuBar(primaryStage);

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(0));
        bp.setMinWidth(width);
        bp.setMaxWidth(width);
        bp.setMinHeight(height);
        bp.setMaxHeight(height);

        double paneHeight = height - Config.MENU_HEIGHT;
        double leftPaneWidth = width * MainWindowConfig.LEFT_PANE_WIDTH - 30;
        double centerPaneWidth = width * MainWindowConfig.CENTER_PANE_WIDTH - 30;
        double rightPaneWidth = width * MainWindowConfig.RIGHT_PANE_WIDTH - 30;


        MainWindowLeftPane leftPane = new MainWindowLeftPane(currentUsr, leftPaneWidth);
        MainWindowCenterPane centerPane = new MainWindowCenterPane(primaryStage, player, gameModelList, centerPaneWidth, paneHeight);
        MainWindowRightPane rightPane = new MainWindowRightPane(primaryStage, currentUsr, onlineList, rightPaneWidth, paneHeight);

        bp.setCenter(centerPane);
        bp.setLeft(leftPane);
        bp.setRight(rightPane);

        bp.setTop(mb);
        bp.setBottom(new Pane());

        return bp;
    }

    public MainWindow(Stage primaryStage, AuthResponseDto authResponseDto) {
        super();
        usrDataApiDao = new UsrDataApiDao();

        // TODO: handle token
        currentUsr = usrDataApiDao.getUsrById(authResponseDto.getId(), authResponseDto.getToken()).getUser();

        this.getChildren().add(createMainWindowPanes(primaryStage, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
    }
}
