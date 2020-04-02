package algorithms.mazeGenerators;

/**
 *An Interface of the maze generator.
 */
public interface IMazeGenerator {
    /*
     generate a maze given rows and columns.
    */
    public Maze generate(int rows, int columns);
    /*
    Find out how long does it take to generate a maze given rows and columns.
     */
    public long measureAlgorithmTimeMillis(int rows, int columns);
}
