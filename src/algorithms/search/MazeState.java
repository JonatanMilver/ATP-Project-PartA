package algorithms.search;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

/**
 * A sub class of AState.
 * implemented specifically for maze states,
 * which can have four possible neighbours at all sides.
 */
public class MazeState extends AState {

    private Position current_position;
    private ArrayList<MazeState> moveable_states;

    private MazeState up_state;
    private MazeState down_state;
    private MazeState left_state;
    private MazeState right_state;

    public MazeState(Position current_position, String name) {
        super(name);
        this.current_position = current_position;
        this.moveable_states = new ArrayList<>();

        this.up_state = null;
        this.down_state=null;
        this.left_state=null;
        this.right_state=null;

    }

    public Position getCurrent_position() {
        return current_position;
    }

    public ArrayList<MazeState> getMoveable_states() {
        return moveable_states;
    }

    public MazeState getUp_state() {
        return up_state;
    }

    public void setUp_state(MazeState up_state) {
        this.up_state = up_state;
    }

    public MazeState getDown_state() {
        return down_state;
    }

    public void setDown_state(MazeState down_state) {
        this.down_state = down_state;
    }

    public MazeState getLeft_state() {
        return left_state;
    }

    public void setLeft_state(MazeState left_state) {
        this.left_state = left_state;
    }

    public MazeState getRight_state() {
        return right_state;
    }

    public void setRight_state(MazeState right_state) {
        this.right_state = right_state;
    }

}
