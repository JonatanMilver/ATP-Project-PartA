package algorithms.mazeGenerators;

import java.util.Arrays;

public class Maze {
    private int rows;
    private int columns;
    private Position StartPosition;
    private Position GoalPosition;
    private int[][] mazeArr;
    private Position[][] posArr;

    public Maze(int rows, int columns , Position StartPosition,Position GoalPosition) {
        this.rows = rows;
        this.columns = columns;
        mazeArr = new int[rows][columns];
        posArr = new Position[rows][columns];
        buildPositions(rows,columns);
        setNeighbours(rows, columns);
        this.StartPosition = posArr[StartPosition.getRowIndex()][StartPosition.getColumnIndex()];
        this.GoalPosition = posArr[GoalPosition.getRowIndex()][GoalPosition.getColumnIndex()];


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

    public Position getStartPosition() {
        return StartPosition;
    }

    public void setStartPosition(Position startPosition) {
        StartPosition = startPosition;
    }

    public Position getGoalPosition() {
        return GoalPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        GoalPosition = goalPosition;
    }

    public int[][] getMazeArr() {
        return mazeArr;
    }

    public void setMazeArr(int[][] mazeArr) {
        this.mazeArr = mazeArr;
    }

    public Position[][] getPosArr() {
        return posArr;
    }

    public void setPosArr(Position[][] posArr) {
        this.posArr = posArr;
    }

    private void buildPositions(int rows, int columns){
        for(int i=0;i<rows; i++){
            for(int j=0;j<columns;j++){
                posArr[i][j] = new Position(i,j);
            }
        }
    }
    private void setNeighbours(int rows, int columns){
        for(int i=0;i<rows;i=i+2){
            for(int j=0;j<columns;j=j+2){
                if(i==0 && j==0){
                    if(findPosition(2,0) != null)
                        posArr[i][j].addNeighbour(findPosition(2, 0));
                    if(findPosition(0,2) != null)
                        posArr[i][j].addNeighbour(findPosition(0, 2));
                }
                else if(i==0 && j == columns-1){
                    if(findPosition(0,j-2) != null)
                        posArr[i][j].addNeighbour(findPosition(0,j-2));
                    if(findPosition(2,j) != null)
                        posArr[i][j].addNeighbour(findPosition(2,j));
                }
                else if(j==0 && i == rows-1){
                    if(findPosition(i-2,0) != null)
                        posArr[i][j].addNeighbour(findPosition(i-2,0));
                    if(findPosition(i,2) != null)
                        posArr[i][j].addNeighbour(findPosition(i,2));
                }
                else if(j==columns-1 && i == rows-1){
                    if(findPosition(i-2,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i-2, j));
                    if(findPosition(i,j-2) != null)
                        posArr[i][j].addNeighbour(findPosition(i, j-2));
                }
                else if(i==rows-1){
                    if(findPosition(i,j+2) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j+2));
                    if(findPosition(i,j-2) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j-2));
                    if(findPosition(i-2,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i-2,j));
                }
                else if(j==columns-1){
                    if(findPosition(i+2,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i+2,j));
                    if(findPosition(i-2,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i-2,j));
                    if(findPosition(i,j-2) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j-2));
                }
                else if(i==0){
                    if(findPosition(i+2,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i+2,j));
                    if(findPosition(i,j+2) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j+2));
                    if(findPosition(i,j-2) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j-2));
                }
                else if( j==0){
                    if(findPosition(i+2,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i+2,j));
                    if(findPosition(i-2,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i-2,j));
                    if(findPosition(i,j+2) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j+2));
                }
                else{
                    if(findPosition(i,j+2) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j+2));
                    if(findPosition(i,j-2) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j-2));
                    if(findPosition(i+2,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i+2,j));
                    if(findPosition(i-2,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i-2,j));
                }
            }
        }
    }




    public Position findPosition(int x, int y){
        try{
            return posArr[x][y];
        }
        catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }



    @Override
    public String toString() {
        String sout = "{\n";
        for (int i=0; i < mazeArr.length ; i++){
            sout += "{";
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
            sout +="}\n";

        }
        sout +="}";
        return sout;
    }
}
