package part2;

import org.junit.Before;
import org.junit.Test;

public class TwoSquareTest {

    private String filename = "twosquare.txt";

    private Part2ExpectedResults results;

    @Before
    public void setup() {
        String[][] expectedStateOfBoard = {
                {"G", "B"}
        };

        results = new Part2ExpectedResults();
        results.expectedStateOfBoard = expectedStateOfBoard;
        results.blueScore = 2;
        results.greenScore = 1;
    }

    @Test
    public void twoSquareMinimaxVsMinimax() {

        results.blueNumNodesExpanded = 4;
        results.blueAvgNumNodesExpandedPerMove = 4.0;

        results.greenNumNodesExpanded = 1;
        results.greenAvgNumNodesExpandedPerMove = 1.0;

        Part2TestHelpers.testMinimaxVsMinimax(filename, results);
    }
}
