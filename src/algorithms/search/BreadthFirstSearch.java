package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {

    protected Queue<AState> queue;

    public BreadthFirstSearch() {
        super();
        this.setAlgo_name("BFS");
        queue = new LinkedList<>();
    }

    public Queue<AState> getQueue() {
        return queue;
    }

    public void setQueue(Queue<AState> queue) {
        this.queue = queue;
    }

    @Override
    public Solution solve(ISearchable domain) {

        if(domain == null)
            return null;
        try {
            Solution sol = new Solution();
            ArrayList<AState> visitedTracker = new ArrayList<>();
            queue.add(domain.getStartState());
            domain.getStartState().setVisited(true); //Starting from the first state
            visitedTracker.add(domain.getStartState());

            while (!queue.isEmpty()) {
                AState cur = queue.poll();
                if (cur.equals(domain.getGoalState())) {
                    AState backTracker = cur;
                    while (backTracker != null) {
                        sol.getSolutionPath().add(backTracker);
                        backTracker = backTracker.getCameFrom();
                    }
                    sol.reversePath();
                    double sol_cost = sol.getSolutionPath().size();
                    sol.setSolutionCost(sol_cost);
                    for (AState aState : visitedTracker) {
                        aState.setVisited(false);
                        aState.setCameFrom(null);
                    }
                    return sol;
                }

                for (int i = 0; i < domain.getAllPossibleStates(cur).size(); i++) {
                    if (!domain.getAllPossibleStates(cur).get(i).isVisited()) {
                        domain.getAllPossibleStates(cur).get(i).setVisited(true); //Setting neighbours as visited
                        visitedTracker.add(domain.getAllPossibleStates(cur).get(i));
                        increaseVisited();
                        domain.getAllPossibleStates(cur).get(i).setCameFrom(cur); //Setting current as parent
                        queue.add(domain.getAllPossibleStates(cur).get(i));
                    }
                }
            }
            return sol;
        }
        catch(Exception e){
            System.out.println("BFS - Solve method Exception");
            return null;
        }

    }

}
