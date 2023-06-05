package Commands;

import GUI.FileManagerGui;
import GUI.ProxyClient;

public class Clear implements ICommand{

    @Override
    public void execute(String[] args, ProxyClient proxyClient) {
        proxyClient.getManagerGui().getTextTerminal().setText("");
        proxyClient.getManagerGui().writeTerminal(" :D");
    }
}
