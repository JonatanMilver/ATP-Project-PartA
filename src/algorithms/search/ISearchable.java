package algorithms.search;

import java.util.ArrayList;

/**
 * An Interface for a searchable domain
 * which can be inserted to a searching algorithm
 */
public interface ISearchable {

    AState getStartState();
    AState getGoalState();
    ArrayList<AState> getAllPossibleStates(AState cur_state);
}
