package javafxapp;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafxapp.authwindows.loginwindow.LoginWindow;
import javafxapp.config.Config;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");

        Scene scene = new Scene(new LoginWindow(primaryStage), Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
