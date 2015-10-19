package part2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class Part2Test {

	@Test
	public void test() {
		int[][] actualSolution = new Part2().solve("Sevastopol.txt");
		int[][] expectedSolution = {
			{1, 2, 1, 2, 1, 2}, 
			{2, 1, 2, 2, 2, 1}, 
			{1, 2, 1, 2, 1, 2}, 
			{1, 1, 2, 1, 2, 1}, 
			{1, 2, 1, 2, 1, 2}, 
			{2, 1, 2, 1, 2, 1}
		};
		assertEquals(actualSolution.length, expectedSolution.length);
		for(int i=0; i<actualSolution.length; i++) {
			assertArrayEquals(actualSolution[i], expectedSolution[i]);
		}
	}
	
	@Test
	public void testTiny() {
		int[][] actualSolution = new Part2().solve("tiny.txt");
		int[][] expectedSolution = {
			{1, 1}, 
			{1, 1}
		};
		assertEquals(actualSolution.length, expectedSolution.length);
		for(int i=0; i<actualSolution.length; i++) {
			assertArrayEquals(actualSolution[i], expectedSolution[i]);
		}
	}

}
