package part2.move;

public abstract class BaseMove implements Move {

    public int row;
    public int col;

    public BaseMove(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Move{" +
                "type=" + this.getClass().getCanonicalName() +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}
