package part2.move;

import part2.BlueGreenPair;
import part2.Board;
import part2.Color;

public class CommandoParaDrop extends BaseMove {

    public CommandoParaDrop(Color playerToMove, int row, int col) {
        super(playerToMove, row, col);
    }

    @Override
    public BlueGreenPair execute(String[][] playerLocations, Board pointValues, BlueGreenPair scores) {

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
