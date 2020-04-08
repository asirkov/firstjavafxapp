package javafxapp.mainwindow.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafxapp.api.dao.UsrDataApiDao;
import javafxapp.api.dto.UsrDto;
import javafxapp.api.model.PlayerModel;
import javafxapp.mainwindow.config.MainWindowConfig;
import javafxapp.util.separators.VSeparator;
import javafxapp.mainwindow.views.PlayerMinInfoView;

import java.util.List;

public class MainWindowRightPane extends VBox {
    private final UsrDataApiDao usrDataApiDao = new UsrDataApiDao();
    private UsrDto currentUsr;


    private List<UsrDto> onlineList = List.of();

    private List<PlayerModel> friendsList = List.of();

    private ScrollPane createPlayersList(Stage primaryStage, List<UsrDto> usrsList, double width, double height) {
        VBox vb = new VBox();
        vb.setPadding(new Insets(0));
        vb.setSpacing(5);

        ScrollPane sp = new ScrollPane();
        sp.setPadding(new Insets(0));
        sp.setContent(vb);

        usrsList.forEach(usr -> {
            byte[] usrAvatarData = usrDataApiDao.getUsrAvatarById(usr.getId(), "");
            PlayerMinInfoView view = new PlayerMinInfoView(primaryStage, usr, usrAvatarData, width);
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

    public MainWindowRightPane(Stage primaryStage, UsrDto currentUsr, List<PlayerModel> playersList, double width, double height) {
        super();
        this.currentUsr = currentUsr;
        // TODO: add token
        this.onlineList = usrDataApiDao.getAllOnlineUsrs("").getUsers();

        this.setPadding(new Insets(0));
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(0);

        TabPane tabPane = new TabPane();
        VBox.setVgrow(tabPane, Priority.ALWAYS);

        Tab tabOnline = new Tab("Online: " + onlineList.size());
        tabOnline.setStyle("-fx-font: bold 16px \"helvetica neue\";");
        tabOnline.setClosable(false);

        Tab tabFriends = new Tab("Friends: " + friendsList.size());
        tabFriends.setStyle("-fx-font: bold 16px \"helvetica neue\";");
        tabFriends.setClosable(false);


        ScrollPane spOnline = createPlayersList(primaryStage, onlineList, width,
                (height - MainWindowConfig.HEADER_HEIGHT * 2) - VSeparator.SEPARATOR_HEIGHT - tabPane.getTabMinHeight());
        spOnline.setPadding(new Insets(VSeparator.SEPARATOR_HEIGHT,0,0,0));
        VBox.setVgrow(spOnline, Priority.ALWAYS);
        tabOnline.setContent(spOnline);

        ScrollPane spFriends = createPlayersList(primaryStage, List.of(), width,
                (height - MainWindowConfig.HEADER_HEIGHT * 2) - VSeparator.SEPARATOR_HEIGHT - tabPane.getTabMinHeight());
        spFriends.setPadding(new Insets(VSeparator.SEPARATOR_HEIGHT,0,0,0));
        VBox.setVgrow(spFriends, Priority.ALWAYS);
        tabFriends.setContent(spFriends);

        tabPane.setTabDragPolicy(TabPane.TabDragPolicy.FIXED);

        tabPane.setTabMinHeight(MainWindowConfig.HEADER_HEIGHT - 3);
        tabPane.setTabMinWidth(width / 2);
        tabPane.getTabs().addAll(tabOnline, tabFriends);
        tabPane.setPadding(new Insets(0));

        this.getChildren().add(tabPane);
    }

    public void update() {}
}
