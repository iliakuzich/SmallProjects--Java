package ru.habrahabr.arlidor.chatnetwork;

public interface TCPConnectionListener {

    void onConnectionReady(TCPConnection con);

    void onDisconnect(TCPConnection con);

    void onException(TCPConnection con, Exception ex);

    void onReciveString(TCPConnection con, String value);
}
