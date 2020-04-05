package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

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
                AState backTracker = cur;
                while(backTracker != null){
                    sol.getSolutionPath().add(backTracker);
                    backTracker = backTracker.getCameFrom();
                }
                sol.reversePath();
                for (AState aState : visitedTrack) aState.setVisited(false);
                return sol;
            }
            if(!cur.isVisited()){
                cur.setVisited(true);
                visitedTrack.add(cur);
//                if(cur.getPredecessor() != null){
//                    cur.getPredecessor().setVisited(true);
//                    visitedTrack.add(cur.getPredecessor());
//                }
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
