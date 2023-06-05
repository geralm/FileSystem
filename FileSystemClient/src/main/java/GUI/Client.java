package GUI;

import Commands.ICommand;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements IClient {
    private Socket socket;
    private String id;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean isLogged;

    public Client(){
        isLogged = false;
    }

    public void connect() throws IOException {
        this.socket = new Socket("localhost", 5000);
        in = new DataInputStream(this.socket.getInputStream());
        out = new DataOutputStream(this.socket.getOutputStream());

    }

    @Override
    public void send(String message) throws IOException {
        out.writeUTF(message);
    }
    @Override
    public String[] receive() throws IOException {
        String[] commands = {""};
        String strRecieved = in.readUTF();
        commands = strRecieved.split(" ");
        return commands;

    }


    @Override
    public void close() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Error al cerrar los flujos y el socket: " + e.getMessage());
        }
        isLogged = false;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getIdUser() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}
