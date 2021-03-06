package part2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {

    /** The basic board with all the scores.  This should not be changed during game play **/
    private Integer[][] board;

    public Board(String filename) {
        board = readBoard("part2-files/" + filename);
    }

    private Integer[][] readBoard(String filename) {
        // ugly data structure just used temporarily for reading in the board
        List<String[]> crudeBoard = new ArrayList<>();

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filename));
            String str;
            while ((str = br.readLine()) != null) {
                crudeBoard.add(str.split("\t"));
            }
            br.close();
        } catch (Exception e) {
            System.out.println("exception");
        }

        Integer[][] dataBoard = new Integer[crudeBoard.size()][crudeBoard.get(0).length];

        for (int i = 0; i < crudeBoard.size(); i++) {
            for (int j = 0; j < crudeBoard.get(0).length; j++) {
                dataBoard[i][j] = Integer.parseInt(crudeBoard.get(i)[j]);
            }
        }
        return dataBoard;
    }

    public int getWidth() {
        return board.length;
    }

    public int getHeight() {
        return board[0].length;
    }

    public int get(int row, int col) {
        return board[row][col];
    }
}
