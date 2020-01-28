package javafxapp.settingswindow.panes;

import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingsPane extends Pane {
    private String name;

    public SettingsPane(String name) {
        super();
        this.name = name;
    }
}
