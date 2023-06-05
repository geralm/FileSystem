package GUI;

import Commands.CommandManager;
import Commands.ICommand;

import java.io.IOException;

public class ProxyClient implements IClient{
    private Client client;
    private FileManagerGui managerGui;

    private CommandManager commandManager;

    public ProxyClient(FileManagerGui managerGui) {
        this.managerGui = managerGui;
        this.client = new Client();
        commandManager = CommandManager.getInstance();
    }
    @Override
    public void send(String line) throws IOException {
        //Log

        if(!line.isBlank()) {

            String[] commands = line.split(" ");
            ICommand command = commandManager.getCommandUsable(commands[0]);
            if (command != null) {
                command.execute(commands, this);
            }else if(client.isLogged()){
                client.send(line);
            }else{
                managerGui.writeTerminal("Please log in first");
            }

        }
    }

    @Override
    public String[] receive() throws IOException{
        String[] commands = client.receive();
        ICommand command = commandManager.getServerComman(commands[0]);
        if(command!=null){
            command.execute(commands,this);
        }else{
            managerGui.writeTerminal("command not found");
        }
        return new String[0];
    }

    @Override
    public void close() {
        client.close();
    }

    public Client getClient() {
        return client;
    }
    public FileManagerGui getManagerGui() {
        return managerGui;
    }
}
