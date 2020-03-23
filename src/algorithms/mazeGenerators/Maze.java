package algorithms.mazeGenerators;

import java.util.Arrays;

public class Maze {
    private int rows;
    private int columns;
    private Position StartPosition;
    private Position GoalPosition;
    private int[][] mazeArr;
    private Position[][] posarr;

    public Maze(int rows, int columns , Position StartPosition,Position GoalPosition) {
        this.rows = 2*rows+1;
        this.columns = 2*columns+1;
        this.StartPosition = StartPosition;
        this.GoalPosition = GoalPosition;
        mazeArr = new int[this.rows][this.columns];
        posarr = new Position[rows][columns];
        buildpositions(rows,columns);
    }

    private void buildpositions(int r , int c){
        for (int i=0;i<r;i++){
            for (int j=0;j<c;j++){
                posarr[i][j] = new Position(i,j);
                posarr[i][j].getNeighbours().add(posarr[i+1][j]);
                posarr[i][j].getNeighbours().add(posarr[i][j+1]);
            }
        }
        for (int i=0;i<r;i++){
            for (int j=0;j<c;j++){
                if (i==0 && j==0){
                    posarr[i][j].getNeighbours().add(posarr[i+1][j]);
                    posarr[i][j].getNeighbours().add(posarr[i][j+1]);
                }
                else if (i==0 && j == c-1){
                    posarr[i][j].getNeighbours().add(posarr[i+1][j]);
                    posarr[i][j].getNeighbours().add(posarr[i][j-1]);
                }
                else if (i==0 && j!=c-1){
                    posarr[i][j].getNeighbours().add(posarr[i+1][j]);
                    posarr[i][j].getNeighbours().add(posarr[i][j-1]);
                    posarr[i][j].getNeighbours().add(posarr[i][j+1]);
                }
                else if (i==r-1 && j==0){
                    posarr[i][j].getNeighbours().add(posarr[i-1][j]);
                    posarr[i][j].getNeighbours().add(posarr[i][j+1]);
                }
                else if (i==r-1 && j==c-1){
                    posarr[i][j].getNeighbours().add(posarr[i][j-1]);
                    posarr[i][j].getNeighbours().add(posarr[i-1][j]);
                }
                else if (i==r-1 && j!=c-1){
                    posarr[i][j].getNeighbours().add(posarr[i-1][j]);
                    posarr[i][j].getNeighbours().add(posarr[i][j-1]);
                    posarr[i][j].getNeighbours().add(posarr[i][j+1]);
                }
                else if (i != r-1 && i != 0 && j == 0){
                    posarr[i][j].getNeighbours().add(posarr[i][j+1]);
                    posarr[i][j].getNeighbours().add(posarr[i+1][j]);
                    posarr[i][j].getNeighbours().add(posarr[i-1][j]);
                }
                else if (i != r-1 && i != 0 && j == c-1){
                    posarr[i][j].getNeighbours().add(posarr[i][j-1]);
                    posarr[i][j].getNeighbours().add(posarr[i+1][j]);
                    posarr[i][j].getNeighbours().add(posarr[i-1][j]);
                }
                else{
                    posarr[i][j].getNeighbours().add(posarr[i+1][j]);
                    posarr[i][j].getNeighbours().add(posarr[i][j+1]);
                    posarr[i][j].getNeighbours().add(posarr[i-1][j]);
                    posarr[i][j].getNeighbours().add(posarr[i][j-1]);
                }
            }
        }
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

    public Position getStartPosition() {
        return StartPosition;
    }

    public Position getGoalPosition() {
        return GoalPosition;
    }

    @Override
    public String toString() {
        String sout = "[";
        for (int i=0; i < mazeArr.length ; i++){
            sout += "[";
            for (int j=0; j<mazeArr[i].length;j++){
                if (i==StartPosition.getRowIndex() && j==StartPosition.getColumnIndex()){
                    sout+="S";
                }
                else if (i==GoalPosition.getRowIndex() && j==GoalPosition.getColumnIndex()){
                    sout+="E";
                }
                else{
                    sout += mazeArr[i][j];
                }
            }
            sout +="]\n";

        }
        sout +="]";
        return sout;
    }
}
