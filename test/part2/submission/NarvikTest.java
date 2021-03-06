package part2.submission;

import org.junit.Test;
import part2.Part2;
import part2.Part2Solution;
import part2.PlayerType;

public class NarvikTest {

    private String filename = "Narvik.txt";

    @Test
    public void narvikMinimaxVsMinimax() {
        System.out.println("Narvik - Minimax vs. Minimax");
        Part2Solution solution = new Part2(filename, PlayerType.MINIMAX, PlayerType.MINIMAX).play();
        System.out.println(solution);
    }

    @Test
    public void narvikAlphabetaVsAlphabeta() {
        System.out.println("Narvik - Alpha-beta vs. Alpha-beta");
        Part2Solution solution = new Part2(filename, PlayerType.ALPHABETA, PlayerType.ALPHABETA).play();
        System.out.println(solution);
    }

    @Test
    public void narvikMinimaxVsAlphabeta() {
        System.out.println("Narvik - Minimax vs. Alpha-beta");
        Part2Solution solution = new Part2(filename, PlayerType.MINIMAX, PlayerType.ALPHABETA).play();
        System.out.println(solution);
    }

    @Test
    public void narvikAlphabetaVsMinimax() {
        System.out.println("Narvik - Alpha-beta vs. Minimax");
        Part2Solution solution = new Part2(filename, PlayerType.ALPHABETA, PlayerType.MINIMAX).play();
        System.out.println(solution);
    }
}
