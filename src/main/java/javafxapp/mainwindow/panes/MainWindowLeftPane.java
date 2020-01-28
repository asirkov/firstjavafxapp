package javafxapp.mainwindow.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafxapp.api.model.PlayerModel;
import javafxapp.mainwindow.separators.VSeparator;
import javafxapp.mainwindow.views.PlayerMaxInfoView;
import javafxapp.labels.*;

public class MainWindowLeftPane extends VBox {

//    private VBox createContent() {
//    }

    public MainWindowLeftPane(PlayerModel player, double width, double height) {
        super();
        this.setPadding(new Insets(0, 0, 0, 10));
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(0);

        Label lblProfileHeader = new BigLabel("Profile: ", width);
        this.getChildren().addAll(
                lblProfileHeader,
                new VSeparator(width),
                new PlayerMaxInfoView(player, width),
                new VSeparator(width));

        this.getChildren().forEach(c -> VBox.setVgrow(c, Priority.ALWAYS));
    }


}
