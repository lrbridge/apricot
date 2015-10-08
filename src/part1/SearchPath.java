package part1;

import part1.part1.assignment.Assignment;

import java.util.ArrayList;
import java.util.List;

public class SearchPath {

    List<String> path = new ArrayList<>();

    public void add(String value) {
        path.add(value);
    }

    public void addRoot() {
        path.add("root");
    }

    public void addBacktrack() {
        path.add("backtrack");
    }

    public void addSolution(Assignment assignment) {
        path.add(" (found result: " + assignment.toString() + ")");
    }

    @Override
    public SearchPath clone() {
        SearchPath clone = new SearchPath();
        for (String item : path) {
            clone.add(item);
        }
        return clone;
    }

    public String prettyPrint(SearchPath previousSearchPath) {

        StringBuilder prettySearchPath = new StringBuilder();

        boolean isSameAsPrevious = true;

        for (int i = 0; i < path.size(); i++) {

            String item = path.get(i);

            StringBuilder stringToAppend = new StringBuilder();

            if (!item.startsWith(" (found result") && !item.equals("root")) {
                stringToAppend.append(" -> ");
            }

            stringToAppend.append(item);

            if (item.startsWith(" (found result") || item.equals("backtrack")) {
                stringToAppend.append("\n");
            }

            if (previousSearchPath != null && isSameAsPrevious) {
                if (previousSearchPath.path.size() > i && previousSearchPath.path.get(i).equals(item)) {
                    // if it's the same as the previous search path item, then blank it out
                    for (int j = 0; j < stringToAppend.length(); j++) {
                        stringToAppend.replace(j, j + 1, " ");
                    }
                } else {
                    isSameAsPrevious = false;
                }
            }

            prettySearchPath.append(stringToAppend.toString());
        }

        String testIfOnlyBacktrack = prettySearchPath.toString();
        if (testIfOnlyBacktrack.trim().equals("-> backtrack")) {
            return ""; // don't print search path if it is just a backtrack
        }

        return prettySearchPath.toString();
    }
}
