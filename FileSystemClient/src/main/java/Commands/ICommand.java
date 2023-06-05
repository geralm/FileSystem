package Commands;

import GUI.FileManagerGui;
import GUI.ProxyClient;

import java.io.IOException;

public interface ICommand {
    void execute(String[] args, ProxyClient proxy)  ;

}
