package part2.agent;

import part2.Board;
import part2.Color;
import part2.move.Move;

import java.util.List;

public class MinimaxAgent extends BaseAgent {

    private Board board;
    private MinimaxPossibleSolution stateSoFar;

    private int numNodesExpanded = 0;
	private long millisecondsProcessing = 0L;

    public MinimaxAgent(Color playerColor, Board board) {
        super(playerColor);
        this.board = board;
        this.stateSoFar = new MinimaxPossibleSolution(board);
    }

    @Override
    public void updateBoard(Color color, Move move) {
        stateSoFar.makeMove(color, move);
    }

    /**
     * Does a minimax search to find the best move at this time.
     *
     * This is a recursive function, which calls minimax() until it finds a solution.
     *
     * TODO: we need to cut this off with an evaluation function.  Right now it explores the entire tree.
     */
    @Override
    public Move pickBestMove() {
        long startTime = System.currentTimeMillis();

        MinimaxPossibleSolution initialSolution = stateSoFar.clone();

        MinimaxPossibleSolution solution;

        if(this.playerColor.equals(Color.BLUE)) {
            solution = moveBlue(initialSolution);
        }
        else {
            solution = moveGreen(initialSolution);
        }
        Move bestMove = solution.getMove();

        long endTime = System.currentTimeMillis();

        millisecondsProcessing += (endTime - startTime);

        System.out.println("BEST MOVE:" + bestMove);
        return bestMove;
    }


    /**
     * Picks the move for Blue which will maximize Blue's score (given Green is playing rationally)
     */
    private MinimaxPossibleSolution moveBlue(MinimaxPossibleSolution possibleSolution) {

        if (possibleSolution.isDone()) {
            return possibleSolution;
        }

        MinimaxPossibleSolution maxBlue = null;

        List<Move> possibleMoves = possibleSolution.getPossibleMoves();

        for (Move possibleMove : possibleMoves) {
            MinimaxPossibleSolution newPossibility = possibleSolution.clone();
            System.out.println("... B plays " + possibleMove);
            newPossibility.makeMove(Color.BLUE, possibleMove); // apply this move
            newPossibility = moveGreen(newPossibility); // then DFS
            newPossibility.setMove(possibleMove);
            numNodesExpanded++;

            if (maxBlue == null) {
                maxBlue = newPossibility;
            } else {

                int maxMoveBlueWinsBy = maxBlue.getBlueScore() - maxBlue.getGreenScore();
                int possibleMoveBlueWinsBy = newPossibility.getBlueScore() - newPossibility.getGreenScore();

                if (maxMoveBlueWinsBy < possibleMoveBlueWinsBy) {
                    maxBlue = newPossibility;
                }

            }

        }

        return maxBlue;
    }

    /**
     * Picks the move for Green which will maximize Green's score (given Blue is playing rationally)
     */
    private MinimaxPossibleSolution moveGreen(MinimaxPossibleSolution possibleSolution) {

        if (possibleSolution.isDone()) {
            return possibleSolution;
        }

        MinimaxPossibleSolution maxGreen = null;

        List<Move> possibleMoves = possibleSolution.getPossibleMoves();

        for (Move possibleMove : possibleMoves) {
            MinimaxPossibleSolution newPossibility = possibleSolution.clone();
            newPossibility.makeMove(Color.GREEN, possibleMove); // apply this move
            System.out.println("... G plays " + possibleMove);
            newPossibility = moveBlue(newPossibility); // then DFS
            newPossibility.setMove(possibleMove);
            numNodesExpanded++;

            if (maxGreen == null) {
                maxGreen = newPossibility;
            } else {

                int maxMoveGreenWinsBy = maxGreen.getGreenScore() - maxGreen.getBlueScore();
                int possibleMoveGreenWinsBy = newPossibility.getGreenScore() - newPossibility.getBlueScore();

                if (maxMoveGreenWinsBy < possibleMoveGreenWinsBy) {
                    maxGreen = newPossibility;
                }

            }

        }

        return maxGreen;

    }

    public int getNumNodesExpanded() {
        return numNodesExpanded;
    }

    public long getMillisecondsProcessing() {
        return millisecondsProcessing;
    }

}
