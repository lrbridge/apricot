package part2.move;

import part2.Color;

public abstract class BaseMove implements Move {

    public Color playerToMove;
    public int row;
    public int col;

    public BaseMove(Color playerToMove, int row, int col) {
        this.playerToMove = playerToMove;
        this.row = row;
        this.col = col;
    }

    @Override
    public Color getPlayerToMove() {
        return playerToMove;
    }

    @Override
    public String toString() {
        return "Move{" +
                "type=" + this.getClass().getCanonicalName() +
                ", color=" + playerToMove.toString() +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}
