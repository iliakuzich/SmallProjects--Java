/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.hellocity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anton
 */
public class InDataHandlerTest {

    /**
     * Test of preparation method, of class InDataHandler.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testPreparation() throws Exception {
        System.out.println("preparation");
        String[] args = new String[]{"Europe/Kiev"};
        InDataHandler instance = new InDataHandler();
        String expResult = "Kiev";
        String result = instance.preparation(args);
        assertEquals(expResult, result);
    }

}
