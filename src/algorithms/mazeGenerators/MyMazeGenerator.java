package algorithms.mazeGenerators;

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
            
        }

    }
}
