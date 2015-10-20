package part2.agent;

import part2.Board;
import part2.Move;

import java.util.List;

public class MinimaxAgent implements Agent {

    private Board board;
    private MinimaxPossibleSolution stateSoFar;

    private int numNodesExpanded = 0;
//	private int timeMs; TODO

    @Override
    public void updateBoard(String agentLetter, Move move) {
        stateSoFar.makeMove(agentLetter, move);
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
        this.stateSoFar = new MinimaxPossibleSolution(board);
    }

    /**
     * Does a minimax search to find the best move at this time.
     *
     * This is a recursive function, which calls minimax() until it finds a solution.
     *
     * TODO: we need to cut this off with an evaluation function.  Right now it explores the entire tree.
     */
    @Override
    public Move pickBestMove(String agentLetter) {
        MinimaxPossibleSolution initialSolution = stateSoFar.clone();

        MinimaxPossibleSolution solution;
        if(agentLetter.equals("B")) {
            solution = minimaxBlue(initialSolution);
        }
        else {
            solution = minimaxGreen(initialSolution);
        }
        Move bestMove = solution.getMove();
        System.out.println("BEST MOVE:" + bestMove);
        return bestMove;
    }


    /**
     * Picks the move for Blue which will maximize Blue's score (given Green is playing rationally)
     */
    private MinimaxPossibleSolution minimaxBlue(MinimaxPossibleSolution possibleSolution) {

        if (possibleSolution.isDone()) {
            return possibleSolution;
        }

        MinimaxPossibleSolution maxBlue = null;

        List<Move> possibleMoves = possibleSolution.getPossibleMoves();

        for (Move possibleMove : possibleMoves) {
            MinimaxPossibleSolution newPossibility = possibleSolution.clone();
            System.out.println("... B plays " + possibleMove.row + " " + possibleMove.col);
            newPossibility.makeMove("B", possibleMove); // apply this move
            newPossibility = minimaxGreen(newPossibility); // then DFS
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
    private MinimaxPossibleSolution minimaxGreen(MinimaxPossibleSolution possibleSolution) {

        if (possibleSolution.isDone()) {
            return possibleSolution;
        }

        MinimaxPossibleSolution maxGreen = null;

        List<Move> possibleMoves = possibleSolution.getPossibleMoves();

        for (Move possibleMove : possibleMoves) {
            MinimaxPossibleSolution newPossibility = possibleSolution.clone();
            newPossibility.makeMove("G", possibleMove); // apply this move
            System.out.println("... G plays " + possibleMove.row + " " + possibleMove.col);
            newPossibility = minimaxBlue(newPossibility); // then DFS
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

}
