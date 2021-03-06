package part1;

import part1.assignment.Assignment;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Words {

    private Map<String, List<String>> wordList;

    public Words(FileReader reader) {
        this.wordList = reader.getWordList();
    }

    public List<String> getWordsForCategory(String category) {
        return wordList.get(category);
    }

    public Set<String> getWordsThatCouldMatch(String category, Assignment assignment, List<Integer> letterPositions) {

        Set<String> wordsThatCouldMatch = new HashSet<>();

        for (String word : wordList.get(category)) {
            if (isPossibleMatch(word, assignment, letterPositions)) {
                wordsThatCouldMatch.add(word);
            }
        }

        return wordsThatCouldMatch;
    }

    private boolean isPossibleMatch(String possibleWordMatch, Assignment assignment, List<Integer> letterPositions) {

        int index = 0;

        if (possibleWordMatch.length() != letterPositions.size()) {
            return false; // can't be match if not the same length of word
        }

        while (index < possibleWordMatch.length()) {

            int position = letterPositions.get(index);
            String letter = assignment.get(position);

            if (letter != null && !letter.equals(possibleWordMatch.substring(index, index + 1))) {
                // if the letter is assigned a value and it does not match the
                // character in the word we are comparing, it is not a match
                return false;
            }

            index++;
        }

        // if no conflicting letters, then it could be a match
        return true;
    }

    public Set<String> getLettersInPositionFor(String category, int position) {
        Set<String> letters = new HashSet<>();
        for (String word : this.wordList.get(category)) {
            letters.add(Character.toString(word.charAt(position)));
        }
        return letters;
    }

    public Set<String> getLettersInPositionForGiven(String category, int position, int positionAssigned, String letterAssigned) {
        Set<String> letters = new HashSet<>();
        for (String word : this.wordList.get(category)) {
            if (Character.toString(word.charAt(positionAssigned)).equals(letterAssigned)) {
                letters.add(Character.toString(word.charAt(position)));
            }
            // if the word doesn't have the character that was just assigned at the position that was just assigned
            // don't add any of that word's letters to the list
        }
        return letters;
    }
}
