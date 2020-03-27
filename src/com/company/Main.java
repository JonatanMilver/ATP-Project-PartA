package com.company;

import algorithms.mazeGenerators.*;

public class Main {

    public static void main(String[] args) {
        AMazeGenerator s = new MyMazeGenerator();
        AMazeGenerator ss = new SimpleMazeGenerator();

        Maze mazz = ss.generate(5,10);
        mazz.print();

//        System.out.println(s.measureAlgorithmTimeMillis(1000,1000));
    }
}
