package part1;

import java.util.Set;

public class Assignment {

	private String[] assignment;
	private PossibleLetters possibleLetters;

	public Assignment(int solutionSize, PossibleLetters possibleLetters) {
		this.assignment = new String[solutionSize];
		this.possibleLetters = possibleLetters;
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
	protected Assignment clone() {
		Assignment clone = new Assignment(this.assignment.length, this.possibleLetters); // TODO must clone this once starts changing as go
		
		int position = 1;
		for(String letter : this.assignment) {
			if(letter != null) {
				clone.set(position, letter);
			}
			position++;
		}
		
		return clone;
	}

	// 1-based!
	public void set(int position, String letter) {
		this.assignment[position-1] = letter;
	}

	public String get(int position) {
		return this.assignment[position-1];
	}
	
	public String getSolution() {
		return this.toString();
	}

	public Set<String> getAllPossibleLetters(int indexInSolution) {
		return this.possibleLetters.get(indexInSolution);
	}

	public int getUnassignedPositionWithFewestRemainingLetters() {

		int unassignedPositionWithFewestRemainingLetters = -1; //error if no value left to assign

		// 1-based!
		for(int position = 1; position <= this.assignment.length; position++) {

			// if the position is unassigned
			if(this.get(position) == null) { //1-based

				if(unassignedPositionWithFewestRemainingLetters == -1) {
					unassignedPositionWithFewestRemainingLetters = position;
				}
				else {
					int currentPositionLettersRemaining = this.possibleLetters.get(position).size();
					int lowestSoFarLettersRemaining = this.possibleLetters.get(unassignedPositionWithFewestRemainingLetters).size();
					if(currentPositionLettersRemaining < lowestSoFarLettersRemaining) {
						unassignedPositionWithFewestRemainingLetters = position; // take the lower number of letters remaining
					}
				}
			}
		}

		return unassignedPositionWithFewestRemainingLetters;

	}
}
