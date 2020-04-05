package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

/**
 * A generator that creates a maze placing walls randomly on that maze.
 * The maze will always have a solution.
 */
public class SimpleMazeGenerator extends AMazeGenerator {
    public SimpleMazeGenerator() {
    }

    @Override
    public Maze generate(int rows, int columns) {
        if(rows<0 || columns<0)
            throw new ArithmeticException("Rows and columns must be non-negative.");

        ArrayList<Position> sAndG = totalyRandomStartGoal(rows , columns);

        Position start = sAndG.get(0);
        Position end = sAndG.get(1);

        Maze ret = new Maze(rows , columns , start , end);

        for(int j=0;j<ret.getPosArr()[0].length-1;j++){
            ret.getPosArr()[0][j].getMovable_neighbours().add(ret.getPosArr()[0][j+1]);
            ret.getPosArr()[0][j+1].getMovable_neighbours().add(ret.getPosArr()[0][j]);
            ret.getPosArr()[rows-1][j].getMovable_neighbours().add(ret.getPosArr()[rows-1][j+1]);
            ret.getPosArr()[rows-1][j+1].getMovable_neighbours().add(ret.getPosArr()[rows-1][j]);
        }

        for(int i=0; i<ret.getPosArr().length-1;i++){
            ret.getPosArr()[i][columns-1].getMovable_neighbours().add(ret.getPosArr()[i+1][columns-1]);
            ret.getPosArr()[i+1][columns-1].getMovable_neighbours().add(ret.getPosArr()[i][columns-1]);
            ret.getPosArr()[i][0].getMovable_neighbours().add(ret.getPosArr()[i+1][0]);
            ret.getPosArr()[i+1][0].getMovable_neighbours().add(ret.getPosArr()[i][0]);
        }

        int[][] randMaze = new int[2*rows-1][2*columns-1];
        for (int i = 0 ; i < 2*rows-1 ; i++){
            for (int j = 0 ; j < 2*columns-1 ; j++){
                Random wall = new Random();
                if(i!=0 && i!= randMaze.length-1 && j!=0 && j!=randMaze[0].length-1)
                    randMaze[i][j] = wall.nextInt(2);
            }
        }
        ret.setMazeArr(randMaze);
        return ret;
    }
}