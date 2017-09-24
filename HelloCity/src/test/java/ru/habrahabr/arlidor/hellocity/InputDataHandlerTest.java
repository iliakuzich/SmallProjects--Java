/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.hellocity;

import java.io.ByteArrayInputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anton
 */
public class InputDataHandlerTest {
    
    public InputDataHandlerTest() {
    }

    /**
     * Test of checkMsg method, of class InputDataHandler.
     */
    @Test
    public void testCheckMsg() {
        System.out.println("checkMsg");
        
        String userMsq1 = "Kiev";
        String userMsq2 = "Europe/Kiev";
        String userMsq3 = "Europe Kiev";
        
        InputDataHandler instance = new InputDataHandler();
        
        boolean expResult = true;
        boolean expResult2 = false;
        
        boolean result1 = instance.checkMsg(userMsq1);
        boolean result2 = instance.checkMsg(userMsq2);
        boolean result3 = instance.checkMsg(userMsq3);
        
        assertEquals(expResult, result1);
        assertEquals(expResult, result2);
        assertEquals(expResult2, result3);
        
    }

}
