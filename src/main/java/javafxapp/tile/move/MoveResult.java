package javafxapp.tile.move;

import javafxapp.tile.checker.Checker;
import lombok.Getter;

@Getter
public class MoveResult {
    private MoveType type;
    private Checker checker;

    public MoveResult(MoveType type) {
        this(type, null);
    }

    public MoveResult(MoveType type, Checker checker) {
        this.type = type;
        this.checker = checker;
    }
}
