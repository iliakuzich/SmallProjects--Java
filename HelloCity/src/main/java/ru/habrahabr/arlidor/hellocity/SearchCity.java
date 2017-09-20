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
 * Класс отвечает за поиск города в стандартной библиотеке часовых поясов
 *
 * @author Anton Zolotaresvkyi
 */
public class SearchCity {

    private ZonedDateTime zone;
    private MessageResource res;

    public SearchCity() {
        res = new MessageResource();
    }

    /**
     * @param msg входные данные пользователя
     * @return необходимая часовая зона
     */
    public ZonedDateTime search(String msg) {
        ZoneId place = null;
        LocalDateTime timePoint = null;
        Set<String> timeZones = ZoneId.getAvailableZoneIds();
        for (String id : timeZones) {
            if (id.contains(msg)) {
                place = ZoneId.of(id);
                timePoint = LocalDateTime.now(place);
            }
        }
        if (place == null) {
            System.out.println(res.viewString("str8"));
            System.out.println(res.viewString("str9"));
            System.out.println(res.viewString("str10"));
            System.exit(1);
        }
        zone = ZonedDateTime.of(timePoint, place);

        return zone;
    }

}
