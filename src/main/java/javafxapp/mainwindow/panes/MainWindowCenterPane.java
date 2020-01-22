package javafxapp.mainwindow.panes;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafxapp.api.model.GameModel;
import javafxapp.mainwindow.separators.VSeparator;

import java.util.List;

public class MainWindowCenterPane extends Pane {

    private VBox createMainWindowCenterPane(double width) {
        VBox vb = new VBox();
        vb.setPadding(new Insets(20, 0, 20, 0));
        vb.setSpacing(10);

        Label lblHeader = new Label("History");
        lblHeader.setPrefWidth(width);
        lblHeader.setAlignment(Pos.CENTER);
        lblHeader.setPadding(new Insets(0, 0, 0, 20));
        lblHeader.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 22));

        vb.getChildren().addAll(lblHeader,
                new VSeparator(width));

        vb.setMinWidth(width);
        vb.setMaxWidth(width);
        return vb;
    }

    public MainWindowCenterPane(List<GameModel> gamesList, double width) {
        VBox vb = createMainWindowCenterPane(width);

        this.getChildren().add(vb);
    }
}
