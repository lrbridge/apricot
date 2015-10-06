package part1;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PuzzleInput {

	private Integer solutionSize = null;
	private Map<String, List<Integer>> letterPositionsPerCategory;
	
	public PuzzleInput(FileReader reader) {
		this.solutionSize = reader.getSolutionSize();	
		this.letterPositionsPerCategory = reader.getCategoryLetterPositions();
	}
	
	public int getSolutionSize() {
		return this.solutionSize;
	}
	
	public Set<String> getCategories() {
		return this.letterPositionsPerCategory.keySet();
	}
	
	public List<Integer> getLetterPositionsInSolutionFor(String category) {
		return this.letterPositionsPerCategory.get(category);
	}
	
}
