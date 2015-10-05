package part1;

import java.util.List;

public class Part1Solution {

	private List<Assignment> solutions;
	private List<String> searchPaths;
	
	public Part1Solution(List<Assignment> solutions, List<String> searchPaths) {
		this.solutions = solutions;
		this.searchPaths = searchPaths;
	}

	public List<Assignment> getSolutions() {
		return solutions;
	}

	public String getSearchTrace() {
		StringBuilder str = new StringBuilder();
		
		for(String searchPath : searchPaths) {
			str.append(searchPath);
		}
		
		return str.toString();
	}

}
