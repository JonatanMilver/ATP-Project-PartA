package algorithms.search;
import algorithms.mazeGenerators.Position;
import java.util.ArrayList;
import java.util.Objects;

public abstract class AState {
    private String name;
    private double cost;
    private AState cameFrom;
//    private AState up_state;
//    private AState down_state;
//    private AState left_state;
//    private AState right_state;
    private boolean visited;
    private AState predecessor;

    public AState(String name) {
        this.name = name;
//        this.up_state = null;
//        this.down_state=null;
//        this.left_state=null;
//        this.right_state=null;
        this.visited = false;
        this.predecessor = null;
        this.setCost(Double.MAX_VALUE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState aState = (AState) o;
        return Objects.equals(name, aState.name);
    }

    @Override
    public String toString() {
        return
                  name
                ;
    }

//    public AState getUp_state() {
//        return up_state;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

//    public void setUp_state(AState up_state) {
//        this.up_state = up_state;
//    }

//    public AState getDown_state() {
//        return down_state;
//    }
//
//    public void setDown_state(AState down_state) {
//        this.down_state = down_state;
//    }

    public AState getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(AState predecessor) {
        this.predecessor = predecessor;
    }

//    public AState getLeft_state() {
//        return left_state;
//    }
//
//    public void setLeft_state(AState left_state) {
//        this.left_state = left_state;
//    }
//
//    public AState getRight_state() {
//        return right_state;
//    }
//
//    public void setRight_state(AState right_state) {
//        this.right_state = right_state;
//    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, cost, cameFrom);
    }
}


