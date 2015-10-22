package part2;

import part2.move.Move;

public class ActualState {

    private Board board;
    private String[][] playerLocations;

    private BlueGreenPair scores = new BlueGreenPair(0, 0);

    private int numMovesBlue = 0;
    private int numMovesGreen = 0;

    public ActualState(Board board) {
        this.playerLocations = new String[board.getWidth()][board.getHeight()];
        this.board = board;
    }

    public int getBlueScore() {
        return scores.blue;
    }

    public int getGreenScore() {
        return scores.green;
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

    void applyMove(Move move) {
        scores = move.execute(playerLocations, board, scores);

        if (move.getPlayerToMove().equals(Color.BLUE)) {
            numMovesBlue++;
        } else {
            numMovesGreen++;
        }
    }

}
