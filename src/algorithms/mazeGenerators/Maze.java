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
//        mazeArr = new int[rows+rows-1][columns+columns-1];
//        posArr = new Position[rows][columns];
        mazeArr = new int[rows][columns];

        boolean is_rows_even = rows%2==0;
        boolean is_columns_even = columns%2==0;
        if (is_rows_even){
            if (is_columns_even){
                posArr = new Position[rows / 2][columns / 2];
            }
            else{
                posArr = new Position[rows / 2][columns / 2 + 1];
            }
        }
        else{
            if (is_columns_even){
                posArr = new Position[rows / 2 + 1][columns / 2];
            }
            else{
                posArr = new Position[rows / 2 + 1][columns / 2 + 1];
            }
        }

        buildPositions(posArr.length,posArr[0].length);
        setNeighbours(posArr.length,posArr[0].length);
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
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(i==0 && j==0){
                    if(findPosition(1,0) != null)
                        posArr[i][j].addNeighbour(findPosition(1, 0));
                    if(findPosition(0,1) != null)
                        posArr[i][j].addNeighbour(findPosition(0, 1));
                }
                else if(i==0 && j == columns-1){
                    if(findPosition(0,j-1) != null)
                        posArr[i][j].addNeighbour(findPosition(0,j-1));
                    if(findPosition(1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(1,j));
                }
                else if(j==0 && i == rows-1){
                    if(findPosition(i-1,0) != null)
                        posArr[i][j].addNeighbour(findPosition(i-1,0));
                    if(findPosition(i,1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,1));
                }
                else if(j==columns-1 && i == rows-1){
                    if(findPosition(i-1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i-1, j));
                    if(findPosition(i,j-1) != null)
                        posArr[i][j].addNeighbour(findPosition(i, j-1));
                }
                else if(i==rows-1){
                    if(findPosition(i,j+1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j+1));
                    if(findPosition(i,j-1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j-1));
                    if(findPosition(i-1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i-1,j));
                }
                else if(j==columns-1){
                    if(findPosition(i+1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i+1,j));
                    if(findPosition(i-1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i-1,j));
                    if(findPosition(i,j-1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j-1));
                }
                else if(i==0){
                    if(findPosition(i+1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i+1,j));
                    if(findPosition(i,j+1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j+1));
                    if(findPosition(i,j-1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j-1));
                }
                else if( j==0){
                    if(findPosition(i+1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i+1,j));
                    if(findPosition(i-1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i-1,j));
                    if(findPosition(i,j+1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j+1));
                }
                else{
                    if(findPosition(i,j+1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j+1));
                    if(findPosition(i,j-1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j-1));
                    if(findPosition(i+1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i+1,j));
                    if(findPosition(i-1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i-1,j));
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

    public void print() {
        String sout = "{\n";
        for (int i=0; i < mazeArr.length ; i++){
            sout += "{";
            for (int j=0; j<mazeArr[i].length;j++){
                if (i==StartPosition.getRowIndex() && j==StartPosition.getColumnIndex()){
                    sout+=" S";
                }
                else if (i==2*GoalPosition.getRowIndex() && j==2*GoalPosition.getColumnIndex()){
                    sout+=" E";
                }
                else{
                    sout += " " + mazeArr[i][j];
                }
            }
            sout +="}\n";

        }
        sout +="}";
        System.out.println(String.format(sout, "%s"));
    }
}
