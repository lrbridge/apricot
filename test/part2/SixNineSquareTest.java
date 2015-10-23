package part2;

import org.junit.Test;

public class SixNineSquareTest {

    @Test
    public void sixSquareMinimaxVsMinimax() {
        Part2ExpectedResults results = new Part2ExpectedResults();

        String[][] expectedStateOfBoard = {
                {"G", "B", "G"},
                {"B", "G", "B"}
        };
        results.expectedStateOfBoard = expectedStateOfBoard;

        results.blueScore = 26;
        results.blueNumNodesExpanded = 2024;
        results.blueAvgNumNodesExpandedPerMove = 674.0;

        results.greenScore = 16;
        results.greenNumNodesExpanded = 341;
        results.greenAvgNumNodesExpandedPerMove = 113.0;

        Part2TestHelpers.testMinimaxVsMinimax("sixsquare.txt", results);
    }

    //@Test
    public void nineSquareMinimaxVsMinimax() {
        Part2ExpectedResults results = new Part2ExpectedResults();

        String[][] expectedStateOfBoard = {
                {"G", "B", "G"},
                {"B", "G", "B"},
                {"B", "B", "G"}
        };
        results.expectedStateOfBoard = expectedStateOfBoard;

        results.blueScore = 37;
        results.blueNumNodesExpanded = 1000449;
        results.blueAvgNumNodesExpandedPerMove = 200089.0;

        results.greenScore = 23;
        results.greenNumNodesExpanded = 111624;
        results.greenAvgNumNodesExpandedPerMove = 27906.0;

        Part2TestHelpers.testMinimaxVsMinimax("ninesquare.txt", results);
    }
}
