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
        ISearchingAlgorithm bfs = new BreadthFirstSearch();
        ISearchingAlgorithm best = new BestFirstSearch();
        ISearchingAlgorithm dfs = new DepthFirstSearch();

        Solution bfsSol = bfs.solve(a);
        Solution bestSol = best.solve(a);
        Solution dfsSol = dfs.solve(a);

        System.out.println(mazz.getStartPosition());
        System.out.println(mazz.getGoalPosition());

        System.out.println("BFS");
        bfsSol.print();
//        System.out.println("bfsSol size: "+bfsSol.getSolutionPath().size());
//        System.out.println("nodes bfs: "+bfs.getNumberOfNodesEvaluated());
//
        System.out.println("BEST");
        bestSol.print();
//        System.out.println("bestSol size: "+bestSol.getSolutionPath().size());
//        System.out.println("nodes best: "+best.getNumberOfNodesEvaluated());


        System.out.println("DFS");
        dfsSol.print();

    }
}
