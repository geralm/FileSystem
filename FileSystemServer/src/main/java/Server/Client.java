package Server;

import Commands.CommandManager;
import Commands.ICommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable {
    private Socket socket;
    private String id= "";
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public Client(String id){
        this.id = id;
    }
    public String connect() throws IOException {
        if(socket.isConnected()){
            this.socket = new Socket("localhost",5000);
            in = new ObjectInputStream(this.socket.getInputStream());
            out = new ObjectOutputStream(this.socket.getOutputStream());
        }else{//the client is already connected
            System.out.println("The client has been connected");
        }
        return this.id;
    }
    public void send(String message) throws IOException {
        try{
            out.writeObject(message);
            System.out.print(this.id+" send->");
        }catch(IOException ex ){
            System.out.println("Cannot send message");
        }
    }
    public String[] receive(){
        String[] commands = {""};
        try{
            String strRecieved = (String) in.readObject();
            System.out.println(this.id+ " received sucessfully");
            commands = strRecieved.split(" ");
        }catch(IOException|ClassNotFoundException e){
            System.out.println(e);
        }finally {
            return commands;
        }

    }

    @Override
    public void run() {
        while (socket.isConnected()){
            String [] args = receive();
            ICommand icommand = CommandManager.getInstance().getCommand(args[0]);
            String response = icommand.execute(args);
            try {
                send(response);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        close();
    }
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
    }
}
