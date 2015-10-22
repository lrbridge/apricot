package part2.agent;

import part2.Board;
import part2.move.Move;

public interface Agent {

    Move pickBestMove(String agentLetter);

    void setBoard(Board board);

    int getNumNodesExpanded();

    void updateBoard(String agentLetter, Move move);

    long getMillisecondsProcessing();
}
