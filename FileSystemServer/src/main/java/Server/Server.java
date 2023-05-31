package Server;

import java.net.ServerSocket;

public class Server extends Thread {
    private ServerSocket socketServer;
    private ClientManager manager;
    public Server(){
        manager = new ClientManager();
    }
}
