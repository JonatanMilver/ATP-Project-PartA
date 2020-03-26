package algorithms.mazeGenerators;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        boolean is_rows_even = rows%2==0;
        boolean is_columns_even = columns%2==0;
        Maze mymaze;
        if (is_rows_even){
            if (is_columns_even){
                mymaze = new Maze(rows,columns,new Position(0,0),new Position(rows/2,columns/2));
            }
            else{
                mymaze = new Maze(rows,columns,new Position(0,0),new Position(rows/2,columns/2+1));
            }
        }
        else{
            if (is_columns_even){
                mymaze = new Maze(rows,columns,new Position(0,0),new Position(rows/2+1,columns/2));
            }
            else{
                mymaze = new Maze(rows,columns,new Position(0,0),new Position(rows/2+1,columns/2+1));
            }
        }
        for (int i=0;i<2*mymaze.getRows()-1;i++){
            for (int j=0; j<2*mymaze.getColumns()-1;j++){
                mymaze.getMazeArr()[i][j]=1;
            }
        }

        Stack<Position> positionStack = new Stack<Position>();
        positionStack.push(mymaze.getStartPosition());

        while(!positionStack.isEmpty()){
            Position current = positionStack.pop();
            current.setVisited(true);
            current.setIsWall(false);
            ArrayList<Position> neighbours = new ArrayList<Position>();
            for(int i=0;i<current.getNeighbours().size();i++){
                if(!current.getNeighbours().get(i).isVisited()){
                    neighbours.add(current.getNeighbours().get(i));
                }
            }
            Position between;

            if(neighbours.size() != 0){
                Random rand = new Random();
                int randIndex = rand.nextInt(neighbours.size());
                Position choosenNeighbour = neighbours.get(randIndex);
                positionStack.push(current);
                choosenNeighbour.setVisited(true);
                choosenNeighbour.setIsWall(false);
                current.getMovable_neighbours().add(choosenNeighbour);
                choosenNeighbour.getMovable_neighbours().add(current);

//                if(choosenNeighbour.getRowIndex() == current.getRowIndex()) {
//                    if (choosenNeighbour.getColumnIndex() > current.getColumnIndex()) {
//                        between = mymaze.findPosition(current.getRowIndex(), current.getColumnIndex() + 1);
//                        if (between != null) {
//                            between.setVisited(true);
//                            between.setIsWall(false);
//                        }
//                    }
//                    else{
//                        between = mymaze.findPosition(current.getRowIndex(), current.getColumnIndex()-1);
//                        if(between!= null){
//                            between.setVisited(true);
//                            between.setIsWall(false);
//                        }
//                    }
//                }
//                if(choosenNeighbour.getColumnIndex() == current.getColumnIndex()){
//                    if (choosenNeighbour.getRowIndex() > current.getRowIndex()) {
//                        between = mymaze.findPosition(current.getRowIndex()+1, current.getColumnIndex());
//                        if (between != null) {
//                            between.setVisited(true);
//                            between.setIsWall(false);
//                        }
//                    }
//                    else{
//                        between = mymaze.findPosition(current.getRowIndex()-1, current.getColumnIndex());
//                        if(between!= null){
//                            between.setVisited(true);
//                            between.setIsWall(false);
//                        }
//                    }
//                }
                positionStack.push(choosenNeighbour);
            }
        }
        for(int i=0;i<mymaze.getRows();i++){
            for(int j=0;j<mymaze.getColumns();j++){
                for (int k=0;k<mymaze.getPosArr()[i][j].getMovable_neighbours().size();k++){
                    if(mymaze.getPosArr()[i][j].getMovable_neighbours().get(k).getRowIndex()== mymaze.getPosArr()[i][j].getRowIndex()) {
                        if (mymaze.getPosArr()[i][j].getMovable_neighbours().get(k).getColumnIndex() > mymaze.getPosArr()[i][j].getColumnIndex()) {
                            mymaze.getMazeArr()[2*i][2*j + 1] = 0;
//                            mymaze.getMazeArr()[i][j] = 0;
//                            mymaze.getMazeArr()[i][j+1] = 0;
//                            mymaze.getMazeArr()[i][j+2] = 0;
                        } else {
                            mymaze.getMazeArr()[2*i][2*j - 1] = 0;
//                            mymaze.getMazeArr()[i][j] = 0;
//                            if (mymaze.getMazeArr()[i][j-1] == 0){
//                                continue;
//                            }
//                            mymaze.getMazeArr()[i][j-1] = 0;
//                            mymaze.getMazeArr()[i][j-2] = 0;
                        }
                    }
                    else{
                        if (mymaze.getPosArr()[i][j].getMovable_neighbours().get(k).getRowIndex() > mymaze.getPosArr()[i][j].getRowIndex()){
                            mymaze.getMazeArr()[2*i+1][2*j] = 0;
//                            mymaze.getMazeArr()[i][j] = 0;
//                            mymaze.getMazeArr()[i+1][j] = 0;
//                            mymaze.getMazeArr()[i+2][j] = 0;
                            }
                        else{
                            mymaze.getMazeArr()[2*i-1][2*j] = 0;
//                            mymaze.getMazeArr()[i][j] = 0;
//                            if (mymaze.getMazeArr()[i-1][j] == 0){
//                                continue;
//                            }
//                            mymaze.getMazeArr()[i-1][j] = 0;
//                            mymaze.getMazeArr()[i-2][j] = 0;
                        }
                    }

                }

            }
        }
        for (int i=0;i<mymaze.getRows();i++){
            for (int j=0;j<mymaze.getColumns();j++){
                mymaze.getMazeArr()[2*i][2*j]=0;
            }
        }

    return mymaze;



    }

}
