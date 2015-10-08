package part1.part1.assignment;

import part1.PuzzleInput;

import java.util.Arrays;

public abstract class BaseAssignment implements Assignment {

    protected String[] assignment;
    protected PuzzleInput puzzleInput;

    public BaseAssignment(int solutionSize, PuzzleInput puzzleInput) {
        this.assignment = new String[solutionSize];
        this.puzzleInput = puzzleInput;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String letter : assignment) {
            str.append(letter);
        }
        return str.toString();
    }

    public boolean isComplete() {
        for (String letter : assignment) {
            if (letter == null) {
                return false; // if any letter is null, not a complete assignment
            }
        }
        return true; // if all letters are assigned, the assignment is complete
    }

    @Override
    public abstract Assignment clone();

    public abstract void set(Object variable, String value);

    public String get(int position) {
        return this.assignment[position - 1];
    }

    // don't want to have duplicate assignments in our list of solutions!
    // override equals to return true for an Assignment if the same letters are in the same order in the assignment
    @Override
    public boolean equals(Object o) {
        BaseAssignment otherAssignment = (BaseAssignment) o;

        for (int i = 0; i < assignment.length; i++) {
            if (!assignment[i].equals(otherAssignment.assignment[i])) {
                return false;
            }
        }

        return true;
    }

    // only overriding because you should if you override equals
    @Override
    public int hashCode() {
        int result = assignment != null ? Arrays.hashCode(assignment) : 0;
        result = 31 * result + (puzzleInput != null ? puzzleInput.hashCode() : 0);
        return result;
    }
}
