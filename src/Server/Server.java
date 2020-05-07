package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private int port;
    private int timeOut;
    private ServerStrategy serverStrategy;
    private volatile boolean stop;


    public Server (int port, int timeOut,  ServerStrategy serverStrategy) {
        this.port = port;
        this.timeOut = timeOut;
        this.serverStrategy = serverStrategy;
        stop = false;

    }

    public void run(){
        try{
            ServerSocket serverSocket = new ServerSocket(this.port);
//            serverSocket.setSoTimeout(this.timeOut);
            while(!stop){
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Listening to : " + clientSocket.getInetAddress()+ " : " + clientSocket.getLocalPort());

                    InputStream inFromClient = clientSocket.getInputStream();
                    OutputStream outToClient = clientSocket.getOutputStream();

                    serverStrategy.handleClient(inFromClient, outToClient);

                    inFromClient.close();
                    outToClient.close();
                    clientSocket.close();
                }
                catch ( IOException e){
                    e.printStackTrace();
//                    serverSocket.close();
                }
            }
            serverSocket.close();
        }
        catch (IOException e){
            e.printStackTrace();

        }
    }

//    public void stop(){
//        this.stop = true;
//    }

//    @Override
//    public void run() {
//        start();
//    }
}
