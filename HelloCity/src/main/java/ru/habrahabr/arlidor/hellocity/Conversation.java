/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.hellocity;

import java.time.ZonedDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anton Zolotarevskyi
 */
public class Conversation {

    private InDataHandler handler;
    private TimeZoneHandler timeZone;
    private MessageResource res;
    private String msg;

    private static final Logger log = Logger.getLogger(Conversation.class.getName());

    public Conversation(String[] args) throws Exception {
        handler = new InDataHandler();
        timeZone = new TimeZoneHandler();
        res = new MessageResource();
        msg = handler.preparation(args);
    }

    /**
     * @author Anton Zolotarevskyi
     * @param args
     *
     */
    public void speaking() {

        if (timeZone.searchTimeZone(msg)) {
            System.out.println(getGreeting(timeZone.getTimeZone()));
            log.info("Result: ".concat(getGreeting(timeZone.getTimeZone())));
        }
    }

    /**
     * Greeting depending on the result of the search
     *
     * @return result string
     */
    public String getGreeting(ZonedDateTime zone) {
        if (zone.getHour() >= 6 && zone.getHour() < 9) {
            return res.viewString("greeting1") + " " + msg;
        } else if (zone.getHour() >= 9 && zone.getHour() < 19) {
            return res.viewString("greeting2") + " " + msg;
        } else if (zone.getHour() >= 19 && zone.getHour() < 23) {
            return res.viewString("greeting3") + " " + msg;
        } else if ((zone.getHour() >= 23 && zone.getHour() <= 24)
                || zone.getHour() < 6) {
            return res.viewString("greeting4") + " " + msg;
        } else {
            return "";
        }
    }
}
