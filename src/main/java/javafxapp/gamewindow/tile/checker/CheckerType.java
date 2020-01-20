package javafxapp.gamewindow.tile.checker;

public enum CheckerType {
    BLACK(1), WHITE(-1);

    final boolean queen = false;
    public int moveDirection;

    CheckerType(int moveDirection) {
        this.moveDirection = moveDirection;
    }


}
