package part2;

public interface Agent {

    Move pickBestMove();

    void setBoard(Board board);

    int getNumNodesExpanded();

    void updateBoard(String agentLetter, Move move);
}
