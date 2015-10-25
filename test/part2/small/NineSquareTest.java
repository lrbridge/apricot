package part2.small;

import org.junit.Before;
import org.junit.Test;
import part2.Part2ExpectedResults;
import part2.Part2TestHelpers;

public class NineSquareTest {

    @Test
    public void nineSquareMinimaxVsMinimax() {
        String[][] expectedStateOfBoard = {
                {"G", "B", "G"},
                {"B", "G", "B"},
                {"B", "B", "G"}
        };

        Part2ExpectedResults results = new Part2ExpectedResults();
        results.expectedStateOfBoard = expectedStateOfBoard;
        results.blueScore = 37;
        results.greenScore = 23;

        results.blueNumNodesExpanded = 1000449;
        results.blueAvgNumNodesExpandedPerMove = 200089.8;

        results.greenNumNodesExpanded = 111624;
        results.greenAvgNumNodesExpandedPerMove = 27906.0;

        Part2TestHelpers.testMinimaxVsMinimax("ninesquare.txt", results);
    }

    @Test
    public void nineSquareAlphabetaVsAlphabeta() {
        String[][] expectedStateOfBoard = {
                {"G", "B", "G"},
                {"B", "G", "B"},
                {"B", "B", "G"}
        };

        Part2ExpectedResults results = new Part2ExpectedResults();
        results.expectedStateOfBoard = expectedStateOfBoard;
        results.blueScore = 37;
        results.greenScore = 23;

        results.blueNumNodesExpanded = 37753;
        results.blueAvgNumNodesExpandedPerMove = 7550.6;

        results.greenNumNodesExpanded = 9019;
        results.greenAvgNumNodesExpandedPerMove = 2254.75;

        Part2TestHelpers.testAlphabetaVsAlphabeta("ninesquare.txt", results);
    }

    @Test
    public void nineSquareV2AlphabetaVsAlphabeta() {
        String[][] expectedStateOfBoard = {
                {"B", "G", "B"},
                {"B", "B", "G"},
                {"B", "B", "G"}
        };

        Part2ExpectedResults results = new Part2ExpectedResults();
        results.expectedStateOfBoard = expectedStateOfBoard;
        results.blueScore = 6;
        results.greenScore = 3;

        results.blueNumNodesExpanded = 25454;
        results.blueAvgNumNodesExpandedPerMove = 5090.8;

        results.greenNumNodesExpanded = 9740;
        results.greenAvgNumNodesExpandedPerMove = 2435.0;

        Part2TestHelpers.testAlphabetaVsAlphabeta("ninesquareV2.txt", results);
    }

    @Test
    public void nineSquareV3AlphabetaVsAlphabeta() {
        String[][] expectedStateOfBoard = {
                {"B", "G", "B"},
                {"G", "B", "G"},
                {"B", "G", "B"}
        };

        Part2ExpectedResults results = new Part2ExpectedResults();
        results.expectedStateOfBoard = expectedStateOfBoard;
        results.blueScore = 495;
        results.greenScore = 4;

        results.blueNumNodesExpanded = 29699;
        results.blueAvgNumNodesExpandedPerMove = 5939.8;

        results.greenNumNodesExpanded = 9270;
        results.greenAvgNumNodesExpandedPerMove = 2317.5;

        Part2TestHelpers.testAlphabetaVsAlphabeta("ninesquareV3.txt", results);
    }
}
