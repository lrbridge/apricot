package part2.move;

import part2.BlueGreenPair;
import part2.Board;
import part2.Color;

public class M1DeathBlitz extends BaseMove {

    public M1DeathBlitz(Color playerToMove, int row, int col) {
        super(playerToMove, row, col);
    }

    @Override
    public BlueGreenPair execute(String[][] playerLocations, Board pointValues, BlueGreenPair scores) {

        BlueGreenPair updatedScores = createPieceInSquare(playerLocations, pointValues, scores);

        if(isNeighborOppositeColor(row, col+1, playerLocations)) {
            updatedScores = turnPiece(row, col+1, updatedScores, playerLocations, pointValues);
        }

        if(isNeighborOppositeColor(row, col-1, playerLocations)) {
            updatedScores = turnPiece(row, col-1, updatedScores, playerLocations, pointValues);
        }

        if(isNeighborOppositeColor(row+1, col, playerLocations)) {
            updatedScores = turnPiece(row+1, col, updatedScores, playerLocations, pointValues);
        }

        if(isNeighborOppositeColor(row-1, col, playerLocations)) {
            updatedScores = turnPiece(row-1, col, updatedScores, playerLocations, pointValues);
        }

        return updatedScores;
    }

    private BlueGreenPair turnPiece(int row, int col, BlueGreenPair updatedScores, String[][] playerLocations, Board pointValues) {
        if (playerToMove.equals(Color.BLUE)) {
            updatedScores.blue += pointValues.get(row, col);
            updatedScores.green -= pointValues.get(row, col);
            playerLocations[row][col] = "B";
        } else {
            updatedScores.green += pointValues.get(row, col);
            updatedScores.blue -= pointValues.get(row, col);
            playerLocations[row][col] = "G";
        }

        return updatedScores;
    }

    private boolean isNeighborOppositeColor(int row, int col, String[][] playerLocations) {
        if(row < 0 || row >= playerLocations.length || col < 0 || col >= playerLocations[0].length) {
            return false; // if the neighbor is off the board
        }
        if(playerLocations[row][col] == null) {
            return false; // if neighbor is empty square, it's not the opposite color
        }

        String letterToMatch = "B"; // opposite color from playerColor
        if(playerToMove.equals(Color.BLUE)) {
            letterToMatch = "G"; // opposite color from playerColor
        }
        return playerLocations[row][col].equals(letterToMatch);
    }
}
