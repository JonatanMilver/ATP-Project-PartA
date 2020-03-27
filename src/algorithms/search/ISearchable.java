package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {

    public AState getStartState();
    public AState getGoalState();
    public ArrayList<AState> getAllPossibleStates(AState cur_state);
}
