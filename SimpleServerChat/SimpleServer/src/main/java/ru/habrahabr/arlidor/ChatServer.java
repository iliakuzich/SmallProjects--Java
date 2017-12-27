package ru.habrahabr.arlidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.habrahabr.arlidor.chatnetwork.TCPConnection;
import ru.habrahabr.arlidor.chatnetwork.TCPConnectionListener;

public class ChatServer implements TCPConnectionListener {

    private List<TCPConnection> connections;

    private ChatServer() {
        connections = new ArrayList();
    }

    public static void main(String args[]) {
        new ChatServer().start();
    }

    private void start() {
        System.out.println("Server running");
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            while (true) {
                new TCPConnection(this, serverSocket.accept());
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public synchronized void onConnectionReady(TCPConnection con) {
        connections.add(con);
        sendToAllConnections("Client connected " + con);
    }

    @Override
    public synchronized void onDisconnect(TCPConnection con) {
        connections.remove(con);
        sendToAllConnections("Client disconnected " + con);
    }

    @Override
    public synchronized void onException(TCPConnection con, Exception ex) {
        System.out.println("TCPConnection Exception: " + ex);
    }

    @Override
    public synchronized void onReciveString(TCPConnection con, String value) {
        sendToAllConnections(con + " " + value);
    }

    private void sendToAllConnections(String value) {
        System.out.println(value);
        connections.forEach((connection) -> {
            connection.sendString(value);
        });
    }

}
