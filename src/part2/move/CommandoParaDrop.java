package part2.move;

import part2.BlueGreenPair;
import part2.Board;
import part2.Color;

public class CommandoParaDrop extends BaseMove {

    public CommandoParaDrop(int row, int col) {
        super(row, col);
    }

    @Override
    public BlueGreenPair execute(String[][] playerLocations, Color color, Board pointValues, int blueScore, int greenScore) {

        if (color.equals(Color.BLUE)) {
            blueScore += pointValues.get(row, col);
            playerLocations[row][col] = "B";
        } else {
            greenScore += pointValues.get(row, col);
            playerLocations[row][col] = "G";
        }

        return new BlueGreenPair(blueScore, greenScore);
    }

}
