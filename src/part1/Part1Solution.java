package part1;

import part1.assignment.Assignment;

import java.util.List;
import java.util.Set;

public class Part1Solution {

    private Set<Assignment> solutions;
    private List<SearchPath> searchPaths;
    private String assignmentOrdering;

    public Part1Solution(Set<Assignment> solutions, List<SearchPath> searchPaths, String assignmentOrdering) {
        this.solutions = solutions;
        this.searchPaths = searchPaths;
        this.assignmentOrdering = assignmentOrdering;
    }

    public Set<Assignment> getSolutions() {
        return solutions;
    }

    public String getSearchTrace() {

        StringBuilder str = new StringBuilder();
        SearchPath previousSearchTrace = null;

        str.append("search order: ");
        str.append(assignmentOrdering);
        str.append("\n");

        for (SearchPath searchPath : searchPaths) {

            String prettySearchPath = searchPath.prettyPrint(previousSearchTrace);

            str.append(prettySearchPath);

            previousSearchTrace = searchPath;
        }

        return str.toString();
    }

}
