/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pc
 */
public class ErrorCodesTest {

    public ErrorCodesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of values method, of class ErrorCodes.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        ErrorCodes[] expResult = ErrorCodes.values();
        ErrorCodes[] result = ErrorCodes.values();
        assertArrayEquals(expResult, result);

    }

    /**
     * Test of valueOf method, of class ErrorCodes.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "V0000";
        ErrorCodes expResult = ErrorCodes.V0000;
        ErrorCodes result = ErrorCodes.valueOf(name);
        assertEquals(expResult, result);

        name = "V0001";
        expResult = ErrorCodes.V0001;
        result= ErrorCodes.valueOf(name);
        assertEquals(expResult, result);

    }

    /**
     * Test of getErrorMessage method, of class ErrorCodes.
     */
    @Test
    public void testGetErrorMessage() {
        System.out.println("getErrorMessage");
        
        ErrorCodes instance = ErrorCodes.V0000;
        String expResult = "Unknown fileType";
        String result = instance.getErrorMessage();
        assertEquals(expResult, result);
        
        instance = ErrorCodes.V0001;
        expResult = "Drill file missing";
        result = instance.getErrorMessage();
        assertEquals(expResult, result);
    }

    /**
     * Test of setErrorMessage method, of class ErrorCodes.
     */
    @Test
    public void testSetErrorMessage() {
        System.out.println("setErrorMessage");
        String errorMessage = "This is a test message.";
        ErrorCodes instance = ErrorCodes.V0000;
        instance.setErrorMessage(errorMessage);
        
    }

}
