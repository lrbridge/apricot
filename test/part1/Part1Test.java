package part1;

import static org.junit.Assert.*;

import org.junit.Test;

public class Part1Test {

	@Test
	public void trivial() {
		Part1 simple = new Part1("trivial.txt", "smallwordlist.txt");
		
		Assignment solution = simple.solve();
		
		// TODO 2 solutions assertEquals(solution.getNumberSolutions(), 1);
		assertEquals("BAD", solution);
		
		// TODO search trace
	}
	
	@Test
	public void simple() {
		Part1 simple = new Part1("simple.txt", "smallwordlist.txt");
		
		Assignment solution = simple.solve();
		
		// TODO 2 solutions assertEquals(solution.getNumberSolutions(), 1);
		assertEquals("BAID", solution.toString());
		
		// TODO search trace
	}

}
