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

    /**
     * Метод получает значение строки по входящему ключу
     *
     * @param str ключ необходимой строки
     * @return значение по входящему параметру
     * @throws java.io.UnsupportedEncodingException
     */
    public String viewString(String str) {
        rb = ResourceBundle.getBundle("resources.text");
        String sv = rb.getString(str);
        try {
            return new String(sv.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MessageResource.class.getName()).log(Level.SEVERE, null, ex);
            return "Error";
        }

    }

    public String viewCity(String str) throws UnsupportedEncodingException {
        rb = ResourceBundle.getBundle("resources.cities");
        String sv = rb.getString(str);
        return new String(sv.getBytes("ISO-8859-1"), "UTF-8");
    }

}
