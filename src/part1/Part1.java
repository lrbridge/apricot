package part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Part1 {

	private Integer solutionSize = null;
	private Map<String, List<Integer>> categoryLetterPositions;
	private Map<String, List<String>> wordList;
	
	private List<Assignment> solutions = new ArrayList<>();
	
	private List<SearchPath> searchPaths = new ArrayList<>();

	public Part1(String puzzleFile, String wordListFile) {
				
		FileReader reader = new FileReader(puzzleFile, wordListFile);
		
		this.wordList = reader.getWordList();
		
		this.solutionSize = reader.getSolutionSize();
		
		this.categoryLetterPositions = reader.getCategoryLetterPositions();
	}

	public Part1Solution solve() {

		SearchPath searchPath = new SearchPath();
		searchPath.addRoot();

		backtrack(new Assignment(this.solutionSize), searchPath);
		
		return new Part1Solution(this.solutions, this.searchPaths);
	}

	// letter-based assignment
	//    variables:  position in array
	//	  domains:  letters A-Z
	//	  constraints:  matches word/part of word for each category/thing
	
	// word-based assignment TODO
	
	private boolean backtrack(Assignment assignment, SearchPath searchPath) {

		if(assignment.isComplete()) {
			// to support multiple solutions, DON'T return here
			// continue searching tree to find all solutions
			solutions.add(assignment);

			searchPath.addSolution(assignment);

			searchPaths.add(searchPath);

			return true;
		}
		
		int variable = selectUnassignedVariable(assignment);
		
		for(char value : getOrderedDomainValues()) {
			
			Assignment newAssignment = assignment.clone();
			newAssignment.set(variable, String.valueOf(value));

			SearchPath newSearchPath = searchPath.clone();
			newSearchPath.add(Character.toString(value));

			boolean isSolution = false;
			
			if(isConsistent(newAssignment)) {
				
				// TODO do inference checking here?
				// if(inferences != failure) {
				
				isSolution = backtrack(newAssignment, newSearchPath);
				
				// }
			
			}
			
			// drop var=value from assignment (done due to cloning above)
			// TODO? remove inferences from assignment
					
			if(!isSolution) {
				newSearchPath.addBacktrack();

				searchPaths.add(newSearchPath);
			}

		}
		
		return false;
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
			String letter = assignment.get(position-1);

			if(letter != null && !letter.equals(possibleWordMatch.substring(index, index+1))) {
				// if the letter is assigned a value and it does not match the
				// character in the word we are comparing, it is not a match
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
		int position = 0; // positions are 1-based
		while(position < this.solutionSize) {
			if(assignment.get(position) == null) {
				return position;
			}
			position++;
		}
		return -1;
	}

}
