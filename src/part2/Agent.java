package part2;

public interface Agent {

	public Move pickBestMove();

	public void setBoard(Board board);

	public int getNumNodesExpanded();
	
	public int getNumMoves();
	
}
