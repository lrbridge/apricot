package part1.part1.assignment;

public interface Assignment {
    
    boolean isComplete();

    String get(int position);

    void set(Object variable, String value);

    Assignment clone();

}
