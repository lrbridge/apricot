package part1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PossibleLetters {

	private List<HashSet<String>> possibleLettersInSolution = new ArrayList<>();
	
	public PossibleLetters(PuzzleInput puzzleInput, Words words) {

        initializePossibleLettersForAllPositionsToNull(puzzleInput);

		for(String category : puzzleInput.getCategories()) {
			// for each category, for each spot that the category has a letter, look at all possible letters
			List<Integer> positionsInSolution = puzzleInput.getLetterPositionsInSolutionFor(category);
			
			for(int i=0; i<positionsInSolution.size(); i++) {
				Set<String> lettersInPosition = words.getLettersInPositionFor(category, i);
				int positionInSolution = positionsInSolution.get(i);	
				
				if(isFirstTimeAddingLettersToPosition(positionInSolution)) {
                    addAllLetters(positionInSolution, lettersInPosition);
				}
				else {
					removeLettersNotInPosition(positionInSolution, lettersInPosition);
				}
			}
		}
	}

    private void initializePossibleLettersForAllPositionsToNull(PuzzleInput puzzleInput) {
        for(int i=0; i<puzzleInput.getSolutionSize(); i++) {
            possibleLettersInSolution.add(null);
        }
    }

    private boolean isFirstTimeAddingLettersToPosition(int position) {
        return this.possibleLettersInSolution.get(position - 1) == null;
    }

    private void addAllLetters(int position, Set<String> lettersInPosition) {
        possibleLettersInSolution.set(position - 1, new HashSet<String>());
        possibleLettersInSolution.get(position - 1).addAll(lettersInPosition);
    }

    private void removeLettersNotInPosition(int position, Set<String> lettersInPosition) {
        List<String> lettersToRemove = new ArrayList<>();
        for(String letter : possibleLettersInSolution.get(position - 1)) {
            if(!lettersInPosition.contains(letter)) {
                lettersToRemove.add(letter); // no concurrent modification
            }
        }
        for(String letter : lettersToRemove) {
            possibleLettersInSolution.get(position - 1).remove(letter);
        }
    }

    // private constructor that is only used by clone method
    private PossibleLetters(List<HashSet<String>> oldPossibleLettersInSolution) {

        // clone possible letters
        for(HashSet<String> lettersInGivenPosition : oldPossibleLettersInSolution) {
            HashSet<String> cloneLettersInGivenPosition = new HashSet<>();
            for(String letter : lettersInGivenPosition) {
                cloneLettersInGivenPosition.add(letter);
            }
            possibleLettersInSolution.add(cloneLettersInGivenPosition);
        }
	}

	public Set<String> get(int indexInSolution) {
		return this.possibleLettersInSolution.get(indexInSolution - 1); // 1-based!
	}

	@Override
	protected PossibleLetters clone() {
		return new PossibleLetters(possibleLettersInSolution);
	}
}
