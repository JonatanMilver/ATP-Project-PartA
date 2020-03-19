package com.company;

import algorithms.mazeGenerators.Maze;

public class Main {

    public static void main(String[] args) {
        Maze mazz = new Maze(3,3);
        System.out.println(mazz.mazeArr[0][2]);
    }
}
