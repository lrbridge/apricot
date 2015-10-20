package part2;


import part2.agent.Agent;

public class Part2 {

    // The values of each square.  This should not be changed during gameplay.
    private Board board;

    // The two agents that are playing the game.  These must implement the Agent interface, which allows
    // us to either make them MinimaxAgents or AlphaBetaAgents.
    private Agent blue;
    private Agent green;

    // The actual state of the board once a piece is physically moved.
    private ActualState actualState;

    // Whose turn it is currently; it starts with Blue
    private boolean isBlueTurn = true;

    public Part2(String filename, Agent blue, Agent green) {
        this.board = new Board(filename);
        this.actualState = new ActualState(this.board);

        this.blue = blue;
        this.blue.setBoard(board); // give the blue agent the board values

        this.green = green;
        this.green.setBoard(board); // give the green agent the board values
    }

    /**
     * Play the game.
     *
     * Basically, this consists of the pattern:
     *      Rotate turns until the game board is full of pieces.
     *      On an agent's turn, they will pick the best move at the time by doing a minimax or alphabeta search.
     *          Once they've decided the best(-ish) move at the time, actually act out the move (updating the state).
     */
    public Part2Solution play() {

        System.out.println("PLAY!");

        while (!isEnd()) {

            if (isBlueTurn) {
                Move move = blue.pickBestMove("B");
                actualState.applyMove("B", move);
                blue.updateBoard("B", move);
                green.updateBoard("B", move);
                isBlueTurn = false;
            } else {
                Move move = green.pickBestMove("G");
                actualState.applyMove("G", move);
                blue.updateBoard("G", move);
                green.updateBoard("G", move);
                isBlueTurn = true;
            }

        }

        // return the pretty solution
        return new Part2Solution(actualState, blue, green);
    }

    public boolean isEnd() {
        for (int i = 0; i < actualState.getStateOfBoard().length; ++i) {
            for (int j = 0; j < actualState.getStateOfBoard()[0].length; ++j) {
                if (actualState.getStateOfBoard()[i][j] == null) {
                    // if any spot on the board is not taken, keep playing
                    return false;
                }
            }
        }
        // if all the spots on the board are taken, then the game is done
        return true;
    }

}