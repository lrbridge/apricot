package part2;

public class Move {

    public enum MOVE_TYPE {COMMANDO_PARA_DROP}

    ; // TODO add M1_DEATH_BLITZ

    public MOVE_TYPE type;
    public int row;
    public int col;

    public Move(MOVE_TYPE type, int row, int col) {
        this.type = type;
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Move{" +
                "type=" + type +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}
