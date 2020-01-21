package javafxapp.mainwindow;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafxapp.api.model.Player;
import javafxapp.mainwindow.gamerecordview.MainWindowLeftPane;

public class MainWindow extends Application {

    HBox createMainWindowPanes() {
        HBox hb = new HBox();
        hb.setPadding(new Insets(20));
        hb.setSpacing(20);


        return hb;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Player player = new Player(1L, 1L, "Player 1", 129L, 0.64d);
        MainWindowLeftPane leftPane = new MainWindowLeftPane(player);

        HBox hb = createMainWindowPanes();
        hb.getChildren().addAll(leftPane);

        Scene scene = new Scene(hb, 800, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
