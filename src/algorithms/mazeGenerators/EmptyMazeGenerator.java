package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * Generates an empty maze - without any walls.
 */
public class EmptyMazeGenerator extends AMazeGenerator {

    public EmptyMazeGenerator() {
    }

    @Override
    public Maze generate(int rows, int columns) {
        if(rows<0 || columns<0)
            throw new ArithmeticException("Rows and columns must be non-negative.");

        ArrayList<Position> startGoal = totalyRandomStartGoal(rows, columns);
        Position start = startGoal.get(0);
        Position end = startGoal.get(1);
        Maze maze = new Maze(rows , columns , start , end);
        for(int i=0;i<maze.getPosArr().length;i++){
            for(int j=0;j<maze.getPosArr()[0].length;j++){
                maze.getPosArr()[i][j].setMovable_neighbours(maze.getPosArr()[i][j].getNeighbours());
            }
        }
        return maze;
    }
}
