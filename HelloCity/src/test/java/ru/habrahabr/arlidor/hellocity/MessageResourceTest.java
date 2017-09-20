/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.hellocity;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anton
 */
public class MessageResourceTest {

    public MessageResourceTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of viewString method, of class MessageResource.
     */
    @Test
    public void testViewString() {
        System.out.println("viewString");
        String str = "str1";
        MessageResource instance = new MessageResource();
        String expResult = "Здраствуйте!";
        String result = instance.viewString(str);
        assertEquals(expResult, result);

    }

    /**
     * Test of getRb method, of class MessageResource.
     */
    @Test
    public void testGetRb() {
        System.out.println("getRb");
        MessageResource instance = new MessageResource();
        Locale current = Locale.getDefault();
        ResourceBundle expResult = ResourceBundle.getBundle("resources.text_ru_RU", current);
        ResourceBundle result = instance.getRb();
        assertEquals(expResult, result);
        
    }

}
