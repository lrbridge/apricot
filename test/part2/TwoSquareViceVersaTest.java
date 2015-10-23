package part2;

import org.junit.Before;
import org.junit.Test;

public class TwoSquareViceVersaTest {

    private String filename = "twosquareviceversa.txt";

    private Part2ExpectedResults results;

    @Before
    public void setup() {
        String[][] expectedStateOfBoard = {
                {"B", "G"}
        };

        results = new Part2ExpectedResults();
        results.expectedStateOfBoard = expectedStateOfBoard;
        results.blueScore = 5;
        results.greenScore = 3;
    }

    @Test
    public void twoSquareViceVersaMinimaxVsMinimax() {
        results.blueNumNodesExpanded = 4;
        results.blueAvgNumNodesExpandedPerMove = 4.0;

        results.greenNumNodesExpanded = 1;
        results.greenAvgNumNodesExpandedPerMove = 1.0;

        Part2TestHelpers.testMinimaxVsMinimax(filename, results);
    }

    // with only 2 squares, all same as minimax...
}
