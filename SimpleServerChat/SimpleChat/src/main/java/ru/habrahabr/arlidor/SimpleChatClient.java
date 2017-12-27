package ru.habrahabr.arlidor;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.habrahabr.arlidor.chatnetwork.TCPConnection;
import ru.habrahabr.arlidor.chatnetwork.TCPConnectionListener;

public class SimpleChatClient extends JFrame implements TCPConnectionListener {

    private JTextField outgoing;
    private JTextArea log;
    private JButton sendButton;
    private PrintWriter writer;
    private TCPConnection connection;

    private SimpleChatClient() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        outgoing = new JTextField(20);
        log = new JTextArea();
        log.setEditable(false);
        log.setLineWrap(true);

        sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        add(outgoing, BorderLayout.NORTH);
        add(sendButton, BorderLayout.SOUTH);
        add(log, BorderLayout.CENTER);
        setSize(400, 500);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleChatClient().go();
            }
        });
    }

    public void go() {
        try {
            connection = new TCPConnection(this, "127.0.0.1", 8189);

            setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(SimpleChatClient.class.getName()).log(Level.SEVERE, null, ex);
            printMsg("Connection exception " + ex);
        }
    }

    public void onConnectionReady(TCPConnection con) {
        printMsg("Connection ready");
    }

    public void onDisconnect(TCPConnection con) {
        printMsg("Connection close");
    }

    public void onException(TCPConnection con, Exception ex) {
        printMsg("Connection exception " + ex);
    }

    public void onReciveString(TCPConnection con, String value) {
        printMsg(value);
    }

    public class SendButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent ev) {
            String msg = outgoing.getText();
            if (msg.equals("")) {
                return;
            }
            outgoing.setText(null);
            connection.sendString(msg);
        }
    }

    private synchronized void printMsg(final String msg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg + "\n");
                log.setCaretPosition(log.getDocument().getLength());

            }
        });
    }

}
