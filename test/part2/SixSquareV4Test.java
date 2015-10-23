package part2;

import org.junit.Before;
import org.junit.Test;

public class SixSquareV4Test {

    private String filename = "sixsquareV4.txt";

    private Part2ExpectedResults results;

    @Before
    public void setup() {
        String[][] expectedStateOfBoard = {
                {"B", "G", "B"},
                {"G", "B", "G"}
        };

        results = new Part2ExpectedResults();
        results.expectedStateOfBoard = expectedStateOfBoard;
        results.blueScore = 7;
        results.greenScore = 6;
    }

    @Test
    public void sixSquarev4MinimaxVsMinimax() {

        results.blueNumNodesExpanded = 2024;
        results.blueAvgNumNodesExpandedPerMove = 674.66666667;

        results.greenNumNodesExpanded = 341;
        results.greenAvgNumNodesExpandedPerMove = 113.66666667;

        Part2TestHelpers.testMinimaxVsMinimax(filename, results);
    }

    @Test
    public void sixSquarev4AlphabetaVsAlphabeta() {

        results.blueNumNodesExpanded = 390;
        results.blueAvgNumNodesExpandedPerMove = 130.0;

        results.greenNumNodesExpanded = 136;
        results.greenAvgNumNodesExpandedPerMove = 45.333333;

        Part2TestHelpers.testAlphabetaVsAlphabeta(filename, results);
    }

}
