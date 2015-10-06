package part1;

public class Assignment {

	private String[] assignment;

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
	
}
