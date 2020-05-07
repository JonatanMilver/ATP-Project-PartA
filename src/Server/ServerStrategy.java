package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ServerStrategy {
    void handleClient(InputStream inFromClient , OutputStream outToClient) throws IOException;
}
