/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.hellocity;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.MissingResourceException;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Wrapper for work with time zones
 *
 * @author Anton Zolotarevskiy
 */
public class TimeZoneHandler {

    private ZonedDateTime timeZone;
    private MessageResource res;
    private List<String> availbTimZon;
    private String msg;
    private boolean ind;
    private static final Logger log = Logger.getLogger(TimeZoneHandler.class.getName());

    public TimeZoneHandler() {
        availbTimZon = Arrays.asList(TimeZone.getAvailableIDs());
        res = new MessageResource();
        ind = false;
    }

    /**
     * Searching time zone
     *
     * @param msg - city or time zone
     * @return result
     */
    public boolean searchTimeZone(String msg) {
        this.msg = msg;
        ZoneId place = null;
        LocalDateTime timePoint = null;
        try {
            for (String id : availbTimZon) {
                if (id.contains(msg)) {
                    place = ZoneId.of(id);
                    timePoint = LocalDateTime.now(place);
                    ind = true;
                }
            }
            if (ind == false) {
                place = ZoneId.of(res.viewCity(msg));
                timePoint = LocalDateTime.now(place);
            }
        } catch (MissingResourceException | UnsupportedEncodingException ex) {
            System.out.println(res.viewString("error1"));
            Logger.getLogger(TimeZoneHandler.class.getName()).log(Level.SEVERE, res.viewString("error1"));
        }
        timeZone = ZonedDateTime.of(timePoint, place);
        return true;
    }

    public ZonedDateTime getTimeZone() {
        return timeZone;
    }

}
