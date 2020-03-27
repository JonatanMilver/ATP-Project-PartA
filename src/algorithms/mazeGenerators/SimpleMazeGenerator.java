package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {
    public SimpleMazeGenerator() {
    }

    @Override
    public Maze generate(int rows, int columns) {
        Position start = new Position(0,0);
        Position end = new Position(rows-1,columns-1);

        Maze ret = new Maze(rows , columns,start,end);

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