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

    private int maxDepth;

    BaseAgent(Color playerColor, Board board, int maxDepth) {
        this.board = board;
        this.playerColor = playerColor;
        this.stateSoFar = new PossibleSolution(board);
        this.maxDepth = maxDepth;
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

        //System.out.println("BEST MOVE:" + bestMove);

        return bestMove;
    }

    protected abstract Move searchForBestMove(PossibleSolution solution);

    protected PossibleSolution updateBestSoFarIfBetter(Color playerToMove, PossibleSolution bestSoFar, PossibleSolution newPossibility) {
        if (bestSoFar == null) {
            bestSoFar = newPossibility;
        }
        else {

            int maxDifference = bestSoFar.getDifferenceBlueMinusGreen();
            int currentDifference = newPossibility.getDifferenceBlueMinusGreen();

            if(playerToMove.equals(Color.BLUE)) {
                if (maxDifference < currentDifference) {
                    bestSoFar = newPossibility;
                }
            }
            else { // GREEN
                if (maxDifference > currentDifference) {
                    bestSoFar = newPossibility;
                }
            }

        }
        return bestSoFar;
    }

    protected boolean isCutoff(int depth) {
        if(depth >= maxDepth) {
            return true;
        }
        return false;
    }

    protected PossibleSolution evaluationFunction(PossibleSolution possibleSolution) {
        // just return solution with blue and green current scores as is
        // ... it will be evaluated as blue score - green score
        //System.out.println("CUTOFF, " + possibleSolution.getBlueScore() + " " + possibleSolution.getGreenScore());
        return possibleSolution;
    }


}
