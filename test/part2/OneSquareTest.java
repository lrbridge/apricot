package part2;

import org.junit.Before;
import org.junit.Test;

public class OneSquareTest {

    private String filename = "onesquare.txt";

    private Part2ExpectedResults results;

    @Before
    public void setup() {
        String[][] expectedStateOfBoard = {
                {"B"}
        };

        results = new Part2ExpectedResults();
        results.expectedStateOfBoard = expectedStateOfBoard;
        results.blueScore = 1;
        results.greenScore = 0;
    }

    @Test
    public void oneSquareMinimaxVsMinimax() {

        results.blueNumNodesExpanded = 1;
        results.blueAvgNumNodesExpandedPerMove = 1.0;

        results.greenNumNodesExpanded = 0;
        results.greenAvgNumNodesExpandedPerMove = 0.0;

        Part2TestHelpers.testMinimaxVsMinimax(filename, results);
    }

}
