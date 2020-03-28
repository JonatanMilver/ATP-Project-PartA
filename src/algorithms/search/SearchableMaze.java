package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {

    Maze maze;
    MazeState StartState;
    MazeState GoalState;
    MazeState[][] mazeStates;
    public SearchableMaze(Maze maze) {
        this.maze = maze;
        mazeStates = new MazeState[maze.getPosArr().length][maze.getPosArr()[0].length];
        buildMazeStates();
        set_neighbouring_states();
        this.StartState = mazeStates[0][0];
        this.StartState.setName("Start");
        this.GoalState = mazeStates[mazeStates.length-1][mazeStates[0].length-1];
        this.GoalState.setName("End");
    }

    private void buildMazeStates() {

        for (int i=0 ; i < mazeStates.length ; i++){
            for (int j=0 ; j < mazeStates[0].length ; j++){
                mazeStates[i][j] = new MazeState(maze.getPosArr()[i][j],maze.getPosArr()[i][j].toString());
            }
        }

        for (int i=0 ; i < mazeStates.length ; i++){
            for (int j=0 ; j < mazeStates[0].length ; j++){
                for (int k = 0 ; k < mazeStates[i][j].getCurrent_position().getMovable_neighbours().size(); k++){
                    Position moveable_neighbour = mazeStates[i][j].getCurrent_position().getMovable_neighbours().get(k);
                    int row_index = moveable_neighbour.getRowIndex();
                    int col_index = moveable_neighbour.getColumnIndex();
                    mazeStates[i][j].getMoveable_states().add(mazeStates[row_index][col_index]);
                }
            }
        }

    }

    private void set_neighbouring_states(){
        for (int i=0 ; i < mazeStates.length ; i++){
            for (int j=0 ; j < mazeStates[0].length ; j++){
                int cur_state_row = mazeStates[i][j].getCurrent_position().getRowIndex();
                int cur_state_col = mazeStates[i][j].getCurrent_position().getColumnIndex();
                for (int k = 0 ; k < mazeStates[i][j].getCurrent_position().getMovable_neighbours().size(); k++) {
                    int neighbour_state_row = mazeStates[i][j].getCurrent_position().getMovable_neighbours().get(k).getRowIndex();
                    int neighbour_state_col = mazeStates[i][j].getCurrent_position().getMovable_neighbours().get(k).getColumnIndex();
                    if (cur_state_row==neighbour_state_row){
                        if (cur_state_col < neighbour_state_col){
                            mazeStates[i][j].setRight_state(mazeStates[neighbour_state_row][neighbour_state_col]);
                        }
                        else {
                            mazeStates[i][j].setLeft_state(mazeStates[neighbour_state_row][neighbour_state_col]);
                        }
                    }
                    else {
                        if (cur_state_row < neighbour_state_row){
                            mazeStates[i][j].setDown_state(mazeStates[neighbour_state_row][neighbour_state_col]);
                        }
                        else {
                            mazeStates[i][j].setUp_state(mazeStates[neighbour_state_row][neighbour_state_col]);
                        }
                    }
                }

            }
        }
    }

    @Override
    public AState getStartState() {
        return StartState;
    }

    @Override
    public AState getGoalState() {
        return GoalState;
    }

    public ArrayList<AState> getAllPossibleStates(AState cur_state){
        ArrayList<AState> p_states = new ArrayList<>();
        if(cur_state.getUp_state() != null) {
            p_states.add(cur_state.getUp_state());
            if(cur_state.getUp_state().getLeft_state() != null){
                p_states.add(cur_state.getUp_state().getLeft_state());
            }
            if(cur_state.getUp_state().getRight_state() != null)
                p_states.add(cur_state.getUp_state().getRight_state());
        }
        if(cur_state.getDown_state() != null) {
            p_states.add(cur_state.getDown_state());
            if(cur_state.getDown_state().getLeft_state() != null){
                p_states.add(cur_state.getDown_state().getLeft_state());
            }
            if(cur_state.getDown_state().getRight_state() != null)
                p_states.add(cur_state.getDown_state().getRight_state());
        }
        if(cur_state.getRight_state() != null) {
            p_states.add(cur_state.getRight_state());
            if(cur_state.getRight_state().getUp_state() != null)
                p_states.add(cur_state.getRight_state().getUp_state());
            if(cur_state.getRight_state().getDown_state() != null)
                p_states.add(cur_state.getRight_state().getDown_state());
        }
        if(cur_state.getLeft_state() != null) {
            p_states.add(cur_state.getLeft_state());
            if(cur_state.getLeft_state().getUp_state() != null)
                p_states.add(cur_state.getLeft_state().getUp_state());
            if(cur_state.getLeft_state().getDown_state() != null)
                p_states.add(cur_state.getLeft_state().getDown_state());
        }

        return p_states;
    }

}
