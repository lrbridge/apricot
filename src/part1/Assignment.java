package part1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Assignment {

	private String[] assignment;
	private PossibleLetters possibleLetters;
    private PuzzleInput puzzleInput;

	public Assignment(int solutionSize, PossibleLetters possibleLetters, PuzzleInput puzzleInput) {
		this.assignment = new String[solutionSize];
		this.possibleLetters = possibleLetters;
        this.puzzleInput = puzzleInput;

//        System.out.println("NEW ASSIGNMENT" + possibleLetters.toString());
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
		PossibleLetters clonedPossibleLetters = this.possibleLetters.clone();
		Assignment clone = new Assignment(this.assignment.length, clonedPossibleLetters, this.puzzleInput);
		
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

    public boolean propagateAssignment(int position, String letter) {

//        System.out.println(position + " & " + letter);
//
//        // for each unassigned variable (position) that is connected to that assignment by constraint (by the assignment)
//        //      aka, for all categories that involve that position
//        //              go through other positions for that category & remove inconsistent values
//
//        //              if any get to down to 0 return false
//        //              if any down to 1, assign & propogate assignment again
//
//        //              if done... return true  (WHAT IS DONE??)
//
//
        // remove all letters except the one assigned for that spot
        HashSet<String> letterAssigned = new HashSet<>();
        letterAssigned.add(letter);
        this.possibleLetters.removeLettersNotInPosition(position, letterAssigned);

        List<String> categoriesAffectedByAssignment = this.puzzleInput.getCategoriesWithPosition(position);

        for(String category : categoriesAffectedByAssignment) {
            possibleLetters.propogateChangeForward(position, letter, category);
        }

//        System.out.println(possibleLetters.toString());

        // TODO return false / see if anything else can be assigned / etc...?

        return true;
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
