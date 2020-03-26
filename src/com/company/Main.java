package com.company;

import algorithms.mazeGenerators.*;

public class Main {

    public static void main(String[] args) {
        AMazeGenerator s = new MyMazeGenerator();
        Maze mazz = s.generate(5,10);
        //Maze mazz = new Maze(100,100, new Position(0,0) , new Position(99,99));
        //System.out.println(mazz.getPosArr());
        mazz.print();
        System.out.println(s.measureAlgorithmTimeMillis(500,500));
    }
}
