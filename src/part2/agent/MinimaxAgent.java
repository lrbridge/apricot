package part2.agent;

import part2.Board;
import part2.Color;
import part2.move.Move;

import java.util.List;

public class MinimaxAgent extends BaseAgent {

    public MinimaxAgent(Color playerColor, Board board) {
        super(playerColor, board, 3); // max depth of 3
    }

    /**
     * Does a minimax search to find the best move at this time.
     */
    @Override
    protected Move searchForBestMove(PossibleSolution initialSolution) {

        int depth = 0;

        PossibleSolution solution = searchForMove(this.playerColor, initialSolution, depth);
        return solution.getMove();
    }

    /**
     * Picks the move for the player which will maximize the player's score (given the other player is playing rationally)
     */
    private PossibleSolution searchForMove(Color playerToMove, PossibleSolution possibleSolution, int depth) {

        if (possibleSolution.isDone()) {
            return possibleSolution;
        }

        if(isCutoff(depth)) {
            return evaluationFunction(possibleSolution);
        }

        depth++;

        PossibleSolution bestSoFar = null;

        List<Move> possibleMoves = possibleSolution.getPossibleMoves(playerToMove);

        for (Move possibleMove : possibleMoves) {

            //System.out.println("... " + playerToMove + " plays " + possibleMove);
            PossibleSolution newPossibility = possibleSolution.clone();
            newPossibility.makeMove(possibleMove); // apply this move
            newPossibility = searchForMove(playerToMove.next(), newPossibility, depth); // then DFS, other color's turn

            newPossibility.setLatestConsideredMove(possibleMove);
            numNodesExpanded++;

            bestSoFar = updateBestSoFarIfBetter(playerToMove, bestSoFar, newPossibility);
        }

        return bestSoFar;
    }
}
