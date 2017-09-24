/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.hellocity;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Anton
 */
public class TimeZoneTest {

    public TimeZoneTest() {

    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of searchTimeZone method, of class TimeZone.
     */
    @Test
    public void testSearchTimeZone() {
        System.out.println("searchTimeZone");
        String msg = "Kiev";
        TimeZone instance = new TimeZone();
        boolean expResult = true;
        boolean result = instance.searchTimeZone(msg);
        assertEquals(expResult, result);
    }

    /**
     * Test of getGreeting method, of class TimeZone.
     */
    @Test
    @Ignore
    public void testGetGreeting() {
        System.out.println("getGreeting");
        TimeZone instance = new TimeZone();
        List<String> expResult = new <String> ArrayList();
        expResult.add("Хорошей ночи! Kiev");
        expResult.add("Доброе утро! Kiev");
        expResult.add("Добрый день! Kiev");
        expResult.add("Добрый вечер! Kiev");
        instance.searchTimeZone("Kiev");
        String result = instance.getGreeting();
        assertEquals(expResult, result);
    }

}
