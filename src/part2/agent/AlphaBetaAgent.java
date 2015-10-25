package part2.agent;

import part2.Board;
import part2.Color;
import part2.move.Move;

import java.util.List;

public class AlphaBetaAgent extends BaseAgent {

    public AlphaBetaAgent(Color playerColor, Board board) {
        super(playerColor, board, 5); // max depth of 5 - 6 took forever
    }

    /**
     * Does a minimax search to find the best move at this time, with alpha-beta pruning.
     */
    @Override
    protected Move searchForBestMove(PossibleSolution initialSolution) {

        int alpha = Integer.MIN_VALUE;  // alpha is best alternative to Blue player, (highest BLUE - GREEN difference)
        int beta = Integer.MAX_VALUE;   // beta is best alternative to Green player, (lowest BLUE - GREEN difference)

        int depth = 0;

        PossibleSolution solution = searchForMove(this.playerColor, initialSolution, alpha, beta, depth);
        return solution.getMove();
    }

    /**
     * Picks the move for the player which will maximize the player's score (given the other player is playing rationally)
     */
    private PossibleSolution searchForMove(Color playerToMove, PossibleSolution possibleSolution, int alpha, int beta, int depth) {

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

            PossibleSolution newPossibility = possibleSolution.clone();
            newPossibility.makeMove(possibleMove); // apply this move
            newPossibility = searchForMove(playerToMove.next(), newPossibility, alpha, beta, depth); // then DFS, other color's turn

            newPossibility.setLatestConsideredMove(possibleMove);
            numNodesExpanded++;

            bestSoFar = updateBestSoFarIfBetter(playerToMove, bestSoFar, newPossibility);

            int v = bestSoFar.getDifferenceBlueMinusGreen();

            if(playerToMove.equals(Color.BLUE)) {
                if(v >= beta) {
                    return bestSoFar;
                }
                if(v > alpha) {
                    alpha = v;
                }
            }
            else {
                if(v <= alpha) {
                    return bestSoFar;
                }
                if(v < beta) {
                    beta = v;
                }
            }
        }

        return bestSoFar;
    }
}