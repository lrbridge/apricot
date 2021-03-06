package part2.small;

import org.junit.Before;
import org.junit.Test;
import part2.Part2ExpectedResults;
import part2.Part2TestHelpers;

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

    @Test
    public void twoSquareAlphabetaVsAlphabeta() {
        // with only two squares, same as minimax

        results.blueNumNodesExpanded = 4;
        results.blueAvgNumNodesExpandedPerMove = 4.0;

        results.greenNumNodesExpanded = 1;
        results.greenAvgNumNodesExpandedPerMove = 1.0;

        Part2TestHelpers.testAlphabetaVsAlphabeta(filename, results);
    }
}
