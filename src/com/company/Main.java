package com.company;

import algorithms.mazeGenerators.*;

public class Main {

    public static void main(String[] args) {
        AMazeGenerator s = new MyMazeGenerator();

        Maze mazz = s.generate(5,10);
        mazz.print();

        System.out.println(s.measureAlgorithmTimeMillis(1000,1000));
    }
}
