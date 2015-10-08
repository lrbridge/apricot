package part1.assignment;

import part1.PuzzleInput;

public class LetterAssignment extends BaseAssignment {

    public LetterAssignment(int solutionSize, PuzzleInput puzzleInput) {
        super(solutionSize, puzzleInput);
    }

    @Override
    public Assignment clone() {
        LetterAssignment clone = new LetterAssignment(this.assignment.length, this.puzzleInput);

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
        Integer position = (Integer) variable;
        String letter = value;
        this.assignment[position - 1] = letter;
    }

}
