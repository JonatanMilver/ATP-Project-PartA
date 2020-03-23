package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {


    @Override
    public Maze generate(int rows, int columns) {
        Position start = new Position(0,0);
        Position end = new Position(rows-1,columns-1);
        return new Maze(rows, columns,start,end);
    }
}
