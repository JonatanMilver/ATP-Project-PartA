package com.company;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AMazeGenerator s = new MyMazeGenerator();
        Maze mazz = null;
//        for (int i = 1; i < 100; i++) {
//            for (int j = 1; j < 100; j++) {
//                mazz = s.generate(i,j);
//                //mazz.print();
//                ISearchable a = new SearchableMaze(mazz);
//                ISearchingAlgorithm search = new BreadthFirstSearch("BFS");
//                Solution sol = search.solve(a);
//                System.out.println(mazz.getStartPosition());
//                System.out.println(mazz.getGoalPosition());
//                sol.print();
//            }
//        }
        mazz = s.generate(5,10);
        mazz.print();
        ISearchable a = new SearchableMaze(mazz);
//        a.getAllPossibleStates(a.getStartState());
//        ISearchingAlgorithm search = new BreadthFirstSearch("BFS");
        ISearchingAlgorithm search3 = new BestFirstSearch("Best_FS");
//        ISearchingAlgorithm search2 = new DepthFirstSearch("DFS");
        Solution sol = search3.solve(a);
//        Solution sol2 = search2.solve(a);
        System.out.println(mazz.getStartPosition());
        System.out.println(mazz.getGoalPosition());
        sol.print();

//        sol2.print();


//        System.out.println(s.measureAlgorithmTimeMillis(1000,1000));
    }
}
