package part2;

import part2.move.Move;

public class ActualState {

    private Board board;
    private String[][] playerLocations;

    private int blueScore = 0;
    private int greenScore = 0;

    private int numMovesBlue = 0;
    private int numMovesGreen = 0;

    public ActualState(Board board) {
        this.playerLocations = new String[board.getWidth()][board.getHeight()];
        this.board = board;
    }

    public int getBlueScore() {
        return blueScore;
    }

    public int getGreenScore() {
        return greenScore;
    }

    public int getNumMovesBlue() {
        return numMovesBlue;
    }

    public int getNumMovesGreen() {
        return numMovesGreen;
    }

    public String[][] getStateOfBoard() {
        return playerLocations;
    }

    void applyMove(String player, Move move) {
        BlueGreenPair newScores = move.execute(playerLocations, player, board, blueScore, greenScore);

        blueScore = newScores.blue;
        greenScore = newScores.green;

        if (player == "B") {
            numMovesBlue++;
        } else {
            numMovesGreen++;
        }
    }

}
