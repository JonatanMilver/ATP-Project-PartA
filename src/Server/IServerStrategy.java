package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface IServerStrategy {
    void handleClient(Socket client);
}
