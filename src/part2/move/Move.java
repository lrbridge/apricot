package part2.move;

import part2.BlueGreenPair;
import part2.Board;

public interface Move {

    BlueGreenPair execute(String[][] playerLocations, String agentLetter, Board pointValues, int blueScore, int greenScore);

}
