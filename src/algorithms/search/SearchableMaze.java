package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {

    Maze maze;
    MazeState StartState;
    MazeState GoalState;
//    MazeState[][] statesArray;
    public SearchableMaze(Maze maze) {
        this.maze = maze;
        this.StartState = new MazeState(maze.getStartPosition(),"Start");
        this.GoalState = new MazeState(maze.getGoalPosition(),"End");
        buildMazeStates();
    }

    private void buildMazeStates() {
        
        for (int i=0;i<this.StartState.current_position.getMovable_neighbours().size();i++){
            String name = Integer.toString(i+1);
            MazeState new_state = new MazeState(this.StartState.current_position.getMovable_neighbours().get(i),name);
            this.StartState.moveable_states.add(new_state);
            new_state.moveable_states.add(StartState);
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

        return null;
    }

}
