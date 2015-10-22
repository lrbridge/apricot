package part2.agent;

import part2.BlueGreenPair;
import part2.Board;
import part2.Color;
import part2.move.CommandoParaDrop;
import part2.move.M1DeathBlitz;
import part2.move.Move;

import java.util.ArrayList;
import java.util.List;

public class MinimaxPossibleSolution {

    private Board pointValues;
    private String[][] playerLocations;

    private Move latestMove;

    private BlueGreenPair scores = new BlueGreenPair(0, 0);

    public MinimaxPossibleSolution(Board pointValues) {
        this.pointValues = pointValues;

        this.playerLocations = new String[pointValues.getWidth()][pointValues.getHeight()];
    }

    private MinimaxPossibleSolution(Board pointValues, String[][] playerLocations, BlueGreenPair scores) {
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

        StringBuilder str = new StringBuilder();
        for (String[] x : playerLocations) {
            for (String y : x) {
                str.append(y + " ");
            }
            str.append("\n");
        }
        System.out.println(str.toString());
        System.out.println("scores... B:" + scores.blue + " vs. G:" + scores.green);
        return true;
    }

    public MinimaxPossibleSolution clone() {
        return new MinimaxPossibleSolution(pointValues, playerLocations, scores);
    }

    public List<Move> getPossibleMoves(Color playerToMove) {

        ArrayList<Move> possibleMoves = new ArrayList<>();

        for (int i = 0; i < playerLocations.length; i++) {
            for (int j = 0; j < playerLocations[0].length; j++) {

                if (playerLocations[i][j] == null) {

                    Move move;

                    if(isPossibleToBlitzTo(playerToMove, i, j)) {
                        move = new M1DeathBlitz(playerToMove, i, j);
                    }
                    else {
                        move = new CommandoParaDrop(playerToMove, i, j);
                    }

//                    System.out.print("adding possible move: " + move);
                    possibleMoves.add(move);
                }
            }
        }
        System.out.println("\n");
        return possibleMoves;
    }

    private boolean isPossibleToBlitzTo(Color playerToMove, int row, int col) {
        // blitzing is mandatory if the empty piece is next to an adjacent piece the player already owns
        return isNeighborMatchingColor(playerToMove, row + 1, col) ||
                isNeighborMatchingColor(playerToMove, row - 1, col) ||
                isNeighborMatchingColor(playerToMove, row, col + 1) ||
                isNeighborMatchingColor(playerToMove, row, col - 1);
    }

    // TODO could refactor duplication by making playerlocations an object with this method (w/death blitz)
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

    public void setMove(Move move) {
        this.latestMove = move;
    }

    public Move getMove() {
        return latestMove;
    }

}
