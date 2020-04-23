package test;

import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

/**
 * Made for testing
 */
public class Main {
    public static void main(String[] args) {
        byte savedMazeBytes[] = {0,0,0,5,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,2,0,1,0,1,0,0,1,0,1,0,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0};
        Maze check = new Maze(savedMazeBytes);
        check.print();

//        AMazeGenerator mazeGenerator = new MyMazeGenerator();
//        Maze maze = mazeGenerator.generate(10/*rows*/, 10/*columns*/);

    }
}
