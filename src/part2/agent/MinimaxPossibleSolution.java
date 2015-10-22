package part2.agent;

import part2.BlueGreenPair;
import part2.Board;
import part2.Color;
import part2.move.CommandoParaDrop;
import part2.move.Move;

import java.util.ArrayList;
import java.util.List;

public class MinimaxPossibleSolution {

    private Board pointValues;
    private String[][] playerLocations;

    private Move latestMove;

    private int blueScore = 0;
    private int greenScore = 0;

    public MinimaxPossibleSolution(Board pointValues) {
        this.pointValues = pointValues;

        this.playerLocations = new String[pointValues.getWidth()][pointValues.getHeight()];
    }

    private MinimaxPossibleSolution(Board pointValues, String[][] playerLocations, int blueScore, int greenScore) {
        this(pointValues);

        for (int i = 0; i < playerLocations.length; i++) {
            for (int j = 0; j < playerLocations[0].length; j++) {
                this.playerLocations[i][j] = playerLocations[i][j];
            }
        }

        this.blueScore = blueScore;
        this.greenScore = greenScore;
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
        str.append("Done....");
        for (String[] x : playerLocations) {
            for (String y : x) {
                str.append(y + " ");
            }
            str.append("\n");
        }
        System.out.println(str.toString());
        return true;
    }

    public MinimaxPossibleSolution clone() {
        return new MinimaxPossibleSolution(pointValues, playerLocations, blueScore, greenScore);
    }

    public List<Move> getPossibleMoves() {

        ArrayList<Move> possibleMoves = new ArrayList<>();

        for (int i = 0; i < playerLocations.length; i++) {
            for (int j = 0; j < playerLocations[0].length; j++) {
                if (playerLocations[i][j] == null) {
                    possibleMoves.add(new CommandoParaDrop(i, j));
                }
            }
        }

        return possibleMoves;
    }

    public void makeMove(Color color, Move move) {
        BlueGreenPair newScores = move.execute(playerLocations, color, pointValues, blueScore, greenScore);

        blueScore = newScores.blue;
        greenScore = newScores.green;
    }

    public int getBlueScore() {
        return blueScore;
    }

    public int getGreenScore() {
        return greenScore;
    }

    public void setMove(Move move) {
        this.latestMove = move;
    }

    public Move getMove() {
        return latestMove;
    }

}
