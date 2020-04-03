package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
        catch(Exception ignored){};




    }

    private void setCosts() {
        this.StartState.setCost(0);
        Queue<AState> queue = new LinkedList<>();
        queue.add(this.StartState);
        while (!queue.isEmpty()){
            AState current = queue.poll();
            ArrayList<AState> possible_neighbours = getAllPossibleStates(current);
            for (AState possible_neighbour : possible_neighbours) {
                if (possible_neighbour == StartState)
                    continue;
                if (possible_neighbour.getCost() == Double.MAX_VALUE){
                    queue.add(possible_neighbour);
                }
                ArrayList<AState> neighbour_possible_neighbours = getAllPossibleStates(possible_neighbour);
                AState min = neighbour_possible_neighbours.get(0);
                for ( AState a : neighbour_possible_neighbours){
                    if (min.getCost()>a.getCost()){
                        min = a;
                    }
                }
                int x;
                if (possible_neighbour.getUp_state() == min || possible_neighbour.getRight_state() == min ||
                        possible_neighbour.getDown_state() == min || possible_neighbour.getLeft_state() == min)
                    x = 10;
                else
                    x = 15;

                possible_neighbour.setCost(x+min.getCost());

            }
        }
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
        if(cur_state == null)
            return null;
        ArrayList<AState> p_states = new ArrayList<>();
        if(cur_state.getUp_state() != null) {

//            if (cur_state.getUp_state().getCost()==0 && !cur_state.getUp_state().equals(this.StartState))
//                cur_state.getUp_state().setCost(10+cur_state.getCost());

            if(cur_state.getUp_state().getLeft_state() != null){
                p_states.add(cur_state.getUp_state().getLeft_state());
                cur_state.getUp_state().getLeft_state().setPredecessor(cur_state.getUp_state());
//                if (cur_state.getUp_state().getLeft_state().getCost()==0 && !cur_state.getUp_state().getLeft_state().equals(this.StartState)) {
//                    cur_state.getUp_state().getLeft_state().setCost(15 + cur_state.getCost());
//                }
            }
            if(cur_state.getUp_state().getRight_state() != null) {
                p_states.add(cur_state.getUp_state().getRight_state());
                cur_state.getUp_state().getRight_state().setPredecessor(cur_state.getUp_state());
//                if (cur_state.getUp_state().getRight_state().getCost()==0 && !cur_state.getUp_state().getRight_state().equals(this.StartState)){
//                    cur_state.getUp_state().getRight_state().setCost(15+cur_state.getCost());
//                }
            }
            p_states.add(cur_state.getUp_state());
        }
        if(cur_state.getRight_state() != null) {

//            if (cur_state.getRight_state().getCost()==0 && !cur_state.getRight_state().equals(this.StartState)){
//                cur_state.getRight_state().setCost(10+cur_state.getCost());
//            }
            if(cur_state.getRight_state().getUp_state() != null) {
                p_states.add(cur_state.getRight_state().getUp_state());
                cur_state.getRight_state().getUp_state().setPredecessor(cur_state.getRight_state());
//                if (cur_state.getRight_state().getUp_state().getCost()==0 && !cur_state.getRight_state().getUp_state().equals(this.StartState)){
//                    cur_state.getRight_state().getUp_state().setCost(15+cur_state.getCost());
//                }
            }
            if(cur_state.getRight_state().getDown_state() != null) {
                p_states.add(cur_state.getRight_state().getDown_state());
                cur_state.getRight_state().getDown_state().setPredecessor(cur_state.getRight_state());
//                if (cur_state.getRight_state().getDown_state().getCost()==0 && !cur_state.getRight_state().getDown_state().equals(this.StartState)){
//                    cur_state.getRight_state().getDown_state().setCost(15+cur_state.getCost());
//                }
            }
            p_states.add(cur_state.getRight_state());
        }

        if(cur_state.getDown_state() != null) {

//            if (cur_state.getDown_state().getCost()==0 && !cur_state.getDown_state().equals(this.StartState))
//                cur_state.getDown_state().setCost(10+cur_state.getCost());

            if(cur_state.getDown_state().getLeft_state() != null){
                p_states.add(cur_state.getDown_state().getLeft_state());
                cur_state.getDown_state().getLeft_state().setPredecessor(cur_state.getDown_state());
//                if (cur_state.getDown_state().getLeft_state().getCost()==0 && !cur_state.getDown_state().getLeft_state().equals(this.StartState)) {
//                    cur_state.getDown_state().getLeft_state().setCost(15 + cur_state.getCost());
//                }
            }
            if(cur_state.getDown_state().getRight_state() != null) {
                p_states.add(cur_state.getDown_state().getRight_state());
                cur_state.getDown_state().getRight_state().setPredecessor(cur_state.getDown_state());
//                if (cur_state.getDown_state().getRight_state().getCost()==0 && !cur_state.getDown_state().getRight_state().equals(this.StartState)){
//                    cur_state.getDown_state().getRight_state().setCost(15+cur_state.getCost());
//                }
            }
            p_states.add(cur_state.getDown_state());
        }



        if(cur_state.getLeft_state() != null) {

//            if (cur_state.getLeft_state().getCost()==0 && !cur_state.getLeft_state().equals(this.StartState)){
//                cur_state.getLeft_state().setCost(10+cur_state.getCost());
//            }
            if(cur_state.getLeft_state().getUp_state() != null) {
                p_states.add(cur_state.getLeft_state().getUp_state());
                cur_state.getLeft_state().getUp_state().setPredecessor(cur_state.getLeft_state());
//                if (cur_state.getLeft_state().getUp_state().getCost()==0 && !cur_state.getLeft_state().getUp_state().equals(this.StartState)){
//                    cur_state.getLeft_state().getUp_state().setCost(15+cur_state.getCost());
//                }
            }
            if(cur_state.getLeft_state().getDown_state() != null) {
                p_states.add(cur_state.getLeft_state().getDown_state());
                cur_state.getLeft_state().getDown_state().setPredecessor(cur_state.getLeft_state());
//                if (cur_state.getLeft_state().getDown_state().getCost()==0 && !cur_state.getLeft_state().getDown_state().equals(this.StartState)){
//                    cur_state.getLeft_state().getDown_state().setCost(15+cur_state.getCost());
//                }
            }
            p_states.add(cur_state.getLeft_state());
        }

        return p_states;
    }

}
