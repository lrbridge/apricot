package part1;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class Part1Test {

	@Test
	public void trivial() {
		Part1 part1 = new Part1("trivial.txt", "smallwordlist.txt");
		Part1Solution part1Solution = part1.solve();
		List<Assignment> solutions = part1Solution.getSolutions();
		
		assertEquals(solutions.size(), 3);
		assertEquals("ANT", solutions.get(0).getSolution());
		assertEquals("APE", solutions.get(1).getSolution());
		assertEquals("BAD", solutions.get(2).getSolution());
		
		System.out.println(part1Solution.getSearchTrace());
		// TODO add assert when shorter?
//		assertEquals("root -> A -> N -> T (found result: ANT)", part1Solution.getSearchTrace());
		
	}
	
	@Test
	public void simple() {
		Part1 part1 = new Part1("simple.txt", "smallwordlist.txt");
		Part1Solution part1Solution = part1.solve();
		List<Assignment> solutions = part1Solution.getSolutions();
		
		assertEquals(solutions.size(), 1);
		assertEquals("BAID", solutions.get(0).getSolution());

		System.out.println(part1Solution.getSearchTrace());
		// TODO add assert when shorter?
//		assertEquals("root -> A -> N -> T (found result: ANT)", part1Solution.getSearchTrace());
	}

}
