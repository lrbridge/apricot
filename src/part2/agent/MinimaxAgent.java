package part2.agent;

import part2.Board;
import part2.Color;
import part2.move.Move;

import java.util.List;

public class MinimaxAgent extends BaseAgent {

    private int maxDepth = 3; // search only to max depth of 3 in minimax tree

    public MinimaxAgent(Color playerColor, Board board) {
        super(playerColor, board);
    }

    /**
     * Does a minimax search to find the best move at this time.
     *
     * TODO: we need to cut this off with an evaluation function.  Right now it explores the entire tree.
     */
    @Override
    protected Move searchForBestMove(PossibleSolution initialSolution) {

        int depth = 0;

        PossibleSolution solution = searchForMove(this.playerColor, initialSolution);

        System.out.println("MOVE NUM noDEs" + this.numNodesExpanded);

        return solution.getMove();
    }

    /**
     * Picks the move for the player which will maximize the player's score (given the other player is playing rationally)
     */
    private PossibleSolution searchForMove(Color playerToMove, PossibleSolution possibleSolution) {

        if (possibleSolution.isDone()) {
            return possibleSolution;
        }

        PossibleSolution bestSoFar = null;

        List<Move> possibleMoves = possibleSolution.getPossibleMoves(playerToMove);

        for (Move possibleMove : possibleMoves) {

            System.out.println("... " + playerToMove + " plays " + possibleMove);
            PossibleSolution newPossibility = possibleSolution.clone();
            newPossibility.makeMove(possibleMove); // apply this move
            newPossibility = searchForMove(playerToMove.next(), newPossibility); // then DFS, other color's turn

            newPossibility.setLatestConsideredMove(possibleMove);
            numNodesExpanded++;

            bestSoFar = updateBestSoFarIfBetter(playerToMove, bestSoFar, newPossibility);
        }

        return bestSoFar;
    }

}
