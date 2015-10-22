package part2;

import org.junit.Test;
import part2.agent.MinimaxAgent;

import static org.junit.Assert.*;

public class Part2Test {

    @Test
    public void oneSquareMinimaxVsMinimax() {

        String[][] expectedStateOfBoard = {
                {"B"}
        };

        int blueScore = 1;
        int blueNumNodesExpanded = 1;
        double blueAvgNumNodesExpandedPerMove = 1.0;

        int greenScore = 0;
        int greenNumNodesExpanded = 0;
        double greenAvgNumNodesExpandedPerMove = 0.0;

        Part2Solution solution = new Part2("onesquare.txt", PlayerType.MINIMAX, PlayerType.MINIMAX).play();

        testPart2(solution,
                expectedStateOfBoard,
                blueScore, blueNumNodesExpanded, blueAvgNumNodesExpandedPerMove,
                greenScore, greenNumNodesExpanded, greenAvgNumNodesExpandedPerMove);
    }

    @Test
    public void twoSquareMinimaxVsMinimax() {

        String[][] expectedStateOfBoard = {
                {"G", "B"}
        };

        int blueScore = 2;
        int blueNumNodesExpanded = 4;
        double blueAvgNumNodesExpandedPerMove = 4;

        int greenScore = 1;
        int greenNumNodesExpanded = 1;
        double greenAvgNumNodesExpandedPerMove = 1.0;

        Part2Solution solution = new Part2("twosquare.txt", PlayerType.MINIMAX, PlayerType.MINIMAX).play();

        testPart2(solution,
                expectedStateOfBoard,
                blueScore, blueNumNodesExpanded, blueAvgNumNodesExpandedPerMove,
                greenScore, greenNumNodesExpanded, greenAvgNumNodesExpandedPerMove);
    }

    @Test
    public void twoSquareViceVersaMinimaxVsMinimax() {

        String[][] expectedStateOfBoard = {
                {"B", "G"}
        };

        int blueScore = 5;
        int blueNumNodesExpanded = 4;
        double blueAvgNumNodesExpandedPerMove = 4;

        int greenScore = 3;
        int greenNumNodesExpanded = 1;
        double greenAvgNumNodesExpandedPerMove = 1.0;

        Part2Solution solution = new Part2("twosquareviceversa.txt", PlayerType.MINIMAX, PlayerType.MINIMAX).play();

        testPart2(solution,
                expectedStateOfBoard,
                blueScore, blueNumNodesExpanded, blueAvgNumNodesExpandedPerMove,
                greenScore, greenNumNodesExpanded, greenAvgNumNodesExpandedPerMove);
    }

    @Test
    public void fourSquareV1MinimaxVsMinimax() {

        String[][] expectedStateOfBoard = {
                {"B", "G"},
                {"G", "B"}
        };

        int blueScore = 2;
        int blueNumNodesExpanded = 68; // 64, then 4
        double blueAvgNumNodesExpandedPerMove = 34.0; // 64 / 2

        int greenScore = 2;
        int greenNumNodesExpanded = 16; // 16, then 0
        double greenAvgNumNodesExpandedPerMove = 8.0; // 16 / 2

        Part2Solution solution = new Part2("foursquareV1.txt", PlayerType.MINIMAX, PlayerType.MINIMAX).play();

        testPart2(solution,
                expectedStateOfBoard,
                blueScore, blueNumNodesExpanded, blueAvgNumNodesExpandedPerMove,
                greenScore, greenNumNodesExpanded, greenAvgNumNodesExpandedPerMove);
    }

    @Test
    public void fourSquareV2MinimaxVsMinimax() {

        String[][] expectedStateOfBoard = {
                {"G", "B"},
                {"B", "G"}
        };

        int blueScore = 16;
        int blueNumNodesExpanded = 68; // 64, then 4
        double blueAvgNumNodesExpandedPerMove = 34.0; // 64 / 2

        int greenScore = 6;
        int greenNumNodesExpanded = 16; // 16, then 0
        double greenAvgNumNodesExpandedPerMove = 8.0; // 16 / 2

        Part2Solution solution = new Part2("foursquareV2.txt", PlayerType.MINIMAX, PlayerType.MINIMAX).play();

        testPart2(solution,
                expectedStateOfBoard,
                blueScore, blueNumNodesExpanded, blueAvgNumNodesExpandedPerMove,
                greenScore, greenNumNodesExpanded, greenAvgNumNodesExpandedPerMove);
    }

    @Test
    public void fourSquareV3MinimaxVsMinimax() {

        String[][] expectedStateOfBoard = {
                {"G", "B"},
                {"B", "G"}
        };

        int blueScore = 18;
        int blueNumNodesExpanded = 68; // 64, then 4
        double blueAvgNumNodesExpandedPerMove = 34.0; // 64 / 2

        int greenScore = 13;
        int greenNumNodesExpanded = 16; // 16, then 0
        double greenAvgNumNodesExpandedPerMove = 8.0; // 16 / 2

        Part2Solution solution = new Part2("foursquareV3.txt", PlayerType.MINIMAX, PlayerType.MINIMAX).play();

        testPart2(solution,
                expectedStateOfBoard,
                blueScore, blueNumNodesExpanded, blueAvgNumNodesExpandedPerMove,
                greenScore, greenNumNodesExpanded, greenAvgNumNodesExpandedPerMove);
    }

    @Test
    public void fourSquareV4MinimaxVsMinimax() {

        String[][] expectedStateOfBoard = {
                {"B", "G"},
                {"G", "B"}
        };

        int blueScore = 7;
        int blueNumNodesExpanded = 68; // 64, then 4
        double blueAvgNumNodesExpandedPerMove = 34.0; // 64 / 2

        int greenScore = 3;
        int greenNumNodesExpanded = 16; // 16, then 0
        double greenAvgNumNodesExpandedPerMove = 8.0; // 16 / 2

        Part2Solution solution = new Part2("foursquareV4.txt", PlayerType.MINIMAX, PlayerType.MINIMAX).play();

        testPart2(solution,
                expectedStateOfBoard,
                blueScore, blueNumNodesExpanded, blueAvgNumNodesExpandedPerMove,
                greenScore, greenNumNodesExpanded, greenAvgNumNodesExpandedPerMove);
    }

    //@Test
    public void nineSquareMinimaxVsMinimax() {

        String[][] expectedStateOfBoard = {
                {"G", "B", "B"},
                {"B", "B", "G"},
                {"G", "G", "B"}
        };

        int blueScore = 34;
        int blueNumNodesExpanded = 1000449;
        double blueAvgNumNodesExpandedPerMove = 200089.0;

        int greenScore = 26;
        int greenNumNodesExpanded = 111624;
        double greenAvgNumNodesExpandedPerMove = 27906.0;

        Part2Solution solution = new Part2("ninesquare.txt", PlayerType.MINIMAX, PlayerType.MINIMAX).play();

        testPart2(solution,
                expectedStateOfBoard,
                blueScore, blueNumNodesExpanded, blueAvgNumNodesExpandedPerMove,
                greenScore, greenNumNodesExpanded, greenAvgNumNodesExpandedPerMove);
    }

//	@Test
//	public void test() {
//		int[][] actualSolution = new Part2().solve("Sevastopol.txt");
//		int[][] expectedSolution = {
//			{1, 2, 1, 2, 1, 2}, 
//			{2, 1, 2, 2, 2, 1}, 
//			{1, 2, 1, 2, 1, 2}, 
//			{1, 1, 2, 1, 2, 1}, 
//			{1, 2, 1, 2, 1, 2}, 
//			{2, 1, 2, 1, 2, 1}
//		};
//		assertEquals(actualSolution.length, expectedSolution.length);
//		for(int i=0; i<actualSolution.length; i++) {
//			assertArrayEquals(actualSolution[i], expectedSolution[i]);
//		}
//	}

    private void testPart2(Part2Solution solution, String[][] expectedStateOfBoard,
                           int blueScore, int blueNumNodesExpanded, double blueAvgNumNodesExpandedPerMove,
                           int greenScore, int greenNumNodesExpanded, double greenAvgNumNodesExpandedPerMove) {

        System.out.println(solution);

        assertEquals(solution.getStateOfBoard().length, expectedStateOfBoard.length);
        for (int i = 0; i < solution.getStateOfBoard().length; i++) {
            assertArrayEquals(solution.getStateOfBoard()[i], expectedStateOfBoard[i]);
        }

        assertEquals(solution.getBlueScore(), blueScore);
        assertEquals(solution.getBlueNumNodesExpanded(), blueNumNodesExpanded);
        assertTrue(solution.getBlueAvgNumNodesExpandedPerMove() == blueAvgNumNodesExpandedPerMove);

        assertEquals(solution.getGreenScore(), greenScore);
        assertEquals(solution.getGreenNumNodesExpanded(), greenNumNodesExpanded);
        assertTrue(solution.getGreenAvgNumNodesExpandedPerMove() == greenAvgNumNodesExpandedPerMove);
    }
}
