package part2.agent;

import part2.Board;
import part2.Color;
import part2.move.Move;

public interface Agent {

    Move pickBestMove();

    int getNumNodesExpanded();

    void updateBoard(Color color, Move move);

    long getMillisecondsProcessing();
}
