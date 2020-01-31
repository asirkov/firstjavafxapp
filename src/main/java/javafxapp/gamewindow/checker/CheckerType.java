package javafxapp.gamewindow.checker;

public enum CheckerType {
    BLACK(1, 1), WHITE(-1, 1),
    BLACK_QUEEN(1, 8), WHITE_QUEEN(-1, 8);

    public final int moveDirection;
    public final int moveDistance;

    CheckerType(int moveDirection, int moveDistance) {
        this.moveDirection = moveDirection;
        this.moveDistance = moveDistance;
    }
}
