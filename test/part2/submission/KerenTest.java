package part2.submission;

import org.junit.Test;
import part2.Part2;
import part2.Part2Solution;
import part2.PlayerType;

public class KerenTest {

    private String filename = "Keren.txt";

    @Test
    public void kerenMinimaxVsMinimax() {
        System.out.println("Keren - Minimax vs. Minimax");
        Part2Solution solution = new Part2(filename, PlayerType.MINIMAX, PlayerType.MINIMAX).play();
        System.out.println(solution);
    }

    @Test
    public void kerenAlphabetaVsAlphabeta() {
        System.out.println("Keren - Alpha-beta vs. Alpha-beta");
        Part2Solution solution = new Part2(filename, PlayerType.ALPHABETA, PlayerType.ALPHABETA).play();
        System.out.println(solution);
    }

    @Test
    public void kerenMinimaxVsAlphabeta() {
        System.out.println("Keren - Minimax vs. Alpha-beta");
        Part2Solution solution = new Part2(filename, PlayerType.MINIMAX, PlayerType.ALPHABETA).play();
        System.out.println(solution);
    }

    @Test
    public void kerenAlphabetaVsMinimax() {
        System.out.println("Keren - Alpha-beta vs. Minimax");
        Part2Solution solution = new Part2(filename, PlayerType.ALPHABETA, PlayerType.MINIMAX).play();
        System.out.println(solution);
    }
}
