package part2;

public enum Color {
    BLUE, GREEN;

    private static Color[] colors = values();
    public Color next()
    {
        return colors[(this.ordinal()+1) % colors.length];
    }
}
