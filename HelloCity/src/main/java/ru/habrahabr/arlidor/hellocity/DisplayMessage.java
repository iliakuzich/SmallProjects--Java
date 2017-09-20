/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.hellocity;

import java.time.ZonedDateTime;

/**
 * Класс отображает взаимодействие с консолью
 *
 * @author Anton Zolotarevskyi
 */
public class DisplayMessage {

    private InputData handler;
    private SearchCity searcher;
    private MessageResource res;
    private String searchInf;

    public DisplayMessage() {
        handler = new InputData();
        searcher = new SearchCity();
        res = new MessageResource();
    }

    /**
     * Метод считывает с консоли данные для поиска данных
     *
     */
    public void conversation() {

        System.out.println(res.viewString("str1"));
        System.out.println(res.viewString("str2"));
        searchInf = handler.getCityName();
        String timeZone = additionalConversation();
        outResults(searcher.search((timeZone + searchInf).trim()));

    }

    private String additionalConversation() {
        System.out.println(res.viewString("str3"));
        String timeZone = handler.getZoneID();
        return timeZone;
    }

    private void outResults(ZonedDateTime zone) {
        if (zone.getHour() >= 6 && zone.getHour() < 9) {
            System.out.println(res.viewString("str4") + searchInf);
        } else if (zone.getHour() >= 9 && zone.getHour() < 19) {
            System.out.println(res.viewString("str5") + searchInf);
        } else if (zone.getHour() >= 19 && zone.getHour() < 23) {
            System.out.println(res.viewString("str6") + searchInf);
        } else if ((zone.getHour() >= 23 && zone.getHour() <= 24) || zone.getHour() < 6) {
            System.out.println(res.viewString("str7") + searchInf);
        }

    }

}
