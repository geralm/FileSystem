package GUI;

import java.io.IOException;

public interface IClient {
    void send(String message)throws IOException;
    String[] receive() throws IOException;
    void close();
}
