package com.company;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.SimpleMazeGenerator;

public class Main {

    public static void main(String[] args) {
        SimpleMazeGenerator s = new SimpleMazeGenerator();
        Maze mazz = s.generate(30,40);
        System.out.println(mazz);
        System.out.println(s.measureAlgorithmsTimeMillis(300,400));
    }
}
