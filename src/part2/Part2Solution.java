package part2;

import part2.agent.Agent;

/**
 * Encapsulates final results for testing and printing to the console.
 *
 * Includes:
 *      The final state of the board (who owns each square).
 *      Total scores (each player)
 *      The total number of game tree nodes expanded in the course of the game (each player)
 *      The average number of nodes expanded per move (each player)
 *      The average amount of time to make a move (each player)
 */
public class Part2Solution {

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
        return blue.getNumNodesExpanded() * 1.0 / actualState.getNumMovesBlue(); // force decimal
    }

    public long getBlueAvgMillisecondsPerMove() {
        if (actualState.getNumMovesBlue() < 1) {
            return 0L;
        }
        return blue.getMillisecondsProcessing() / actualState.getNumMovesBlue();
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
        return green.getNumNodesExpanded() * 1.0 / actualState.getNumMovesGreen(); // force decimal
    }

    public long getGreenAvgMillisecondsPerMove() {
        if (actualState.getNumMovesGreen() < 1) {
            return 0L;
        }
        return green.getMillisecondsProcessing() / actualState.getNumMovesGreen();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Final state of board:\n");
        for (String[] x : getStateOfBoard()) {
            for (String y : x) {
                str.append("  " + y);
            }
            str.append("\n");
        }
        str.append("Blue: " + "\n");
        str.append("  score: " + getBlueScore() + "\n");
        str.append("  nodes expanded: " + getBlueNumNodesExpanded() + "\n");
        str.append("  avg nodes / move: " + getBlueAvgNumNodesExpandedPerMove() + "\n");
        str.append("  avg milliseconds / move: " + getBlueAvgMillisecondsPerMove() + "\n");
        str.append("Green: " + "\n");
        str.append("  score: " + getGreenScore() + "\n");
        str.append("  nodes expanded: " + getGreenNumNodesExpanded() + "\n");
        str.append("  avg nodes / move: " + getGreenAvgNumNodesExpandedPerMove() + "\n");
        str.append("  avg milliseconds / move: " + getGreenAvgMillisecondsPerMove() + "\n");
        str.append("---------------\n");
        return str.toString();
    }
}
