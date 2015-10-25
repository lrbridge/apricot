package part2.submission;

import org.junit.Test;
import part2.Part2;
import part2.Part2Solution;
import part2.PlayerType;

public class WesterplatteTest {

    private String filename = "Westerplatte.txt";

    @Test
    public void westerplatteMinimaxVsMinimax() {
        System.out.println("Westerplatte - Minimax vs. Minimax");
        Part2Solution solution = new Part2(filename, PlayerType.MINIMAX, PlayerType.MINIMAX).play();
        System.out.println(solution);
    }

    @Test
    public void westerplatteAlphabetaVsAlphabeta() {
        System.out.println("Westerplatte - Alpha-beta vs. Alpha-beta");
        Part2Solution solution = new Part2(filename, PlayerType.ALPHABETA, PlayerType.ALPHABETA).play();
        System.out.println(solution);
    }

    @Test
    public void westerplatteMinimaxVsAlphabeta() {
        System.out.println("Westerplatte - Minimax vs. Alpha-beta");
        Part2Solution solution = new Part2(filename, PlayerType.MINIMAX, PlayerType.ALPHABETA).play();
        System.out.println(solution);
    }

    @Test
    public void westerplatteAlphabetaVsMinimax() {
        System.out.println("Westerplatte - Alpha-beta vs. Minimax");
        Part2Solution solution = new Part2(filename, PlayerType.ALPHABETA, PlayerType.MINIMAX).play();
        System.out.println(solution);
    }
}
