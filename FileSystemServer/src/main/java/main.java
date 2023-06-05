import Server.Server;

import java.io.IOException;

public class main {
    public static Server server;
    public static void main(String[] args){
        server=Server.getInstance();
        try {
            server.startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
