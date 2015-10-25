package part2.agent;

import part2.BlueGreenPair;
import part2.Board;
import part2.Color;
import part2.move.CommandoParaDrop;
import part2.move.M1DeathBlitz;
import part2.move.Move;

import java.util.ArrayList;
import java.util.List;

public class PossibleSolution {

    private Board pointValues;
    private String[][] playerLocations;

    private Move latestMove;

    private BlueGreenPair scores = new BlueGreenPair(0, 0);

    public PossibleSolution(Board pointValues) {
        this.pointValues = pointValues;

        this.playerLocations = new String[pointValues.getWidth()][pointValues.getHeight()];
    }

    private PossibleSolution(Board pointValues, String[][] playerLocations, BlueGreenPair scores) {
        this(pointValues);

        for (int i = 0; i < playerLocations.length; i++) {
            for (int j = 0; j < playerLocations[0].length; j++) {
                this.playerLocations[i][j] = playerLocations[i][j];
            }
        }

        this.scores = new BlueGreenPair(scores.blue, scores.green);
    }

    public boolean isDone() {
        for (String[] x : playerLocations) {
            for (String y : x) {
                if (y == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public PossibleSolution clone() {
        return new PossibleSolution(pointValues, playerLocations, scores);
    }

    public List<Move> getPossibleMoves(Color playerToMove) {

        ArrayList<Move> blitzMoves = new ArrayList<>();
        ArrayList<Move> paraDropMoves = new ArrayList<>();

        for (int i = 0; i < playerLocations.length; i++) {
            for (int j = 0; j < playerLocations[0].length; j++) {

                if (playerLocations[i][j] == null) {

                    if(isPossibleToBlitzTo(playerToMove, i, j)) {
                        blitzMoves.add(new M1DeathBlitz(playerToMove, i, j));
                    }
                    else {
                        paraDropMoves.add(new CommandoParaDrop(playerToMove, i, j));
                    }
                }
            }
        }
        blitzMoves.addAll(paraDropMoves);
        return blitzMoves;
    }

    private boolean isPossibleToBlitzTo(Color playerToMove, int row, int col) {
        // blitzing is mandatory if the empty piece is next to an adjacent piece the player already owns
        return isNeighborMatchingColor(playerToMove, row + 1, col) ||
                isNeighborMatchingColor(playerToMove, row - 1, col) ||
                isNeighborMatchingColor(playerToMove, row, col + 1) ||
                isNeighborMatchingColor(playerToMove, row, col - 1);
    }

    private boolean isNeighborMatchingColor(Color playerToMove, int row, int col) {
        if(row < 0 || row >= playerLocations.length || col < 0 || col >= playerLocations[0].length) {
            return false; // if the neighbor is off the board
        }
        if(playerLocations[row][col] == null) {
            return false; // if neighbor is empty square, it's not me
        }

        String letterToMatch = "G";
        if(playerToMove.equals(Color.BLUE)) {
            letterToMatch = "B";
        }
        return playerLocations[row][col].equals(letterToMatch);
    }

    public void makeMove(Move move) {
        scores = move.execute(playerLocations, pointValues, scores);
    }

    public int getBlueScore() {
        return scores.blue;
    }

    public int getGreenScore() {
        return scores.green;
    }

    public void setLatestConsideredMove(Move move) {
        this.latestMove = move;
    }

    public Move getMove() {
        return latestMove;
    }

    public int getDifferenceBlueMinusGreen() {
        return scores.blue - scores.green;
    }
}
