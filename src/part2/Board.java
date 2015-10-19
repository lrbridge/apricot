package part2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {

	private int[][] board;
	
	private int numNodesExpanded;
//	private int numMoves;  // TODO need to do these... at a player-level per piazza
//	private int totalTimeMs;
	
	public Board(String filename) {
		board = readBoard("part2-files/" + filename);
	}

	private int[][] readBoard(String filename) {
		// ugly data structure just used temporarily for reading in the board
		List<String[]> crudeBoard = new ArrayList<String[]>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
			String str;
			while ((str = br.readLine()) != null) {
				crudeBoard.add(str.split("\t"));
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
		} catch (IOException e) {
			System.out.println("File not Found");
		}		
			
		int[][] dataBoard = new int[crudeBoard.size()][crudeBoard.get(0).length];

		for (int i = 0; i < crudeBoard.size(); i++) {
			for (int j = 0; j < crudeBoard.get(0).length; j++) {
				dataBoard[i][j] = Integer.parseInt(crudeBoard.get(i)[j]);
			}
		}
		return dataBoard;
	}
	
	private Part2Solution moveGreen(Part2Solution possibleSolution) {
		
		if(possibleSolution.isDone()) {
			return possibleSolution;
		}
		
		// if is impossible, newPossibility will be null
		Part2Solution maxGreen = null;
		
		Part2Solution newPossibility = possibleSolution.clone().commandoParaDropGreen(0,0);
		if(newPossibility != null) {
			maxGreen = moveBlue(newPossibility);
		}
//		
//		newPossibility = possibleSolution.clone().moveMin(0,1);
//		if(newPossibility != null) {
//			PossibleSolution option2 = maxMove(newPossibility);
//			if(minOption == null) {
//				minOption = option2;
//			}
//			else if(option2.getMaxScore() < minOption.getMaxScore()) {
//				minOption = option2;
//			}
//		}
//		
//		newPossibility = possibleSolution.clone().moveMin(1,0);
//		if(newPossibility != null) {
//			PossibleSolution option3 = maxMove(newPossibility);
//			if(minOption == null) {
//				minOption = option3;
//			}
//			else if(option3.getMaxScore() < minOption.getMaxScore()) {
//				minOption = option3;
//			}
//		}
//		
//		newPossibility = possibleSolution.clone().moveMin(1,1);
//		if(newPossibility != null) {
//			PossibleSolution option4 = maxMove(newPossibility);
//			if(minOption == null) {
//				minOption = option4;
//			}
//			else if(option4.getMaxScore() < minOption.getMaxScore()) {
//				minOption = option4;
//			}
//		}
		
		return maxGreen;
		
	}
	
	private Part2Solution moveBlue(Part2Solution possibleSolution) {
		
		if(possibleSolution.isDone()) {
			return possibleSolution;
		}
		
		// if is impossible, newPossibility will be null
		Part2Solution maxBlue = null;
		
		Part2Solution newPossibility = possibleSolution.clone().commandoParaDropBlue(0,0);
		if(newPossibility != null) {
			numNodesExpanded++;
			maxBlue = moveGreen(newPossibility);
		}
		
//		newPossibility = possibleSolution.clone().moveMax(0,1);
//		if(newPossibility != null) {
//			PossibleSolution option2 = minMove(newPossibility);
//			if(maxOption == null) {
//				maxOption = option2;
//			}
//			else if(option2.getMaxScore() > maxOption.getMaxScore()) {
//				maxOption = option2;
//			}
//		}
//		
//		newPossibility = possibleSolution.clone().moveMax(1,0);
//		if(newPossibility != null) {
//			PossibleSolution option3 = minMove(newPossibility);
//			if(maxOption == null) {
//				maxOption = option3;
//			}
//			else if(option3.getMaxScore() > maxOption.getMaxScore()) {
//				maxOption = option3;
//			}
//		}
//		
//		newPossibility = possibleSolution.clone().moveMax(1,1);
//		if(newPossibility != null) {
//			PossibleSolution option4 = minMove(newPossibility);
//			if(maxOption == null) {
//				maxOption = option4;
//			}
//			else if(option4.getMaxScore() > maxOption.getMaxScore()) {
//				maxOption = option4;
//			}
//		}

		return maxBlue;
	}

	public Part2Solution solve() {

		// TODO: depth-limited search
		// 		for now, we're just searching the entire tree since it's small
				
		// BLUE gets first move
		
		// TODO: add 2nd action, for now, can only commando paro drop
		
		// can commando paro drop in any of the squares
	
		Part2Solution initialSolution = new Part2Solution(board);
		
		Part2Solution solution = moveBlue(initialSolution);
		
//		solution.setFinalResults(numNodesExpanded, -1, -1); // TODO need avg num nodes/move & avg time/move
		return solution;
	}
	
}
