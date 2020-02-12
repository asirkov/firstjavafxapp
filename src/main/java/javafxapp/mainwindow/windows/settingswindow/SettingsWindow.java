package javafxapp.mainwindow.windows.settingswindow;

import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafxapp.mainwindow.windows.panes.SettingsPane;

public class SettingsWindow extends Parent {
    private double width;
    private double height;

    private TabPane createContent(double width, double height) {
        TabPane tp = new TabPane();



        return tp;
    }


    public SettingsWindow(double v, double v1) {
        width = v;
        height = v1;

        this.getChildren().add(createContent(v, v1));
    }
}
