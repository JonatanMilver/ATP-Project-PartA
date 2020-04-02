package algorithms.mazeGenerators;

/**
 * An abstract class that implements IMazeGenerator.
 * Other classes can extend this class and make various types of mazes.
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long startTime = System.currentTimeMillis();
        generate(rows, columns);
        long endTime = System.currentTimeMillis();
        return endTime-startTime;
    }
}
