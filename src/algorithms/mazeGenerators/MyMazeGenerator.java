package algorithms.mazeGenerators;

import javafx.geometry.Pos;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        Maze mymaze;
        if(rows%2==0 && columns%2==0){
            mymaze = new Maze(rows,columns,new Position(0,0),new Position(rows-1,columns-2));
        }
        else {
            mymaze = new Maze(rows, columns, new Position(0, 0), new Position(rows - 1, columns - 1));
        }
        for (int i=0;i<mymaze.getRows();i++){
            for (int j=0; j<mymaze.getColumns();j++){
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

                if(choosenNeighbour.getRowIndex() == current.getRowIndex()) {
                    if (choosenNeighbour.getColumnIndex() > current.getColumnIndex()) {
                        between = mymaze.findPosition(current.getRowIndex(), current.getColumnIndex() + 1);
                        if (between != null) {
                            between.setVisited(true);
                            between.setIsWall(false);
                        }
                    }
                    else{
                        between = mymaze.findPosition(current.getRowIndex(), current.getColumnIndex()-1);
                        if(between!= null){
                            between.setVisited(true);
                            between.setIsWall(false);
                        }
                    }
                }
                if(choosenNeighbour.getColumnIndex() == current.getColumnIndex()){
                    if (choosenNeighbour.getRowIndex() > current.getRowIndex()) {
                        between = mymaze.findPosition(current.getRowIndex()+1, current.getColumnIndex());
                        if (between != null) {
                            between.setVisited(true);
                            between.setIsWall(false);
                        }
                    }
                    else{
                        between = mymaze.findPosition(current.getRowIndex()-1, current.getColumnIndex());
                        if(between!= null){
                            between.setVisited(true);
                            between.setIsWall(false);
                        }
                    }
                }
                positionStack.push(choosenNeighbour);
            }
        }
        for(int i=0;i<mymaze.getRows();i++){
            for(int j=0;j<mymaze.getColumns();j++){
                if(!mymaze.getPosArr()[i][j].getIsWall())
                    mymaze.getMazeArr()[i][j] = 0;
            }
        }

    return mymaze;



    }

}
