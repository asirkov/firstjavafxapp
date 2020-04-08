package javafxapp.mainwindow.windows;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafxapp.api.dto.UsrDto;
import javafxapp.api.model.PlayerModel;

public class PlayerMaxInfoWindow extends Parent {

    private GridPane createContent(UsrDto usrDto, double width, double height) {
        GridPane gp = new GridPane();

        gp.setMinSize(width, height);

        return gp;
    }

    public PlayerMaxInfoWindow(UsrDto usrDto, double i, double i1) {

        this.getChildren().add(createContent(usrDto, i, i1));
    }
}
