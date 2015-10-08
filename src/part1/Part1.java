package part1;

import part1.part1.assignment.Assignment;
import part1.part1.assignment.LetterAssignment;
import part1.part1.assignment.WordAssignment;
import part1.part1.type.VariableValueSelection;
import part1.part1.type.LetterBasedVarValSelection;
import part1.part1.type.WordBasedVarValSelection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part1 {

    private Words words;
    private PuzzleInput puzzleInput;
    private boolean isWordBased;

    private Set<Assignment> solutions = new HashSet<>();
    private List<SearchPath> searchPaths = new ArrayList<>();

    public Part1(String puzzleFile, String wordListFile, String wordOrLetterBased) {
        FileReader reader = new FileReader(puzzleFile, wordListFile);
        this.words = new Words(reader);
        this.puzzleInput = new PuzzleInput(reader);
        this.isWordBased = wordOrLetterBased.equals("word");
    }

    /**
     * Solve the word puzzle
     */
    public Part1Solution solve() {

        Assignment initialAssignment;
        VariableValueSelection initialVariableValueSelection;
        String assignmentOrdering;

        // Change behavior of word-based or letter-based assignment by using different implementations
        // of Assignment and VariableValueSelection
        if (isWordBased) {
            initialAssignment = new WordAssignment(puzzleInput.getSolutionSize(), puzzleInput);
            initialVariableValueSelection = new WordBasedVarValSelection(puzzleInput, words);
            assignmentOrdering = "category with fewest remaining words";
        }
        else {
            initialAssignment = new LetterAssignment(puzzleInput.getSolutionSize(), puzzleInput);
            initialVariableValueSelection = new LetterBasedVarValSelection(puzzleInput, words);
            assignmentOrdering = "position with fewest remaining letters";
        }

        // searchPath and this.searchPaths keep track of all the paths we visited/backtracking for printing out later
        SearchPath searchPath = new SearchPath();
        searchPath.addRoot();

        // start recursion to search tree for solutions
        backtrack(initialAssignment, initialVariableValueSelection, searchPath);

        // return the pretty-printed search paths, solutions, etc.
        return new Part1Solution(this.solutions, this.searchPaths, assignmentOrdering);
    }

    private boolean backtrack(Assignment assignment, VariableValueSelection variableValueSelection, SearchPath searchPath) {

        if (assignment.isComplete()) {
            // to find all solutions, DON'T stop when find a solution; search entire tree

            solutions.add(assignment);

            searchPath.addSolution(assignment);
            searchPaths.add(searchPath);

            return true;
        }

        Object variable = variableValueSelection.selectUnassignedVariable(variableValueSelection, assignment);

        for (String value : variableValueSelection.getOrderedDomainValues(variable, variableValueSelection)) {

            // clone all the recursing changing state so we don't have to bother removing nodes when roll up tree
            Assignment newAssignment = assignment.clone();
            VariableValueSelection newAssignmentType = variableValueSelection.clone();
            SearchPath newSearchPath = searchPath.clone();

            // assign the variable to the value
            newAssignment.set(variable, value);
            newSearchPath.add(value);

            boolean isSolution = false;

            if (isConsistent(newAssignment)) {

                // Perform inferences by propagating assignment changes through TODO
                boolean isStillConsistent = newAssignmentType.propagateAssignment(variable, value, newAssignment);

                if (isStillConsistent) {

                    // if it's still consistent after inferences, recurse deeper
                    isSolution = backtrack(newAssignment, newAssignmentType, newSearchPath);

                }

                // if it's not consistent, than don't bother searching deeper in path

            }

            // Because we cloned the assignments (and possible values) above, we don't need to revert the
            // assignments/inferences here (we just ditch the clone and roll back to the previous instance)

            // if we didn't find a solution, backtrack up tree
            if (!isSolution) {
                newSearchPath.addBacktrack();
                searchPaths.add(newSearchPath);
            }

        }

        return false;
    }

    private boolean isConsistent(Assignment assignment) {

        for (String category : this.puzzleInput.getCategories()) {

            // if there is a category where NO words match, then this is not a consistent assignment
            List<Integer> letterPositions = this.puzzleInput.getLetterPositionsInSolutionFor(category);
            if (this.words.getWordsThatCouldMatch(category, assignment, letterPositions).size() == 0) {
                return false;
            }

        }

        // if all categories still have words that could match, this is consistent
        return true;
    }

}