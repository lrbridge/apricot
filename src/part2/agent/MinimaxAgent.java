package part2.agent;

import part2.Board;
import part2.Color;
import part2.move.Move;

import java.util.List;

public class MinimaxAgent extends BaseAgent {

    private MinimaxPossibleSolution stateSoFar;

    private int maxDepth = 3; // search only to max depth of 3 in minimax tree

    public MinimaxAgent(Color playerColor, Board board) {
        super(playerColor, board);
        this.stateSoFar = new MinimaxPossibleSolution(board);
    }

    @Override
    public void updateAgentInternalBoard(Move move) {
        stateSoFar.makeMove(move);
    }

    /**
     * Does a minimax search to find the best move at this time.
     *
     * TODO: we need to cut this off with an evaluation function.  Right now it explores the entire tree.
     */
    @Override
    protected Move searchForBestMove() {
        MinimaxPossibleSolution initialSolution = stateSoFar.clone();

        MinimaxPossibleSolution solution;

        int depth = 0;

        solution = searchForMove(this.playerColor, initialSolution);
        System.out.println("MOVE NUM noDEs" + this.numNodesExpanded);

        return solution.getMove();
    }

    /**
     * Picks the move for the player which will maximize the player's score (given the other player is playing rationally)
     */
    private MinimaxPossibleSolution searchForMove(Color playerToMove, MinimaxPossibleSolution possibleSolution) {

        if (possibleSolution.isDone()) {
            return possibleSolution;
        }

        MinimaxPossibleSolution bestSoFar = null;

        List<Move> possibleMoves = possibleSolution.getPossibleMoves(playerToMove);

        for (Move possibleMove : possibleMoves) {

            System.out.println("... " + playerToMove + " plays " + possibleMove);
            MinimaxPossibleSolution newPossibility = possibleSolution.clone();
            newPossibility.makeMove(possibleMove); // apply this move
            newPossibility = searchForMove(playerToMove.next(), newPossibility); // then DFS, other color's turn

            newPossibility.setLatestConsideredMove(possibleMove);
            numNodesExpanded++;

            bestSoFar = updateBestSoFarIfBetter(playerToMove, bestSoFar, newPossibility);
        }

        return bestSoFar;
    }

}
