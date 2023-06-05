package Commands;

import GUI.ProxyClient;

import java.io.IOException;

public class LogIn implements ICommand{


    @Override
    public void execute(String[] args, ProxyClient proxy) {

        try {
            proxy.getClient().send(args[0] + " "+proxy.getClient().getIdUser()+ " "+args[2]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
