package com.company;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AMazeGenerator s = new MyMazeGenerator();
        AMazeGenerator simple = new SimpleMazeGenerator();
        AMazeGenerator empty = new EmptyMazeGenerator();
        Maze mazz = null;
        //mazz = s.generate(5,10);
        mazz = empty.generate(5,10);
        mazz.print();
        ISearchable a = new SearchableMaze(mazz);
        ISearchingAlgorithm bfs = new BreadthFirstSearch("BFS");
        ISearchingAlgorithm best = new BestFirstSearch("Best_FS");
//        ISearchingAlgorithm dfs = new DepthFirstSearch("DFS");
        Solution bfsSol = bfs.solve(a);
        Solution bestSol = best.solve(a);
//        Solution dfsSol = dfs.solve(a);
        System.out.println(mazz.getStartPosition());
        System.out.println(mazz.getGoalPosition());
        System.out.println("BFS");
        bfsSol.print();
        System.out.println("BEST");
        bestSol.print();
//        System.out.println("DFS");
//        dfsSol.print();

    }
}
