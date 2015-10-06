package part1;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class Part1Test {

	private int getNumLines(String searchTrace) {
		int numLinesOfTrace = 0;
		for(char c : searchTrace.toCharArray()) {
			if(c == '\n') {
				numLinesOfTrace++;
			}
		}
		return numLinesOfTrace;
	}
	
	@Test
	public void trivial() {
		Part1 part1 = new Part1("trivial.txt", "smallwordlist.txt");
		Part1Solution part1Solution = part1.solve();
		List<Assignment> solutions = part1Solution.getSolutions();
		
		assertEquals(solutions.size(), 3);
		assertEquals("APE", solutions.get(0).getSolution());
		assertEquals("ANT", solutions.get(1).getSolution());
		assertEquals("BAD", solutions.get(2).getSolution());
		
		String searchTrace = part1Solution.getSearchTrace();
		int numLinesOfTrace = getNumLines(searchTrace);
		
		System.out.println(searchTrace);
		assertEquals(numLinesOfTrace, 17);
	}
	
	@Test
	public void simple() {
		Part1 part1 = new Part1("simple.txt", "smallwordlist.txt");
		Part1Solution part1Solution = part1.solve();
		List<Assignment> solutions = part1Solution.getSolutions();
		
		assertEquals(solutions.size(), 1);
		assertEquals("BAID", solutions.get(0).getSolution());

		String searchTrace = part1Solution.getSearchTrace();
		int numLinesOfTrace = getNumLines(searchTrace);
		
		System.out.println(searchTrace);
		assertEquals(numLinesOfTrace, 17);
	}

}
