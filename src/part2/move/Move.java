package part2.move;

import part2.BlueGreenPair;
import part2.Board;
import part2.Color;

public interface Move {

    BlueGreenPair execute(String[][] playerLocations, Color color, Board pointValues, int blueScore, int greenScore);

}
