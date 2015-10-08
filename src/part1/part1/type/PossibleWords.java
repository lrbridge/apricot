package part1.part1.type;

import part1.PuzzleInput;
import part1.Words;
import part1.part1.assignment.BaseAssignment;

import java.util.*;

/*
   word-based assignment
      variables:  category to assign word
	  domains:  words in the word list for the given category
	  constraints:  matches word for each category in given positions
*/
public class PossibleWords implements AssignmentType {

    private PuzzleInput puzzleInput;
    private Words words;

    private HashMap<String, HashSet<String>> possibleWordsForEachCategory = new HashMap<>();

    public PossibleWords(PuzzleInput puzzleInput, Words words) {
        this.puzzleInput = puzzleInput;
        this.words = words;

        initializePossibleWordsForEachCategory();
    }

    private void initializePossibleWordsForEachCategory() {

        for(String category : this.puzzleInput.getCategories()) {
            possibleWordsForEachCategory.put(category, new HashSet<>(this.words.getWordsForCategory(category)));
        }

    }

    // private constructor that is only used by clone method
    private PossibleWords(HashMap<String, HashSet<String>> oldPossibleWordsForEachCategory, PuzzleInput puzzleInput, Words words) {

        this.puzzleInput = puzzleInput;
        this.words = words;

        // clone possible letters
        for (String category : oldPossibleWordsForEachCategory.keySet()) {
            HashSet<String> cloneWordsForCategory = new HashSet<>();
            for(String word : oldPossibleWordsForEachCategory.get(category)) {
                cloneWordsForCategory.add(word);
            }
            this.possibleWordsForEachCategory.put(category, cloneWordsForCategory);
        }
    }

    @Override
    public AssignmentType clone() {

        return new PossibleWords(this.possibleWordsForEachCategory, this.puzzleInput, this.words);
    }

    @Override
    public boolean propagateAssignment(Object variableAssigned, String valueAssigned, BaseAssignment assignment) {

        String categoryAssigned = (String) variableAssigned;

        // update possible words for assignment that just happened
        HashSet<String> valuesAssigned = new HashSet<>();
        valuesAssigned.add(valueAssigned);
        this.removeWordsNotInCategory(categoryAssigned, valuesAssigned);

        // propogate changes forward to other unassigned categories (TODO just affected?)

        for(String category : this.puzzleInput.getCategories()) {

            if(isUnassigned(category, assignment)) {

                List<Integer> letterPositionsInSolution = this.puzzleInput.getLetterPositionsInSolutionFor(category);

                Set<String> wordsThatCouldMatch = this.words.getWordsThatCouldMatch(category, assignment, letterPositionsInSolution);

                removeWordsNotInCategory(category, wordsThatCouldMatch);
            }

        }

        return true;
    }

    private void removeWordsNotInCategory(String category, Set<String> wordsThatCouldMatch) {
        List<String> wordsToRemove = new ArrayList<>();
        for (String word : possibleWordsForEachCategory.get(category)) {
            if (!wordsThatCouldMatch.contains(word)) {
                wordsToRemove.add(word); // no concurrent modification
            }
        }
        for (String letter : wordsToRemove) {
            possibleWordsForEachCategory.get(category).remove(letter);
        }
    }

    @Override
    public Set<String> getOrderedDomainValues(Object variable, AssignmentType assignmentType) {

        // TODO not ordered... but at least a smaller & smaller list as we go

        String category = (String) variable;

        return this.possibleWordsForEachCategory.get(category);
    }

    @Override
    public Object selectUnassignedVariable(AssignmentType assignmentType, BaseAssignment assignment) {

        // TODO make this better... for now, just first unassigned category we run across
        for (String category : this.puzzleInput.getCategories()) {

            if(isUnassigned(category, assignment)) {
                return category;
            }

        }

        return "";

    }

    private boolean isUnassigned(String category, BaseAssignment assignment) {
        List<Integer> positionsInSolution = this.puzzleInput.getLetterPositionsInSolutionFor(category);

        for (Integer position : positionsInSolution) {

            if (assignment.get(position) == null) {
                return true;
            }

        }

        return false;
    }
}
