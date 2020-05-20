package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;

import java.io.*;
import java.net.Socket;

public class ServerStrategyGenerateMaze implements IServerStrategy {
<<<<<<< HEAD


=======
    /**
     * Generates a Maze class according to client's specifications
     * @param client
     */
>>>>>>> 6deca6da9641b745c1b2be434884d309b278e9f5
    @Override
    public void handleClient(Socket client){
        try{
            // input/output streams
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            OutputStream comp = new MyCompressorOutputStream(baos);
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            try{

                // int[2] which specifies Maze dimensions
                int[] mazeSize = (int[])objectInputStream.readObject();
                IMazeGenerator mazeGenerator = Server.Configurations.getGeneratingAlgorithm();
<<<<<<< HEAD
//                IMazeGenerator mazeGenerator = new MyMazeGenerator();
=======

                // generating maze
>>>>>>> 6deca6da9641b745c1b2be434884d309b278e9f5
                Maze maze = mazeGenerator.generate(mazeSize[0] , mazeSize[1]);
                if (maze == null){
                    System.out.println("maze is null!");
                    return;
                }

                // compressing maze
                comp.write(maze.toByteArray());
                comp.flush();

                // writing compressed maze back to client
                byte[] mazeBytes = baos.toByteArray();
                objectOutputStream.writeObject(mazeBytes);
                objectOutputStream.flush();
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            finally {
                baos.close();
                comp.close();
                client.close();
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
