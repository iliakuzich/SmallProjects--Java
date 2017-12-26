package ru.habrahabr.arlidor.chapter5;

import java.util.Arrays;
import junit.framework.TestCase;

public class SimpleDotComTest extends TestCase {

    public SimpleDotComTest(String testName) {
        super(testName);
    }

    public void testSetLocationCells() {
        System.out.println("setLocationCells");
        int[] locs = {2, 3, 4};
        SimpleDotCom instance = new SimpleDotCom();
        instance.setLocationCells(locs);
        assertTrue(Arrays.equals(instance.locationCells, locs));
    }

    public void testCheckYourself() {
        System.out.println("checkYourself");
        String stringGuess = "2";
        SimpleDotCom instance = new SimpleDotCom();
        int[] locs = {2, 3, 4};
        instance.setLocationCells(locs);
        String expResult = "hit";
        String result = instance.checkYourself(stringGuess);
        assertEquals(expResult, result);
    }

}
