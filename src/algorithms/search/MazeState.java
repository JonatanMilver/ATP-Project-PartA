package algorithms.search;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class MazeState extends AState {

    private Position current_position;
    private ArrayList<AState> moveable_states;


    public MazeState(Position current_position, String name) {
        super(name);
        this.current_position = current_position;
        this.moveable_states = new ArrayList<>();

    }

    public Position getCurrent_position() {
        return current_position;
    }

    public void setCurrent_position(Position current_position) {
        this.current_position = current_position;
    }

    public ArrayList<AState> getMoveable_states() {
        return moveable_states;
    }

    public void setMoveable_states(ArrayList<AState> moveable_states) {
        this.moveable_states = moveable_states;
    }


}
