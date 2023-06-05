package Commands;

import java.util.HashMap;

public class CommandManager {
    private HashMap<String, ICommand> serverResponseCommands;
    private HashMap<String, ICommand> usableCommands;

    private static CommandManager instance;

    private CommandManager(){
        serverResponseCommands = new HashMap<>();
        usableCommands   = new HashMap<>();
        init_all_commands();
    };
    public static CommandManager getInstance(){
        if(instance ==null){
            instance = new CommandManager();
        }
        return instance;
    }

    public ICommand getCommandUsable(String key){
        ICommand c =usableCommands.get(key);
        return c;
    }
    public ICommand getServerComman(String key){
        ICommand c =serverResponseCommands.get(key);
        return c;
    }
    public void init_all_commands(){
        serverResponseCommands.put("id", new SetId());
        usableCommands.put("clear", new Clear());
        usableCommands.put("lg", new LogIn());
    }
}
