package algorithms.search;
import algorithms.mazeGenerators.Position;
import java.util.ArrayList;
import java.util.Objects;

public abstract class AState {
    private String name;
    private double cost;
    private AState cameFrom;

    public AState(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState aState = (AState) o;
        return Objects.equals(name, aState.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, cameFrom);
    }
}
