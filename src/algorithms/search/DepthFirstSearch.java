package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    public DepthFirstSearch(String algo_name) {
        super(algo_name);
    }

    @Override
    public Solution solve(ISearchable domain) {
        Solution sol = new Solution();
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
                return sol;
            }
            if(!cur.isVisited()){
                cur.setVisited(true);
                ArrayList<AState> possibleNeighbours = domain.getAllPossibleStates(cur);
                for(int i=0;i<possibleNeighbours.size();i++){
                    stack.push(possibleNeighbours.get(i));
                    increaseVisited();
                    if(!possibleNeighbours.get(i).isVisited())
                        possibleNeighbours.get(i).setCameFrom(cur);
                }
            }
        }
        return sol;
    }
}
