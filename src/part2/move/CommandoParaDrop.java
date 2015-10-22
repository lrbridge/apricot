package part2.move;

import part2.BlueGreenPair;
import part2.Board;

public class CommandoParaDrop extends BaseMove {

    public CommandoParaDrop(int row, int col) {
        super(row, col);
    }

    @Override
    public BlueGreenPair execute(String[][] playerLocations, String agentLetter, Board pointValues, int blueScore, int greenScore) {
        playerLocations[row][col] = agentLetter;

        if (agentLetter.equals("B")) {
            blueScore += pointValues.get(row, col);
        } else {
            greenScore += pointValues.get(row, col);
        }

        return new BlueGreenPair(blueScore, greenScore);
    }

}
