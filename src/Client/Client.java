package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client{
    private IClientStrategy client_strategy;
    private InetAddress host;
    private int port;

    public Client(InetAddress host, int port,IClientStrategy client_strategy) {
        this.client_strategy = client_strategy;
        this.host = host;
        this.port = port;
    }

    /**
     * Communicates with the server in a different thread.
     */
    public void communicateWithServer(){
        run();
    }

    /**
     * Runs a client socket, connects to the server with given port.
     */
    public void run() {
        try {
            Socket socket = new Socket(host, port);
            System.out.println("Client is connected to the server!");
            client_strategy.clientStrategy(socket.getInputStream(), socket.getOutputStream());
            socket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}