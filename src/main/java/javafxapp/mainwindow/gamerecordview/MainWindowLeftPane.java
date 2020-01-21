package javafxapp.mainwindow.gamerecordview;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafxapp.api.model.Player;
import javafxapp.mainwindow.MainWindow;
import javafxapp.mainwindow.config.MainWindowConfig;

public class MainWindowLeftPane extends Pane {
    private final String playerName;
    private final Long playerGames;
    private final Double playerRate;


    private VBox createMainWindowLeftPane() {
        VBox vb = new VBox();
        vb.setPadding(new Insets(40));
        vb.setSpacing(10);

        Label lblPlayerName = new Label(playerName);

        lblPlayerName.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 24));

        Label lblPlayerGames = new Label(String.valueOf(playerGames) + " games.");
        lblPlayerGames.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        lblPlayerGames.setTextFill(Paint.valueOf(MainWindowConfig.SECOND_FONT_COLOR));

        Label lblPlayerRate = new Label(String.valueOf(playerRate * 100) + "% wins.");
        lblPlayerRate.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        lblPlayerRate.setTextFill(Paint.valueOf(MainWindowConfig.SECOND_FONT_COLOR));

        vb.getChildren().addAll(lblPlayerName, lblPlayerGames, lblPlayerRate);

        return vb;
    }

    public MainWindowLeftPane(Player player) {
        playerName = player.getPlayerName();
        playerGames = player.getGames();
        playerRate = player.getRate();

        VBox vb = createMainWindowLeftPane();
        this.getChildren().add(vb);
    }


}
