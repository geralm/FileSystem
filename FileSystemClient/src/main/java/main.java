import GUI.FileManagerGui;
import GUI.ProxyClient;

import javax.swing.*;
import java.io.IOException;

public class main {
    public static void main(String[] args)   {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FileManagerGui().setVisible(true);
            }
        });
    }
}
