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
		String previousSearchTrace = null;

		for(String searchPath : searchPaths) {

			String cleanedUpSearchTrace = searchPath;

			if(previousSearchTrace != null) {
				// if there is a search trace the line above

				int diffIndex;
				for(diffIndex=0; diffIndex<previousSearchTrace.length(); diffIndex++) {
					if(!searchPath.substring(0, diffIndex).equals(previousSearchTrace.substring(0, diffIndex))) {
						break;
					}
				}

				String firstPart = searchPath.substring(0, diffIndex - 4).replaceAll(".", " ");
				String secondPart = searchPath.substring(diffIndex - 4);

				cleanedUpSearchTrace = firstPart + secondPart;
			}

			str.append(cleanedUpSearchTrace);
			previousSearchTrace = searchPath;
		}
		
		return str.toString();
	}

}
