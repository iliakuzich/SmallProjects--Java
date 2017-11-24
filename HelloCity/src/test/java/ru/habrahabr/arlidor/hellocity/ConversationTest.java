/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.habrahabr.arlidor.hellocity;

import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Anton
 */
public class ConversationTest {

    /**
     * Test of speaking method, of class Conversation.
     */
    @Test

    public void testSpeaking() {
        System.out.println("speaking");
        String[] args = new String[]{"Dnepr"};
        try {
            Conversation instance = new Conversation(args);
            instance.speaking();
        } catch (Exception ex) {
            Logger.getLogger(ConversationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testDependenceForTime() {
        try {
            Locale.setDefault(Locale.ENGLISH);
            String[] args = new String[]{"Dnepr"};

            Conversation instanceCon = new Conversation(args);
            TimeZoneHandler instanceTime = new TimeZoneHandler();
            instanceTime.searchTimeZone("Dnepr");

            ZonedDateTime test = instanceTime.getTimeZone().withHour(8);
            String result = instanceCon.getGreeting(test);
            String expResult = "Good Morning! Dnepr";
            assertEquals(expResult, result);

            test = instanceTime.getTimeZone().withHour(12);
            result = instanceCon.getGreeting(test);
            expResult = "Good Day! Dnepr";
            assertEquals(expResult, result);

            test = instanceTime.getTimeZone().withHour(20);
            result = instanceCon.getGreeting(test);
            expResult = "Good Evening! Dnepr";
            assertEquals(expResult, result);

            test = instanceTime.getTimeZone().withHour(2);
            result = instanceCon.getGreeting(test);
            expResult = "Good Night! Dnepr";
            assertEquals(expResult, result);

        } catch (Exception ex) {
            Logger.getLogger(ConversationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
