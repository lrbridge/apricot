package part2.agent;

import part2.Board;
import part2.Color;
import part2.move.Move;

public abstract class BaseAgent implements Agent {

    protected Board board;

    protected Color playerColor;

    protected int numNodesExpanded = 0;
    protected long millisecondsProcessing = 0L;

    BaseAgent(Color playerColor, Board board) {
        this.board = board;
        this.playerColor = playerColor;
    }

    public int getNumNodesExpanded() {
        return numNodesExpanded;
    }

    public long getMillisecondsProcessing() {
        return millisecondsProcessing;
    }

    /**
     * Does a search to find the best move at this time.
     *
     * This is a recursive function, which searches until it finds a solution.
     */
    @Override
    public Move pickBestMove() {

        long startTime = System.currentTimeMillis();

        Move bestMove = this.searchForBestMove();

        long endTime = System.currentTimeMillis();

        millisecondsProcessing += (endTime - startTime);

        System.out.println("BEST MOVE:" + bestMove);

        return bestMove;
    }

    protected abstract Move searchForBestMove();

}
