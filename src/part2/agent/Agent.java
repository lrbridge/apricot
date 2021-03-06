package part2.agent;

import part2.move.Move;

public interface Agent {

    Move pickBestMove();

    int getNumNodesExpanded();

    void updateAgentInternalBoard(Move move);

    long getMillisecondsProcessing();
}
