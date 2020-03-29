package com.company;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AMazeGenerator my = new MyMazeGenerator();
        AMazeGenerator simple = new SimpleMazeGenerator();
        AMazeGenerator empty = new EmptyMazeGenerator();
        Maze mazz = null;

//        mazz = my.generate(5,10);
//        mazz.print();
//        System.out.println();
//
//        mazz = simple.generate(5,10);
//        mazz.print();
//        System.out.println();

        mazz = empty.generate(5,10);
        mazz.print();
        System.out.println();

        ISearchable a = new SearchableMaze(mazz);
        ISearchingAlgorithm bfs = new BreadthFirstSearch("BFS");
        ISearchingAlgorithm best = new BestFirstSearch("Best_FS");
        ISearchingAlgorithm dfs = new DepthFirstSearch("DFS");

        Solution bfsSol = bfs.solve(a);
        Solution bestSol = best.solve(a);
        Solution dfsSol = dfs.solve(a);

        System.out.println(mazz.getStartPosition());
        System.out.println(mazz.getGoalPosition());

//        System.out.println("BFS");
//        bfsSol.print();
//        System.out.println("bfsSol size: "+bfsSol.getSolutionPath().size());
//        System.out.println("sum bfs: "+bfsSol.getSolutionCost());
//
//        System.out.println("BEST");
//        bestSol.print();
//        System.out.println("bestSol size: "+bestSol.getSolutionPath().size());
//        System.out.println("sum best: "+bestSol.getSolutionCost());


        System.out.println("DFS");
        dfsSol.print();

    }
}
