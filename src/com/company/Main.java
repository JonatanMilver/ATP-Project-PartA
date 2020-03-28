package com.company;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        AMazeGenerator s = new MyMazeGenerator();
        Maze mazz = s.generate(5,12);
        mazz.print();
        ISearchable a = new SearchableMaze(mazz);
        ISearchingAlgorithm search = new BreadthFirstSearch("BFS");
        ISearchingAlgorithm search2 = new DepthFirstSearch("DFS");
        Solution sol = search.solve(a);
        Solution sol2 = search2.solve(a);
        System.out.println(mazz.getStartPosition());
        System.out.println(mazz.getGoalPosition());
        sol.print();
        System.out.println();
        sol2.print();


//        System.out.println(s.measureAlgorithmTimeMillis(1000,1000));
    }
}
