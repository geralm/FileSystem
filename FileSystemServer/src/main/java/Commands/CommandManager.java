package Commands;

import java.util.HashMap;

public class CommandManager  {
    private HashMap<String, ICommand> commands;
    private static CommandManager instance;

    private CommandManager(){
        commands = new HashMap<>();
        init_all_commands();
    };
    public static CommandManager getInstance(){
        if(instance == null){
            instance= new CommandManager();
        }
        return instance;
    }
    private void registCommand(String key, ICommand newCommand){
        commands.put(key, newCommand);
    }
    public ICommand getCommand(String key){
        return commands.get(key);
    }
    private void init_all_commands(){
        registCommand("cd", new CreateDrive());
        registCommand("ed", new GoToDrive());
        registCommand("ca", new CreateFile());
        registCommand("cd", new CreateDirectory());
        registCommand("chdir", new ChangeDirectory());
        registCommand("ls", new ListDirectory());
        registCommand("ma", new ModifyFile());
        registCommand("vp", new SeeProperties());
        registCommand("va", new SeeFile());
        registCommand("cp", new Copy());
        registCommand("mv", new Move());
        registCommand("del", new Delete());
        registCommand("shr", new Share());
    }
}
