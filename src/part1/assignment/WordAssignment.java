package part1.assignment;

import part1.PuzzleInput;

import java.util.List;

public class WordAssignment extends BaseAssignment {

    public WordAssignment(int solutionSize, PuzzleInput puzzleInput) {
        super(solutionSize, puzzleInput);
    }

    @Override
    public Assignment clone() {
        WordAssignment clone = new WordAssignment(this.assignment.length, this.puzzleInput);

        int position = 1;
        for (String letter : this.assignment) {
            if (letter != null) {
                clone.assignment[position - 1] = letter;
            }
            position++;
        }

        return clone;
    }

    public void set(Object variable, String value) {

        String category = (String) variable;

        List<Integer> positionsInSolution = this.puzzleInput.getLetterPositionsInSolutionFor(category);

        int index = 0;
        for (Integer position : positionsInSolution) {
            this.assignment[position - 1] = value.substring(index, index + 1);
            index++;
        }
    }

}
