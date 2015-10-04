package part1;

import java.util.List;
import java.util.Map;

public class Part1 {

	private Integer arraySize = null;
	private Map<String, List<Integer>> categoryLetterPositions;
	private Map<String, List<String>> wordList;
	
	public Part1(String puzzleFile, String wordListFile) {
				
		FileReader reader = new FileReader(puzzleFile, wordListFile);
		
		this.wordList = reader.getWordList();
		
		this.arraySize = reader.getSolutionSize();
		
		this.categoryLetterPositions = reader.getCategoryLetterPositions();
	
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

}
