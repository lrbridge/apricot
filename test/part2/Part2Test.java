package part2;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Part2Test {
	
	@Test
	public void testOneSquare() {
		Part2Solution solution = new Part2("onesquare.txt", new MinimaxAgent(), new MinimaxAgent()).play();
		
		String[][] expectedStateOfBoard = {
			{"B"}
		};
		
		System.out.println(solution);
				
		assertEquals(solution.getStateOfBoard().length, expectedStateOfBoard.length);
		for(int i=0; i<solution.getStateOfBoard().length; i++) {
			assertArrayEquals(solution.getStateOfBoard()[i], expectedStateOfBoard[i]);
		}
		
		assertEquals(solution.getBlueScore(), 1);
		assertEquals(solution.getBlueNumNodesExpanded(), 1);
		assertTrue(solution.getBlueAvgNumNodesExpandedPerMove() == 1.0);

		assertEquals(solution.getGreenScore(), 0);
		assertEquals(solution.getGreenNumNodesExpanded(), 0);
		assertTrue(solution.getGreenAvgNumNodesExpandedPerMove() == 0.0);
	}
	
	@Test
	public void testTiny() {
		Part2Solution solution = new Part2("tiny.txt", new MinimaxAgent(), new MinimaxAgent()).play();
		
		String[][] expectedStateOfBoard = {
			{"B","G"},
			{"x","y"}
		};
		
		System.out.println(solution);
				
		assertEquals(solution.getStateOfBoard().length, expectedStateOfBoard.length);
		for(int i=0; i<solution.getStateOfBoard().length; i++) {
			assertArrayEquals(solution.getStateOfBoard()[i], expectedStateOfBoard[i]);
		}
		
		assertEquals(solution.getBlueScore(), 1);
		assertEquals(solution.getBlueNumNodesExpanded(), 1);
		assertTrue(solution.getBlueAvgNumNodesExpandedPerMove() == 1.0);

		assertEquals(solution.getGreenScore(), 0);
		assertEquals(solution.getGreenNumNodesExpanded(), 0);
		assertTrue(solution.getGreenAvgNumNodesExpandedPerMove() == 0.0);
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


}
