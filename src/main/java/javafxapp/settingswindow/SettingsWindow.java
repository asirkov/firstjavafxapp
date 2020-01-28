package javafxapp.settingswindow;

import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafxapp.settingswindow.panes.SettingsPane;

public class SettingsWindow extends Parent {
    private double width;
    private double height;

    private BorderPane createContent(double width, double height) {
        BorderPane bp = new BorderPane();

        SettingsPane settingsWindowRootPane = new SettingsPane("");

        SettingsPane accountSettings = createAccountSettings(settingsWindowRootPane);
        SettingsPane viewSettings = createViewSettings(settingsWindowRootPane);

        SettingsPane generalSettings = createGeneralSettings(settingsWindowRootPane, accountSettings, viewSettings);

        TreeItem<Text> root = new TreeItem<>(new Text(generalSettings.getName()));
//        root.getGraphic().setOnMouseClicked(e -> {
//        });

        TreeItem<Text> child1 = new TreeItem<>(new Text(accountSettings.getName()));
//        child1.getGraphic().setOnMouseClicked(e -> {
//        });

        TreeItem<Text> child2 = new TreeItem<>(new Text(viewSettings.getName()));
//        child2.getGraphic().setOnMouseClicked(e -> {
//        });

        root.getChildren().addAll(child1, child2);

        TreeView<Text> treeView = new TreeView<>(root);
        treeView.setMinWidth(width / 3);
        treeView.setMaxWidth(width / 3);

        settingsWindowRootPane = generalSettings;
        settingsWindowRootPane.setMinWidth(width / 3 * 2);

        bp.setLeft(treeView);
        bp.setCenter(settingsWindowRootPane);

        bp.setMinSize(width, height);
        return bp;
    }

    private SettingsPane createGeneralSettings(SettingsPane parent, SettingsPane... childs) {
        SettingsPane p = new SettingsPane("General");
        VBox vb = new VBox();
        vb.setSpacing(10d);

        for (SettingsPane child: childs) {
            Hyperlink h = new Hyperlink(child.getName());

//            h.setOnMouseClicked(e -> {
////                parent.getChildren().
////                parent.getChildren().set(0, child);
//            });

            vb.getChildren().add(h);
        }

        p.getChildren().add(vb);
        return p;
    }

    private SettingsPane createAccountSettings(SettingsPane parent) {
        SettingsPane p = new SettingsPane("Account");

        return p;
    }

    private SettingsPane createViewSettings(SettingsPane parent) {
        SettingsPane p = new SettingsPane("View");

        return p;
    }

    public SettingsWindow(double v, double v1) {
        width = v;
        height = v1;

        this.getChildren().add(createContent(v, v1));
    }
}
