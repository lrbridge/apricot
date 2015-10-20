package part2;

public class Part2Solution {

//	The final state of the board (who owns each square) and the total scores for each player;
//			The total number of game tree nodes expanded by each player in the course of the game;
//			The average number of nodes expanded per move and the average amount of time to make a move.

    private ActualState actualState;
    private Agent blue;
    private Agent green;

    public Part2Solution(ActualState actualState, Agent blue, Agent green) {
        this.actualState = actualState;
        this.blue = blue;
        this.green = green;
    }

    public String[][] getStateOfBoard() {
        return this.actualState.getStateOfBoard();
    }

    public int getBlueScore() {
        return this.actualState.getBlueScore();
    }

    public int getBlueNumNodesExpanded() {
        return blue.getNumNodesExpanded();
    }

    public double getBlueAvgNumNodesExpandedPerMove() {
        if (actualState.getNumMovesBlue() < 1) {
            return 0.0;
        }
        return blue.getNumNodesExpanded() / actualState.getNumMovesBlue();
    }

    public int getGreenScore() {
        return this.actualState.getGreenScore();
    }

    public int getGreenNumNodesExpanded() {
        return green.getNumNodesExpanded();
    }

    public double getGreenAvgNumNodesExpandedPerMove() {
        if (actualState.getNumMovesGreen() < 1) {
            return 0.0;
        }
        return green.getNumNodesExpanded() / actualState.getNumMovesGreen();
    }

    // TODO blue & green avg time per move

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String[] x : getStateOfBoard()) {
            for (String y : x) {
                str.append(y + " ");
            }
            str.append("\n");
        }
        str.append("Blue: " + getBlueScore() + "\n");
        str.append("  nodes expanded: " + getBlueNumNodesExpanded() + "\n");
        str.append("  avg nodes / move: " + getBlueAvgNumNodesExpandedPerMove() + "\n");
        str.append("Green: " + getGreenScore() + "\n");
        str.append("  nodes expanded: " + getGreenNumNodesExpanded() + "\n");
        str.append("  avg nodes / move: " + getGreenAvgNumNodesExpandedPerMove() + "\n");
        str.append("---------------\n");
        return str.toString();
    }
}
