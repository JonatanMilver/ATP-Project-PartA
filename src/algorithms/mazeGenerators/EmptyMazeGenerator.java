package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {



    @Override
    public Maze generate(int rows, int columns) {
        Position start = new Position(0,0);
        Position end = new Position(rows-1,columns-1);
        Maze maze =new Maze(rows, columns,start,end);
        for(int i=0;i<maze.getPosArr().length;i++){
            for(int j=0;j<maze.getPosArr()[0].length;j++){
                maze.getPosArr()[i][j].setMovable_neighbours(maze.getPosArr()[i][j].getNeighbours());
            }
        }
        return maze;
    }
}
