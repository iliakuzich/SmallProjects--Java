/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.hellocity;

/**
 *
 * @author Anton Zolotarevskyi
 */
public class Conversation {

    private InputDataHandler handler;
    private TimeZone timeZone;
    private MessageResource res;
    private String msg;

    public Conversation() {
        handler = new InputDataHandler();
        timeZone = new TimeZone();
        res = new MessageResource();
    }

    public void speaking() {
        System.out.println(res.viewString("str1"));
        System.out.println(res.viewString("str2"));
        msg = handler.getMessage();
        if (timeZone.searchTimeZone(msg)) {
            System.out.println(timeZone.getGreeting());
        } else {
            System.out.println(res.viewString("str6"));
            speaking();
        }
    }
}
