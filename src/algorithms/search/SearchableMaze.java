package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A class that implements ISearchable.
 *
 */
public class SearchableMaze implements ISearchable {

    Maze maze;
    MazeState StartState;
    MazeState GoalState;
    MazeState[][] mazeStates;

    public SearchableMaze(Maze maze) {
        try {
            this.maze = maze;
            mazeStates = new MazeState[maze.getPosArr().length][maze.getPosArr()[0].length];

            buildMazeStates();
            set_neighbouring_states();

            this.StartState = mazeStates[maze.getStartPosition().getRowIndex()][maze.getStartPosition().getColumnIndex()];
            this.StartState.setName("Start");

            this.GoalState = mazeStates[maze.getGoalPosition().getRowIndex()][maze.getGoalPosition().getColumnIndex()];
            this.GoalState.setName("End");

            setCosts();
        }
        catch(Exception ignored){
            System.out.println("SearchableMaze Constructor Exception");
        }
    }

    private void buildMazeStates() {

        for (int i=0 ; i < mazeStates.length ; i++){
            for (int j=0 ; j < mazeStates[0].length ; j++){
                mazeStates[i][j] = new MazeState(maze.getPosArr()[i][j],maze.getPosArr()[i][j].toString());
            }
        }

        for (MazeState[] mazeState : mazeStates) {
            for (int j = 0; j < mazeStates[0].length; j++) {
                for (int k = 0; k < mazeState[j].getCurrent_position().getMovable_neighbours().size(); k++) {
                    Position moveable_neighbour = mazeState[j].getCurrent_position().getMovable_neighbours().get(k);
                    int row_index = moveable_neighbour.getRowIndex();
                    int col_index = moveable_neighbour.getColumnIndex();
                    mazeState[j].getMoveable_states().add(mazeStates[row_index][col_index]);
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

    private void setCosts() {
        this.StartState.setCost(0);
        Queue<MazeState> queue = new LinkedList<>();
        queue.add(this.StartState);
        while (!queue.isEmpty()){
            AState current = queue.poll();
            ArrayList<AState> possible_neighbours = getAllPossibleStates(current);
            for (AState possibleNeighbour : possible_neighbours) {
                MazeState possible_neighbour = (MazeState) possibleNeighbour;
                if (possible_neighbour == StartState)
                    continue;
                if (possible_neighbour.getCost() == Double.MAX_VALUE) {
                    queue.add(possible_neighbour);
                }
                ArrayList<AState> neighbour_possible_neighbours = getAllPossibleStates(possible_neighbour);
                AState min = neighbour_possible_neighbours.get(0);
                for (AState a : neighbour_possible_neighbours) {
                    if (min.getCost() > a.getCost()) {
                        min = a;
                    }
                }
                int x;
                if (possible_neighbour.getUp_state() == min || possible_neighbour.getRight_state() == min ||
                        possible_neighbour.getDown_state() == min || possible_neighbour.getLeft_state() == min)
                    x = 10;
                else
                    x = 15;

                possible_neighbour.setCost(x + min.getCost());

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

    /**
     * @param cur_state the current state which its neighbouring states you want to discover.
     * @return an ArrayList of all cur_states neighbours.
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState cur_state){
        if(cur_state == null)
            return null;
        ArrayList<AState> p_states = new ArrayList<>();
        MazeState cur = (MazeState) cur_state;

        if(cur.getUp_state() != null) {
            if(cur.getUp_state().getLeft_state() != null){
                p_states.add(cur.getUp_state().getLeft_state());
            }
            if(cur.getUp_state().getRight_state() != null) {
                p_states.add(cur.getUp_state().getRight_state());
            }
            p_states.add(cur.getUp_state());
        }

        if(cur.getRight_state() != null) {
            if(cur.getRight_state().getUp_state() != null) {
                p_states.add(cur.getRight_state().getUp_state());
            }
            if(cur.getRight_state().getDown_state() != null) {
                p_states.add(cur.getRight_state().getDown_state());
            }
            p_states.add(cur.getRight_state());
        }

        if(cur.getDown_state() != null) {

            if(cur.getDown_state().getLeft_state() != null){
                p_states.add(cur.getDown_state().getLeft_state());
            }
            if(cur.getDown_state().getRight_state() != null) {
                p_states.add(cur.getDown_state().getRight_state());
            }
            p_states.add(cur.getDown_state());
        }

        if(cur.getLeft_state() != null) {

            if(cur.getLeft_state().getUp_state() != null) {
                p_states.add(cur.getLeft_state().getUp_state());
            }
            if(cur.getLeft_state().getDown_state() != null) {
                p_states.add(cur.getLeft_state().getDown_state());
            }
            p_states.add(cur.getLeft_state());
        }

        return p_states;
    }

}
