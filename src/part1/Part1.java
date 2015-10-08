package part1;

import part1.part1.assignment.BaseAssignment;
import part1.part1.assignment.LetterAssignment;
import part1.part1.assignment.WordAssignment;
import part1.part1.type.AssignmentType;
import part1.part1.type.PossibleLetters;
import part1.part1.type.PossibleWords;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part1 {

    private Words words;
    private PuzzleInput puzzleInput;
    private boolean isWordBased;

    private Set<BaseAssignment> solutions = new HashSet<>();
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

        BaseAssignment initialAssignment;
        AssignmentType initialAssignmentType;
        if (isWordBased) {
            initialAssignment = new WordAssignment(puzzleInput.getSolutionSize(), puzzleInput);
            initialAssignmentType = new PossibleWords(puzzleInput, words);
        } else {
            initialAssignment = new LetterAssignment(puzzleInput.getSolutionSize(), puzzleInput);
            initialAssignmentType = new PossibleLetters(puzzleInput, words);
        }

        backtrack(initialAssignment, initialAssignmentType, searchPath);

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

    private boolean backtrack(BaseAssignment assignment, AssignmentType assignmentType, SearchPath searchPath) {

        if (assignment.isComplete()) {
            // to support multiple solutions, DON'T return here
            // continue searching tree to find all solutions

            solutions.add(assignment);

            searchPath.addSolution(assignment);
            searchPaths.add(searchPath);

            return true;
        }

        Object variable = assignmentType.selectUnassignedVariable(assignmentType, assignment);

        for (String value : assignmentType.getOrderedDomainValues(variable, assignmentType)) {

            BaseAssignment newAssignment = assignment.clone();
            newAssignment.set(variable, value);

            AssignmentType newAssignmentType = assignmentType.clone();

            SearchPath newSearchPath = searchPath.clone();
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

            // Because we cloned the assignments (and possible values) above, we
            // don't need to revert the assignments/inferences here because it is
            // take care of in the cloning (we just ditch the clone and roll back to the previous instance)

            if (!isSolution) {
                newSearchPath.addBacktrack();

                searchPaths.add(newSearchPath);
            }

        }

        return false;
    }

    private boolean isConsistent(BaseAssignment assignment) {

        for (String category : this.puzzleInput.getCategories()) {

            // if there is a category where NO words match, then this is
            // not a consistent assignment
            List<Integer> letterPositions = this.puzzleInput.getLetterPositionsInSolutionFor(category);
            if (this.words.getWordsThatCouldMatch(category, assignment, letterPositions).size() == 0) {
                return false;
            }

        }

        // if all categories still have words that could match, this is consistent
        return true;
    }

}