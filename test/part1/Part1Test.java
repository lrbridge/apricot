package part1;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class Part1Test {
	
	@Test
	public void trivial() {
		String[] expectedWords = {"APE", "ANT", "BAD"};
		testPart1("trivial.txt", "smallwordlist.txt", expectedWords, 17);
	}
	
	@Test
	public void simple() {
		String[] expectedWords = {"BAID"};
		testPart1("simple.txt", "smallwordlist.txt", expectedWords, 9);
	}
	
	@Test
	public void puzzle1() {
		String[] expectedWords = {"NNEMANDYE", "NWEMANDYE", "NNESAYDYE", "NWESAYDYE"};
		testPart1("puzzle1.txt", "wordlist.txt", expectedWords, 104);
	}
	
	@Test
	public void puzzle2() {
		String[] expectedWords = {"HSIAIWNCS", "HSIAIWNPS", "HSIOIWNDS", "HSIOIWNYS"};
		testPart1("puzzle2.txt", "wordlist.txt", expectedWords, 148);
	}
	
	@Test
	public void puzzle3() {
		String[] expectedWords = {"ASULPEA", "ASULPIE"};
		testPart1("puzzle3.txt", "wordlist.txt", expectedWords, 261);
	}
	
	@Test
	public void puzzle4() {
		String[] expectedWords = {"HEDITYRE", "HELITYRE", "HETITYRE"};
		testPart1("puzzle4.txt", "wordlist.txt", expectedWords, 76);
	}
	
	@Test
	public void puzzle5() {
		String[] expectedWords = {"IHTTNOIEN", "THTTNOIEN", "IHTTYOIEN", "THTTYOIEN"};
		testPart1("puzzle5.txt", "wordlist.txt", expectedWords, 102);
	}

	private void testPart1(String filename, String wordlist, String[] expectedWords, int expectedLinesInTrace) {
		Part1 part1 = new Part1(filename, wordlist);
		Part1Solution part1Solution = part1.solve();
		List<Assignment> solutions = part1Solution.getSolutions();
		
		assertEquals(solutions.size(), expectedWords.length);
		for(int i=0; i<solutions.size(); i++) {
			System.out.println(solutions.get(i).getSolution());
			assertEquals(expectedWords[i], solutions.get(i).getSolution());
		}

		String searchTrace = part1Solution.getSearchTrace();
		int numLinesOfTrace = getNumLines(searchTrace);
		
//		System.out.println(searchTrace);
		assertEquals(numLinesOfTrace, expectedLinesInTrace);
	}
	
	private int getNumLines(String searchTrace) {
		int numLinesOfTrace = 0;
		for(char c : searchTrace.toCharArray()) {
			if(c == '\n') {
				numLinesOfTrace++;
			}
		}
		return numLinesOfTrace;
	}
}
