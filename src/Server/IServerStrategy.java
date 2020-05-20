package Server;

import java.net.Socket;

public interface IServerStrategy {
    void handleClient(Socket client);
}
