package part1.part1.assignment;

import part1.PuzzleInput;

public abstract class BaseAssignment {

	protected String[] assignment;
    protected PuzzleInput puzzleInput;

	public BaseAssignment(int solutionSize, PuzzleInput puzzleInput) {
		this.assignment = new String[solutionSize];
        this.puzzleInput = puzzleInput;
    }
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(String letter : assignment) {
			str.append(letter);
		}
		return str.toString();
	}

	public boolean isComplete() {
		for(String letter : assignment) {
			if(letter == null) {
				return false; // if any letter is null
			}
		}
		return true; // if all letters are assigned
	}

	@Override
	public abstract BaseAssignment clone();

	public abstract void set(Object variable, String value);

	public String get(int position) {
		return this.assignment[position-1];
	}
	
	public String getSolution() {
		return this.toString();
	}
}
