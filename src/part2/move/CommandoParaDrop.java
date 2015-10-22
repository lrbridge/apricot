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
        return createPieceInSquare(playerLocations, pointValues, scores);
    }

}
