package algorithms.mazeGenerators;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {

        //generating new maze with start position in index (0,0) and goal position in index (r-1,c-1)
        Maze mymaze = new Maze(rows,columns,new Position(0,0),new Position(rows-1,columns-1));

        //initiating a new stack
        Stack<Position> positionStack = new Stack<Position>();

        DFS_algorithm(positionStack,mymaze);

        mazearr_generate(mymaze);

        return mymaze;
    }

    private void DFS_algorithm(Stack<Position> positionStack, Maze mymaze){

        //pushing to the stack the start position of the maze
        positionStack.push(mymaze.getStartPosition());

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