package algorithms.mazeGenerators;

import javafx.geometry.Pos;

import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        Maze mymaze = new Maze(rows,columns,new Position(0,0),new Position(2*rows,2*columns));
        for (int i=0;i<mymaze.getRows();i++){
            for (int j=0; j<mymaze.getColumns();j++){
                mymaze.getMazeArr()[i][j]=1;
            }
        }

        Stack<Position> stack = new Stack<Position>();
        stack.push(mymaze.getStartPosition());

        while (!stack.empty()){
            Position cur = stack.pop();
            Position choosenNeighbour = new Position(0,0);
            mymaze.getMazeArr()[cur.getRowIndex()][cur.getColumnIndex()] = 0;
            //System.out.println(mymaze.getMazeArr()[cur.getRowIndex()][cur.getColumnIndex()]);
            cur.setVisited(true);
            boolean notAllVisited = false;
            System.out.println(cur.getNeighbours().size());
            System.out.println(mymaze.getPosarr().length);
            for(int i=0; i<cur.getNeighbours().size();i++){
                if(!cur.getNeighbours().get(i).getVisited())
                    notAllVisited = true;
            }
            if(!notAllVisited)
                continue;
            while(notAllVisited){
                int randomChoose = new Random().nextInt(cur.getNeighbours().size());
                choosenNeighbour = cur.getNeighbours().get(randomChoose);
                if(!choosenNeighbour.getVisited())
                    break;
            }
            if(choosenNeighbour.getRowIndex() == cur.getRowIndex()){
                if(choosenNeighbour.getColumnIndex() > cur.getColumnIndex()) {
                    mymaze.getMazeArr()[cur.getRowIndex()][cur.getColumnIndex() + 1] = 0;
                    mymaze.getMazeArr()[cur.getRowIndex()][cur.getColumnIndex() + 2] = 0;
                }
                else{
                    mymaze.getMazeArr()[cur.getRowIndex()][cur.getColumnIndex() - 1] = 0;
                    mymaze.getMazeArr()[cur.getRowIndex()][cur.getColumnIndex() - 2] = 0;
                }
            }
            else if(choosenNeighbour.getColumnIndex() == cur.getColumnIndex()){
                if(choosenNeighbour.getRowIndex() > cur.getRowIndex()) {
                    mymaze.getMazeArr()[cur.getRowIndex() + 1][cur.getColumnIndex()] = 0;
                    mymaze.getMazeArr()[cur.getRowIndex() + 2][cur.getColumnIndex()] = 0;
                }
                else{
                    mymaze.getMazeArr()[cur.getRowIndex() - 1][cur.getColumnIndex()] = 0;
                    mymaze.getMazeArr()[cur.getRowIndex() - 2][cur.getColumnIndex()] = 0;
                }
            }
            choosenNeighbour.setVisited(true);
            stack.push(choosenNeighbour);
        }
        return mymaze;

    }
}
