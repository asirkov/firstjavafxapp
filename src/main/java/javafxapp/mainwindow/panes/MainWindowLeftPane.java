package javafxapp.mainwindow.panes;

import javafx.beans.value.ObservableStringValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import javafxapp.api.model.PlayerModel;
import javafxapp.mainwindow.config.MainWindowConfig;
import javafxapp.mainwindow.separators.VSeparator;
import javafxapp.mainwindow.views.PlayerMaxInfoView;

public class MainWindowLeftPane extends Pane {
    private final String playerName;
    private final Long playerGames;
    private final Double playerRate;


    private VBox createMainWindowLeftPane(double width) {
        VBox vb = new VBox();
        vb.setPadding(new Insets(20, 0, 20, 0));
        vb.setSpacing(10);

//        Label lblPlayerName = new Label(playerName);
//        lblPlayerName.setPadding(new Insets(0, 0, 20, 20));
//        lblPlayerName.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 22));
//
//        Tooltip tooltipPlayerName = new Tooltip(playerName);
//        tooltipPlayerName.setTextAlignment(TextAlignment.CENTER);
//        tooltipPlayerName.setShowDelay(Duration.seconds(0.5));
//        tooltipPlayerName.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 14));
//        lblPlayerName.setTooltip(tooltipPlayerName);
//
//
//        Label lblPlayerGames = new Label("Games:\t" + String.valueOf(playerGames) + "");
//        lblPlayerGames.setPadding(new Insets(0, 0, 0, 20));
//        lblPlayerGames.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
//        lblPlayerGames.setTextFill(Paint.valueOf(MainWindowConfig.SECOND_FONT_COLOR));
//
//        Label lblPlayerRate = new Label("Wins:\t" + (playerRate * 100) + "%");
//        lblPlayerRate.setPadding(new Insets(0, 0, 0, 20));
//        lblPlayerRate.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
//        lblPlayerRate.setTextFill(Paint.valueOf(MainWindowConfig.SECOND_FONT_COLOR));

        vb.getChildren().addAll(
                new PlayerMaxInfoView(playerName, playerGames, playerRate, width),
                new VSeparator(width));

        vb.setMinWidth(width);
        vb.setMaxWidth(width);
        return vb;
    }

    public MainWindowLeftPane(PlayerModel playerModel, double width) {
        playerName = playerModel.getPlayerName();
        playerGames = playerModel.getGames();
        playerRate = playerModel.getRate();

        VBox vb = createMainWindowLeftPane(width);

        this.getChildren().add(vb);
    }


}
