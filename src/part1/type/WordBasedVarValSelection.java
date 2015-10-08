package part1.type;

import part1.PuzzleInput;
import part1.Words;
import part1.assignment.Assignment;

import java.util.*;

/*
   word-based assignment
      variables:  category to assign word
	  domains:  words in the word list for the given category
	  constraints:  matches word for each category in given positions
*/
public class WordBasedVarValSelection implements VariableValueSelection {

    private PuzzleInput puzzleInput;
    private Words words;

    private HashMap<String, HashSet<String>> possibleWordsForEachCategory = new HashMap<>();

    public WordBasedVarValSelection(PuzzleInput puzzleInput, Words words) {
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
    private WordBasedVarValSelection(HashMap<String, HashSet<String>> oldPossibleWordsForEachCategory, PuzzleInput puzzleInput, Words words) {

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
    public VariableValueSelection clone() {
        return new WordBasedVarValSelection(this.possibleWordsForEachCategory, this.puzzleInput, this.words);
    }

    @Override
    public boolean propagateAssignment(Object variableAssigned, String valueAssigned, Assignment assignment) {

        String categoryAssigned = (String) variableAssigned;

        updatePossibleWordsForRecentAssignment(valueAssigned, categoryAssigned);

        // propagate changes forward to other unassigned categories that were affected
        List<Integer> affectedPositions = this.puzzleInput.getLetterPositionsInSolutionFor(categoryAssigned);
        for(Integer affectedPosition : affectedPositions) {
            for(String affectedCategory : this.puzzleInput.getCategoriesWithPosition(affectedPosition)) {
                if(isUnassigned(affectedCategory, assignment)) { // for unassigned categories that were affected

                    List<Integer> letterPositionsInSolution = this.puzzleInput.getLetterPositionsInSolutionFor(affectedCategory);

                    Set<String> wordsThatCouldMatch = this.words.getWordsThatCouldMatch(affectedCategory, assignment, letterPositionsInSolution);

                    if(wordsThatCouldMatch.size() == 0) {
                        // if we ever remove all possible words, then quit early - inconsistent assignment!
                        return false;
                    }

                    removeWordsNotInCategory(affectedCategory, wordsThatCouldMatch);
                }
            }
        }
        return true;
    }

    private void updatePossibleWordsForRecentAssignment(String valueAssigned, String categoryAssigned) {
        // update possible words for assignment that just happened
        HashSet<String> valuesAssigned = new HashSet<>();
        valuesAssigned.add(valueAssigned);
        this.removeWordsNotInCategory(categoryAssigned, valuesAssigned);
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
    public Set<String> getOrderedDomainValues(Object variable, VariableValueSelection assignmentType) {
        // return just the possible words for each category at this time
        // these are not ordered currently, because to order we would have to return in order the
        //      words that rule out fewest choices for neighboring vars... which seems hard to compute

        String category = (String) variable;
        return this.possibleWordsForEachCategory.get(category);
    }

    @Override
    public Object selectUnassignedVariable(VariableValueSelection assignmentType, Assignment assignment) {

        // MRV heuristic - choose variable (category) with minimum remaining values

        String categoryWithFewestRemainingValues = null;

        for (String category : this.puzzleInput.getCategories()) {

            if(isUnassigned(category, assignment)) {

                if(categoryWithFewestRemainingValues == null) {
                    categoryWithFewestRemainingValues = category;
                }
                else {
                    int bestCategoryNumValues = this.possibleWordsForEachCategory.get(categoryWithFewestRemainingValues).size();
                    int categoryNumValues = this.possibleWordsForEachCategory.get(category).size();
                    if(categoryNumValues < bestCategoryNumValues) {
                        categoryWithFewestRemainingValues = category;
                    }
                }
            }
        }

        return categoryWithFewestRemainingValues;
    }

    private boolean isUnassigned(String category, Assignment assignment) {

        for (Integer position : this.puzzleInput.getLetterPositionsInSolutionFor(category)) {
            if (assignment.get(position) == null) {
                return true; // unassigned if any of the positions of that word in the assignment are null
            }
        }
        return false;
    }
}
