package part2.agent;

import part2.Board;
import part2.Color;
import part2.move.Move;

import java.util.List;

public class AlphaBetaAgent extends BaseAgent {

    public AlphaBetaAgent(Color playerColor, Board board) {
        super(playerColor, board, 3); // max depth of 3
    }

    /**
     * Does a minimax search to find the best move at this time.
     *
     * TODO: we need to cut this off with an evaluation function.  Right now it explores the entire tree.
     */
    @Override
    protected Move searchForBestMove(PossibleSolution initialSolution) {

        int alpha = Integer.MIN_VALUE;  // alpha is best alternative to Blue player, (highest BLUE - GREEN difference)
        int beta = Integer.MAX_VALUE;   // beta is best alternative to Green player, (lowest BLUE - GREEN difference)

        int depth = 0;

        PossibleSolution solution = searchForMove(this.playerColor, initialSolution, alpha, beta, depth);

System.out.println("MOVE NUM noDEs" + this.numNodesExpanded);

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

            System.out.println("... " + playerToMove + " plays " + possibleMove);
            PossibleSolution newPossibility = possibleSolution.clone();
            newPossibility.makeMove(possibleMove); // apply this move
            newPossibility = searchForMove(playerToMove.next(), newPossibility, alpha, beta, depth); // then DFS, other color's turn

            newPossibility.setLatestConsideredMove(possibleMove);
            numNodesExpanded++;

            bestSoFar = updateBestSoFarIfBetter(playerToMove, bestSoFar, newPossibility);

            int v = bestSoFar.getDifferenceBlueMinusGreen();
System.out.println(v + " " + alpha + " " + beta);
            if(playerToMove.equals(Color.BLUE)) {
                if(v >= beta) {
                    System.out.println("RETURNING, v>=beta");
                    return bestSoFar;
                }
                if(v > alpha) {
                    alpha = v;
                }
            }
            else {
                if(v <= alpha) {
                    System.out.println("RETURNING, v<=alpha");
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