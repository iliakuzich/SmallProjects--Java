/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.hellocity;

import java.util.Scanner;

/**
 * Обработчик входящих сообщений
 *
 * @author Anton Zolotarevskyi
 */
public class InputDataHandler {

    private String userMsg;
    private Scanner in;
    private String city;

    public InputDataHandler() {
        in = new Scanner(System.in);
    }

    public String getMessage() {
        userMsg = in.nextLine();
        int ind = userMsg.indexOf("/");
        city = userMsg.substring(ind + 1);

        if (checkMsg(city)) {
            return city;
        } else {
            getMessage();
            return null;
        }
    }

    public boolean checkMsg(String userMsq) {
        if (userMsq.isEmpty()) {
            return false;
        } else if (userMsq.contains(" ")) {
            return false;
        } else if (!userMsq.matches("^[a-zA-Z_/]+$")) {
            return false;
        }
        return true;
    }
}
