package com.company;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        AMazeGenerator s = new MyMazeGenerator();
        Maze mazz = s.generate(5,10);
        mazz.print();
        ISearchable a = new SearchableMaze(mazz);
        AState k = a.getStartState().getDown_state();
        //ISearchingAlgorithm search = new BreadthFirstSearch("BFS");
        ISearchingAlgorithm search = new DepthFirstSearch("DFS");
        Solution sol = search.solve(a);
        sol.print();
        System.out.println(search.getNumberOfNodesEvaluated());
        System.out.println(mazz.getStartPosition().getMovable_neighbours());
        System.out.println(mazz.getGoalPosition());


//        System.out.println(s.measureAlgorithmTimeMillis(1000,1000));
    }
}
