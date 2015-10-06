package part1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PossibleLetters {

	private List<HashSet<String>> possibleLettersInSolution = new ArrayList<HashSet<String>>();
	
	public PossibleLetters(PuzzleInput puzzleInput, Words words) {
		// initialize list with 1 spot for every position in the solution
		for(int x=0; x<puzzleInput.getSolutionSize(); x++) {
			possibleLettersInSolution.add(new HashSet<String>());
		}
		
		for(String category : puzzleInput.getCategories()) {
			// for each category, for each spot that the category has a letter, note all possible letters
			List<Integer> positionsInSolution = puzzleInput.getLetterPositionsInSolutionFor(category);
			
			for(int i=0; i<positionsInSolution.size(); i++) {
				Set<String> lettersInPosition = words.getLettersInPositionFor(category, i);
				int positionInSolution = positionsInSolution.get(i);				
				possibleLettersInSolution.get(positionInSolution - 1).addAll(lettersInPosition);
			}
		}
	}
	
	public Set<String> get(int indexInSolution) {
		return this.possibleLettersInSolution.get(indexInSolution - 1); // 1-based!
	}
	
}
