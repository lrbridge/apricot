package part2.submission;

import org.junit.Test;
import part2.Part2;
import part2.Part2Solution;
import part2.PlayerType;

public class SevastopolTest {

    private String filename = "Sevastopol.txt";

    @Test
    public void sevastopolMinimaxVsMinimax() {
        System.out.println("Sevastopol - Minimax vs. Minimax");
        Part2Solution solution = new Part2(filename, PlayerType.MINIMAX, PlayerType.MINIMAX).play();
        System.out.println(solution);
    }

    @Test
    public void sevastopolAlphabetaVsAlphabeta() {
        System.out.println("Sevastopol - Alpha-beta vs. Alpha-beta");
        Part2Solution solution = new Part2(filename, PlayerType.ALPHABETA, PlayerType.ALPHABETA).play();
        System.out.println(solution);
    }

    @Test
    public void sevastopolMinimaxVsAlphabeta() {
        System.out.println("Sevastopol - Minimax vs. Alpha-beta");
        Part2Solution solution = new Part2(filename, PlayerType.MINIMAX, PlayerType.ALPHABETA).play();
        System.out.println(solution);
    }

    @Test
    public void sevastopolAlphabetaVsMinimax() {
        System.out.println("Sevastopol - Alpha-beta vs. Minimax");
        Part2Solution solution = new Part2(filename, PlayerType.ALPHABETA, PlayerType.MINIMAX).play();
        System.out.println(solution);
    }
}
