package Server;

import Commands.CommandManager;
import Commands.ICommand;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {
    private Socket socket;

    private String id= "";
    private DataInputStream in;
    private DataOutputStream out;
    public Client(String id, Socket socket) throws IOException {
        this.id = id;
        this.socket = socket;
        in = new DataInputStream(this.socket.getInputStream());
        out = new DataOutputStream(this.socket.getOutputStream());
        this.start();
    }

    public void send(String message) throws IOException {
        out.writeUTF(message);
        System.out.println(this.id+" send-> "+message);
    }
    public String[] receive() throws IOException {
        String[] commands = {""};
        String strRecieved = in.readUTF();
        System.out.println(this.id+ " received sucessfully");
        commands = strRecieved.split(" ");
        return commands;

    }

    @Override
    public void run() {
        String response = "";
        while (!socket.isClosed()){
            String [] args = new String[0];
            try {
                args = receive();
                ICommand icommand = CommandManager.getInstance().getCommand(args[0]);
                if (icommand !=null) {
                    response = icommand.execute(args);
                }else{
                    response = "command not found";
                }
                send(response);
            } catch (IOException e) {
                break;

            }

        }
        Server.getInstance().close(this);
    }



    public String getIdUser() {
        return id;
    }

    public void setIdUser(String id) {
        this.id = id;
    }

    public Socket getSocket() {
        return socket;
    }

    public DataInputStream getIn() {
        return in;
    }

    public DataOutputStream getOut() {
        return out;
    }
}
