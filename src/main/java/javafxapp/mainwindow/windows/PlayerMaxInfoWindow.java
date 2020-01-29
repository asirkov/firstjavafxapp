package javafxapp.mainwindow.windows;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafxapp.api.model.PlayerModel;

public class PlayerMaxInfoWindow extends Parent {

    private GridPane createContent(PlayerModel playerModel, double width, double height) {
        GridPane gp = new GridPane();


        gp.setMinSize(width, height);

        return gp;
    }

    public PlayerMaxInfoWindow(PlayerModel playerModel, double i, double i1) {

        this.getChildren().add(createContent(playerModel, i, i1));
    }
}
