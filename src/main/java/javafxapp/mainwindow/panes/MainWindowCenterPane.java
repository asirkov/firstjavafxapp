package javafxapp.mainwindow.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafxapp.api.model.GameModel;
import javafxapp.mainwindow.separators.HSeparator;
import javafxapp.mainwindow.separators.VSeparator;
import javafxapp.mainwindow.views.GameMinInfoView;
import javafxapp.mainwindow.labels.*;

import java.util.List;

public class MainWindowCenterPane extends HBox {

    private ScrollPane createMainWindowGameList(List<GameModel> gamesList, double width, double height) {
        VBox vb = new VBox();
        vb.setPadding(new Insets(0));
        vb.setSpacing(5);

        ScrollPane sp = new ScrollPane();
        sp.setPadding(new Insets(0));
        sp.setContent(vb);

        gamesList.forEach(gameModel -> vb.getChildren().add(new GameMinInfoView(gameModel, width) ));

        sp.setMinSize(width, height);
        sp.setMaxSize(width, height);

        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        return sp;
    }

    public MainWindowCenterPane(List<GameModel> gamesList, double width, double height) {
        super();
        this.setPadding(new Insets(0));
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(0);

        Label lblGamesHeader = new BigLabel("Games: ", width, height * 0.07d);
        ScrollPane sp = createMainWindowGameList(gamesList, width, height * 0.93d  - VSeparator.SEPARATOR_HEIGHT);

        VBox vb = new VBox();
        vb.setPadding(new Insets(0));
        vb.setAlignment(Pos.TOP_CENTER);

        vb.getChildren().addAll(
                lblGamesHeader,
                new VSeparator(width),
                sp
        );

        this.getChildren().addAll(new HSeparator(height), vb, new HSeparator(height));

        this.setMinWidth(width);
        this.setMaxWidth(width);
    }
}
