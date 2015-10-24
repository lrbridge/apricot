package part2.small;

import org.junit.Before;
import org.junit.Test;
import part2.Part2ExpectedResults;
import part2.Part2TestHelpers;

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

    @Test
    public void oneSquareAlphabetaVsAlphabeta() {
        // with only one square, same as minimax

        results.blueNumNodesExpanded = 1;
        results.blueAvgNumNodesExpandedPerMove = 1.0;

        results.greenNumNodesExpanded = 0;
        results.greenAvgNumNodesExpandedPerMove = 0.0;

        Part2TestHelpers.testAlphabetaVsAlphabeta(filename, results);
    }

}
