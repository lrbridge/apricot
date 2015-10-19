package part2;

import part2.Move.MOVE_TYPE;

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
		
		if(move.type.equals(MOVE_TYPE.COMMANDO_PARA_DROP)) {
			
			playerLocations[move.row][move.col] = player;
			
			if(player == "B") {
				blueScore += board.get(move.row, move.col);
				numMovesBlue++;
			}
			else {
				greenScore += board.get(move.row, move.col);
				numMovesGreen++;
			}
			
		}
//		else { // TODO m1 death blitz
//			
//		}
	}

}
