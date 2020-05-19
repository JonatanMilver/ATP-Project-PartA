package algorithms.mazeGenerators;

import algorithms.search.MazeState;
import algorithms.search.Solution;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;



/**
 * A maze class.
 * Holds maze's rows and columns.
 * Holds it's Start position and goal position.
 * mazeArr - int array for the maze's presentation
 * posArr - Array of positions holding each position at it's place.
 */
public class Maze implements Serializable{
    private int rows;
    private int columns;
    private Position StartPosition;
    private Position GoalPosition;
    private int[][] mazeArr;
    private Position[][] posArr;

    public Maze(int rows, int columns , Position StartPosition,Position GoalPosition) {
        this.rows = rows;
        this.columns = columns;
        mazeArr = new int[rows+rows-1][columns+columns-1];
        posArr = new Position[rows][columns];

        buildPositions(rows,columns);
        setNeighbours(rows, columns);

        this.StartPosition = posArr[StartPosition.getRowIndex()][StartPosition.getColumnIndex()];
        this.GoalPosition = posArr[GoalPosition.getRowIndex()][GoalPosition.getColumnIndex()];
    }

    /**
     * Gets decompressed byte array and builds a maze from it's content
     * 24 first elements are the features of the maze.
     * other elements will have the content of the maze(walls, passes...)
     * @param decompressed byte[]
     */
    public Maze(byte[] decompressed) {
        initializeMaze(decompressed);
    }

    /**
     * overrides writeObject of ObjectOutputStream
     * made for writing a smaller size of the object
     * used to write Mazes while communicating between different servers.
     * @param outputStream ObjectOutputStream
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        //convert the maze to byte array and write it to outputStream
        outputStream.writeObject(toByteArray());
    }

    /**
     * overrides readObject of ObjectInputStream
     * receives a byte array and builds the maze from the array using initializeMaze method
     * used to read Mazes while communicating between different servers.
     * @param inputStream ObjectInputStream.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        byte[] bytes = (byte[]) inputStream.readObject();
        initializeMaze(bytes);
    }

    /**
     * Build a maze from a given data in byte array.
     * @param decompressed byte[]
     */
    private void initializeMaze(byte[] decompressed){
        //        Retrieving all the information from the byte[]
        int rows = new BigInteger(Arrays.copyOfRange(decompressed, 0 , 2)).intValue();
        int columns = new BigInteger(Arrays.copyOfRange(decompressed, 2 , 4)).intValue();
        int start_row = new BigInteger(Arrays.copyOfRange(decompressed, 4, 6)).intValue();
        int start_column = new BigInteger(Arrays.copyOfRange(decompressed, 6, 8)).intValue();
        int end_row = new BigInteger(Arrays.copyOfRange(decompressed, 8, 10)).intValue();
        int end_column = new BigInteger(Arrays.copyOfRange(decompressed, 10, 12)).intValue();
        this.rows = (rows+1)/2;
        this.columns = (columns+1)/2;
        this.mazeArr = new int[rows][columns];
        this.posArr = new Position[this.rows][this.columns];
//        Setting neighbours and positions
        buildPositions(this.rows, this.columns);
//      Placing 0/1 at the display ( mazeArr)
        buildMazeFromBytes(decompressed , columns, rows);
//      Setting start and goal position according to given info from the byte array.
        this.StartPosition = posArr[start_row][start_column];
        this.GoalPosition = posArr[end_row][end_column];
        createNeighboursFromBytes();
    }


    /**
     * Builds the maze array(mazeArr) by setting "0/1" at the rights places
     * given by the decompressed byte array
     * A private method being called at the Maze(byte[] decompressed) constructor
     * @param decompressed  byte[]
     * @param columns int
     */
    private void buildMazeFromBytes(byte[] decompressed, int columns, int rows){
        if(decompressed == null)
            return;
        int decompressed_length = decompressed.length;
        int row = 0;
        int column = 0 ;
        int decompressed_index = 12;
        int cont_len = rows*columns;
        //Loop runs until every cell at the maze is filled with wall/pass.
        while(row*column < cont_len){
            column = 0;
            while(column < columns) {
                this.mazeArr[row][column] = decompressed[decompressed_index];
                column++;
                decompressed_index++;
            }
            row++;
        }

    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Position getStartPosition() {
        return StartPosition;
    }

    public Position getGoalPosition() {
        return GoalPosition;
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

    /**
     * Build positions array(posArr).
     * @param rows int
     * @param columns int
     */
    private void buildPositions(int rows, int columns){
        for(int i=0;i<rows; i++){
            for(int j=0;j<columns;j++){
                posArr[i][j] = new Position(i,j);
            }
        }
    }

    /**
     * Builds neighbours array list
     * @param rows int
     * @param columns int
     */
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

    /**
     * Find whether a position exists or not
     * @param x int
     * @param y int
     * @return Position if found - else return null.
     */
    public Position findPosition(int x, int y){
        try{
            return posArr[x][y];
        }
        catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    /**
     * Creates the neighbours list - being used after receiving maze data in bytes.
     */
    public void createNeighboursFromBytes(){
        for(int i=0; i < this.posArr.length;i++){
            for(int j=0;j<this.posArr[i].length ; j++){
                if(findPosition(i-1,j) != null){
                    if(mazeArr[2*i-1][2*j] == 0){
                        posArr[i][j].addNeighbour(posArr[i-1][j]);
                        posArr[i][j].getMovable_neighbours().add(posArr[i-1][j]);
                    }
                }
                if(findPosition(i,j+1) != null){
                    if(mazeArr[2*i][2*j+1] == 0){
                        posArr[i][j].addNeighbour(posArr[i][j+1]);
                        posArr[i][j].getMovable_neighbours().add(posArr[i][j+1]);
                    }
                }
                if(findPosition(i+1,j) != null){
                    if(mazeArr[2*i+1][2*j] == 0){
                        posArr[i][j].addNeighbour(posArr[i+1][j]);
                        posArr[i][j].getMovable_neighbours().add(posArr[i+1][j]);
                    }
                }
                if(findPosition(i,j-1) != null){
                    if(mazeArr[2*i][2*j-1] == 0){
                        posArr[i][j].addNeighbour(posArr[i][j-1]);
                        posArr[i][j].getMovable_neighbours().add(posArr[i][j-1]);
                    }
                }
            }
        }
    }

    public void print() {
        for(int i=0;i<mazeArr.length;i++){
            for(int j=0;j<mazeArr[i].length;j++){
                if(i==2*StartPosition.getRowIndex() && j == 2*StartPosition.getColumnIndex()){
                    System.out.print("S");
                }
                else if(i==2*GoalPosition.getRowIndex() && j== 2*GoalPosition.getColumnIndex()) {
                    System.out.print("E");
                }
                else if(mazeArr[i][j] == 1) {
                    System.out.print(String.format("%d", mazeArr[i][j]));
                }
                else if(mazeArr[i][j] == 0){
                    System.out.print(String.format("%d" , mazeArr[i][j]));
                }

            }
            System.out.println();
        }
    }
    public void printWithColor(Solution sol) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001b[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_WHITE = "\u001B[37m";
        final String	BACKGROUND_BLACK	= "\u001B[40m";
        final String	BACKGROUND_RED		= "\u001B[41m";
        final String	BACKGROUND_GREEN	= "\u001B[42m";
        final String	BACKGROUND_WHITE	= "\u001B[47m";
        for(int i=0;i<mazeArr.length;i++){
            for(int j=0;j<mazeArr[i].length;j++){
                if(i==2*StartPosition.getRowIndex() && j == 2*StartPosition.getColumnIndex()){
                    System.out.print(BACKGROUND_RED + /*ANSI_RED +*/ " S "  + ANSI_RESET);
                }

                else if(i==2*GoalPosition.getRowIndex() && j== 2*GoalPosition.getColumnIndex()) {
                    System.out.print(BACKGROUND_GREEN + /*ANSI_GREEN +*/ " E " + ANSI_RESET);
                }

                else if(mazeArr[i][j] == 1) {
                    System.out.print(BACKGROUND_WHITE + ANSI_WHITE + String.format(" %d ", mazeArr[i][j]) + ANSI_RESET);
                }

                else if(mazeArr[i][j] == 0){
                    boolean t = true;
                    for (int k = 0 ; k < sol.getSolutionPath().size(); k++){
                        MazeState s = (MazeState) sol.getSolutionPath().get(k);
                        if ( 2*s.getCurrent_position().getRowIndex() == i && 2*s.getCurrent_position().getColumnIndex() == j){
                            System.out.print(String.format(" %d " , mazeArr[i][j]));
                            t = false;
                            break;
                        }
                    }
                    if (t){
                        System.out.print(BACKGROUND_BLACK + ANSI_BLACK + String.format(" %d " , mazeArr[i][j]) + ANSI_RESET);
                    }
                }

            }
            System.out.println("\u001b[107m");
        }
    }







    public byte[] toByteArray(){
        byte[] bytemaze = new byte[12 + (mazeArr.length*mazeArr[0].length)];

        byte[] numOfRows = bigIntToByteArray(mazeArr.length);
        byte[] numOfCols = bigIntToByteArray(mazeArr[0].length);

        byte[] startRows = bigIntToByteArray(StartPosition.getRowIndex());
        byte[] startCols = bigIntToByteArray(StartPosition.getColumnIndex());

        byte[] endRows = bigIntToByteArray(GoalPosition.getRowIndex());
        byte[] endCols = bigIntToByteArray(GoalPosition.getColumnIndex());

        copyToBytemaze(numOfRows , bytemaze,0);
        copyToBytemaze(numOfCols , bytemaze , 2);
        copyToBytemaze(startRows , bytemaze , 4);
        copyToBytemaze(startCols , bytemaze , 6);
        copyToBytemaze(endRows , bytemaze , 8);
        copyToBytemaze(endCols , bytemaze , 10);

        int index = 12;
        for (int[] row : mazeArr) {
            for (int j = 0; j < mazeArr[0].length; j++) {
                bytemaze[index] = (byte) row[j];
                index++;
            }
        }
        
        return bytemaze;
    }

    private void copyToBytemaze(byte[] tocopy, byte[] bytemaze , int fromIndex) {

        for (int i = 0 ; i < 2-tocopy.length ; i++){
            bytemaze[fromIndex] = 0;
            fromIndex++;
        }
        for (byte b : tocopy) {
            bytemaze[fromIndex] = b;
            fromIndex++;
        }
    }

    private byte[] bigIntToByteArray(int i) {
        BigInteger bigInt = BigInteger.valueOf(i);
        return bigInt.toByteArray();
    }

}
