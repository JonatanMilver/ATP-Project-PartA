package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    private static String solutionsdirPath;
    private static ConcurrentHashMap<String , String> mazeToSol;
    private volatile static AtomicInteger solnumber;

    public ServerStrategySolveSearchProblem() {
        solutionsdirPath = System.getProperty("java.io.tmpdir");
        solnumber = new AtomicInteger();
        initializeHashMap();
    }

    /**
     * Runs the strategy of getting a maze, solving it and returning the solution to the client.
     * @param client Socket
     */
    @Override
    public synchronized void handleClient(Socket client) {
        try {
            //Create objectInputStream to get the object from inputStream and objectOutputStream to write an object to outputStream.
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream solutionToClient = new ObjectOutputStream(client.getOutputStream());
            try {
                //Retrieve the maze from the inputStream
                Maze mazeFromClient = (Maze)objectInputStream.readObject();

                Solution mazeSolution;
                if (checkIfMazeExist(mazeFromClient)){
                    String stringSol = getMazeToSol().get(Arrays.toString(mazeFromClient.toByteArray()));
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getSolutionsdirPath()+"\\"+stringSol));
                    mazeSolution =  (Solution)ois.readObject();
                }
                else {
                    ISearchable searchableMaze = new SearchableMaze(mazeFromClient);

                    ISearchingAlgorithm searchingAlgorithm = Server.Configurations.getSearchAlgorithm();
                    mazeSolution = searchingAlgorithm.solve(searchableMaze);


                    writeToSolDB(mazeFromClient);
                    writeMazeToTemp(mazeFromClient);
                    writeSolToFile(mazeSolution);
                    updateHashMap();
                    solnumber.incrementAndGet();

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

    /**
     * Checks whether the maze already exists in the Data Base(dictionary)
     * @param mazeFromClient Maze
     * @return boolean
     */
    private boolean checkIfMazeExist(Maze mazeFromClient) {
        String mazeID = Arrays.toString(mazeFromClient.toByteArray());
        return getMazeToSol().containsKey(mazeID);
    }

    /**
     * Writes given maze to the DB(dictionary)
     * Key - maze represented as byte[]. Value - file's name with auto-increased number.
     * @param mazeFromClient Maze
     */
    private void writeToSolDB(Maze mazeFromClient) {
        String mazeID = Arrays.toString(mazeFromClient.toByteArray());
        String solID = "sol"+solnumber;

        getMazeToSol().put(mazeID , solID);
    }

    /**
     * Writes given maze to temporary folder.
     * @param mazeFromClient Maze
     */
    private void writeMazeToTemp(Maze mazeFromClient) {
        try {
            FileOutputStream file = new FileOutputStream(getSolutionsdirPath()+"\\maze"+solnumber);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(mazeFromClient);
            oos.flush();
            oos.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes given Solution to a file.
     * @param mazeSolution Solution
     */
    private void writeSolToFile(Solution mazeSolution) {
        try {
            FileOutputStream fos = new FileOutputStream(getSolutionsdirPath()+"\\sol"+solnumber);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mazeSolution);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the DB while running the program.
     * If there's no DB yet, opens one.
     * Else, loads the DB.
     */
    private synchronized void initializeHashMap(){
        if (new File(solutionsdirPath , "solDB").exists()){
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getSolutionsdirPath()+"\\solDB"));
                mazeToSol = (ConcurrentHashMap<String, String>) ois.readObject();
                solnumber.set(mazeToSol.size());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            mazeToSol = new ConcurrentHashMap<>();
            solnumber.set(0);
        }
    }

    /**
     * Updates the solDB file with new inserted elements.
     */
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
