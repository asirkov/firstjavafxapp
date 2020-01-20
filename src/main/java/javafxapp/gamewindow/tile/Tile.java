package javafxapp.gamewindow.tile;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafxapp.gamewindow.config.TileConfig;
import javafxapp.gamewindow.tile.checker.Checker;
import lombok.Getter;
import lombok.Setter;

import static javafxapp.gamewindow.GameWindow.TILE_SIZE;


@Getter
@Setter
public class Tile extends Rectangle {
    private final TileConfig tileConfig = new TileConfig();
    private Checker checker;

    public boolean hasChecker() {
        return checker == null;
    }

    public Tile(boolean light, int x, int y) {
        this.setWidth(TILE_SIZE);
        this.setHeight(TILE_SIZE);

        this.relocate(x * TILE_SIZE, y * TILE_SIZE);
        this.setFill(light ?
                Color.valueOf(tileConfig.getWHITE_TILE_COLOR()) :
                Color.valueOf(tileConfig.getBLACK_TILE_COLOR()));
    }
}
