package part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Part1 {

	private Words words;
	private PuzzleInput puzzleInput;
	private PossibleLetters possibleLetters;
	
	private List<Assignment> solutions = new ArrayList<>();
	private List<SearchPath> searchPaths = new ArrayList<>();

	public Part1(String puzzleFile, String wordListFile) {
		FileReader reader = new FileReader(puzzleFile, wordListFile);
		this.words = new Words(reader);
		this.puzzleInput = new PuzzleInput(reader);
		this.possibleLetters = new PossibleLetters(this.puzzleInput, this.words);
	}

	public Part1Solution solve() {

		SearchPath searchPath = new SearchPath();
		searchPath.addRoot();

		backtrack(new Assignment(this.puzzleInput.getSolutionSize()), searchPath);
		
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

		for(String value : getOrderedDomainValues(variable)) {

			Assignment newAssignment = assignment.clone();
			newAssignment.set(variable, value);

			SearchPath newSearchPath = searchPath.clone();
			newSearchPath.add(value);

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
		
		for(String category : this.puzzleInput.getCategories()) {
				
			// if there is a category where NO words match, then this is
			// not a consistent assignment
			List<Integer> letterPositions = this.puzzleInput.getLetterPositionsInSolutionFor(category);
			if(!this.words.hasAPossibleMatch(category, assignment, letterPositions)) {
				return false; 
			}
			
		}
		
		// if all categories still have words that could match, this is consistent
		return true;
	}

	private Set<String> getOrderedDomainValues(int indexInSolution) {
		return this.possibleLetters.get(indexInSolution);
	}

	private int selectUnassignedVariable(Assignment assignment) {
		// letter-based assignment first, variable = position in array
		// TODO - word-based assignment will change this

		// MRV heuristic - choose variable with fewest remaining values
		return this.possibleLetters.getUnassignedPositionWithFewestRemainingLetters(assignment);
	}

}
