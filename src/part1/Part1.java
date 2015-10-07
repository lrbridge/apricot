package part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Part1 {

	private Words words;
	private PuzzleInput puzzleInput;
    private boolean isWordBased;

	private List<Assignment> solutions = new ArrayList<>();
	private List<SearchPath> searchPaths = new ArrayList<>();

	public Part1(String puzzleFile, String wordListFile, String wordOrLetterBased) {
		FileReader reader = new FileReader(puzzleFile, wordListFile);
		this.words = new Words(reader);
		this.puzzleInput = new PuzzleInput(reader);
        this.isWordBased = wordOrLetterBased.equals("word");
	}

	public Part1Solution solve() {

		SearchPath searchPath = new SearchPath();
		searchPath.addRoot();

        Assignment initialAssignment = new Assignment(puzzleInput.getSolutionSize(), puzzleInput);
		PossibleLetters initialPossibleLetters = new PossibleLetters(puzzleInput, words);
		backtrack(initialAssignment, initialPossibleLetters, searchPath);
		
		return new Part1Solution(this.solutions, this.searchPaths);
	}

	// letter-based assignment
	//    variables:  position in array
	//	  domains:  letters A-Z
	//	  constraints:  matches word for each category in given positions
	
	// word-based assignment TODO
    //    variables:  category to assign word
    //    domains:  words in the word list for the given category
    //    constraints:  matches word for each category in given positions

    // ONLY things that change between the two:
    //    selectUnassignedVariable & variable -> gets a category (later MRV)
    //    getOrderedDomainValues & value -> words for the category (later LRV?)
    //    propogateAssignment --> ...?
    //    need to pull all possible values stuff out of assignment...

    // also TODO In the first line of the trace file, indicate your assignment order

	private boolean backtrack(Assignment assignment, PossibleLetters possibleLetters, SearchPath searchPath) {

		if(assignment.isComplete()) {
			// to support multiple solutions, DON'T return here
			// continue searching tree to find all solutions

			solutions.add(assignment);

			searchPath.addSolution(assignment);
			searchPaths.add(searchPath);

			return true;
		}
		
		int variable = selectUnassignedVariable(possibleLetters, assignment);
System.out.println(variable);
		for(String value : getOrderedDomainValues(variable, possibleLetters)) {

			Assignment newAssignment = assignment.clone();
			newAssignment.set(variable, value);

            PossibleLetters newPossibleLetters = possibleLetters.clone();

			SearchPath newSearchPath = searchPath.clone();
			newSearchPath.add(value);

			boolean isSolution = false;
			
			if(isConsistent(newAssignment)) {

                // Perform inferences by propagating assignment changes through TODO
                boolean isStillConsistent = newPossibleLetters.propagateAssignment(variable, value);

				if(isStillConsistent) {

                    // if it's still consistent after inferences, recurse deeper
				    isSolution = backtrack(newAssignment, newPossibleLetters, newSearchPath);
				
				}

                // if it's not consistent, than don't bother searching deeper in path
			
			}
			
			// Because we cloned the assignments (and possible values) above, we
            // don't need to revert the assignments/inferences here because it is
            // take care of in the cloning (we just ditch the clone and roll back to the previous instance)
					
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

	private Set<String> getOrderedDomainValues(int indexInSolution, PossibleLetters possibleLetters) {
		// TODO not ordered... what does "least constrained" value mean in this case?
        //      I don't know if there is any easy way to compute this... every assignment dramatically changes
        //      the possible values of every other space.  Maybe we can assume inferences take care of this?
		return possibleLetters.getAllPossibleLetters(indexInSolution);
    }

	private int selectUnassignedVariable(PossibleLetters possibleLetters, Assignment assignment) {
		// letter-based assignment first, variable = position in array
		// TODO - word-based assignment will change this

		// MRV heuristic - choose variable with fewest remaining values
		return possibleLetters.getUnassignedPositionWithFewestRemainingLetters(assignment);
	}

}