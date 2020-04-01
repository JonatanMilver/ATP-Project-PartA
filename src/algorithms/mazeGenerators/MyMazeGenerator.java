package algorithms.mazeGenerators;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator {

    public MyMazeGenerator() {
    }

    @Override
    public Maze generate(int rows, int columns) {

        if(rows<0 || columns<0)
            throw new ArithmeticException("Rows and columns must be non-negative.");

        Maze mymaze;
        if (rows == 1 || columns ==1){
            //generating new maze with start position in index (0,0) and goal position in index (r-1,c-1)
            mymaze = new Maze(rows, columns,new Position(0,0),new Position(rows-1,columns-1));
        }
        else if(rows == 0 && columns == 0) {
            return null;
        }
        else{
            //generating new maze with random start and goal positions
            ArrayList<Position> startGoal = totalyRandomStartGoal(rows, columns);
            mymaze = new Maze(rows,columns,startGoal.get(0),startGoal.get(1));
        }


        //initiating a new stack
        Stack<Position> positionStack = new Stack<Position>();

        DFS_algorithm(positionStack,mymaze);

        mazearr_generate(mymaze);

        return mymaze;
    }


    private void DFS_algorithm(Stack<Position> positionStack, Maze mymaze){

        //pushing to the stack the start position of the maze
        positionStack.push(mymaze.getPosArr()[0][0]);

        while(!positionStack.isEmpty()){
            Position current = positionStack.pop();
            current.setVisited(true);

            //checking if current position has any unvisited neighbours
            ArrayList<Position> unvisited_neighbours = new ArrayList<Position>();
            for(int i=0;i<current.getNeighbours().size();i++){
                if(!current.getNeighbours().get(i).isVisited()){
                    unvisited_neighbours.add(current.getNeighbours().get(i));
                }
            }

            //if there are any unvisited neighbours,
            //choose one randomly and add current position and chosen neighbour to the stack
            if(unvisited_neighbours.size() != 0){
                Random rand = new Random();
                int randIndex = rand.nextInt(unvisited_neighbours.size());
                Position chosenNeighbour = unvisited_neighbours.get(randIndex);
                positionStack.push(current);
                chosenNeighbour.setVisited(true);
                current.getMovable_neighbours().add(chosenNeighbour);
                chosenNeighbour.getMovable_neighbours().add(current);

                positionStack.push(chosenNeighbour);
            }
        }
    }

    private ArrayList<Position> totalyRandomStartGoal(int rows, int columns) {
        ArrayList<Position> startNgoal = new ArrayList<>();
        Random randgen = new Random();

        Position start;
        Position end;
        int start_edge = randgen.nextInt(4); // 0 - up , 1 - right , 2 - down , 3 - left
        int start_pos, end_edge , end_pos;
        if (start_edge == 0 || start_edge == 2){
            start_pos = randgen.nextInt(columns);

            // start position in (0,?)
            if (start_edge == 0){

                //start position in (0,0)
                if (start_pos == 0){
                    start = new Position(0,0);
                    end_edge = randgen.nextInt(2); // 0 - right , 1 - down
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1)+1;
                        end = new Position(end_pos,columns-1);
                    }
                    else { //end_edge == 1
                        end_pos = randgen.nextInt(columns-1)+1;
                        end = new Position(rows-1 , end_pos);
                    }
                }

                //start position in (0,columns-1)
                else if (start_pos == columns-1) {
                    start = new Position(0,columns-1);
                    end_edge = randgen.nextInt(2); // 0 - left , 1 - down
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1)+1;
                        end = new Position(end_pos,0);
                    }
                    else{ //end_edge == 1
                        end_pos = randgen.nextInt(columns-1);
                        end = new Position(rows-1 , end_pos);
                    }
                }

                else{
                    start = new Position(0,start_pos);
                    end_edge = randgen.nextInt(3); // 0 - left , 1 - down , 2 - right
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1)+1;
                        end = new Position(end_pos,0);
                    }
                    else if (end_edge == 2){
                        end_pos = randgen.nextInt(rows-1)+1;
                        end = new Position(end_pos,columns-1);
                    }
                    else{ // end_pos == 1
                        end_pos = randgen.nextInt(columns);
                        end = new Position(rows-1 , end_pos);
                    }
                }
            }

            // start position in (rows-1,?)
            else{ //start_edge == 2

                //start position in (rows-1,0)
                if (start_pos == 0){
                    start = new Position(rows-1,0);
                    end_edge = randgen.nextInt(2); // 0 - right , 1 - up
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1);
                        end = new Position(end_pos,columns-1);
                    }
                    else { //end_edge == 1
                        end_pos = randgen.nextInt(columns-1)+1;
                        end = new Position(0 , end_pos);
                    }
                }


                //start position in (rows-1,columns-1)
                else if (start_pos == columns-1){
                    start = new Position(rows-1,columns-1);
                    end_edge = randgen.nextInt(2); // 0 - left , 1 - up
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1);
                        end = new Position(end_pos,0);
                    }
                    else{ //end_edge == 1
                        end_pos = randgen.nextInt(columns-1);
                        end = new Position(0 , end_pos);
                    }
                }

                else{
                    start = new Position(rows-1,start_pos);
                    end_edge = randgen.nextInt(3); // 0 - left , 1 - up , 2 - right
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1);
                        end = new Position(end_pos,0);
                    }
                    else if (end_edge == 2){
                        end_pos = randgen.nextInt(rows-1);
                        end = new Position(end_pos,columns-1);
                    }
                    else{ // end_pos == 1
                        end_pos = randgen.nextInt(columns);
                        end = new Position(0 , end_pos);
                    }
                }
            }

        }

        else{ //start_edge == 1 || start_edge == 3
            start_pos = randgen.nextInt(rows);

            // start position in right-most column (?,columns-1)
            if (start_edge == 1){

                //start position in (0,columns-1)
                if (start_pos==0){
                    start = new Position(start_pos,columns-1);
                    end_edge = randgen.nextInt(2); // 0 - left , 1 - down
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1)+1;
                        end = new Position(end_pos,0);
                    }
                    else {
                        end_pos = randgen.nextInt(columns-1)+1;
                        end = new Position(rows-1,end_pos);
                    }
                }

                //start position in (rows-1,columns-1)
                else if (start_pos == rows-1){
                    start = new Position(start_pos,columns-1);
                    end_edge = randgen.nextInt(2); // 0 - left , 1 - up
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1);
                        end = new Position(end_pos,0);
                    }
                    else{
                        end_pos = randgen.nextInt(columns-1);
                        end = new Position(0 , end_pos);
                    }
                }

                else{
                    start = new Position(start_pos,columns-1);
                    end_edge = randgen.nextInt(3); // 0 - left , 1 - up , 2 - down
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows);
                        end = new Position(end_pos,0);
                    }
                    else if (end_edge == 1){
                        end_pos = randgen.nextInt(columns-1);
                        end = new Position(0,end_pos);
                    }
                    else{
                        end_pos = randgen.nextInt(columns-1);
                        end = new Position(rows-1,end_pos);
                    }
                }
            }

            // start position in left-most column (?,0)
            else{ //start_edge == 3

                //start position in (0,0)
                if (start_pos == 0){
                    start = new Position(0,0);
                    end_edge = randgen.nextInt(2); // 0 - right , 1 - down
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1)+1;
                        end = new Position(end_pos,columns-1);
                    }
                    else {
                        end_pos = randgen.nextInt(columns-1)+1;
                        end = new Position(rows-1,end_pos);
                    }
                }

                //start position in (rows-1,0)
                else if (start_pos == rows-1){
                    start = new Position(start_pos,0);
                    end_edge = randgen.nextInt(2); // 0 - right , 1 - up
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1);
                        end = new Position(end_pos,columns-1);
                    }
                    else{
                        end_pos = randgen.nextInt(columns-1)+1;
                        end = new Position(0 , end_pos);
                    }
                }

                else{
                    start = new Position(start_pos , 0);
                    end_edge = randgen.nextInt(3); // 0 - up , 1 - right , 2 - down
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(columns-1)+1;
                        end = new Position(0 , end_pos);
                    }
                    else if (end_edge == 2){
                        end_pos = randgen.nextInt(columns-1)+1;
                        end = new Position(rows-1,end_pos);
                    }
                    else{
                        end_pos = randgen.nextInt(rows);
                        end = new Position(end_pos,columns-1);
                    }
                }

            }

        }

        startNgoal.add(start);
        startNgoal.add(end);
        return startNgoal;
    }

    private ArrayList<Position> randomStartGoal(int rows, int columns){
        ArrayList<Position> startNgoal = new ArrayList<>();
        Random randGen = new Random();
        int edge = randGen.nextInt(4);
        int startRow = 0;
        int startCol = 0;
        int endRow = 0;
        int endCol = 0;
        if((edge == 0|| edge==1)){
            startRow = 0;
            startCol = 0;
            endRow = rows-1;
            endCol = columns-1;
        }
        else if(edge == 2){
            startRow = 0;
            startCol = columns-1;
            endRow = rows-1;
            endCol = 0;
        }
        else if( edge == 3){
            startRow = rows-1;
            startCol = columns-1;
            endRow = 0;
            endCol = 0;
        }
        Position start = new Position(startRow, startCol);
        Position end = new Position(endRow, endCol);
        startNgoal.add(start);
        startNgoal.add(end);
        return startNgoal;
    }

    private void mazearr_generate(Maze mymaze){

        for (int i=0;i<2*mymaze.getRows()-1;i++){
            for (int j=0; j<2*mymaze.getColumns()-1;j++){
                mymaze.getMazeArr()[i][j]=1;
            }
        }

        for(int i=0;i<mymaze.getRows();i++){
            for(int j=0;j<mymaze.getColumns();j++){
                for (int k=0;k<mymaze.getPosArr()[i][j].getMovable_neighbours().size();k++){
                    if(mymaze.getPosArr()[i][j].getMovable_neighbours().get(k).getRowIndex()== mymaze.getPosArr()[i][j].getRowIndex()) {
                        if (mymaze.getPosArr()[i][j].getMovable_neighbours().get(k).getColumnIndex() > mymaze.getPosArr()[i][j].getColumnIndex()) {
                            mymaze.getMazeArr()[2*i][2*j + 1] = 0;
                        }
                        else { mymaze.getMazeArr()[2*i][2*j - 1] = 0; }
                    }
                    else{
                        if (mymaze.getPosArr()[i][j].getMovable_neighbours().get(k).getRowIndex() > mymaze.getPosArr()[i][j].getRowIndex()){
                            mymaze.getMazeArr()[2*i+1][2*j] = 0;
                        }
                        else{ mymaze.getMazeArr()[2*i-1][2*j] = 0; }
                    }

                }

            }
        }

        for (int i=0;i<mymaze.getRows();i++){
            for (int j=0;j<mymaze.getColumns();j++){
                mymaze.getMazeArr()[2*i][2*j]=0;
            }
        }
    }
}