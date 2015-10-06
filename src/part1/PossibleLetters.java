package part1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PossibleLetters {

	private List<HashSet<String>> possibleLettersInSolution = new ArrayList<HashSet<String>>();
	
	public PossibleLetters(PuzzleInput puzzleInput, Words words) {	
		for(int x=0; x<puzzleInput.getSolutionSize(); x++) {
 			possibleLettersInSolution.add(null);
 		}
		
		for(String category : puzzleInput.getCategories()) {
			// for each category, for each spot that the category has a letter, look at all possible letters
			List<Integer> positionsInSolution = puzzleInput.getLetterPositionsInSolutionFor(category);
			
			for(int i=0; i<positionsInSolution.size(); i++) {
				Set<String> lettersInPosition = words.getLettersInPositionFor(category, i);
				int positionInSolution = positionsInSolution.get(i);	
				
				// initialize possibleLettersInSolution and add all the letters from the first category
				if(possibleLettersInSolution.get(positionInSolution - 1) == null) {
					possibleLettersInSolution.set(positionInSolution - 1, new HashSet<String>());
					possibleLettersInSolution.get(positionInSolution - 1).addAll(lettersInPosition);
				}
				else {
					// then for other categories, remove letters that aren't in it
					List<String> lettersToRemove = new ArrayList<String>();
					for(String letter : possibleLettersInSolution.get(positionInSolution - 1)) {
						if(!lettersInPosition.contains(letter)) {
							lettersToRemove.add(letter); // no concurrent modification
						}
					}
					for(String letter : lettersToRemove) {
						possibleLettersInSolution.get(positionInSolution - 1).remove(letter);
					}
				}
			}
		}
	}
	
	public Set<String> get(int indexInSolution) {
		return this.possibleLettersInSolution.get(indexInSolution - 1); // 1-based!
	}
	
}
