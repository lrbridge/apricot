package part2;

import java.util.List;

public class MinimaxAgent implements Agent {

	private Board board;
    private MinimaxPossibleSolution stateSoFar;

	private int numNodesExpanded = 0;
//	private int timeMs; TODO

    @Override
    public void updateBoard(String agentLetter, Move move) {
        stateSoFar.makeMove(agentLetter, move);
    }

    @Override
	public void setBoard(Board board) {
		this.board = board;
        this.stateSoFar = new MinimaxPossibleSolution(board);
	}
	
	@Override
	public Move pickBestMove() {		
		MinimaxPossibleSolution initialSolution = stateSoFar.clone();
		
		MinimaxPossibleSolution solution = moveBlue(initialSolution);

		Move bestMove = solution.getMove();
		System.out.println("BEST MOVE:" + bestMove);
		return bestMove;
	}

	private MinimaxPossibleSolution moveBlue(MinimaxPossibleSolution possibleSolution) {
			
		if(possibleSolution.isDone()) {
			return possibleSolution;
		}
		
		MinimaxPossibleSolution maxBlue = null;

		List<Move> possibleMoves = possibleSolution.getPossibleMoves();

		for(Move possibleMove : possibleMoves) {
			MinimaxPossibleSolution newPossibility = possibleSolution.clone();
            System.out.println("... B plays " + possibleMove.row + " " + possibleMove.col);
            newPossibility.makeMove("B", possibleMove); // apply this move
            newPossibility = moveGreen(newPossibility); // then DFS
            numNodesExpanded++;

			if(maxBlue == null) {
				maxBlue = newPossibility;
			}
			else {

				int maxMoveBlueWinsBy = maxBlue.getBlueScore() - maxBlue.getGreenScore();
				int possibleMoveBlueWinsBy = newPossibility.getBlueScore() - newPossibility.getGreenScore();

				if(maxMoveBlueWinsBy < possibleMoveBlueWinsBy) {
					maxBlue = newPossibility;
				}

			}

		}

        return maxBlue;
	}
	
	private MinimaxPossibleSolution moveGreen(MinimaxPossibleSolution possibleSolution) {
			
		if(possibleSolution.isDone()) {
			return possibleSolution;
		}
		
		MinimaxPossibleSolution maxGreen = null;

        List<Move> possibleMoves = possibleSolution.getPossibleMoves();

        for(Move possibleMove : possibleMoves) {
            MinimaxPossibleSolution newPossibility = possibleSolution.clone();
            newPossibility.makeMove("G", possibleMove); // apply this move
            System.out.println("... G plays " + possibleMove.row + " " + possibleMove.col);
            newPossibility = moveBlue(newPossibility); // then DFS
            numNodesExpanded++;

            if(maxGreen == null) {
                maxGreen = newPossibility;
            }
            else {

                int maxMoveGreenWinsBy = maxGreen.getGreenScore() - maxGreen.getBlueScore();
                int possibleMoveGreenWinsBy = newPossibility.getGreenScore() - newPossibility.getBlueScore();

                if(maxMoveGreenWinsBy < possibleMoveGreenWinsBy) {
                    maxGreen = newPossibility;
                }

            }

        }
		
		return maxGreen;
		
	}

	public int getNumNodesExpanded() {
		return numNodesExpanded;
	}


	//private Part2Solution moveGreen(Part2Solution possibleSolution) {
	//	
//		if(possibleSolution.isDone()) {
//			return possibleSolution;
//		}
	//	
//		// if is impossible, newPossibility will be null
//		Part2Solution maxGreen = null;
	//	
//		Part2Solution newPossibility = possibleSolution.clone().commandoParaDropGreen(0,0);
//		if(newPossibility != null) {
//			maxGreen = moveBlue(newPossibility);
//		}
	////	
////		newPossibility = possibleSolution.clone().moveMin(0,1);
////		if(newPossibility != null) {
////			PossibleSolution option2 = maxMove(newPossibility);
////			if(minOption == null) {
////				minOption = option2;
////			}
////			else if(option2.getMaxScore() < minOption.getMaxScore()) {
////				minOption = option2;
////			}
////		}
	////	
////		newPossibility = possibleSolution.clone().moveMin(1,0);
////		if(newPossibility != null) {
////			PossibleSolution option3 = maxMove(newPossibility);
////			if(minOption == null) {
////				minOption = option3;
////			}
////			else if(option3.getMaxScore() < minOption.getMaxScore()) {
////				minOption = option3;
////			}
////		}
	////	
////		newPossibility = possibleSolution.clone().moveMin(1,1);
////		if(newPossibility != null) {
////			PossibleSolution option4 = maxMove(newPossibility);
////			if(minOption == null) {
////				minOption = option4;
////			}
////			else if(option4.getMaxScore() < minOption.getMaxScore()) {
////				minOption = option4;
////			}
////		}
	//	
//		return maxGreen;
	//	
	//}
	//
	//private Part2Solution moveBlue(Part2Solution possibleSolution) {
	//	
//		if(possibleSolution.isDone()) {
//			return possibleSolution;
//		}
	//	
//		// if is impossible, newPossibility will be null
//		Part2Solution maxBlue = null;
	//	
//		Part2Solution newPossibility = possibleSolution.clone().commandoParaDropBlue(0,0);
//		if(newPossibility != null) {
//			numNodesExpanded++;
//			maxBlue = moveGreen(newPossibility);
//		}
	//	
////		newPossibility = possibleSolution.clone().moveMax(0,1);
////		if(newPossibility != null) {
////			PossibleSolution option2 = minMove(newPossibility);
////			if(maxOption == null) {
////				maxOption = option2;
////			}
////			else if(option2.getMaxScore() > maxOption.getMaxScore()) {
////				maxOption = option2;
////			}
////		}
	////	
////		newPossibility = possibleSolution.clone().moveMax(1,0);
////		if(newPossibility != null) {
////			PossibleSolution option3 = minMove(newPossibility);
////			if(maxOption == null) {
////				maxOption = option3;
////			}
////			else if(option3.getMaxScore() > maxOption.getMaxScore()) {
////				maxOption = option3;
////			}
////		}
	////	
////		newPossibility = possibleSolution.clone().moveMax(1,1);
////		if(newPossibility != null) {
////			PossibleSolution option4 = minMove(newPossibility);
////			if(maxOption == null) {
////				maxOption = option4;
////			}
////			else if(option4.getMaxScore() > maxOption.getMaxScore()) {
////				maxOption = option4;
////			}
////		}
	//
//		return maxBlue;
	//}
	//
	//public Part2Solution solve() {
	//
//		// TODO: depth-limited search
//		// 		for now, we're just searching the entire tree since it's small
//				
//		// BLUE gets first move
	//	
//		// TODO: add 2nd action, for now, can only commando paro drop
	//	
//		// can commando paro drop in any of the squares
	//
//		Part2Solution initialSolution = new Part2Solution(board);
	//	
//		
	//	
////		solution.setFinalResults(numNodesExpanded, -1, -1); // TODO need avg num nodes/move & avg time/move
//		return solution;
	//}
	//private int numNodesExpanded;
	////private int numMoves;  // TODO need to do these... at a player-level per piazza
	////private int totalTimeMs;
	//
	
}
