/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.hellocity;

import java.time.ZonedDateTime;
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

    /**
     * Test of searchTimeZone method, of class TimeZone.
     */
    @Test
    public void testSearchTimeZone() {
        System.out.println("searchTimeZone");
        String msg = "Kiev";
        TimeZoneHandler instance = new TimeZoneHandler();
        boolean expResult = true;
        boolean result = instance.searchTimeZone(msg);
        assertEquals(expResult, result);
    }
}
