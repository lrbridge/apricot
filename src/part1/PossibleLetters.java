package part1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PossibleLetters {

	private List<HashSet<String>> possibleLettersInSolution = new ArrayList<>();

    private PuzzleInput puzzleInput;
    private Words words;

	public PossibleLetters(PuzzleInput puzzleInput, Words words) {

        this.puzzleInput = puzzleInput;
        this.words = words;

        initializePossibleLettersForAllPositionsToNull();

		for(String category : puzzleInput.getCategories()) {
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

    private void initializePossibleLettersForAllPositionsToNull() {
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

    public void removeLettersNotInPosition(int position, Set<String> lettersInPosition) {
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

    public void propogateChangeForward(int positionAssigned, String letterAssigned, String category) {

        // ex: already got rid of O from AO
        // had word CAT and DOG
        // now, want to get rid of D and G because O is gone

        // so, again, get all words for the given category and the OTHER positions (start fresh for them kinda)
        //      then, if position letter does not match in a word, remove that letter

        List<Integer> positionsInSolution = puzzleInput.getLetterPositionsInSolutionFor(category);

        int indexJustAssigned = -1;
        for(int i=0; i<positionsInSolution.size(); i++) {
            if(positionsInSolution.get(i).intValue() == positionAssigned) {
                indexJustAssigned = i;
            }
        }

        for(int index=0; index<positionsInSolution.size(); index++) { // for each spot position in the solution

            if(index != indexJustAssigned) { // for all the other positions (besides where we just assigned)
                int positionInSolution = positionsInSolution.get(index);

                Set<String> lettersInPosition = words.getLettersInPositionForGiven(category, index, indexJustAssigned, letterAssigned);

                removeLettersNotInPosition(positionInSolution, lettersInPosition);

            }

        }

    }

    // private constructor that is only used by clone method
    private PossibleLetters(List<HashSet<String>> oldPossibleLettersInSolution, PuzzleInput puzzleInput, Words words) {

        this.puzzleInput = puzzleInput;
        this.words = words;

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
		return new PossibleLetters(possibleLettersInSolution, puzzleInput, words);
	}

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Possible letters: \n");
        int i=0;
        for(HashSet<String> x : possibleLettersInSolution) {
            str.append(" " + i);
            for(String y : x) {
                str.append(y);
            }
            i++;
            str.append("\n");
        }
        return str.toString();
    }
}
