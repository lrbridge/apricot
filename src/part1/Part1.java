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
		System.out.println("---- selected variable: " + variable + "-----");
		
		for(char value : getOrderedDomainValues()) {
			
			Assignment newAssignment = assignment.clone();
			newAssignment.set(variable, String.valueOf(value));
			
			if(isConsistent(newAssignment)) {
				
				// TODO do inference checking here?
				// if(inferences != failure) {
				
				Assignment result = backtrack(newAssignment);
				
				if(result != null) {
					return result;
				}
				
				// }
			
			}
			
			// drop var=value from assignment (done due to cloning above)
			// TODO? remove inferences from assignment
		}
		
		return null; // null = failure
	}
	
	private boolean isConsistent(Assignment assignment) {
		
		for(String category : categoryLetterPositions.keySet()) {
				
			// if there is a category where NO words match, then this is
			// not a consistent assignment
			if(!hasAPossibleWordMatch(category, assignment)) {
				return false; 
			}
			
		}
		
		// if all categories still have words that could match, this is consistent
		return true;
	}

	private boolean hasAPossibleWordMatch(String category, Assignment assignment) {
		
		for(String word : wordList.get(category)) {
			System.out.println("check " + word);
			List<Integer> letterPositions = categoryLetterPositions.get(category);
			if(isPossibleMatch(word, assignment, letterPositions)) {
				return true;
			}
		}
		
		// if no words in the category could match
		return false;
	}

	private boolean isPossibleMatch(String possibleWordMatch, Assignment assignment, List<Integer> letterPositions) {
				
		int index = 0;
		
		if(possibleWordMatch.length() != letterPositions.size()) {
			return false; // can't be match if not the same length of word
		}
		
		while(index < possibleWordMatch.length()) {
			
			int position = letterPositions.get(index);
			String letter = assignment.assignment[position - 1]; // position numbers are 1-based

			if(letter != null) {
				System.out.println("  " + letter + " vs " + index + " " + possibleWordMatch.substring(index, index+1) + "(" + possibleWordMatch + ")");	
			}

			if(letter != null && !letter.equals(possibleWordMatch.substring(index, index+1))) {
				// if the letter is assigned a value and it does not match the
				// character in the word we are comparing, it is not a match
				System.out.println("FALSE");
				return false;
			}
			
			index++;
		}
		
		// if no conflicting letters, then it could be a match
		return true;
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
