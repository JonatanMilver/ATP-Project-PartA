package com.company;

import algorithms.mazeGenerators.*;

public class Main {

    public static void main(String[] args) {
        AMazeGenerator s = new MyMazeGenerator();
        //Maze mazz = s.generate(1000,1000);
        //Maze mazz = new Maze(100,100, new Position(0,0) , new Position(99,99));
        //System.out.println(mazz.getPosArr());
        //System.out.println(mazz);
        System.out.println(s.measureAlgorithmsTimeMillis(1000,1000));
    }
}
