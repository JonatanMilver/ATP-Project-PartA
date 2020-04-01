package algorithms.mazeGenerators;

import java.util.ArrayList;

public class Position {
    private int row;
    private int column;
    private boolean visited;
    private ArrayList<Position> neighbours;
    private ArrayList<Position> movable_neighbours;

    public ArrayList<Position> getMovable_neighbours() {
        return movable_neighbours;
    }

    public void setMovable_neighbours(ArrayList<Position> movable_neighbours) {
        this.movable_neighbours = movable_neighbours;
    }

    public Position(int row, int column) {
        if(row<0 || column<0)
            throw new ArithmeticException("A Position must be inside the maze.");
        this.row = row;
        this.column = column;
        this.visited = false;
        this.neighbours = new ArrayList<Position>();
        this.movable_neighbours = new ArrayList<>();
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean getVisited(){ return this.visited; }



    public ArrayList<Position> getNeighbours() {
        return neighbours;
    }

    public int getRowIndex() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumnIndex() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void addNeighbour(Position otherPos){
        if(otherPos.getRowIndex() == this.getRowIndex() && otherPos.getColumnIndex() == this.getColumnIndex()+1)
            this.neighbours.add(otherPos);
        else if(otherPos.getRowIndex() == this.getRowIndex() && otherPos.getColumnIndex() == this.getColumnIndex()-1)
            this.neighbours.add(otherPos);
        else if(otherPos.getColumnIndex() == this.getColumnIndex() && otherPos.getRowIndex() == this.getRowIndex()+1)
            this.neighbours.add(otherPos);
        else if(otherPos.getColumnIndex() == this.getColumnIndex() && otherPos.getRowIndex() == this.getRowIndex()-1)
            this.neighbours.add(otherPos);
    }


    @Override
    public String toString() {
        return "{" +
                 row +
                ", " +
                column +
                '}';
    }
}
