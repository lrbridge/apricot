package part2.small;

import org.junit.Before;
import org.junit.Test;
import part2.Part2ExpectedResults;
import part2.Part2TestHelpers;

public class SixSquareV2Test {

    private String filename = "sixsquareV2.txt";

    private Part2ExpectedResults results;

    @Before
    public void setup() {
        String[][] expectedStateOfBoard = {
                {"G", "B", "G"},
                {"B", "G", "B"}
        };

        results = new Part2ExpectedResults();
        results.expectedStateOfBoard = expectedStateOfBoard;
        results.blueScore = 15;
        results.greenScore = 3;
    }

    @Test
    public void sixSquarev2MinimaxVsMinimax() {

        results.blueNumNodesExpanded = 2024;
        results.blueAvgNumNodesExpandedPerMove = 674.66666667;

        results.greenNumNodesExpanded = 341;
        results.greenAvgNumNodesExpandedPerMove = 113.66666667;

        Part2TestHelpers.testMinimaxVsMinimax(filename, results);
    }

    @Test
    public void sixSquarev2AlphabetaVsAlphabeta() {

        results.blueNumNodesExpanded = 436;
        results.blueAvgNumNodesExpandedPerMove = 145.33333;

        results.greenNumNodesExpanded = 175;
        results.greenAvgNumNodesExpandedPerMove = 58.333333;

        Part2TestHelpers.testAlphabetaVsAlphabeta(filename, results);
    }

}
