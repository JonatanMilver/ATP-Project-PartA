package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

/**
 * A Depth First Search algorithm implementation.
 */
public class DepthFirstSearch extends ASearchingAlgorithm {
    public DepthFirstSearch() {
        super();
        this.setAlgo_name("DFS");
    }

    @Override
    public Solution solve(ISearchable domain) {
        if(domain == null)
            return null;
        Solution sol = new Solution();
        ArrayList<AState> visitedTrack = new ArrayList<>();
        Stack<AState> stack = new Stack<>();
        stack.push(domain.getStartState());

        while(!stack.isEmpty()){
            AState cur = stack.pop();
            if(cur.equals(domain.getGoalState())){
                sol = buildSolution(cur);
                resetDomain(visitedTrack);
                return sol;
            }
            if(!cur.isVisited()){
                cur.setVisited(true);
                visitedTrack.add(cur);

                ArrayList<AState> possibleNeighbours = domain.getAllPossibleStates(cur);
                for (AState possibleNeighbour : possibleNeighbours) {
                    stack.push(possibleNeighbour);
                    increaseVisited();
                    if (!possibleNeighbour.isVisited()) {
                        possibleNeighbour.setCameFrom(cur);
                    }
                }
            }
        }
        return sol;
    }
}
