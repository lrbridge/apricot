package part2;

import org.junit.Before;
import org.junit.Test;

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

        results.greenNumNodesExpanded = 16; // 16, then 0
        results.greenAvgNumNodesExpandedPerMove = 8.0; // 16 / 2

        Part2TestHelpers.testMinimaxVsMinimax(filename, results);
    }

}
