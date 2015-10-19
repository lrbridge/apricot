package part2;


public class Part2 {
		
	private Board board;
	private Agent blue;
	private Agent green;
	
	private ActualState actualState;
	
	private boolean isBlueTurn = true;
	
	public Part2(String filename, Agent blue, Agent green) {
		this.board = new Board(filename);
		this.actualState = new ActualState(this.board);
		
		this.blue = blue;
		this.blue.setBoard(board);
		
		this.green = green;
		this.green.setBoard(board);
	}
	
	public Part2Solution play() {
		
		while(!isEnd()) {
			
			if(isBlueTurn) {
				Move move = blue.pickBestMove();
				actualState.applyMove("B", move);
				isBlueTurn = false;
			}
			else {
				Move move = green.pickBestMove();
				actualState.applyMove("G", move);
				isBlueTurn = true;
			}
		}
		
		return new Part2Solution(actualState, blue, green);
	}
	
	public boolean isEnd() {
		for (int i = 0; i < actualState.getStateOfBoard().length; ++i) {
			for (int j = 0; j < actualState.getStateOfBoard()[0].length; ++j) {
				if (actualState.getStateOfBoard()[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}
	
}