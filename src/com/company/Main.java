package com.company;

import algorithms.mazeGenerators.*;
import algorithms.search.ISearchable;
import algorithms.search.SearchableMaze;

public class Main {

    public static void main(String[] args) {
        AMazeGenerator s = new MyMazeGenerator();
        Maze mazz = s.generate(5,10);
        mazz.print();
        ISearchable a = new SearchableMaze(mazz);


//        System.out.println(s.measureAlgorithmTimeMillis(1000,1000));
    }
}
