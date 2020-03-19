package algorithms.mazeGenerators;

public interface IMazeGenerator {
    public Maze generate(int rows, int columns);
    public long measureAlgorithmsTimeMillis(int rows, int columns);
}
