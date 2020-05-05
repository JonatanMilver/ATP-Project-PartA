package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerStrategySolveSearchProblem implements ServerStrategy {
    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        try {
            //Create objectInputStream to get the object from inputStream and objectOutputStream to write an object to outputStream.
            ObjectInputStream objectInputStream = new ObjectInputStream(inFromClient);

//            BufferedReader from_server = new BufferedReader(new InputStreamReader(inFromClient));
            ObjectOutputStream solutionToClient = new ObjectOutputStream(outToClient);
            try {
                //Retrieve the maze from the inputStream
                Maze mazeFromClient = (Maze)objectInputStream.readObject();
                ISearchable searchableMaze = new SearchableMaze(mazeFromClient);
                //Create a searching algorithm(I assume we should use the best one) and get a solution to the maze.
                ISearchingAlgorithm searchingAlgorithm = new BestFirstSearch();
                Solution mazeSolution = searchingAlgorithm.solve(searchableMaze);
                solutionToClient.writeObject(mazeSolution);
                solutionToClient.flush();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
