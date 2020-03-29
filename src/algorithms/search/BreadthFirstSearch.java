package algorithms.search;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {

     private Queue<AState> queue;
    public BreadthFirstSearch(String algo_name) {
        super(algo_name);
        queue = new LinkedList<AState>();
    }

    public Queue<AState> getQueue() {
        return queue;
    }

    public void setQueue(Queue<AState> queue) {
        this.queue = queue;
    }

    @Override
    public Solution solve(ISearchable domain) {
        Solution sol = new Solution();
        LinkedList<AState> visitedTracker = new LinkedList<>();
        queue.add(domain.getStartState());
        domain.getStartState().setVisited(true); //Starting from the first state
        visitedTracker.add(domain.getStartState());

        while(!queue.isEmpty()){
            AState cur = queue.poll();
            if(cur.equals(domain.getGoalState())) {
                AState backTracker = cur;
                while(backTracker != null){
                    sol.getSolutionPath().add(backTracker);
                    backTracker = backTracker.getCameFrom();
                }
                sol.reversePath();
                for(int i=0;i<visitedTracker.size();i++)
                    visitedTracker.get(i).setVisited(false);
                return sol;
            }
            for(int i=0;i<domain.getAllPossibleStates(cur).size();i++){
                if(!domain.getAllPossibleStates(cur).get(i).isVisited()){
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

}
