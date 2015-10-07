package part1.part1.type;

import part1.PuzzleInput;
import part1.Words;
import part1.part1.assignment.BaseAssignment;

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
public class PossibleLetters implements AssignmentType {

    private List<HashSet<String>> possibleLettersInSolution = new ArrayList<>();
    private PuzzleInput puzzleInput;
    private Words words;

    public PossibleLetters(PuzzleInput puzzleInput, Words words) {

        this.puzzleInput = puzzleInput;
        this.words = words;

        initializePossibleLettersForAllPositionsToNull();

        for (String category : puzzleInput.getCategories()) {
            List<Integer> positionsInSolution = puzzleInput.getLetterPositionsInSolutionFor(category);

            for (int i = 0; i < positionsInSolution.size(); i++) {
                Set<String> lettersInPosition = words.getLettersInPositionFor(category, i);
                int positionInSolution = positionsInSolution.get(i);

                if (isFirstTimeAddingLettersToPosition(positionInSolution)) {
                    addAllLetters(positionInSolution, lettersInPosition);
                } else {
                    removeLettersNotInPosition(positionInSolution, lettersInPosition);
                }
            }
        }
    }

    public boolean propagateAssignment(Object variable, String letter) {

        Integer position = (Integer) variable;

        // remove all letters except the one assigned for that spot
        HashSet<String> letterAssigned = new HashSet<>();
        letterAssigned.add(letter);
        this.removeLettersNotInPosition(position, letterAssigned);

        List<String> categoriesAffectedByAssignment = this.puzzleInput.getCategoriesWithPosition(position);

        for (String category : categoriesAffectedByAssignment) {
            propogateChangeForward(position, letter, category);
        }

        // TODO return false / see if anything else can be assigned / etc...?

        return true;
    }

    public Set<String> getOrderedDomainValues(Object variable, AssignmentType assignmentType) {
        // TODO not ordered... what does "least constrained" value mean in this case?
        //      I don't know if there is any easy way to compute this... every assignment dramatically changes
        //      the possible values of every other space.  Maybe we can assume inferences take care of this?

        Integer indexInSolution = (Integer) variable;

        return this.get(indexInSolution);
    }

    public Object selectUnassignedVariable(AssignmentType assignmentType, BaseAssignment assignment) {
        // letter-based assignment first, variable = position in array
        // TODO - word-based assignment will change this

        // MRV heuristic - choose variable with fewest remaining values
        int unassignedPositionWithFewestRemainingLetters = -1; //error if no value left to assign

        // 1-based!
        for (int position = 1; position <= this.possibleLettersInSolution.size(); position++) {

            // if the position is unassigned
            if (assignment.get(position) == null) { //1-based

                if (unassignedPositionWithFewestRemainingLetters == -1) {
                    unassignedPositionWithFewestRemainingLetters = position;
                } else {
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

    private void initializePossibleLettersForAllPositionsToNull() {
        for (int i = 0; i < puzzleInput.getSolutionSize(); i++) {
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
        for (String letter : possibleLettersInSolution.get(position - 1)) {
            if (!lettersInPosition.contains(letter)) {
                lettersToRemove.add(letter); // no concurrent modification
            }
        }
        for (String letter : lettersToRemove) {
            possibleLettersInSolution.get(position - 1).remove(letter);
        }
    }

    private void propogateChangeForward(int positionAssigned, String letterAssigned, String category) {

        // ex: already got rid of O from AO
        // had word CAT and DOG
        // now, want to get rid of D and G because O is gone

        // so, again, get all words for the given category and the OTHER positions (start fresh for them kinda)
        //      then, if position letter does not match in a word, remove that letter

        List<Integer> positionsInSolution = puzzleInput.getLetterPositionsInSolutionFor(category);

        int indexJustAssigned = -1;
        for (int i = 0; i < positionsInSolution.size(); i++) {
            if (positionsInSolution.get(i).intValue() == positionAssigned) {
                indexJustAssigned = i;
            }
        }

        for (int index = 0; index < positionsInSolution.size(); index++) { // for each spot position in the solution

            if (index != indexJustAssigned) { // for all the other positions (besides where we just assigned)
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
        for (HashSet<String> lettersInGivenPosition : oldPossibleLettersInSolution) {
            HashSet<String> cloneLettersInGivenPosition = new HashSet<>();
            for (String letter : lettersInGivenPosition) {
                cloneLettersInGivenPosition.add(letter);
            }
            possibleLettersInSolution.add(cloneLettersInGivenPosition);
        }
    }

    @Override
    public PossibleLetters clone() {
        return new PossibleLetters(possibleLettersInSolution, puzzleInput, words);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Possible letters: \n");
        int i = 0;
        for (HashSet<String> x : possibleLettersInSolution) {
            str.append(" " + i);
            for (String y : x) {
                str.append(y);
            }
            i++;
            str.append("\n");
        }
        return str.toString();
    }

    private Set<String> get(int indexInSolution) {
        return this.possibleLettersInSolution.get(indexInSolution - 1); // 1-based!
    }
}
