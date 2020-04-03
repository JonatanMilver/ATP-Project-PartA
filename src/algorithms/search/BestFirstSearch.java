package algorithms.search;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch() {
        super();
        this.setAlgo_name("Best First Search");
        queue = new PriorityQueue<AState>((x,y) -> (int)(x.getCost()-y.getCost()));
//        queue = new PriorityQueue<AState>(new BestComparator());
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
