package ru.habrahabr.arlidor.chatnetwork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;

public class TCPConnection implements Runnable {

    private final Socket socket;
    private final Thread rxThread;
    private final BufferedReader in;
    private final BufferedWriter out;
    private final TCPConnectionListener eventListener;

    public TCPConnection(TCPConnectionListener eventListener, String ipAdd, int port) throws IOException {
        this(eventListener, new Socket(ipAdd, port));
    }

    public TCPConnection(TCPConnectionListener eventListener, Socket socket) throws IOException {
        this.socket = socket;
        this.eventListener = eventListener;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
        rxThread = new Thread(this);
        rxThread.start();
    }

    @Override
    public void run() {
        try {
            eventListener.onConnectionReady(TCPConnection.this);
            while (!rxThread.isInterrupted()) {
                eventListener.onReciveString(TCPConnection.this, in.readLine());
            }
        } catch (IOException ex) {
            eventListener.onException(TCPConnection.this, ex);
        } finally {
            eventListener.onDisconnect(TCPConnection.this);
        }
    }

    public synchronized void sendString(String value) {
        try {
            out.write(value + "\r\n");
            out.flush();
        } catch (IOException ex) {
            eventListener.onException(TCPConnection.this, ex);
            disconnect();
        }
    }

    public synchronized void disconnect() {
        try {
            rxThread.interrupt();
            socket.close();
        } catch (IOException ex) {
            eventListener.onException(TCPConnection.this, ex);
        }
    }

    @Override
    public String toString() {
        return "TCPConnection: User " + socket.getInetAddress();
    }

}
