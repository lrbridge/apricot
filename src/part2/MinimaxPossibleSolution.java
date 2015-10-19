package part2;

import part2.Move.MOVE_TYPE;

public class MinimaxPossibleSolution {
	
	private Board pointValues;
	private String[][] playerLocations;

	private Move latestMove;
	
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
		return true;
	}
	
	public MinimaxPossibleSolution clone() {
		return new MinimaxPossibleSolution(pointValues, playerLocations); 
	}
	
	public MinimaxPossibleSolution commandoParaDropGreen(int row, int col) {
		
		if(playerLocations[row][col] != null) {
			return null;
		}
		
		this.latestMove = new Move();
		this.latestMove.col = col;
		this.latestMove.row = row;
		this.latestMove.type = MOVE_TYPE.COMMANDO_PARA_DROP;
		
		this.playerLocations[row][col] = "G";
//		this.greenScore += this.pointValues.get(row, col);
		
		return this;
	}

	public MinimaxPossibleSolution commandoParaDropBlue(int row, int col) {
		
		if(this.playerLocations[row][col] != null) {
			return null;
		}
		
		this.latestMove = new Move();
		this.latestMove.col = col;
		this.latestMove.row = row;
		this.latestMove.type = MOVE_TYPE.COMMANDO_PARA_DROP;
		
		this.playerLocations[row][col] = "B";
//		this.blueScore += this.pointValues.get(row, col);

		return this;
	}

	public Move getMove() {
		return latestMove;
	}

}
