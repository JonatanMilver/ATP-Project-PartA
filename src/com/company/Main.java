package com.company;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        AMazeGenerator s = new MyMazeGenerator();
        Maze mazz = s.generate(50,100);
        mazz.print();
        ISearchable a = new SearchableMaze(mazz);
        ISearchingAlgorithm search = new BreadthFirstSearch("BFS");
//        ISearchingAlgorithm search = new DepthFirstSearch("DFS");
        Solution sol = search.solve(a);
        System.out.println(mazz.getStartPosition());
        System.out.println(mazz.getGoalPosition());
        sol.print();


//        System.out.println(s.measureAlgorithmTimeMillis(1000,1000));
    }
}
