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
	
	public Part1(String puzzleFile, String wordListFile) {
				
		this.wordList = readWordListFrom(wordListFile);
		
		this.arraySize = readArraySizeFrom(puzzleFile);
		
		this.categoryLetterPositions = readCategoryLetterPositionsFrom(puzzleFile);
	
//		debugInitialization();
	}

	public Assignment solve() {		
		return backtrack(new Assignment(this.arraySize));	
	}

	// letter-based assignment
	//    variables:  position in array
	//	  domains:  letters A-Z
	//	  constraints:  matches word/part of word for each category/thing
	
	// word-based assignment TODO
	
	private Assignment backtrack(Assignment assignment) {
		
		System.out.println("backtrack " + assignment);
		
		if(assignment.isComplete()) {
			return assignment;
		}
		
		int variable = selectUnassignedVariable(assignment);
		System.out.println("selected variable: " + variable);
		
		for(char value : getOrderedDomainValues()) {
			
			// TODO do consistent check here
			// if(consistent) {
			
			Assignment newAssignment = assignment.clone();
			newAssignment.set(variable, String.valueOf(value));
			
			// TODO do inference checking here?
			// if(inferences != failure) {
			
			Assignment result = backtrack(newAssignment);
			
			if(result != null) {
				return result;
			}
			
			// }
			
			// }
			
			// drop var=value from assignment (done due to cloning above)
			// TODO? remove inferences from assignment
		}
		
		return null; // null = failure
	}
	
	private char[] getOrderedDomainValues() {
		// TODO just returning alphabet for now... do something better here
		return "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
	}

	private int selectUnassignedVariable(Assignment assignment) {
		// letter-based assignment first, variable = position in array
		// TODO - word-based assignment will change this
		
		// TODO - for now, just picking positions in order... do something 
		// else later
		int position = 0;
		for(String letter : assignment.assignment) {
			if(letter == null) {
				return position;
			}
			position++;
		}
		return -1;
	}

	private void debugInitialization() {
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
	}

	private Map<String, List<String>> readWordListFrom(String filename) {
		List<String> linesOfFile = readFile("part1-files/" + filename);
		
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
