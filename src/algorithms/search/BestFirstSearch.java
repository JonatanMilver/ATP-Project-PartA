package algorithms.search;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch(String algo_name) {
        super(algo_name);
        this.setQueue(new PriorityQueue<>(new BestComparator()));
    }

    public Solution solve(ISearchable domain) {
        Solution sol = new Solution();
        LinkedList<AState> visitedTracker = new LinkedList<>();
//        PriorityQueue<AState> priorityQueue = new PriorityQueue<>(new BestComparator());

        this.getQueue().add(domain.getStartState());
        while (!this.getQueue().isEmpty()){
            AState cur = this.getQueue().poll();
            if (cur.equals(domain.getGoalState())){
                AState backTracker = cur;
                while(backTracker != null){
                    sol.getSolutionPath().add(backTracker);
                    backTracker = backTracker.getCameFrom();
                }
                sol.reversePath();
                double sol_cost = 0;
                for (int i = 0; i<sol.getSolutionPath().size();i++){
                    sol_cost += sol.getSolutionPath().get(i).getCost();
                }
                sol.setSolutionCost(sol_cost);
                for(int i=0;i<visitedTracker.size();i++) {
                    visitedTracker.get(i).setVisited(false);
                    visitedTracker.get(i).setCameFrom(null);
                    visitedTracker.get(i).setCost(0);
                }
                return sol;
            }
            for(int i=0;i<domain.getAllPossibleStates(cur).size();i++){
                if(!domain.getAllPossibleStates(cur).get(i).isVisited()){
                    domain.getAllPossibleStates(cur).get(i).setVisited(true); //Setting neighbours as visited
                    visitedTracker.add(domain.getAllPossibleStates(cur).get(i));
                    increaseVisited();
                    domain.getAllPossibleStates(cur).get(i).setCameFrom(cur); //Setting current as parent
                    this.getQueue().add(domain.getAllPossibleStates(cur).get(i));
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
