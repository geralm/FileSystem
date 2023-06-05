package GUI;

import Commands.CommandManager;
import Commands.ICommand;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;

public class FileManagerGui extends JFrame implements Runnable {
    private JPanel panel1;
    private JButton buttonExit;
    private JButton buttonCommands;
    private JPanel panelButtons;
    private JPanel panelTerminal;
    private JPanel panelTree;
    private JTree tree;
    private JTextPane textTerminal;
    private JButton buttonSend;
    private JLabel labelID;
    private JLabel labelState;

    ProxyClient proxyClient;

    public FileManagerGui( ){
        //Init some frame components
        this.setSize(1500, 700);
        setContentPane(panel1);
        setTitle("File Manager");
        setLocationRelativeTo(null);
        init_components();
        //Init proxy
        try {
            proxyClient = new ProxyClient(this); // creates client, and commandmanger
            proxyClient.getClient().connect(); //Connect client
            new Thread(this).start();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error to connect client");

        }

    }
    private void init_components(){
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    proxyClient.send(getLastLineText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        textTerminal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        proxyClient.send(getLastLineText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }

            }
        });
        buttonCommands.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommandsInfo commandsInfo = new CommandsInfo();
                commandsInfo.setVisible(true);
            }
        });

    }

    //
//To recieve commands from server




    //Terminal functions
    public void writeTerminal(String text) {
        StyledDocument doc = textTerminal.getStyledDocument();
        Style customStyle = doc.addStyle("CustomStyle", null);
        StyleConstants.setForeground(customStyle, new Color(214, 92,169));
        StyleConstants.setBold(customStyle, true);
        try {
            doc.insertString(doc.getLength(), "> "+text+"\n", customStyle);
        } catch (BadLocationException e) {
            System.out.println(e);
        }
        moveCursorToLastCharacter();
    }
    private void moveCursorToLastCharacter() {
        int lastPosition = textTerminal.getDocument().getLength();
        textTerminal.setCaretPosition(lastPosition);
    }
    public String getLastLineText() {

        StyledDocument doc = textTerminal.getStyledDocument();
        int caretPosition = textTerminal.getCaretPosition();

        Element root = doc.getDefaultRootElement();
        int currentLine = root.getElementIndex(caretPosition);

        Element lineElement = root.getElement(currentLine);
        int lineStart = lineElement.getStartOffset();
        int lineEnd = lineElement.getEndOffset();

        String lineText = "";
        try {
            lineText = doc.getText(lineStart, lineEnd - lineStart);
        } catch (BadLocationException e) {
            System.out.println(e);
        }

        return lineText.replaceAll("\\r|\\n", "");

    }
//Setters - getters

    public JPanel getPanel1() {
        return panel1;
    }

    public JButton getButtonExit() {
        return buttonExit;
    }

    public JButton getButtonCommands() {
        return buttonCommands;
    }

    public JPanel getPanelButtons() {
        return panelButtons;
    }

    public JPanel getPanelTerminal() {
        return panelTerminal;
    }

    public JPanel getPanelTree() {
        return panelTree;
    }

    public JTree getTree() {
        return tree;
    }

    public JEditorPane getTextTerminal() {
        return textTerminal;
    }

    public JButton getButtonSend() {
        return buttonSend;
    }

    public JLabel getLabelID() {
        return labelID;
    }

    public JLabel getLabelState() {
        return labelState;
    }

    @Override
    public void run() {
        Socket socket = proxyClient.getClient().getSocket();
        while(!socket.isClosed()){
            try {
                proxyClient.receive();
            } catch (IOException e) {
                proxyClient.close();
            }
        }
    }
}
