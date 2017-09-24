/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.hellocity;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс отвечает за локализацию приложения
 *
 * @author Anton Zolotarevskyi
 */
public class MessageResource {

    private Locale current;
    private ResourceBundle rb;

    public MessageResource() {
        String language = null;
        String country = null;
       
        if (Locale.getDefault().getDisplayLanguage().equals("русский")) {
            language = "RU";
            country = "RU";
            current = new Locale(language, country);
            rb = ResourceBundle.getBundle("resources.text_ru_RU", current);
        } else if (Locale.getDefault().getDisplayLanguage().equals("English")) {
            language = "EN";
            country = "EN";
            current = new Locale(language, country);
            rb = ResourceBundle.getBundle("resources.text_en_EN", current);
        }
    }

    /**
     * Метод получает значение строки по входящему ключу
     *
     * @param str ключ необходимой строки
     * @return значение по входящему параметру
     */
    public String viewString(String str) {
        String s = null;
        try {
            String sv = rb.getString(str);
            s = new String(sv.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MessageResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

}
