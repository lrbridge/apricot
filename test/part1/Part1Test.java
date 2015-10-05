package part1;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class Part1Test {

	@Test
	public void trivial() {
		Part1 part1 = new Part1("trivial.txt", "smallwordlist.txt");
		List<Assignment> solutions = part1.solve();
		
		assertEquals(solutions.size(), 3);
		assertEquals("ANT", solutions.get(0).toString());
		assertEquals("APE", solutions.get(1).toString());
		assertEquals("BAD", solutions.get(2).toString());
		
		// TODO search trace
	}
	
	@Test
	public void simple() {
		Part1 part1 = new Part1("simple.txt", "smallwordlist.txt");
		List<Assignment> solutions = part1.solve();
		
		assertEquals(solutions.size(), 1);
		assertEquals("BAID", solutions.get(0).toString());
		
		// TODO search trace
	}

}
