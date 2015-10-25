package part2.submission;

import org.junit.Before;
import org.junit.Test;
import part2.Part2ExpectedResults;
import part2.Part2TestHelpers;

public class SmolenskTest {

    private String filename = "Smolensk.txt";

    private Part2ExpectedResults results;

    @Before
    public void setup() {
        String[][] expectedStateOfBoard = {
                {"B", "G"},
                {"G", "B"}
        };

        results = new Part2ExpectedResults();
        results.expectedStateOfBoard = expectedStateOfBoard;
        results.blueScore = 2;
        results.greenScore = 2;
    }

    @Test
    public void smolenskMinimaxVsMinimax() {
        System.out.println("Smolensk - Minimax vs. Minimax");

        results.blueNumNodesExpanded = 68; // 64, then 4
        results.blueAvgNumNodesExpandedPerMove = 34.0; // 64 / 2

        results.greenNumNodesExpanded = 16; // 15, then 1
        results.greenAvgNumNodesExpandedPerMove = 8.0; // 16 / 2

        Part2TestHelpers.testMinimaxVsMinimax(filename, results);
    }

    @Test
    public void smolenskAlphabetaVsAlphabeta() {
        System.out.println("Smolensk - Alphabeta vs. Alphabeta");

        results.blueNumNodesExpanded = 41; // 37, then 4
        results.blueAvgNumNodesExpandedPerMove = 20.5; // 64 / 2

        results.greenNumNodesExpanded = 14; // 13, then 1
        results.greenAvgNumNodesExpandedPerMove = 7.0; // 16 / 2

        Part2TestHelpers.testAlphabetaVsAlphabeta(filename, results);
    }

}
