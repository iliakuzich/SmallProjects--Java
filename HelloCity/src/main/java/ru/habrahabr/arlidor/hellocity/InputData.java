/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.hellocity;

import java.util.Scanner;

/**
 * Класс отвечает за обработку полученных данных с консоли
 *
 * @author Anton Zolotarevskyi
 */
public class InputData {

    private String nameCity;
    private String timeZoneID;
    private MessageResource res;

    public InputData() {
        res = new MessageResource();
    }

    /**
     * @return возвращает название города
     */
    public String getCityName() {
        Scanner in = new Scanner(System.in);
        System.out.println(res.viewString("str11"));
        String buf = in.nextLine();
        setNameCity(buf);
        return getNameCity();
    }

    private void setNameCity(String nameCity) {
        if (nameCity.isEmpty()) {
            System.out.println(res.viewString("str14"));
            getCityName();
        } else if (nameCity.contains(" ")) {
            System.out.println(res.viewString("str12"));
            getCityName();
        } else if (!nameCity.matches("^[a-zA-Z_]+$")) {
            System.out.println(res.viewString("str13"));
            getCityName();
        }
        this.nameCity = nameCity;
    }

    private String getNameCity() {
        return nameCity;
    }

    /**
     * @return возвращает название часовой зоны
     */
    public String getZoneID() {
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            getID();
            return getTimeZoneID();
        } else {
            return "";
        }
    }

    private void getID() {
        System.out.println(res.viewString("str15"));
        Scanner in = new Scanner(System.in);
        setTimeZoneID(in.nextLine());
    }

    private void setTimeZoneID(String timeZoneID) {
        if (timeZoneID.contains(" ")) {
            System.out.println(res.viewString("str12"));
            getID();
        } else if (timeZoneID.isEmpty()) {
            System.out.println(res.viewString("str14"));
            getID();
        } else if (!timeZoneID.matches("^[a-zA-Z_]+$")) {
            System.out.println(res.viewString("str13"));
            getID();
        } else {
            this.timeZoneID = timeZoneID + "/";
        }
    }

    private String getTimeZoneID() {
        return timeZoneID;
    }
}
