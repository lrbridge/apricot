package part1.type;

import part1.PuzzleInput;
import part1.Words;
import part1.assignment.Assignment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
   letter-based assignment
      variables:  position in array
	  domains:  letters A-Z
	  constraints:  matches word for each category in given positions
*/
public class LetterBasedVarValSelection implements VariableValueSelection {

    private PuzzleInput puzzleInput;
    private Words words;

    private List<HashSet<String>> possibleLettersInSolution = new ArrayList<>();

    public LetterBasedVarValSelection(PuzzleInput puzzleInput, Words words) {
        this.puzzleInput = puzzleInput;
        this.words = words;

        initializePossibleLettersWithCommonLettersForAllCategories();
    }

    public boolean propagateAssignment(Object variableAssigned, String letterAssigned, Assignment assignment) {

        Integer positionAssigned = (Integer) variableAssigned;

        updatePossibleLettersForRecentAssignment(letterAssigned, positionAssigned);


        // propagate changes forward to other unassigned positions that were affected

        List<String> affectedCategories = this.puzzleInput.getCategoriesWithPosition(positionAssigned);
        for (String affectedCategory : affectedCategories) {

            List<Integer> positionsInSolution = puzzleInput.getLetterPositionsInSolutionFor(affectedCategory);
            for (int index = 0; index < positionsInSolution.size(); index++) { // for each position in the solution

                int positionInSolution = positionsInSolution.get(index);
                if (isUnassigned(positionInSolution, assignment)) { // for unassigned positions

                    // compute the index into the word, for comparing in word
                    int indexJustAssigned = getIndexJustAssigned(positionAssigned, positionsInSolution);

                    Set<String> lettersInPosition = words.getLettersInPositionForGiven(affectedCategory, index, indexJustAssigned, letterAssigned);

                    if(lettersInPosition.size() == 0) {
                        // if we ever remove all possible letters in a position, then quit early - inconsistent assignment!
                        return false;
                    }

                    removeLettersNotInPosition(positionInSolution, lettersInPosition);
                }
            }
        }
        return true;
    }

    private boolean isUnassigned(int positionInSolution, Assignment assignment) {
        return assignment.get(positionInSolution) == null;
    }

    private int getIndexJustAssigned(Integer positionAssigned, List<Integer> positionsInSolution) {
        int indexJustAssigned = -1;
        for (int i = 0; i < positionsInSolution.size(); i++) {
            if (positionsInSolution.get(i).intValue() == positionAssigned) {
                indexJustAssigned = i;
            }
        }
        return indexJustAssigned;
    }

    private void updatePossibleLettersForRecentAssignment(String letter, Integer position) {
        HashSet<String> letterAssigned = new HashSet<>();
        letterAssigned.add(letter);
        this.removeLettersNotInPosition(position, letterAssigned);
    }

    public Set<String> getOrderedDomainValues(Object variable, VariableValueSelection assignmentType) {
        // return just the possible letters for each position at this time
        // these are not ordered currently, because to order we would have to return in order the
        //      letter assignments that rule out fewest choices for neighboring vars... which seems hard to compute

        Integer indexInSolution = (Integer) variable;
        return this.get(indexInSolution);
    }

    public Object selectUnassignedVariable(VariableValueSelection assignmentType, Assignment assignment) {

        // MRV heuristic - choose variable (position) with minimum remaining values (letters)

        Integer unassignedPositionWithFewestRemainingLetters = null;

        for (int position = 1; position <= this.possibleLettersInSolution.size(); position++) {

            if (isUnassigned(position, assignment)) {

                if (unassignedPositionWithFewestRemainingLetters == null) {
                    unassignedPositionWithFewestRemainingLetters = position;
                }
                else {
                    int currentPositionLettersRemaining = this.get(position).size();
                    int lowestSoFarLettersRemaining = this.get(unassignedPositionWithFewestRemainingLetters).size();
                    if (currentPositionLettersRemaining < lowestSoFarLettersRemaining) {
                        unassignedPositionWithFewestRemainingLetters = position; // take the lower number of letters remaining
                    }
                }
            }
        }
        return unassignedPositionWithFewestRemainingLetters;
    }

    private void initializePossibleLettersWithCommonLettersForAllCategories() {
        for (int i = 0; i < this.puzzleInput.getSolutionSize(); i++) {
            possibleLettersInSolution.add(null);
        }

        for (String category : this.puzzleInput.getCategories()) {
            List<Integer> positionsInSolution = this.puzzleInput.getLetterPositionsInSolutionFor(category);

            for (int indexInWord = 0; indexInWord < positionsInSolution.size(); indexInWord++) {
                adjustPossibleLettersFor(category, positionsInSolution, indexInWord);
            }
        }
    }

    private void adjustPossibleLettersFor(String category, List<Integer> positionsInSolution, int indexInWord) {
        Set<String> lettersInPosition = this.words.getLettersInPositionFor(category, indexInWord);
        int positionInSolution = positionsInSolution.get(indexInWord);

        if (isFirstTimeAddingLettersToPosition(positionInSolution)) {
            addAllLetters(positionInSolution, lettersInPosition);
        } else {
            removeLettersNotInPosition(positionInSolution, lettersInPosition);
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
        for (String letter : possibleLettersInSolution.get(position - 1)) {
            if (!lettersInPosition.contains(letter)) {
                lettersToRemove.add(letter); // no concurrent modification
            }
        }
        for (String letter : lettersToRemove) {
            possibleLettersInSolution.get(position - 1).remove(letter);
        }
    }

    // private constructor that is only used by clone method
    private LetterBasedVarValSelection(List<HashSet<String>> oldPossibleLettersInSolution, PuzzleInput puzzleInput, Words words) {

        this.puzzleInput = puzzleInput;
        this.words = words;

        // clone possible letters
        for (HashSet<String> lettersInGivenPosition : oldPossibleLettersInSolution) {
            HashSet<String> cloneLettersInGivenPosition = new HashSet<>();
            for (String letter : lettersInGivenPosition) {
                cloneLettersInGivenPosition.add(letter);
            }
            possibleLettersInSolution.add(cloneLettersInGivenPosition);
        }
    }

    @Override
    public LetterBasedVarValSelection clone() {
        return new LetterBasedVarValSelection(possibleLettersInSolution, puzzleInput, words);
    }

    private Set<String> get(int indexInSolution) {
        return this.possibleLettersInSolution.get(indexInSolution - 1); // 1-based!
    }
}
