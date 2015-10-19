package part2;

import part2.Move.MOVE_TYPE;

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
	
	private MinimaxPossibleSolution(Board pointValues, String[][] playerLocations) {
		this(pointValues);

		for(int i=0; i<playerLocations.length; i++) {
			for(int j=0; j<playerLocations[0].length; j++) {
				this.playerLocations[i][j] = playerLocations[i][j];
			}
		}
	}

	public boolean isDone() {
		for(String[] x : playerLocations) {
			for(String y : x) {
				if(y == null) {
					return false;
				}
			}
		}

		StringBuilder str = new StringBuilder();
		str.append("Done....");
		for(String[] x : playerLocations) {
			for(String y : x) {
				str.append(y + " ");
			}
			str.append("\n");
		}
		System.out.println(str.toString());
		return true;
	}
	
	public MinimaxPossibleSolution clone() {
		return new MinimaxPossibleSolution(pointValues, playerLocations); 
	}

	public MinimaxPossibleSolution commandoParaDrop(String agentLetter, int row, int col) {
		this.latestMove = new Move(MOVE_TYPE.COMMANDO_PARA_DROP, row, col);
		
		this.playerLocations[row][col] = agentLetter;

		if(agentLetter.equals("B")) {
			this.blueScore += this.pointValues.get(row, col);
		}
		else {
			this.greenScore += this.pointValues.get(row, col);
		}

		return this;
	}

	public Move getMove() {
		return latestMove;
	}

	public List<Move> getPossibleMoves() {

		ArrayList<Move> possibleMoves = new ArrayList<Move>();

		for(int i=0; i<playerLocations.length; i++) {
			for(int j=0; j<playerLocations[0].length; j++) {
				if(playerLocations[i][j] == null) {
					possibleMoves.add(new Move(MOVE_TYPE.COMMANDO_PARA_DROP, i, j));
				}
			}
		}

		return possibleMoves;
	}

	public void makeMove(String agentLetter, Move move) {
		if(move.type.equals(MOVE_TYPE.COMMANDO_PARA_DROP)) {
			commandoParaDrop(agentLetter, move.row, move.col);
		}
	}

	public int getBlueScore() {
		return blueScore;
	}

	public int getGreenScore() {
		return greenScore;
	}

}
