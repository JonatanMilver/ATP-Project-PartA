package com.company;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;

public class Main {

    public static void main(String[] args) {
        MyMazeGenerator s = new MyMazeGenerator();
        Maze mazz = s.generate(4,4);
        System.out.println(mazz);
        System.out.println(s.measureAlgorithmsTimeMillis(10,10));
    }
}
