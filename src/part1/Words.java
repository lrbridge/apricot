package part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Words {

	private Map<String, List<String>> wordList;
	
	private List<List<String>> possibleValuesEachSpot = new ArrayList<>();

	public Words(Map<String, List<String>> wordList) {
		this.wordList = wordList;
	}
	
	public boolean hasAPossibleMatch(String category, Assignment assignment, List<Integer> letterPositions) {
		
		for(String word : wordList.get(category)) {
			if(isPossibleMatch(word, assignment, letterPositions)) {
				return true;
			}
		}
		
		// if no words in the category could match
		return false;
	}

	private boolean isPossibleMatch(String possibleWordMatch, Assignment assignment, List<Integer> letterPositions) {
				
		int index = 0;
		
		if(possibleWordMatch.length() != letterPositions.size()) {
			return false; // can't be match if not the same length of word
		}
		
		while(index < possibleWordMatch.length()) {
			
			int position = letterPositions.get(index);
			String letter = assignment.get(position-1);

			if(letter != null && !letter.equals(possibleWordMatch.substring(index, index+1))) {
				// if the letter is assigned a value and it does not match the
				// character in the word we are comparing, it is not a match
				return false;
			}
			
			index++;
		}
		
		// if no conflicting letters, then it could be a match
		return true;
	}
	
}
