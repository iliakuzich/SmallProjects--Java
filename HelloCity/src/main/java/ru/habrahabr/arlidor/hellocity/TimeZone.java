/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.hellocity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Обёртка для работы с часовыми поясами
 *
 * @author Anton Zolotarevskiy
 */
public class TimeZone {

    private ZonedDateTime timeZone;
    private MessageResource res;
    private Set<String> availbTimZon;
    private String msg;

    public TimeZone() {
        availbTimZon = ZoneId.getAvailableZoneIds();
        res = new MessageResource();
    }

    /**
     * Обёртка для работы с часовыми поясами
     *
     * @param msg должен содержать необх. информацию о городе или часовом поясе
     * @return результат поиска
     */
    public boolean searchTimeZone(String msg) {
        this.msg = msg;
        ZoneId place = null;
        LocalDateTime timePoint = null;

        for (String id : availbTimZon) {
            if (id.contains(msg)) {
                place = ZoneId.of(id);
                timePoint = LocalDateTime.now(place);
            }
        }
        if (place == null) {
            return false;
        } else {
            timeZone = ZonedDateTime.of(timePoint, place);
            return true;
        }
    }

    /**
     * @return приветствие
     */
    public String getGreeting() {
        if (timeZone.getHour() >= 6 && timeZone.getHour() < 9) {
            return res.viewString("str3") + " " + msg;
        } else if (timeZone.getHour() >= 9 && timeZone.getHour() < 19) {
            return res.viewString("str4") + " " + msg;
        } else if (timeZone.getHour() >= 19 && timeZone.getHour() < 23) {
            return res.viewString("str5") + " " + msg;
        } else if ((timeZone.getHour() >= 23 && timeZone.getHour() <= 24)
                || timeZone.getHour() < 6) {
            return res.viewString("str6") + " " + msg;
        } else {
            return "Something wrong";
        }
    }
}
