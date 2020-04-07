package algorithms.search;

import java.util.PriorityQueue;

/**
 * A Best First Search algorithm implementation.
 * Uses the BFS solve method but replaces the Linked List Queue with a Priority Queue.
 */
public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch() {
        super();
        this.setAlgo_name("Best First Search");
        queue = new PriorityQueue<>((x,y) -> (int)(x.getCost()-y.getCost()));
    }
}