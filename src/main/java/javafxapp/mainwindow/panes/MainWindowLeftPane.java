package javafxapp.mainwindow.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafxapp.api.model.PlayerModel;
import javafxapp.mainwindow.config.MainWindowConfig;
import javafxapp.util.ActionButton;
import javafxapp.util.separators.HSeparator;
import javafxapp.util.separators.VSeparator;
import javafxapp.mainwindow.views.PlayerMaxInfoView;
import javafxapp.util.labels.*;

public class MainWindowLeftPane extends VBox {

//    private VBox createContent() {
//    }

    public MainWindowLeftPane(PlayerModel player, double width) {
        super();
        this.setPadding(new Insets(0, 10, 20, 10));
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(0);

        ActionButton btnNewGame = new ActionButton("New Game", width);
        btnNewGame.setOnAction(e -> {
        });

        double playerMaxInfoWidth = width - 2 * HSeparator.SEPARATOR_WIDTH;

        this.getChildren().addAll(
                new HeaderLabel("Profile: ", width),
                new VSeparator(width - 2 * HSeparator.SEPARATOR_WIDTH),
                new PlayerMaxInfoView(player,  playerMaxInfoWidth),
                new VSeparator(width - 2 * HSeparator.SEPARATOR_WIDTH),
                btnNewGame);

        this.getChildren().forEach(c -> VBox.setVgrow(c, Priority.ALWAYS));
    }


}
