package algorithms.search;
import java.util.Objects;

/**
 * An abstract state class.
 * a basic building block which all searching algorithms can work with,
 * regardless of subclasses implementation.
 */
public abstract class AState {
    private String name;
    private double cost;
    private AState cameFrom;
    private boolean visited;

    public AState(String name) {
        this.name = name;
        this.visited = false;
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
        return name;
    }

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


