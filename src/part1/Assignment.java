package part1;

public class Assignment {

	public String[] assignment;

	public Assignment(Integer arraySize) {
		this.assignment = new String[arraySize];
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
		Assignment clone = new Assignment(this.assignment.length);
		
		int position = 0;
		for(String letter : this.assignment) {
			if(letter != null) {
				clone.set(position, letter);
			}
			position++;
		}
		
		return clone;
	}

	public void set(int position, String letter) {
		this.assignment[position] = letter;
	}
	
}
