package part1.part1.type;

import part1.part1.assignment.BaseAssignment;
import part1.PuzzleInput;
import part1.Words;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
   word-based assignment
      variables:  category to assign word
	  domains:  words in the word list for the given category
	  constraints:  matches word for each category in given positions
*/
public class PossibleWords implements AssignmentType {

    private PuzzleInput puzzleInput;
    private Words words;

    public PossibleWords(PuzzleInput puzzleInput, Words words) {
        this.puzzleInput = puzzleInput;
        this.words = words;
    }

    @Override
    public AssignmentType clone() {

        return new PossibleWords(this.puzzleInput, this.words);
    }

    @Override
    public boolean propagateAssignment(Object variable, String value) {
        // TODO do something here, for now don't

        return true;
    }

    @Override
    public Set<String> getOrderedDomainValues(Object variable, AssignmentType assignmentType) {

        // TODO make this better... for now, just return the words for the given category

        String category = (String) variable;

        return new HashSet<>(this.words.getWordsForCategory(category));
    }

    @Override
    public Object selectUnassignedVariable(AssignmentType assignmentType, BaseAssignment assignment) {

        // TODO make this better... for now, just first unassigned category we run across
        for(String category : this.puzzleInput.getCategories()) {

            List<Integer> positionsInSolution = this.puzzleInput.getLetterPositionsInSolutionFor(category);

            for(Integer position : positionsInSolution) {

                if(assignment.get(position) == null) {
                    return category;
                }

            }

        }

        return "";

    }
}
