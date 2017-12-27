package ru.habrahabr.arlidor.chapter15;

import java.io.*;
import java.net.*;

public class DailyAdviceClient {

    public static void main(String[] args) {
        DailyAdviceClient client = new DailyAdviceClient();
        client.go();
    }

    public void go() {
        try {
            Socket s = new Socket("127.0.0.1", 4242);
            InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
            try (BufferedReader reader = new BufferedReader(streamReader)) {
                String advice = reader.readLine();
                System.out.println("Today you should: " + advice);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
