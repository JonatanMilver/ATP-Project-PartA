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

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int[][] getMazeArr() {
        return mazeArr;
    }

    public void setMazeArr(int[][] mazeArr) {
        this.mazeArr = mazeArr;
    }

    @Override
    public String toString() {
        String sout = "[";
        for (int i=0; i < mazeArr.length ; i++){
            sout += "[";
            for (int j=0; j<mazeArr[i].length;j++){
                sout += mazeArr[i][j];
            }
            sout +="]\n";

        }
        sout +="]";
        return sout;
    }
}
