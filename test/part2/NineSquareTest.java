package part2;

import org.junit.Before;
import org.junit.Test;

public class NineSquareTest {

    private String filename = "ninesquare.txt";

    private Part2ExpectedResults results;

    @Before
    public void setup() {
        String[][] expectedStateOfBoard = {
                {"G", "B", "G"},
                {"B", "G", "B"},
                {"B", "B", "G"}
        };

        results = new Part2ExpectedResults();
        results.expectedStateOfBoard = expectedStateOfBoard;
        results.blueScore = 37;
        results.greenScore = 23;
    }

    //@Test
    public void nineSquareMinimaxVsMinimax() {

        results.blueNumNodesExpanded = 1000449;
        results.blueAvgNumNodesExpandedPerMove = 200089.8;

        results.greenNumNodesExpanded = 111624;
        results.greenAvgNumNodesExpandedPerMove = 27906.0;

        Part2TestHelpers.testMinimaxVsMinimax(filename, results);
    }

    //@Test
    public void nineSquareAlphabetaVsAlphabeta() {

        results.blueNumNodesExpanded = 37753;
        results.blueAvgNumNodesExpandedPerMove = 7550.6;

        results.greenNumNodesExpanded = 9019;
        results.greenAvgNumNodesExpandedPerMove = 2254.75;

        Part2TestHelpers.testAlphabetaVsAlphabeta(filename, results);
    }
}
