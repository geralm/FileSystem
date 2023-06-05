package Commands;

import GUI.FileManagerGui;
import GUI.ProxyClient;

import java.util.Arrays;

public class SetId implements ICommand{
    @Override
    public void execute(String[] args, ProxyClient proxyClient) {
        proxyClient.getClient().setId(args[1]);
        proxyClient.getManagerGui().getLabelID().setText("ID: "+args[1]);;

        boolean isLogged = Boolean.parseBoolean(args[2]);
        proxyClient.getClient().setLogged(isLogged);
    }
}
