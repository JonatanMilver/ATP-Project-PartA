package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {
    public SimpleMazeGenerator() {
    }

    @Override
    public Maze generate(int rows, int columns) {
        Maze ret = new Maze(rows , columns);

        int[][] randMaze = new int[rows][columns];
        for (int i = 0 ; i < rows ; i++){
            for (int j = 0 ; j < columns ; j++){
                Random wall = new Random();
                randMaze[i][j] = wall.nextInt(2);
            }
        }
        ret.setMazeArr(randMaze);
        return ret;
    }
}
