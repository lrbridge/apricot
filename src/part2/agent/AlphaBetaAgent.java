package part2.agent;

import part2.Board;
import part2.Color;
import part2.move.Move;

import java.util.List;

public class AlphaBetaAgent extends BaseAgent {

    private MinimaxPossibleSolution stateSoFar;

    private int maxDepth = 3; // search only to max depth of 3 in minimax tree

    public AlphaBetaAgent(Color playerColor, Board board) {
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

        int alpha = Integer.MIN_VALUE;  // alpha is best alternative to Blue player, (highest BLUE - GREEN difference)
        int beta = Integer.MAX_VALUE;   // beta is best alternative to Green player, (lowest BLUE - GREEN difference)

        MinimaxPossibleSolution initialSolution = stateSoFar.clone();

        MinimaxPossibleSolution solution;

        int depth = 0;

        solution = searchForMove(this.playerColor, initialSolution, alpha, beta);
System.out.println("MOVE NUM noDEs" + this.numNodesExpanded);
        return solution.getMove();
    }

    /**
     * Picks the move for the player which will maximize the player's score (given the other player is playing rationally)
     */
    private MinimaxPossibleSolution searchForMove(Color playerToMove, MinimaxPossibleSolution possibleSolution, int alpha, int beta) {

        if (possibleSolution.isDone()) {
            return possibleSolution;
        }

        MinimaxPossibleSolution bestSoFar = null;

        List<Move> possibleMoves = possibleSolution.getPossibleMoves(playerToMove);

        for (Move possibleMove : possibleMoves) {

            System.out.println("... " + playerToMove + " plays " + possibleMove);
            MinimaxPossibleSolution newPossibility = possibleSolution.clone();
            newPossibility.makeMove(possibleMove); // apply this move
            newPossibility = searchForMove(playerToMove.next(), newPossibility, alpha, beta); // then DFS, other color's turn

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

//int alpha = Integer.MIN_VALUE;  // alpha is best alternative to Blue player, (highest BLUE - GREEN difference)
//int beta = Integer.MAX_VALUE;   // beta is best alternative to Green player, (lowest BLUE - GREEN difference)