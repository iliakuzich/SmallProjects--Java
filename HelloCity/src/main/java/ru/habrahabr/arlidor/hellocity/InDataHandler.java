/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.hellocity;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Обработчик входящих сообщений
 *
 * @author Anton Zolotarevskyi
 */
public class InDataHandler {

    private static final Logger log = Logger.getLogger(InDataHandler.class.getName());
    private MessageResource res;
    private String msg;

    public InDataHandler() {
        res = new MessageResource();
    }

    public String preparation(String[] args) throws Exception {
        String msgIn = Arrays.asList(args).get(args.length - 1);
        int ind = msgIn.indexOf("/");
        msg = msgIn.substring(ind + 1);
        return modification(msg);
    }

    private String modification(String msg) throws Exception {
        if (msg.contains(" ")) {
            msg = msg.replace(" ", "_");
        }
        if (msg.isEmpty()) {
            System.out.println(res.viewString("error2"));
            throw new Exception(res.viewString("error2"));
        }
        if (!msg.matches("^[a-zA-Z_/]+$")) {
            System.out.println(res.viewString("error3"));
            throw new Exception(res.viewString("error3"));
        }
        return msg;
    }
}
