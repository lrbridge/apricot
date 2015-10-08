package part1;

import org.junit.Test;
import part1.part1.assignment.Assignment;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Part1Test {

    @Test
    public void trivial() {
        String[] expectedWords = {"APE", "ANT", "BAD"};
        testPart1("letter", "trivial.txt", "smallwordlist.txt", expectedWords, 3);
    }

    @Test
    public void simple() {
        String[] expectedWords = {"BAID"};
        testPart1("letter", "simple.txt", "smallwordlist.txt", expectedWords, 1);
    }

    @Test
    public void sample() {
        String[] expectedWords = {"BAURANH", "BAARATH", "BAARAMH", "BAIRAEH"};
        testPart1("letter", "sample.txt", "wordlist.txt", expectedWords, 7);
    }

    @Test
    public void puzzle1() {
        String[] expectedWords = {"NNEMANDYE", "NWEMANDYE", "NNESAYDYE", "NWESAYDYE"};
        testPart1("letter", "puzzle1.txt", "wordlist.txt", expectedWords, 11);
    }

    @Test
    public void puzzle2() {
        String[] expectedWords = {"HSIAIWNCS", "HSIAIWNPS", "HSIOIWNDS", "HSIOIWNYS"};
        testPart1("letter", "puzzle2.txt", "wordlist.txt", expectedWords, 19);
    }

    @Test
    public void puzzle3() {
        String[] expectedWords = {"ASULPEA", "ASULPIE"};
        testPart1("letter", "puzzle3.txt", "wordlist.txt", expectedWords, 7);
    }

    @Test
    public void puzzle4() {
        String[] expectedWords = {"HEDITYRE", "HELITYRE", "HETITYRE"};
        testPart1("letter", "puzzle4.txt", "wordlist.txt", expectedWords, 6);
    }

    @Test
    public void puzzle5() {
        String[] expectedWords = {"IHTTNOIEN", "THTTNOIEN", "IHTTYOIEN", "THTTYOIEN"};
        testPart1("letter", "puzzle5.txt", "wordlist.txt", expectedWords, 16);
    }

    @Test
    public void trivialWord() {
        String[] expectedWords = {"APE", "ANT", "BAD"};
        testPart1("word", "trivial.txt", "smallwordlist.txt", expectedWords, 3);
    }

    @Test
    public void simpleWord() {
        String[] expectedWords = {"BAID"};
        testPart1("word", "simple.txt", "smallwordlist.txt", expectedWords, 3);
    }

    @Test
    public void sampleWord() {
        String[] expectedWords = {"BAURANH", "BAARATH", "BAARAMH", "BAIRAEH"};
        testPart1("word", "sample.txt", "wordlist.txt", expectedWords, 10);
    }

    @Test
    public void puzzle1Word() {
        String[] expectedWords = {"NNEMANDYE", "NWEMANDYE", "NNESAYDYE", "NWESAYDYE"};
        testPart1("word", "puzzle1.txt", "wordlist.txt", expectedWords, 15);
    }

    @Test
    public void puzzle2Word() {
        String[] expectedWords = {"HSIAIWNCS", "HSIAIWNPS", "HSIOIWNDS", "HSIOIWNYS"};
        testPart1("word", "puzzle2.txt", "wordlist.txt", expectedWords, 26);
    }

    @Test
    public void puzzle3Word() {
        String[] expectedWords = {"ASULPEA", "ASULPIE"};
        testPart1("word", "puzzle3.txt", "wordlist.txt", expectedWords, 22);
    }

    @Test
    public void puzzle4Word() {
        String[] expectedWords = {"HEDITYRE", "HELITYRE", "HETITYRE"};
        testPart1("word", "puzzle4.txt", "wordlist.txt", expectedWords, 11);
    }

    @Test
    public void puzzle5Word() {
        String[] expectedWords = {"IHTTNOIEN", "THTTNOIEN", "IHTTYOIEN", "THTTYOIEN"};
        testPart1("word", "puzzle5.txt", "wordlist.txt", expectedWords, 8);
    }

    private void testPart1(String wordOrLetterBased, String filename, String wordlist, String[] expectedWordsArray, int expectedLinesInTrace) {
        List<String> expectedWords = Arrays.asList(expectedWordsArray);

        Part1 part1 = new Part1(filename, wordlist, wordOrLetterBased);
        Part1Solution part1Solution = part1.solve();
        Set<Assignment> solutions = part1Solution.getSolutions();

        for (Assignment solution : solutions) {
            System.out.println(solution.toString());
            assertTrue(expectedWords.contains(solution.toString()));
        }
        assertEquals(solutions.size(), expectedWords.size());

        String searchTrace = part1Solution.getSearchTrace();
        int numLinesOfTrace = getNumLines(searchTrace);

        System.out.println(searchTrace);
        assertEquals(numLinesOfTrace, expectedLinesInTrace+1); // +1 for search order: line added to top
    }

    private int getNumLines(String searchTrace) {
        int numLinesOfTrace = 0;
        for (char c : searchTrace.toCharArray()) {
            if (c == '\n') {
                numLinesOfTrace++;
            }
        }
        return numLinesOfTrace;
    }
}
