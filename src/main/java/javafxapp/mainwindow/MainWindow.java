package javafxapp.mainwindow;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafxapp.api.model.GameModel;
import javafxapp.api.model.PlayerModel;
import javafxapp.mainwindow.config.MainWindowConfig;
import javafxapp.mainwindow.panes.MainWindowCenterPane;
import javafxapp.mainwindow.panes.MainWindowLeftPane;
import javafxapp.mainwindow.separators.HSeparator;

import java.util.List;

public class MainWindow extends Application {

    HBox createMainWindowPanes() {
        HBox hb = new HBox();
        hb.setPadding(new Insets(0));
        hb.setSpacing(0);


        return hb;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        double width = MainWindowConfig.WINDOW_WIDTH;

        PlayerModel playerModel = new PlayerModel(1L, 1L, "Developer", 129L, 0.64d);
        MainWindowLeftPane leftPane = new MainWindowLeftPane(playerModel, width * 0.2d);
        leftPane.setPadding(new Insets(10, 0, 10, 0));

        List<GameModel> gameModelList = List.of();
        MainWindowCenterPane centerPane = new MainWindowCenterPane(gameModelList, width * 0.4d);
        centerPane.setPadding(new Insets(10, 0, 10, 0));

        HBox hb = createMainWindowPanes();
        hb.getChildren().add(leftPane);
        hb.getChildren().add(new HSeparator(primaryStage.getHeight()));
        hb.getChildren().add(centerPane);
        hb.getChildren().add(new HSeparator(primaryStage.getHeight()));


        Scene scene = new Scene(hb, MainWindowConfig.WINDOW_WIDTH, MainWindowConfig.WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
