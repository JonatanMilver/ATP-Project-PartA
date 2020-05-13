package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public class ServerStrategySolveSearchProblem implements ServerStrategy {
    private static String solutionsdirPath;
    private static ConcurrentHashMap<String , String> mazeToSol;
    private static int solnumber;

    public ServerStrategySolveSearchProblem() {
        solutionsdirPath = System.getProperty("java.io.tmpdir");

        initializeHashMap();
    }

    @Override
    public void handleClient(Socket client) {
        try {
            //Create objectInputStream to get the object from inputStream and objectOutputStream to write an object to outputStream.
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream solutionToClient = new ObjectOutputStream(client.getOutputStream());
            try {
                //Retrieve the maze from the inputStream
                Maze mazeFromClient = (Maze)objectInputStream.readObject();

                Solution mazeSolution;
                if (checkIfMazeExist(mazeFromClient)){
                    System.out.println("I'm in!");
                    String stringSol = getMazeToSol().get(Arrays.toString(mazeFromClient.toByteArray()));
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getSolutionsdirPath()+"\\"+stringSol));
                    mazeSolution =  (Solution)ois.readObject();
                }
                else {
                    ISearchable searchableMaze = new SearchableMaze(mazeFromClient);

                    ISearchingAlgorithm searchingAlgorithm = Server.Configurations.getSearchAlgorithm();
                    mazeSolution = searchingAlgorithm.solve(searchableMaze);

                    writeToSolDB(mazeFromClient , mazeSolution);
                    writeMazeToTemp(mazeFromClient);
                    writeSolToFile(mazeSolution);
                    updateHashMap();
                }

                solutionToClient.writeObject(mazeSolution);
                solutionToClient.flush();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                objectInputStream.close();
                solutionToClient.close();
                client.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private boolean checkIfMazeExist(Maze mazeFromClient) {
        String mazeID = Arrays.toString(mazeFromClient.toByteArray());
        return getMazeToSol().containsKey(mazeID);
    }

    private void writeToSolDB(Maze mazeFromClient, Solution mazeSolution) {
        String mazeID = Arrays.toString(mazeFromClient.toByteArray());
        String solID = "sol"+solnumber;

        getMazeToSol().put(mazeID , solID);
    }

    private void writeMazeToTemp(Maze mazeFromClient) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getSolutionsdirPath()+"\\maze"+solnumber));
            oos.writeObject(mazeFromClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeSolToFile(Solution mazeSolution) {
        try {
            FileOutputStream fos = new FileOutputStream(getSolutionsdirPath()+"\\sol"+solnumber);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mazeSolution);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeHashMap(){
        if (new File(solutionsdirPath , "solDB").exists()){
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getSolutionsdirPath()+"\\solDB"));
                mazeToSol = (ConcurrentHashMap<String, String>) ois.readObject();
                solnumber = mazeToSol.size();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            mazeToSol = new ConcurrentHashMap<>();
            solnumber = 0;
        }
    }

    private void updateHashMap() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getSolutionsdirPath()+"\\solDB"));
            oos.writeObject(mazeToSol);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConcurrentHashMap<String, String> getMazeToSol() {
        return mazeToSol;
    }

    public String getSolutionsdirPath() {
        return solutionsdirPath;
    }
}
