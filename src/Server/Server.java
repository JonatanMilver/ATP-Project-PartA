package Server;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;
import algorithms.search.ISearchingAlgorithm;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.*;

public class Server{
    private int port;
    private int timeOut;
    private IServerStrategy IServerStrategy;
    private volatile boolean stop;
//    private final int MAXTHREADS = Configurations.getMaxThreadsPerServer();
    private int MAXTHREADS;
    private ExecutorService pool;



    public Server (int port, int timeOut, IServerStrategy IServerStrategy) {
        Configurations.setGeneratingAlgorithm("My");
        Configurations.setMaxThreadsPerServer("5");
        Configurations.setSearchAlgorithm(new BestFirstSearch());
        this.port = port;
        this.timeOut = timeOut;
        this.IServerStrategy = IServerStrategy;
        stop = false;
        MAXTHREADS = Configurations.getMaxThreadsPerServer();
        pool = Executors.newFixedThreadPool(MAXTHREADS);
    }

    /**
     * Starts the server by running it in a different thread.
     */
    public void start(){
        Runnable r= new Thread(()->run());
        pool.execute(r);

    }

    /**
     * Running the server on, waiting for clients to connect.
     * Goes offline when timeOut is up.
     */
    public void run(){
        try{
            ServerSocket serverSocket = new ServerSocket(this.port);
            serverSocket.setSoTimeout(this.timeOut);
            while(!stop){
                try {
//                    Wait for clients at this line.
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Listening to : " + clientSocket.getInetAddress()+ " : " + clientSocket.getLocalPort());
                    System.out.println("Listening from : " + serverSocket.getInetAddress() + " : " + serverSocket.getLocalPort());

//                  Runs the strategy in a different thread.
                    Runnable r = new Thread(()-> IServerStrategy.handleClient(clientSocket));
                    pool.execute(r);

                }
                catch ( IOException e){
                    System.out.println("Waiting for connections...");
                }
            }
            pool.shutdown();
            serverSocket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Stop the run of the server.
     */
    public void stop() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.stop = true;
    }

    /**
     * Configurations class, sets configurations to the config.properties file.
     */
    static class Configurations{
        private static Properties properties = new Properties();
        private static OutputStream output;
        private static InputStream input;

        static {
            try {
                output = new FileOutputStream( System.getProperty("user.dir")+"\\resources\\config.properties");
                input = new FileInputStream(System.getProperty("user.dir")+"\\resources\\config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public static void setSearchAlgorithm(ISearchingAlgorithm algo){
            if (algo != null) {
                properties.setProperty("SearchingAlgorithm", algo.getName());
                store();
            }
            else {
                System.out.println("method input is null!");
            }
        }
        public static void setGeneratingAlgorithm(String algo){
            if (algo != null) {
                properties.setProperty("GeneratingAlgorithm", algo);
                store();
            }
            else {
                System.out.println("method input is null!");
            }
        }
        public static void setMaxThreadsPerServer(String numberOfThreads){
            boolean integer = isInteger(numberOfThreads);
            if (integer){
                properties.setProperty("MaxTreads" , numberOfThreads);
                store();
            }
            else {
                System.out.println("method input not an integer!");
            }
        }


        public static ISearchingAlgorithm getSearchAlgorithm(){
            String s = properties.getProperty("SearchingAlgorithm");
            switch (s) {
                case "BFS":
                    return new BreadthFirstSearch();
                case "BestFirstSearch":
                    return new BestFirstSearch();
                case "DFS":
                    return new DepthFirstSearch();
                default:
                    throw new NullPointerException("No such searching Algorithm");
            }
        }
        public static IMazeGenerator getGeneratingAlgorithm(){
            String s = properties.getProperty("GeneratingAlgorithm");
            switch (s){
                case "Empty":
                    return new EmptyMazeGenerator();
                case "Simple":
                    return new SimpleMazeGenerator();
                case "My":
                    return  new MyMazeGenerator();
                default:
                    throw new NullPointerException("No such generating Algorithm");
            }
        }
        public static int getMaxThreadsPerServer(){
            String s = properties.getProperty("MaxTreads");
            return Integer.parseInt(s);
        }

        private static void store(){
            try {
                properties.store(output,null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private static boolean isInteger(String s){
            boolean integer = true;
            try{
                Integer.parseInt(s);
            }
            catch (NumberFormatException e){
                integer = false;
            }
            return integer;
        }
    }
}
