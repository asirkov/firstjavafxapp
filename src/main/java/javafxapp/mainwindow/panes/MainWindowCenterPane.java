package javafxapp.mainwindow.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafxapp.api.model.GameModel;
import javafxapp.api.model.PlayerModel;
import javafxapp.mainwindow.config.MainWindowConfig;
import javafxapp.util.separators.HSeparator;
import javafxapp.util.separators.VSeparator;
import javafxapp.mainwindow.views.GameMinInfoView;
import javafxapp.util.labels.*;

import java.util.List;

public class MainWindowCenterPane extends HBox {

    private ScrollPane createMainWindowGameList(Stage primaryStage, PlayerModel player, List<GameModel> gamesList, double width, double height) {
        VBox vb = new VBox();
        vb.setPadding(new Insets(0));
        vb.setSpacing(5);

        ScrollPane sp = new ScrollPane();
        sp.setPadding(new Insets(0));
        sp.setContent(vb);

        gamesList.forEach(gameModel -> {
            GameMinInfoView view = new GameMinInfoView(primaryStage, player, gameModel, width);
            view.setBorder(new Border(new BorderStroke(Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY,
                    BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
                    CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));

            vb.getChildren().add(view);
            VBox.setVgrow(view, Priority.ALWAYS);
        });

//        sp.setMinSize(width, height);
//        sp.setMaxSize(width, height);


        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setFitToWidth(true);

        return sp;
    }

    public MainWindowCenterPane(Stage primaryStage, PlayerModel player, List<GameModel> gamesList, double width, double height) {
        super();
        this.setPadding(new Insets(0, 10, 0, 10));
        this.setAlignment(Pos.TOP_CENTER);
        this.setFillHeight(true);
        this.setSpacing(0);

        VBox vb = new VBox();
        vb.setPadding(new Insets(0));
        vb.setAlignment(Pos.TOP_CENTER);

        VSeparator separator = new VSeparator(width);

        vb.getChildren().addAll(
                new HeaderLabel("Games: ", width),
                new VSeparator(width),
                createMainWindowGameList(primaryStage, player, gamesList, width, height - MainWindowConfig.HEADER_HEIGHT - VSeparator.SEPARATOR_HEIGHT));

        vb.getChildren().forEach(c -> VBox.setVgrow(c, Priority.ALWAYS));

        this.getChildren().addAll(
                new HSeparator(height),
                vb,
                new HSeparator(height));

        this.getChildren().forEach(c -> HBox.setHgrow(c, Priority.ALWAYS));
    }
}
