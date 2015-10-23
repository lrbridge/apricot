package part2;

import org.junit.Before;
import org.junit.Test;

public class FourSquareV2Test {

    private String filename = "foursquareV2.txt";

    private Part2ExpectedResults results;

    @Before
    public void setup() {
        String[][] expectedStateOfBoard = {
                {"G", "B"},
                {"B", "G"}
        };

        results = new Part2ExpectedResults();
        results.expectedStateOfBoard = expectedStateOfBoard;
        results.blueScore = 16;
        results.greenScore = 6;
    }

    @Test
    public void fourSquareV2MinimaxVsMinimax() {

        results.blueNumNodesExpanded = 68; // 64, then 4
        results.blueAvgNumNodesExpandedPerMove = 34.0; // 64 / 2

        results.greenNumNodesExpanded = 16; // 15, then 1
        results.greenAvgNumNodesExpandedPerMove = 8.0; // 16 / 2

        Part2TestHelpers.testMinimaxVsMinimax(filename, results);
    }

    @Test
    public void fourSquareV2AlphabetaVsAlphabeta() {

        results.blueNumNodesExpanded = 44; // 40, then 4
        results.blueAvgNumNodesExpandedPerMove = 22.0; // 44 / 2

        results.greenNumNodesExpanded = 14; // 13, then 1
        results.greenAvgNumNodesExpandedPerMove = 7.0; // 14 / 2

        Part2TestHelpers.testAlphabetaVsAlphabeta(filename, results);
    }

}
