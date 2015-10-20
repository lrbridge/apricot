package part2;

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

    @Override
    public Move pickBestMove(String agentLetter) {
        MinimaxPossibleSolution initialSolution = stateSoFar.clone();

        MinimaxPossibleSolution solution;
        if(agentLetter.equals("B")) {
            solution = moveBlue(initialSolution);
        }
        else {
            solution = moveGreen(initialSolution);
        }
        Move bestMove = solution.getMove();
        System.out.println("BEST MOVE:" + bestMove);
        return bestMove;
    }

    private MinimaxPossibleSolution moveBlue(MinimaxPossibleSolution possibleSolution) {

        if (possibleSolution.isDone()) {
            return possibleSolution;
        }

        MinimaxPossibleSolution maxBlue = null;

        List<Move> possibleMoves = possibleSolution.getPossibleMoves();

        for (Move possibleMove : possibleMoves) {
            MinimaxPossibleSolution newPossibility = possibleSolution.clone();
            System.out.println("... B plays " + possibleMove.row + " " + possibleMove.col);
            newPossibility.makeMove("B", possibleMove); // apply this move
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

    private MinimaxPossibleSolution moveGreen(MinimaxPossibleSolution possibleSolution) {

        if (possibleSolution.isDone()) {
            return possibleSolution;
        }

        MinimaxPossibleSolution maxGreen = null;

        List<Move> possibleMoves = possibleSolution.getPossibleMoves();

        for (Move possibleMove : possibleMoves) {
            MinimaxPossibleSolution newPossibility = possibleSolution.clone();
            newPossibility.makeMove("G", possibleMove); // apply this move
            System.out.println("... G plays " + possibleMove.row + " " + possibleMove.col);
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

}
