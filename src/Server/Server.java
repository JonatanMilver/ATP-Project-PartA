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
    private int MAXTHREADS;
    private ThreadPoolExecutor pool;
    private static Configurations config = Configurations.getInstance();



    public Server (int port, int timeOut, IServerStrategy IServerStrategy) {
        this.port = port;
        this.timeOut = timeOut;
        this.IServerStrategy = IServerStrategy;
        stop = false;
        MAXTHREADS = Configurations.getMaxThreadsPerServer();
//        MAXTHREADS = 5;
        pool = (ThreadPoolExecutor)Executors.newFixedThreadPool(MAXTHREADS);
    }

    /**
     * Starts the server by running it in a different thread.
     */
    public void start(){
        Thread r= new Thread(()->run());
        r.start();

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
                    if(IServerStrategy == null)
                        return;
//                  Runs the strategy in a different thread.
                    Runnable r = new Thread(()-> IServerStrategy.handleClient(clientSocket));
                    pool.submit(r);
                }
                catch ( IOException e){
//                    System.out.println("Waiting for connections...");
//                    stop=true;
                }
            }
            pool.shutdown();
            serverSocket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void setConfigurations(String setProperty, String value){
        if(setProperty.equals("SearchingAlgorithm")){
            Configurations.setSearchAlgorithm(value);
        }
        else if(setProperty.equals("GeneratingAlgorithm")){
            Configurations.setGeneratingAlgorithm(value);
        }
        else if(setProperty.equals("MaxThreads")){
            Configurations.setMaxThreadsPerServer(value);
        }
    }

    public static String getConfigurations(String property){
        if(property.equals("SearchingAlgorithm")){
            return Configurations.getSearchAlgorithm().getName();
        }
        if(property.equals("GeneratingAlgorithm")){
            return Configurations.getGeneratingAlgorithm().toString();
        }
        if(property.equals("MaxThreads")){
            return String.valueOf(Configurations.getMaxThreadsPerServer());
        }
        return "";
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
    static class Configurations {
        private static Properties properties;
        private static OutputStream output;
        private static InputStream input;
        private static Configurations config;

        private  Configurations() {

            try {
                properties = new Properties();
                input = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config.properties");
                properties.load(input);
                output = new FileOutputStream(System.getProperty("user.dir") + "\\resources\\config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {

            }
        }

        public static Configurations getInstance(){
            if(config == null){
                config = new Configurations();
            }
            return config;
        }

        public static void setSearchAlgorithm(String algo){
            if (algo != null) {
                if(properties.containsKey("SearchingAlgorithm"))
                    properties.replace("SearchingAlgorithm", algo);
                else{
                    properties.setProperty("SearchingAlgorithm", algo);
                }
                store();
            }
            else {
                System.out.println("method input is null!");
            }
        }
        public static void setGeneratingAlgorithm(String algo){
            if (algo != null) {
                if(properties.containsKey("GeneratingAlgorithm"))
                    properties.replace("GeneratingAlgorithm", algo);
                else{
                    properties.setProperty("GeneratingAlgorithm", algo);
                }
                store();
            }
            else {
                System.out.println("method input is null!");
            }
        }
        public static void setMaxThreadsPerServer(String numberOfThreads){
            boolean integer = isInteger(numberOfThreads);
            if (integer){
                if(properties.containsKey("MaxThreads"))
                    properties.replace("MaxThreads" , numberOfThreads);
                else{
                    properties.setProperty("MaxThreads" , numberOfThreads);
                }
                store();
            }
            else {
                System.out.println("method input not an integer!");
            }
        }


        public static ISearchingAlgorithm getSearchAlgorithm(){
            String s = properties.getProperty("SearchingAlgorithm");
            if(s.equals("Breadth First Search"))
                return new BreadthFirstSearch();
            else if(s.equals("Depth First Search"))
                return new DepthFirstSearch();
            else return new BestFirstSearch();

        }
        public static IMazeGenerator getGeneratingAlgorithm(){
            String s = properties.getProperty("GeneratingAlgorithm");
            if(s.equals("EmptyMazeGenerator"))
                return new EmptyMazeGenerator();
            else if (s.equals("SimpleMazeGenerator"))
                return new SimpleMazeGenerator();
            else return new MyMazeGenerator();

        }
        public static int getMaxThreadsPerServer(){
            String s;
            if(properties.getProperty("MaxThreads") != null)
                s = properties.getProperty("MaxThreads");
            else{
                s = "5"; //default
            }
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
