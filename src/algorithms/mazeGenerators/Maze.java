package algorithms.mazeGenerators;

import java.util.Arrays;

public class Maze {
    private int rows;
    private int columns;
    private int[][] mazeArr;

    public Maze(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        mazeArr = new int[rows][columns];
    }


    @Override
    public String toString() {
        return "Maze{" +
                "mazeArr=" + Arrays.toString(mazeArr) +
                '}';
    }
}
