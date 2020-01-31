package javafxapp.gamewindow.tile;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafxapp.gamewindow.GameWindow;
import javafxapp.gamewindow.checker.Checker;
import javafxapp.gamewindow.config.TileConfig;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static javafxapp.gamewindow.GameWindow.TILE_SIZE;

@Getter
@ToString(exclude={"tileParent", "attacker", "isBordered", "isAttacked"})
public class Tile extends Rectangle {
    private GameWindow tileParent;
    private Checker checker;
    private Checker attacker;
    private boolean isBordered;
    private boolean isAttacked;

    public boolean hasChecker() {
        return checker == null;
    }

    public void setChecker(Checker checker) {
        this.checker = checker;
        if (this.checker != null)
            checker.setCheckerParent(this);
    }

    public Tile(GameWindow tileParent, boolean light, int x, int y) {
        this.tileParent = tileParent;
        this.isBordered = false;
        this.isAttacked = false;
        this.attacker = null;

        this.setStrokeType(StrokeType.INSIDE);
        this.setWidth(TILE_SIZE);
        this.setHeight(TILE_SIZE);

        this.relocate(x * TILE_SIZE, y * TILE_SIZE);
        this.setFill(light ?
                Color.valueOf(TileConfig.WHITE_TILE_COLOR) :
                Color.valueOf(TileConfig.BLACK_TILE_COLOR));
    }

    public void setTileBorder(BorderType border, Checker attacker) {
        this.attacker = attacker;
        switch (border) {
            case RED:
                this.setStroke(Paint.valueOf("#FF0000"));
                this.setStrokeWidth(2);
                this.isBordered = true;
                this.isAttacked = true;
                break;
            case BLUE:
                this.setStroke(Paint.valueOf("#0000FF"));
                this.setStrokeWidth(2);
                this.isBordered = true;
                this.isAttacked = false;
                break;
            default:
                this.setStrokeWidth(0);
                this.isBordered = false;
                this.isAttacked = false;
                break;
        }
    }

    public void showMoves(double mouseX, double mouseY) {
        System.err.println(tileParent.toString());
        tileParent.showMoves(mouseX, mouseY);
    }


}
