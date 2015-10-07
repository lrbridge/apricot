package part1;

import part1.part1.assignment.BaseAssignment;
import part1.part1.assignment.LetterAssignment;

import java.util.List;

public class Part1Solution {

	private List<BaseAssignment> solutions;
	private List<SearchPath> searchPaths;
	
	public Part1Solution(List<BaseAssignment> solutions, List<SearchPath> searchPaths) {
		this.solutions = solutions;
		this.searchPaths = searchPaths;
	}

	public List<BaseAssignment> getSolutions() {
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
