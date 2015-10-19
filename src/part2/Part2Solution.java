package part2;

public class Part2Solution {

//	The final state of the board (who owns each square) and the total scores for each player;
//			The total number of game tree nodes expanded by each player in the course of the game;
//			The average number of nodes expanded per move and the average amount of time to make a move.
	
	private ActualState actualState;
	private Agent blue;
	private Agent green;
	
	public Part2Solution(ActualState actualState, Agent blue, Agent green) {
		this.actualState = actualState;
		this.blue = blue;
		this.green = green;
	}

	public String[][] getStateOfBoard() {
		return this.actualState.getStateOfBoard();
	}

	public int getBlueScore() {
		return this.actualState.getBlueScore();
	}

	public int getGreenScore() {
		return this.actualState.getGreenScore();
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(String[] x : getStateOfBoard()) {
			for(String y : x) {
				str.append(y + " ");
			}
			str.append("\n");
		}
		str.append("Blue: " + getBlueScore() + "\n");
		str.append("Green: " + getGreenScore() + "\n");
		str.append("---------------\n");
		return str.toString();
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
