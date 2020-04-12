package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A Breadth First Search algorithm implementation.
 * Uses a Linked List implementation of a Queue.
 */
public class BreadthFirstSearch extends ASearchingAlgorithm {

    protected Queue<AState> queue;

    public BreadthFirstSearch() {
        super();
        this.setAlgo_name("BFS");
        queue = new LinkedList<>();
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

                    sol = buildSolution(cur);
                    resetDomain(visitedTracker);
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
