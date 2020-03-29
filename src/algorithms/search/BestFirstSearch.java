package algorithms.search;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class BestFirstSearch extends ASearchingAlgorithm {

    public BestFirstSearch(String algo_name) {
        super(algo_name);
    }

    public Solution solve(ISearchable domain) {
        Solution sol = new Solution();
        LinkedList<AState> visitedTracker = new LinkedList<>();
        PriorityQueue<AState> priorityQueue = new PriorityQueue<>(new BestComparator());

        priorityQueue.add(domain.getStartState());
        while (!priorityQueue.isEmpty()){
            AState cur = priorityQueue.poll();
            if (cur.equals(domain.getGoalState())){
                AState backTracker = cur;
                while(backTracker != null){
                    sol.getSolutionPath().add(backTracker);
                    backTracker = backTracker.getCameFrom();
                }
                sol.reversePath();
                return sol;
            }
            for(int i=0;i<domain.getAllPossibleStates(cur).size();i++){
                if(!domain.getAllPossibleStates(cur).get(i).isVisited()){
                    domain.getAllPossibleStates(cur).get(i).setVisited(true); //Setting neighbours as visited
                    visitedTracker.add(domain.getAllPossibleStates(cur).get(i));
                    increaseVisited();
                    domain.getAllPossibleStates(cur).get(i).setCameFrom(cur); //Setting current as parent
                    priorityQueue.add(domain.getAllPossibleStates(cur).get(i));
                }
            }
            cur.setVisited(true);
            visitedTracker.add(cur);
        }

        return sol;
    }
}

class BestComparator implements Comparator<AState> {
    @Override
    public int compare(AState o1, AState o2) {
        if (o1.getCost() > o2.getCost()){
            return 1;
        }
        else if (o1.getCost() < o2.getCost()){
            return -1;
        }
        else {
            return 0;
        }
    }
}
