package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;

import java.io.*;
import java.net.Socket;

public class ServerStrategyGenerateMaze implements IServerStrategy {


    @Override
    public void handleClient(Socket client){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            OutputStream comp = new MyCompressorOutputStream(baos);
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            try{

                int[] mazeSize = (int[])objectInputStream.readObject();
                IMazeGenerator mazeGenerator = Server.Configurations.getGeneratingAlgorithm();
                Maze maze = mazeGenerator.generate(mazeSize[0] , mazeSize[1]);
                if (maze == null){
                    System.out.println("maze is null!");
                    return;
                }

                comp.write(maze.toByteArray());
                comp.flush();

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
