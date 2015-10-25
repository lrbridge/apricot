package part2.agent;

import part2.Board;
import part2.Color;
import part2.move.Move;

public abstract class BaseAgent implements Agent {

    protected Board board;

    protected Color playerColor;

    protected PossibleSolution stateSoFar;

    protected int numNodesExpanded = 0;
    protected long millisecondsProcessing = 0L;

    BaseAgent(Color playerColor, Board board) {
        this.board = board;
        this.playerColor = playerColor;
        this.stateSoFar = new PossibleSolution(board);
    }

    @Override
    public void updateAgentInternalBoard(Move move) {
        stateSoFar.makeMove(move);
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

        PossibleSolution initialSolution = stateSoFar.clone();
        Move bestMove = this.searchForBestMove(initialSolution);

        long endTime = System.currentTimeMillis();

        millisecondsProcessing += (endTime - startTime);

        System.out.println("BEST MOVE:" + bestMove);

        return bestMove;
    }

    protected abstract Move searchForBestMove(PossibleSolution solution);

    protected PossibleSolution updateBestSoFarIfBetter(Color playerToMove, PossibleSolution bestSoFar, PossibleSolution newPossibility) {
        if (bestSoFar == null) {
            bestSoFar = newPossibility;
        } else {

            int maxDifference = bestSoFar.getDifferenceBlueMinusGreen();
            int currentDifference = newPossibility.getDifferenceBlueMinusGreen();

            if(playerToMove.equals(Color.BLUE)) {
//                maxDifference = maxSoFar.getBlueScore() - maxSoFar.getGreenScore();
//                currentDifference = newPossibility.getBlueScore() - newPossibility.getGreenScore();
                if (maxDifference < currentDifference) {
                    bestSoFar = newPossibility;
                }
            }
            else { // GREEN
//                maxDifference = maxSoFar.getGreenScore() - maxSoFar.getBlueScore();
//                currentDifference = newPossibility.getGreenScore() - newPossibility.getBlueScore();
                if (maxDifference > currentDifference) {
                    bestSoFar = newPossibility;
                }
            }

//            if (maxDifference < currentDifference) {
//                maxSoFar = newPossibility;
//            }

        }
        return bestSoFar;
    }

}
