/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.hellocity;

import java.util.Locale;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anton
 */
public class MessageResourceTest {

    /**
     * Test of viewString method, of class MessageResource.
     */
    @Test
    public void testViewString() {
        System.out.println("viewString");
        String str = "greeting1";
        Locale.setDefault(Locale.ENGLISH);
        MessageResource instance = new MessageResource();
        String expResult = "Good Morning!";
        String result = instance.viewString(str);
        assertEquals(expResult, result);
    }

}
