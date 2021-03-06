package part2.small;

import org.junit.Before;
import org.junit.Test;
import part2.Part2ExpectedResults;
import part2.Part2TestHelpers;

public class SixSquareV3Test {

    private String filename = "sixsquareV3.txt";

    private Part2ExpectedResults results;

    @Before
    public void setup() {
        String[][] expectedStateOfBoard = {
                {"B", "G", "B"},
                {"G", "B", "G"}
        };

        results = new Part2ExpectedResults();
        results.expectedStateOfBoard = expectedStateOfBoard;
        results.blueScore = 3;
        results.greenScore = 3;
    }

    @Test
    public void sixSquarev3MinimaxVsMinimax() {

        results.blueNumNodesExpanded = 2024;
        results.blueAvgNumNodesExpandedPerMove = 674.66666667;

        results.greenNumNodesExpanded = 341;
        results.greenAvgNumNodesExpandedPerMove = 113.66666667;

        Part2TestHelpers.testMinimaxVsMinimax(filename, results);
    }

    @Test
    public void sixSquarev3AlphabetaVsAlphabeta() {

        results.blueNumNodesExpanded = 382;
        results.blueAvgNumNodesExpandedPerMove = 127.333333;

        results.greenNumNodesExpanded = 130;
        results.greenAvgNumNodesExpandedPerMove = 43.333333;

        Part2TestHelpers.testAlphabetaVsAlphabeta(filename, results);
    }

}
