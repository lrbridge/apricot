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

        if(this.playerColor.equals(Color.BLUE)) {
            solution = moveBlue(initialSolution);
        }
        else {
            solution = moveGreen(initialSolution);
        }

        return solution.getMove();
    }

    /**
     * Picks the move for Blue which will maximize Blue's score (given Green is playing rationally)
     */
    private MinimaxPossibleSolution moveBlue(MinimaxPossibleSolution possibleSolution) {
        boolean isBlueMove = true; // move blue
        return searchForMove(isBlueMove, possibleSolution);
    }

    /**
     * Picks the move for Green which will maximize Green's score (given Blue is playing rationally)
     */
    private MinimaxPossibleSolution moveGreen(MinimaxPossibleSolution possibleSolution) {
        boolean isBlueMove = false; // move green
        return searchForMove(isBlueMove, possibleSolution);
    }

    private MinimaxPossibleSolution searchForMove(boolean isBlueMove, MinimaxPossibleSolution possibleSolution) {

        if (possibleSolution.isDone()) {
            return possibleSolution;
        }

        MinimaxPossibleSolution maxSoFar = null;

        Color colorToMove = Color.BLUE;
        if(!isBlueMove) {
            colorToMove = Color.GREEN;
        }

        List<Move> possibleMoves = possibleSolution.getPossibleMoves(colorToMove);

        for (Move possibleMove : possibleMoves) {

            MinimaxPossibleSolution newPossibility = possibleSolution.clone();
            newPossibility.makeMove(possibleMove); // apply this move

            System.out.println("... " + colorToMove + " plays " + possibleMove);

            newPossibility = searchForMove(!isBlueMove, newPossibility); // then DFS, other color's turn

            newPossibility.setLatestConsideredMove(possibleMove);
            numNodesExpanded++;

            if (maxSoFar == null) {
                maxSoFar = newPossibility;
            } else {

                int maxDifference, currentDifference;

                if(isBlueMove) {
                    maxDifference = maxSoFar.getBlueScore() - maxSoFar.getGreenScore();
                    currentDifference = newPossibility.getBlueScore() - newPossibility.getGreenScore();
                }
                else {
                    maxDifference = maxSoFar.getGreenScore() - maxSoFar.getBlueScore();
                    currentDifference = newPossibility.getGreenScore() - newPossibility.getBlueScore();
                }

                if (maxDifference < currentDifference) {
                    maxSoFar = newPossibility;
                }

            }

        }

        return maxSoFar;
    }


}
