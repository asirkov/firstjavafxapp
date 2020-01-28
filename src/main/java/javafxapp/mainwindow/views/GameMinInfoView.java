package javafxapp.mainwindow.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafxapp.api.model.GameModel;
import javafxapp.api.model.GameResultType;
import javafxapp.api.model.PlayerModel;
import javafxapp.mainwindow.config.MainWindowConfig;
import javafxapp.labels.*;
import lombok.ToString;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.text.SimpleDateFormat;

@ToString
public class GameMinInfoView extends HBox {
    public GameMinInfoView(PlayerModel player, GameModel gameModel, double width) {
        this.setSpacing(0);
        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER);

        Label lblStartTime = new SmallLabel(new SimpleDateFormat("MM/dd HH:mm").format(gameModel.getStartTime()),
                (width / 3) - this.getSpacing() - 10, Paint.valueOf(MainWindowConfig.FIRST_FONT_COLOR));
        lblStartTime.setAlignment(Pos.CENTER_RIGHT);

        Label lblWinner = new SmallLabel("",(width / 3) - this.getSpacing() - 10);
        if (gameModel.getGameResultType() == GameResultType.DRAW) {
            lblWinner.setText("Draw");
        } else if ((player.getId().equals(gameModel.getParticipant1().getId()) && gameModel.getGameResultType() == GameResultType.WHITE_WINS)
                 ||(player.getId().equals(gameModel.getParticipant2().getId()) && gameModel.getGameResultType() == GameResultType.BLACK_WINS)) {
            lblWinner.setText("Win");
            lblWinner.setTextFill(Paint.valueOf(MainWindowConfig.FIRST_FONT_COLOR));
        } else {
            lblWinner.setText("Win");
            lblWinner.setTextFill(Paint.valueOf(MainWindowConfig.SECOND_FONT_COLOR));
        }
        lblWinner.setAlignment(Pos.CENTER_RIGHT);


        VBox leftVb = new VBox(
                new RegularLabel("B:  " + gameModel.getParticipant1().getPlayerName(), (width / 3 * 2 ) - this.getSpacing() - 20),
                new RegularLabel("W: " + gameModel.getParticipant2().getPlayerName(), (width / 3 * 2 ) - this.getSpacing() - 20));
        leftVb.setMinWidth((width / 3 * 2 ) - this.getSpacing() - 20);
        leftVb.getChildren().forEach(c -> VBox.setVgrow(c, Priority.ALWAYS));

        VBox rightVb = new VBox(lblStartTime, lblWinner);

        rightVb.setMinWidth((width / 3) - this.getSpacing() - 10);
        VBox.setVgrow(lblStartTime, Priority.NEVER);
        VBox.setVgrow(lblWinner, Priority.NEVER);

        this.getChildren().addAll(leftVb, rightVb);
        this.setBorder(new Border(new BorderStroke(
                        Color.GRAY,
                        BorderStrokeStyle.SOLID,
                        new CornerRadii(5),
                        BorderWidths.DEFAULT)));

        HBox.setHgrow(leftVb, Priority.ALWAYS);
        HBox.setHgrow(rightVb, Priority.NEVER);
//        this.getChildren().forEach(c -> HBox.setHgrow(c, Priority.ALWAYS));

        this.setCursor(Cursor.HAND);

        this.setOnMouseClicked(e -> {

            System.err.println(e.getSource().toString());


//            if (this.getBackground().getFills().stream().anyMatch(p -> p.getFill().equals(Paint.valueOf("#0f0")))) {
//                this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#00f"), CornerRadii.EMPTY, Insets.EMPTY)));
//            } else {
//                this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#0f0"), CornerRadii.EMPTY, Insets.EMPTY)));
//            }

        });
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
