package part2;

public class Part2Solution {
	
	private int[][] pointValues;
	
	private String[][] playerLocations;
	private int blueScore = 0;
	private int greenScore = 0;
// TODO this is per player...
//	private int numNodesExpanded;
//	private double avgNodesExpandedPerMove;
//	private double avgTimePerMove;

	public Part2Solution(int[][] pointValues) {
		this.pointValues = pointValues;
		
		this.playerLocations = new String[this.pointValues.length][this.pointValues[0].length];
	}
	
	private Part2Solution(int[][] pointValues, String[][] playerLocations) {
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
		
		for(String[] x : playerLocations) {
			for(String y : x) {
				System.out.print(y + " ");
			}
			System.out.println(" ");
		}
		System.out.println("---------------");
		
		return true;
	}
	
	public Part2Solution clone() {
		return new Part2Solution(pointValues, playerLocations); 
	}
	
	public Part2Solution commandoParaDropGreen(int row, int col) {
		
		if(playerLocations[row][col] != null) {
			return null;
		}
		
		this.playerLocations[row][col] = "G";
		this.greenScore += this.pointValues[row][col];
		
		return this;
	}

	public Part2Solution commandoParaDropBlue(int row, int col) {
		
		if(this.playerLocations[row][col] != null) {
			return null;
		}
		
		this.playerLocations[row][col] = "B";
		this.blueScore += this.pointValues[row][col];

		return this;
	}
	
//	The final state of the board (who owns each square) and the total scores for each player;
//			The total number of game tree nodes expanded by each player in the course of the game;
//			The average number of nodes expanded per move and the average amount of time to make a move.
	
	public String[][] getStateOfBoard() {
		return playerLocations;
	}

	public int getBlueScore() {
		return blueScore;
	}

	public int getGreenScore() {
		return greenScore;
	}

	// TODO this is per-player...
//	public void setFinalResults(int numNodesExpanded, double avgNodesExpandedPerMove, double avgTimePerMove) {
//		this.numNodesExpanded = numNodesExpanded;
//		this.avgNodesExpandedPerMove = avgNodesExpandedPerMove;
//		this.avgTimePerMove = avgTimePerMove;
//	}
//	
//	public int getNumNodesExpanded() {
//		return numNodesExpanded;
//	}
//
//	public double getAvgNodesExpandedPerMove() {
//		return avgNodesExpandedPerMove;
//	}
//
//	public double getAvgTimePerMove() {
//		return avgTimePerMove;
//	}

}
