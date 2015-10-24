package part2.small;

import org.junit.Before;
import org.junit.Test;
import part2.Part2ExpectedResults;
import part2.Part2TestHelpers;

public class FourSquareV3Test {

    private String filename = "foursquareV3.txt";

    private Part2ExpectedResults results;

    @Before
    public void setup() {
        String[][] expectedStateOfBoard = {
                {"G", "B"},
                {"B", "G"}
        };

        results = new Part2ExpectedResults();
        results.expectedStateOfBoard = expectedStateOfBoard;
        results.blueScore = 18;
        results.greenScore = 13;
    }

    @Test
    public void fourSquareV3MinimaxVsMinimax() {

        results.blueNumNodesExpanded = 68; // 64, then 4
        results.blueAvgNumNodesExpandedPerMove = 34.0; // 64 / 2

        results.greenNumNodesExpanded = 16; // 15, then 1
        results.greenAvgNumNodesExpandedPerMove = 8.0; // 16 / 2

        Part2TestHelpers.testMinimaxVsMinimax(filename, results);
    }

    @Test
    public void fourSquareV3AlphabetaVsAlphabeta() {

        results.blueNumNodesExpanded = 49; // 45, then 4
        results.blueAvgNumNodesExpandedPerMove = 24.5; // 49 / 2

        results.greenNumNodesExpanded = 14; // 13, then 1
        results.greenAvgNumNodesExpandedPerMove = 7.0; // 14 / 2

        Part2TestHelpers.testAlphabetaVsAlphabeta(filename, results);
    }

}
