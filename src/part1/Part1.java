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

public class Part1 {

	private Integer arraySize = null;
	private Map<String, List<Integer>> categoryLetterPositions;
	private Map<String, List<String>> wordList;
	
	public Part1() {
		
		String puzzleFile = "puzzle1.txt";
		
		this.wordList = readWordList();
		
		this.arraySize = readArraySizeFrom(puzzleFile);
		
		this.categoryLetterPositions = readCategoryLetterPositionsFrom(puzzleFile);
	}

	public int solve() {

		System.out.println("array size: " + arraySize);
		for(String category : categoryLetterPositions.keySet()) {
			System.out.print("  category: " + category);
			for(Integer position : categoryLetterPositions.get(category)) {
				System.out.print(" " + position);
			}
			System.out.print("\n");
		}
		
		for(String category : wordList.keySet()) {
			System.out.print("word category: " + category);
			for(String word : wordList.get(category)) {
				System.out.print(" " + word);
			}
			System.out.print("\n");
		}
		
		return 2;
	}
	
	private Map<String, List<String>> readWordList() {
		List<String> linesOfFile = readFile("part1-files/wordlist.txt");
		
		Map<String, List<String>> wordList = new HashMap<String, List<String>>();
		for(String line : linesOfFile) {
			String[] valuesInLine = line.split("[:,]");
			String category = null;
			List<String> words = new ArrayList<String>();
			for(String value : valuesInLine) {
				if(category == null) {
					category = value; // category is first
				}
				else {
					words.add(value.trim()); // followed by all words
				}
			}
			wordList.put(category, words);
		}
		
		return wordList;
	}

	private Integer readArraySizeFrom(String filename) {
		List<String> linesOfFile = readFile("part1-files/" + filename);
		return Integer.parseInt(linesOfFile.get(0)); // arraySize is first line of puzzle file
	}
	
	private Map<String, List<Integer>> readCategoryLetterPositionsFrom(String filename) {
		List<String> linesOfFile = readFile("part1-files/" + filename);
		
		Map<String, List<Integer>> categoryLetterPositions = new HashMap<String, List<Integer>>();
		boolean isFirstLine = true;
		for(String line : linesOfFile) {
			if(isFirstLine) {
				// do nothing, because it is the array size
				isFirstLine = false;
			}
			else {
				String[] valuesInLine = line.split("[:,]");
				String category = null;
				List<Integer> letterPositions = new ArrayList<Integer>();
				for(String value : valuesInLine) {
					if(category == null) {
						category = value; // category is first
					}
					else {
						letterPositions.add(Integer.parseInt(value.trim())); // followed by all positions of letters
					}
				}
				categoryLetterPositions.put(category, letterPositions);
			}
		}
		
		return categoryLetterPositions;
	}
	
	private List<String> readFile(String filename) {
		List<String> fileLines = new ArrayList<String>();
		Path file = Paths.get(filename);
		try (InputStream in = Files.newInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				fileLines.add(line);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		
		return fileLines;
	}

}
