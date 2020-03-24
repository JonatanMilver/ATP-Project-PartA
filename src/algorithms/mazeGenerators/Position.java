package algorithms.mazeGenerators;

import java.util.ArrayList;

public class Position {
    private int row;
    private int column;
    private boolean visited;
    private boolean isWall;
    private ArrayList<Position> neighbours;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.visited = false;
        this.isWall = true;
        this.neighbours = new ArrayList<Position>();
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean getVisited(){ return this.visited; }
    public boolean isIsWall() {
        return isWall;
    }

    public void setIsWall(boolean iswall) {
        this.isWall = iswall;
    }
    public boolean getIsWall(){return this.isWall;}

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
        if(otherPos.getRowIndex() == this.getRowIndex() && otherPos.getColumnIndex() == this.getColumnIndex()+2)
            this.neighbours.add(otherPos);
        else if(otherPos.getRowIndex() == this.getRowIndex() && otherPos.getColumnIndex() == this.getColumnIndex()-2)
            this.neighbours.add(otherPos);
        else if(otherPos.getColumnIndex() == this.getColumnIndex() && otherPos.getRowIndex() == this.getRowIndex()+2)
            this.neighbours.add(otherPos);
        else if(otherPos.getColumnIndex() == this.getColumnIndex() && otherPos.getRowIndex() == this.getRowIndex()-2)
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
