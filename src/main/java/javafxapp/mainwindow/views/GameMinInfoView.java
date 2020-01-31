package javafxapp.mainwindow.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafxapp.api.model.GameModel;
import javafxapp.api.model.GameResultType;
import javafxapp.api.model.PlayerModel;
import javafxapp.mainwindow.config.MainWindowConfig;
import javafxapp.util.labels.*;
import javafxapp.util.separators.HSeparator;
import javafxapp.mainwindow.views.icons.PlayerOnlineIcon;
import lombok.ToString;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.text.SimpleDateFormat;

@ToString
public class GameMinInfoView extends HBox {
    public GameMinInfoView(Stage primaryStage, PlayerModel player, GameModel gameModel, double width) {
        this.setSpacing(0);
        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER);

        PlayerMinInfoView whitePlayerMinView = new PlayerMinInfoView(primaryStage, gameModel.getParticipant1(), width * 0.7d - HSeparator.SEPARATOR_WIDTH - PlayerOnlineIcon.ICON_RADIUS * 2 - 20);
        whitePlayerMinView.setOnMouseClicked(e -> {});

        PlayerMinInfoView blackPlayerMinView = new PlayerMinInfoView(primaryStage, gameModel.getParticipant2(), width * 0.7d - HSeparator.SEPARATOR_WIDTH - PlayerOnlineIcon.ICON_RADIUS * 2 - 20);
        blackPlayerMinView.setOnMouseClicked(e -> {});

        VBox leftVb = new VBox(whitePlayerMinView, blackPlayerMinView);
        leftVb.setMinWidth(width * 0.7d - HSeparator.SEPARATOR_WIDTH - 10);
        leftVb.getChildren().forEach(c -> VBox.setVgrow(c, Priority.ALWAYS));


        Label lblStartTime = new SmallLabel(new SimpleDateFormat("MM/dd HH:mm").format(gameModel.getStartTime()),
                width * 0.3d - 20, Paint.valueOf(MainWindowConfig.FIRST_FONT_COLOR));
        lblStartTime.setAlignment(Pos.CENTER);

        Label lblWinner = new RegularLabel("",width * 0.2d - 20);
        if (gameModel.getGameResultType() == GameResultType.DRAW) {
            lblWinner.setText("Draw");
        } else if ((player.getId().equals(gameModel.getParticipant1().getId()) && gameModel.getGameResultType() == GameResultType.WHITE_WINS)
                ||(player.getId().equals(gameModel.getParticipant2().getId()) && gameModel.getGameResultType() == GameResultType.BLACK_WINS)) {
            lblWinner.setText("Win");
            lblWinner.setTextFill(Paint.valueOf(MainWindowConfig.FIRST_FONT_COLOR));
        } else {
            lblWinner.setText("Lose");
            lblWinner.setTextFill(Paint.valueOf(MainWindowConfig.SECOND_FONT_COLOR));
        }
        lblWinner.setAlignment(Pos.CENTER_RIGHT);

        VBox rightVb = new VBox(lblStartTime, lblWinner);

        rightVb.setMinWidth(width * 0.3d - HSeparator.SEPARATOR_WIDTH);
        rightVb.setAlignment(Pos.TOP_LEFT);
        VBox.setVgrow(lblStartTime, Priority.NEVER);
        VBox.setVgrow(lblWinner, Priority.NEVER);

        this.getChildren().addAll(leftVb, new HSeparator(MainWindowConfig.HEADER_HEIGHT * 2 + 10), rightVb);

        HBox.setHgrow(leftVb, Priority.ALWAYS);
        HBox.setHgrow(rightVb, Priority.NEVER);

        this.setCursor(Cursor.HAND);

        this.setOnMouseClicked(e -> {
            this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#00f"), CornerRadii.EMPTY, Insets.EMPTY)));
        });
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
