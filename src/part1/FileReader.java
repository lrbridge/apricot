package part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReader {

    List<String> wordListFileLines;
    List<String> puzzleFileLines;

    public FileReader(String puzzleFilename, String wordListFilename) {
        this.wordListFileLines = readFile("part1-files/" + wordListFilename);
        this.puzzleFileLines = readFile("part1-files/" + puzzleFilename);
    }

    public Map<String, List<String>> getWordList() {
        Map<String, List<String>> wordList = new HashMap<>();

        for (String line : wordListFileLines) {
            String[] valuesInLine = line.split("[:,]");
            String category = null;
            List<String> words = new ArrayList<>();
            for (String value : valuesInLine) {
                if (category == null) {
                    category = value; // category is first
                } else {
                    words.add(value.trim()); // followed by all words
                }
            }
            wordList.put(category, words);
        }

        return wordList;
    }

    public Integer getSolutionSize() {
        // solution size is first line of puzzle file
        return Integer.parseInt(puzzleFileLines.get(0));
    }

    public Map<String, List<Integer>> getCategoryLetterPositions() {
        Map<String, List<Integer>> categoryLetterPositions = new HashMap<String, List<Integer>>();
        boolean isFirstLine = true;
        for (String line : puzzleFileLines) {
            if (isFirstLine) {
                // do nothing, because it is the array size
                isFirstLine = false;
            } else {
                String[] valuesInLine = line.split("[:,]");
                String category = null;
                List<Integer> letterPositions = new ArrayList<>();
                for (String value : valuesInLine) {
                    if (category == null) {
                        category = value; // category is first
                    } else {
                        letterPositions.add(Integer.parseInt(value.trim())); // followed by all positions of letters
                    }
                }
                categoryLetterPositions.put(category, letterPositions);
            }
        }

        return categoryLetterPositions;
    }

    private List<String> readFile(String filename) {
        List<String> fileLines = new ArrayList<>();
        Path file = Paths.get(filename);
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        return fileLines;
    }

}
