package javafxapp.mainwindow.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafxapp.api.model.GameModel;
import javafxapp.mainwindow.config.MainWindowConfig;
import javafxapp.mainwindow.labels.*;

import java.text.SimpleDateFormat;

public class GameMinInfoView extends HBox {
    public GameMinInfoView(GameModel gameModel, double width) {
        this.setSpacing(0);
        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER);

        Label lblBlackParticipant = new RegularLabel("B:  " + gameModel.getParticipant1().getPlayerName(), (width / 3 * 2 ) - this.getSpacing() - 20);
        Label lblWhiteParticipant = new RegularLabel("W: " + gameModel.getParticipant2().getPlayerName(), (width / 3 * 2 ) - this.getSpacing() - 20);

        Label lblStartTime = new Label(new SimpleDateFormat("MM/dd HH:mm").format(gameModel.getStartTime()));
        lblStartTime.setTextFill(Paint.valueOf(MainWindowConfig.FIRST_FONT_COLOR));

        Label lblWinner = new Label(gameModel.getGameResultType().name());
        lblWinner.setTextFill(Paint.valueOf(MainWindowConfig.FIRST_FONT_COLOR));


        VBox leftVb = new VBox(lblBlackParticipant, lblWhiteParticipant);
        leftVb.setMinWidth((width / 3 * 2 ) - this.getSpacing() - 20);
        leftVb.setMaxWidth(leftVb.getMinWidth());

        VBox rightVb = new VBox(lblStartTime, lblWinner);
        rightVb.setMinWidth((width / 3) - this.getSpacing() - 10);
        rightVb.setMaxWidth(rightVb.getMinWidth());

        this.getChildren().addAll(leftVb, rightVb);
        this.setBorder(new Border(new BorderStroke(
                        Color.BLACK,
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));

        this.setCursor(Cursor.HAND);

//        this.setOnMouseClicked(e -> {
//
//        });
    }
}
