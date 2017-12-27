package ru.habrahabr.arlidor.chapter15;

import java.io.*;
import java.net.*;

public class DailyAdviceServer {

    private String[] adviceList = {"Take smaller bites", "Go for the tight jeans."
        + " No they do NOT make you look fat", "One word: inappropriate",
        "Just for today, be honest.  Tell your boss what you *really* think",
        "You might want to rethink that haircut"};

    public static void main(String[] args) {
        DailyAdviceServer server = new DailyAdviceServer();
        server.go();
    }

    public void go() {
        try {
            ServerSocket serverSock = new ServerSocket(4242);
            while (true) {
                Socket sock = serverSock.accept();
                String advice;
                try (PrintWriter writer = new PrintWriter(sock.getOutputStream())) {
                    advice = getAdvice();
                    writer.println(advice);
                }
                System.out.println(advice);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }

}
