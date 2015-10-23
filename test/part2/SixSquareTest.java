package part2;

import org.junit.Before;
import org.junit.Test;

public class SixSquareTest {

    private String filename = "sixsquare.txt";

    private Part2ExpectedResults results;

    @Before
    public void setup() {
        String[][] expectedStateOfBoard = {
                {"G", "B", "G"},
                {"B", "G", "B"}
        };

        results = new Part2ExpectedResults();
        results.expectedStateOfBoard = expectedStateOfBoard;
        results.blueScore = 26;
        results.greenScore = 16;
    }

    @Test
    public void sixSquareMinimaxVsMinimax() {

        results.blueNumNodesExpanded = 2024;
        results.blueAvgNumNodesExpandedPerMove = 674.66666667;

        results.greenNumNodesExpanded = 341;
        results.greenAvgNumNodesExpandedPerMove = 113.66666667;

        Part2TestHelpers.testMinimaxVsMinimax(filename, results);
    }

    @Test
    public void sixSquareAlphabetaVsAlphabeta() {

        results.blueNumNodesExpanded = 488;
        results.blueAvgNumNodesExpandedPerMove = 162.6666667;

        results.greenNumNodesExpanded = 156;
        results.greenAvgNumNodesExpandedPerMove = 52.0;

        Part2TestHelpers.testAlphabetaVsAlphabeta(filename, results);
    }

}
