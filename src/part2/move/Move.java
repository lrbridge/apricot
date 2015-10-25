package part2.move;

import part2.BlueGreenPair;
import part2.Board;
import part2.Color;

public interface Move {

    BlueGreenPair execute(String[][] playerLocations, Board pointValues, BlueGreenPair scores);

    Color getPlayerToMove();

    int getRow();
    int getCol();
}
