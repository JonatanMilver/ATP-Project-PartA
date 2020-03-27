package algorithms.search;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class MazeState extends AState {

    Position current_position;
    ArrayList<AState> moveable_states;

    public MazeState(Position current_position,String name) {
        super(name);
        this.current_position = current_position;
    }
}
