package part2;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Part2TestHelpers {

    public static void testMinimaxVsMinimax(String filename, Part2ExpectedResults results) {
        Part2Solution solution = new Part2(filename, PlayerType.MINIMAX, PlayerType.MINIMAX).play();
        testPart2(solution, results);
    }

    public static void testAlphabetaVsAlphabeta(String filename, Part2ExpectedResults results) {
        Part2Solution solution = new Part2(filename, PlayerType.ALPHABETA, PlayerType.ALPHABETA).play();
        testPart2(solution, results);
    }

    private static void testPart2(Part2Solution solution, Part2ExpectedResults results) {

        System.out.println(solution);

        assertEquals(solution.getStateOfBoard().length, results.expectedStateOfBoard.length);
        for (int i = 0; i < solution.getStateOfBoard().length; i++) {
            assertArrayEquals(solution.getStateOfBoard()[i], results.expectedStateOfBoard[i]);
        }

        assertEquals(solution.getBlueScore(), results.blueScore);
        assertEquals(solution.getBlueNumNodesExpanded(), results.blueNumNodesExpanded);
        assertTrue(solution.getBlueAvgNumNodesExpandedPerMove() == results.blueAvgNumNodesExpandedPerMove);

        assertEquals(solution.getGreenScore(), results.greenScore);
        assertEquals(solution.getGreenNumNodesExpanded(), results.greenNumNodesExpanded);
        assertTrue(solution.getGreenAvgNumNodesExpandedPerMove() == results.greenAvgNumNodesExpandedPerMove);
    }
}
