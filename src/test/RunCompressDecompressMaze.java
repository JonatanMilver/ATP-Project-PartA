package test;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Aviadjo on 3/26/2017.
 */
public class RunCompressDecompressMaze {
    public static void main(String[] args) {
        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(30, 30); //Generate new maze

        maze.print();
        System.out.println();


        try {
            // save maze to a file
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
            out.write(maze.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte savedMazeBytes[] = new byte[0];
        try {
            //read maze from file
            InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
            savedMazeBytes = new byte[maze.toByteArray().length];
            in.read(savedMazeBytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Maze loadedMaze = new Maze(savedMazeBytes);

        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());
        System.out.println(String.format("Mazes equal: %s",areMazesEquals)); //maze should be equal to loadedMaze

        System.out.println();

        System.out.println("toByteArray length: " + maze.toByteArray().length);

        System.out.println();

        System.out.println("Maze:       " + Arrays.toString(maze.toByteArray()));
        System.out.println("loadedMaze: " + Arrays.toString(loadedMaze.toByteArray()));

//        System.out.println(loadedMaze.toByteArray().length);
//        for(int i=0; i<maze.toByteArray().length;i++) {
//            if (maze.toByteArray()[i] != loadedMaze.toByteArray()[i]) {
//                System.out.println(loadedMaze.toByteArray()[i]);
//                System.out.println(maze.toByteArray()[i]);
//                System.out.println(i);
//            }
//        }
        int asdaf=0;
    }
}