package javafxapp.api.model;

public enum GameResultType {
    DRAW("DRAW"), WHITE_WINS("WHITE_WINS"), BLACK_WINS("BLACK_WINS");

    private String gameResult;

    GameResultType(String gameResult) {
        this.gameResult = gameResult;
    }
}