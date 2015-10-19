package part2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {
	
	/* the board to keep track of the player's occupation in the board */
	int[][] Pboard;
	/*
	 * the board to calculate the possible movement of the player's occupation
	 * in the board
	 */
	int[][] TPboard;
	/* the board to calculate the score of the player in the board */
	int[][] Sboard;
	
	int boardWidth;
	int boardHeight;
	
	public Board(String filename) {
		Sboard = readBoard("part2-files/" + filename);

		boardWidth = Sboard.length;
		boardHeight = Sboard[0].length;
		
		Pboard = new int[boardWidth][boardHeight];
		TPboard = new int[boardWidth][boardHeight];
	}

	public int[][] readBoard(String filename) {
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

	// When either player place a move, it will affect other points
	public void Pmove(Point point, int turn) {
		Pboard[point.x][point.y] = turn;
		if (oldpointP(point, turn)) {
			if (point.x + 1 < boardWidth) {
				if (Pboard[point.x + 1][point.y] != 0)
					Pboard[point.x + 1][point.y] = turn;
			}
			if (point.x - 1 >= 0) {
				if (Pboard[point.x - 1][point.y] != 0)
					Pboard[point.x - 1][point.y] = turn;
			}
			if (point.y + 1 < boardHeight) {
				if (Pboard[point.x][point.y + 1] != 0)
					Pboard[point.x][point.y + 1] = turn;
			}
			if (point.y - 1 >= 0) {
				if (Pboard[point.x][point.y - 1] != 0)
					Pboard[point.x][point.y - 1] = turn;
			}
		}
	}

	public void TPmove(Point point, int turn) {
		TPboard[point.x][point.y] = turn;
		if (oldpointTP(point, turn)) {
			if (point.x + 1 < boardWidth) {
				if (TPboard[point.x + 1][point.y] != 0)
					TPboard[point.x + 1][point.y] = turn;
			}
			if (point.x - 1 >= 0) {
				if (TPboard[point.x - 1][point.y] != 0)
					TPboard[point.x - 1][point.y] = turn;
			}
			if (point.y + 1 < boardHeight) {
				if (TPboard[point.x][point.y + 1] != 0)
					TPboard[point.x][point.y + 1] = turn;
			}
			if (point.y - 1 >= 0) {
				if (TPboard[point.x][point.y - 1] != 0)
					TPboard[point.x][point.y - 1] = turn;
			}
		}
	}

	private boolean oldpointTP(Point point, int turn) {
		int k = 0;
		if (point.x + 1 < boardWidth) {
			if (TPboard[point.x + 1][point.y] == turn)
				k++;
		}
		if (point.x - 1 >= 0) {
			if (TPboard[point.x - 1][point.y] == turn)
				k++;
		}
		if (point.y + 1 < boardHeight) {
			if (TPboard[point.x][point.y + 1] == turn)
				k++;
		}
		if (point.y - 1 >= 0) {
			if (TPboard[point.x][point.y - 1] == turn)
				k++;
		}
		if (k > 0) {
			return true;
		} else
			return false;
	}

	private boolean oldpointP(Point point, int turn) {
		int k = 0;
		if (point.x + 1 < boardWidth) {
			if (Pboard[point.x + 1][point.y] == turn)
				k++;
		}
		if (point.x - 1 >= 0) {
			if (Pboard[point.x - 1][point.y] == turn)
				k++;
		}
		if (point.y + 1 < boardHeight) {
			if (Pboard[point.x][point.y + 1] == turn)
				k++;
		}
		if (point.y - 1 >= 0) {
			if (Pboard[point.x][point.y - 1] == turn)
				k++;
		}
		if (k > 0) {
			return true;
		} else
			return false;
	}

	// Find the total value player1 owned
	public int checkTPvalue1() {
		int sum = 0;
		for (int i = 0; i < boardWidth; i++) {
			for (int j = 0; j < boardHeight; j++) {
				if (TPboard[i][j] == 1)
					sum += Sboard[i][j];
			}
		}
		return sum;
	}

	public int checkTPvalue2() {
		int sum = 0;
		for (int i = 0; i < boardWidth; i++) {
			for (int j = 0; j < boardHeight; j++) {
				if (TPboard[i][j] == 2)
					sum += Sboard[i][j];
			}
		}
		return sum;
	}

	public List<Point> getAvailableStatesTP() {
		List<Point> availablePoints = new ArrayList<>();
		for (int i = 0; i < boardWidth; ++i) {
			for (int j = 0; j < boardHeight; ++j) {
				if (TPboard[i][j] == 0) {
					availablePoints.add(new Point(i, j));
				}
			}
		}
		return availablePoints;
	}

	public List<Point> getAvailableStatesP() {
		List<Point> availablePoints = new ArrayList<>();
		for (int i = 0; i < boardWidth; ++i) {
			for (int j = 0; j < boardHeight; ++j) {
				if (Pboard[i][j] == 0) {
					availablePoints.add(new Point(i, j));
				}
			}
		}
		return availablePoints;
	}

	Point nextmove = new Point(0, 0);

	public boolean ifEnd() {
		List<Point> point = getAvailableStatesP();
		return point.isEmpty();
	}

	public int minimax_max(int depth, int turn) {

		int score = 0;
		if (turn == 1) {
			score = checkTPvalue1();
		} else if (turn == 2)
			score = checkTPvalue2();

		List<Point> pointsAvailable = getAvailableStatesTP();
		if (pointsAvailable.isEmpty())
			return score;

		if (depth == 4)
			return score;

		int max = -1;

		int[][] restore = new int[boardWidth][boardHeight];
		for (int a = 0; a < boardWidth; a++) {
			for (int b = 0; b < boardHeight; b++) {
				restore[a][b] = TPboard[a][b];
			}
		}
		if (turn == 1) {

			for (int i = 0; i < pointsAvailable.size(); i++) {
				Point point = pointsAvailable.get(i);

				for (int a = 0; a < boardWidth; a++) {
					for (int b = 0; b < boardHeight; b++) {
						TPboard[a][b] = restore[a][b];
					}
				}

				TPmove(point, 1);

				int currentScore = minimax_min(depth + 1, 2);
				if (currentScore > max) {
					max = currentScore;
					nextmove = point;
				}
			}
			if (depth == 0) {
				Pmove(nextmove, 1);
				System.out.println("PPPPPPP");
				for (int o = 0; o < boardWidth; o++) {
					for (int j = 0; j < boardHeight; j++) {
						System.out.print(Pboard[o][j] + " ");
					}
					System.out.println("");
				}
				System.out.println("");
			}
		}

		if (turn == 2) {
			for (int i = 0; i < pointsAvailable.size(); i++) {
				Point point = pointsAvailable.get(i);
				for (int a = 0; a < boardWidth; a++) {
					for (int b = 0; b < boardHeight; b++) {
						TPboard[a][b] = restore[a][b];
					}
				}
				TPmove(point, 2);

				int currentScore = minimax_min(depth + 1, 1);
				if (currentScore > max) {
					max = currentScore;
					nextmove = point;
				}
			}
			if (depth == 0) {
				Pmove(nextmove, 2);
				System.out.println("PPPPPPP");
				for (int o = 0; o < boardWidth; o++) {
					for (int j = 0; j < boardHeight; j++) {
						System.out.print(Pboard[o][j] + " ");
					}
					System.out.println("");
				}
				System.out.println("");

			}
		}

		return max;
	}

	public int minimax_min(int depth, int turn) {
		int score = 0;
		if (turn == 2) {
			score = checkTPvalue1();
		} else if (turn == 1)
			score = checkTPvalue2();

		List<Point> pointsAvailable = getAvailableStatesTP();
		if (pointsAvailable.isEmpty())
			return score;
		// if (depth==3) return score;
		int min = Integer.MAX_VALUE;

		int[][] restore = new int[boardWidth][boardHeight];
		for (int a = 0; a < boardWidth; a++) {
			for (int b = 0; b < boardHeight; b++) {
				restore[a][b] = TPboard[a][b];
			}
		}
		if (turn == 1) {
			for (int i = 0; i < pointsAvailable.size(); i++) {
				Point point = pointsAvailable.get(i);
				for (int a = 0; a < boardWidth; a++) {
					for (int b = 0; b < boardHeight; b++) {
						TPboard[a][b] = restore[a][b];
					}
				}
				TPmove(point, 1);
				// System.out.println("Player1 turn");
				// for (int o=0;o<boardWidth;o++){
				// for (int j=0;j<boardHeight;j++){
				// System.out.print(TPboard[o][j]+" ");
				// }
				// System.out.println("");
				// }
				// System.out.println("");

				int currentScore = minimax_max(depth + 1, 2);
				if (currentScore < min) {
					min = currentScore;
				}
			}
		}
		if (turn == 2) {
			for (int i = 0; i < pointsAvailable.size(); i++) {
				Point point = pointsAvailable.get(i);
				for (int a = 0; a < boardWidth; a++) {
					for (int b = 0; b < boardHeight; b++) {
						TPboard[a][b] = restore[a][b];
					}
				}
				TPmove(point, 2);
				// System.out.println("Player2 turn");
				// for (int o=0;o<boardWidth;o++){
				// for (int j=0;j<boardHeight;j++){
				// System.out.print(TPboard[o][j]+" ");
				// }
				// System.out.println("");
				// }
				// System.out.println("");

				int currentScore = minimax_max(depth + 1, 1);
				if (currentScore < min) {
					min = currentScore;
				}
			}
		}
		return min;

	}

	public int[][] solve() {
		while (!ifEnd()) {
			minimax_max(0, 2);
			for (int a = 0; a < boardWidth; a++) {
				for (int c = 0; c < boardHeight; c++) {
					TPboard[a][c] = Pboard[a][c];
				}
			}
			minimax_max(0, 1);
			for (int a = 0; a < boardWidth; a++) {
				for (int c = 0; c < boardHeight; c++) {
					TPboard[a][c] = Pboard[a][c];
				}
			}
			/*
			 * for (int o=0;o<6;o++){ for (int j=0;j<6;j++){
			 * System.out.print(Pboard[o][j]+" "); } System.out.println(""); }
			 * System.out.println("");
			 */
		}
		System.out.println("DONE!");
		return Pboard;
	}
}
