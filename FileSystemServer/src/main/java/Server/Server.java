package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class Server extends Thread {
    private ServerSocket socketServer;
    private ClientManager manager;
    private HashMap<String, Client> unLoggedClients;
    private static Server instance;

    private Server(){
        manager = new ClientManager();
        unLoggedClients = new HashMap<>();
    }
    public static Server getInstance(){
        if(instance ==null){
            instance = new Server();
        }
        return instance;
    }
    public void startServer() throws IOException {
        socketServer = new ServerSocket(5000);
        this.start();
        System.out.println("Server Started...");
    }
    @Override
    public void run(){
        Client client;
        Socket socket;
        String idUser;
        while (!socketServer.isClosed()){
            try {
                System.out.println("Waiting client..."); //Esperando conexión
                socket = socketServer.accept();
                System.out.println("Client online");
                String tempKey = String.valueOf(manager.getRand());

                client = new Client(tempKey,socket);
                unLoggedClients.put(tempKey, client);
                client.send("id "+tempKey+ " false");
            } catch (IOException e) {
                throw new RuntimeException(e);

            }


        }

    }
    public void close(Client client) {
        manager.removeClient(client.getIdUser());
        try {
            if (client.getIn() != null) {
                client.getIn().close();
            }
            if (client.getOut() != null) {
                client.getOut().close();
            }
            if (client.getSocket() != null && !client.getSocket().isClosed()) {
                client.getSocket().close();
            }
        } catch (IOException e) {
            System.out.println("Error al cerrar los flujos y el socket: " + e.getMessage());
        }
        System.out.println("Client disconnected");
    }

    public ClientManager getManager() {
        return manager;
    }

    public HashMap<String, Client> getUnLoggedClients() {
        return unLoggedClients;
    }
}
