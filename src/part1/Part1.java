package part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 {

	public int solve() {
		
		Integer arraySize = null;
		Map<String, List<Integer>> categoryLetterPositions = new HashMap<String, List<Integer>>();
		
		Path file = Paths.get("part1-files/puzzle1.txt");
		try (InputStream in = Files.newInputStream(file);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				if(arraySize == null) {
					arraySize = Integer.parseInt(line); // arraySize is first line of puzzle file
				}
				else {
					String[] valuesInLine = line.split("[:,]");
					String category = null;
					List<Integer> letterPositions = new ArrayList<Integer>();
					for(String value : valuesInLine) {
						if(category == null) {
							category = value; // category is first
						}
						else {
							letterPositions.add(Integer.parseInt(value.trim())); // followed by all positions of letters
						}
					}
					categoryLetterPositions.put(category, letterPositions);
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		
		System.out.println("array size: " + arraySize);
		for(String category : categoryLetterPositions.keySet()) {
			System.out.print("  category: " + category);
			for(Integer position : categoryLetterPositions.get(category)) {
				System.out.print(" " + position);
			}
			System.out.print("\n");
		}
		
		return 2;
	}
	
}
