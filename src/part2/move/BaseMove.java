package part2.move;

import part2.BlueGreenPair;
import part2.Board;
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
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
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

    protected BlueGreenPair createPieceInSquare(String[][] playerLocations, Board pointValues, BlueGreenPair scores) {
        if (playerToMove.equals(Color.BLUE)) {
            scores.blue += pointValues.get(row, col);
            playerLocations[row][col] = "B";
        } else {
            scores.green += pointValues.get(row, col);
            playerLocations[row][col] = "G";
        }
        return new BlueGreenPair(scores.blue, scores.green);
    }
}
