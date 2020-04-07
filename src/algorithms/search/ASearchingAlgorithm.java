package algorithms.search;

import java.util.ArrayList;

/**
 *An abstract class that implements ISearchingAlgorithm.
 *Other classes can extend this class and make various types of search algorithms.
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    private String algo_name;
    private int visitedNode;

    public ASearchingAlgorithm() {
        this.algo_name = "default";
        this.visitedNode = 0;
    }

    public void setAlgo_name(String algo_name) {
        this.algo_name = algo_name;
    }

    public String getName() {
        return algo_name;
    }

    public int getNumberOfNodesEvaluated(){
        return visitedNode;
    }

    public void increaseVisited(){
        this.visitedNode++;
    }

    Solution buildSolution(AState cur){
        Solution sol = new Solution();
        while (cur != null) {
            sol.getSolutionPath().add(cur);
            cur = cur.getCameFrom();
        }
        sol.reversePath();
        double sol_cost = sol.getSolutionPath().size();
        sol.setSolutionCost(sol_cost);
        return sol;
    }

    void resetDomain(ArrayList<AState> visitedTracker){
        for (AState State : visitedTracker)
            State.setVisited(false);
    }
}