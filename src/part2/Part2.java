package part2;

public class Part2 {
	
	public int[][] solve(String filename) {
		Board b = new Board(filename);
		return b.solve();
	}

	public static void main(String args[]) {
		(new Part2()).solve("Sevastopol.txt");
	}
	
}
