package part1;

import java.util.List;

public class Part1Solution {

	private List<Assignment> solutions;
	private List<SearchPath> searchPaths;
	
	public Part1Solution(List<Assignment> solutions, List<SearchPath> searchPaths) {
		this.solutions = solutions;
		this.searchPaths = searchPaths;
	}

	public List<Assignment> getSolutions() {
		return solutions;
	}

	public String getSearchTrace() {

		StringBuilder str = new StringBuilder();
		SearchPath previousSearchTrace = null;

		for(SearchPath searchPath : searchPaths) {

			String prettySearchPath = searchPath.prettyPrint(previousSearchTrace);

			str.append(prettySearchPath);

			previousSearchTrace = searchPath;
		}
		
		return str.toString();
	}

}
