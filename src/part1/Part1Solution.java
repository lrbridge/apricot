package part1;

import part1.part1.assignment.BaseAssignment;
import part1.part1.assignment.LetterAssignment;

import java.util.List;
import java.util.Set;

public class Part1Solution {

	private Set<BaseAssignment> solutions;
	private List<SearchPath> searchPaths;
	
	public Part1Solution(Set<BaseAssignment> solutions, List<SearchPath> searchPaths) {
		this.solutions = solutions;
		this.searchPaths = searchPaths;
	}

	public Set<BaseAssignment> getSolutions() {
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
