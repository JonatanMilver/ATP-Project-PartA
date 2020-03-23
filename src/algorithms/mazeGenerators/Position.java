package algorithms.mazeGenerators;

import java.util.ArrayList;

public class Position {
    private int row;
    private int column;
    private boolean visited;
    private boolean iswall;
    private ArrayList<Position> neighbours;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.visited = false;
        this.iswall = true;
        this.neighbours = new ArrayList<Position>();
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean getVisited(){ return this.visited; }
    public boolean isIswall() {
        return iswall;
    }

    public void setIswall(boolean iswall) {
        this.iswall = iswall;
    }

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

    @Override
    public String toString() {
        return "{" +
                 row +
                ", " +
                column +
                '}';
    }
}
