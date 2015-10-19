package part2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Part2Test {
	
	@Test
	public void testOneSquare() {
		Part2Solution solution = new Part2().solve("onesquare.txt");
		
		String[][] expectedStateOfBoard = {
			{"B"}
		};
				
		assertEquals(solution.getStateOfBoard().length, expectedStateOfBoard.length);
		for(int i=0; i<solution.getStateOfBoard().length; i++) {
			assertArrayEquals(solution.getStateOfBoard()[i], expectedStateOfBoard[i]);
		}
		
		assertEquals(solution.getBlueScore(), 1);
		assertEquals(solution.getGreenScore(), 0);

//		assertEquals(solution.getNumNodesExpanded(), 1);
//		assertTrue(solution.getAvgNodesExpandedPerMove() == 1.0);
//		assertTrue(solution.getAvgTimePerMove() == 1.0);
		
		System.out.println(solution);
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
	
//	@Test
//	public void testTiny() {
//		int[][] actualSolution = new Part2().solve("tiny.txt");
//		int[][] expectedSolution = {
//			{1, 1}, 
//			{1, 1}
//		};
//		assertEquals(actualSolution.length, expectedSolution.length);
//		for(int i=0; i<actualSolution.length; i++) {
//			assertArrayEquals(actualSolution[i], expectedSolution[i]);
//		}
//	}

}
