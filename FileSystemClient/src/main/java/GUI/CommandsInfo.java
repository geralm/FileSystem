package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandsInfo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JList list;

    public CommandsInfo() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(new Dimension(600,600));
        setLocationRelativeTo(null);
        setResizable(false);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        addCommands();
    }

    private void onOK() {
        // add your code here
        dispose();
    }
    public void addCommands(){
        DefaultListModel model = new DefaultListModel();
        model.addElement("lg user (Entra al drive)");
        model.addElement("cdrive bytes user (Crea un drive)");

        model.addElement("ca nombre extension (Crea un archivo)");
        model.addElement("cd nombDirectorio (Crea directorio)");
        model.addElement("chdir nombDirectorio (Cambia de directorio)");
        model.addElement("ls  (Lista el directorio actual)");
        model.addElement("ma nombArchivo (Modifica el archivo)");
        model.addElement("vp nombArchivo (Ve las propiedades)");
        model.addElement("va nombArchivo (Ve el contenido del archivo)");
        model.addElement("cp -rv rutaReal rutaVirtual");
        model.addElement("cp -vr rutaVirtual rutaReal");
        model.addElement("cp -vv rutaVirtual rutaVirtual");
        model.addElement("mv arch/Directorio nuevaUbicacion");
        model.addElement("del arch/Directorio");
        model.addElement("shr archivo/Directorio user");
        model.addElement("clear (Limpia la consola)");

        list.setModel(model);
    }
}
