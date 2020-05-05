package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze  implements ServerStrategy{


    @Override
    public void handleClient(InputStream inFromClient , OutputStream outToClient) throws IOException {
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            OutputStream comp = new MyCompressorOutputStream(baos);
            ObjectInputStream objectInputStream = new ObjectInputStream(inFromClient);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outToClient);
            int[] mazeSize = (int[])objectInputStream.readObject();

            AMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(mazeSize[0] , mazeSize[1]);

            comp.write(maze.toByteArray());
            comp.flush();

            byte[] mazeBytes = baos.toByteArray();
            objectOutputStream.writeObject(mazeBytes);
            objectOutputStream.flush();
            baos.close();
            comp.close();

//            comp.write(maze.toByteArray());
//            comp.flush();

        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
