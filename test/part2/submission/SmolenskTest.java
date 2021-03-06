package part2.submission;

import org.junit.Test;
import part2.*;

public class SmolenskTest {

    private String filename = "Smolensk.txt";

    @Test
    public void smolenskMinimaxVsMinimax() {
        System.out.println("Smolensk - Minimax vs. Minimax");
        Part2Solution solution = new Part2(filename, PlayerType.MINIMAX, PlayerType.MINIMAX).play();
        System.out.println(solution);
    }

    @Test
    public void smolenskAlphabetaVsAlphabeta() {
        System.out.println("Smolensk - Alpha-beta vs. Alpha-beta");
        Part2Solution solution = new Part2(filename, PlayerType.ALPHABETA, PlayerType.ALPHABETA).play();
        System.out.println(solution);
    }

    @Test
    public void smolenskMinimaxVsAlphabeta() {
        System.out.println("Smolensk - Minimax vs. Alpha-beta");
        Part2Solution solution = new Part2(filename, PlayerType.MINIMAX, PlayerType.ALPHABETA).play();
        System.out.println(solution);
    }

    @Test
    public void smolenskAlphabetaVsMinimax() {
        System.out.println("Smolensk - Alpha-beta vs. Minimax");
        Part2Solution solution = new Part2(filename, PlayerType.ALPHABETA, PlayerType.MINIMAX).play();
        System.out.println(solution);
    }
}
