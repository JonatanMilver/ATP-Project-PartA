package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {
    public BestFirstSearch(String algo_name) {
        super(algo_name);
        queue = new PriorityQueue<AState>(new BestComparator());
    }

//    public Solution solve(ISearchable domain) {
//        Solution sol = new Solution();
//
//        PriorityQueue<AState> priorityQueue = new PriorityQueue<>(new BestComparator());
//
//        priorityQueue.add(domain.getStartState());
//        while (!priorityQueue.isEmpty()){
//            AState cur = priorityQueue.poll();
//            if (cur.equals(domain.getGoalState())){
//                AState backTracker = cur;
//                while(backTracker != null){
//                    sol.getSolutionPath().add(backTracker);
//                    backTracker = backTracker.getCameFrom();
//                }
//                sol.reversePath();
//                return sol;
//            }
////            for ()
//        }
//
//        return null;
//    }
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
