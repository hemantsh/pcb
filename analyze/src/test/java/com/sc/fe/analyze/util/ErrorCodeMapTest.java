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
public class ErrorCodeMapTest {

    public ErrorCodeMapTest() {
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
     * Test of getCodeForFileType method, of class ErrorCodeMap.
     */
    @Test
    public void testGetCodeForFileType() {
        System.out.println("getCodeForFileType");

        String fileType = "";
        ErrorCodes expResult = ErrorCodes.V0000;
        ErrorCodes result = ErrorCodeMap.getCodeForFileType(fileType);
        assertEquals(expResult, result);

        fileType = "drill";
        expResult = ErrorCodes.V0001;
        result = ErrorCodeMap.getCodeForFileType(fileType);
        assertEquals(expResult, result);

        fileType = "signal";
        expResult = ErrorCodes.V0002;
        result = ErrorCodeMap.getCodeForFileType(fileType);
        assertEquals(expResult, result);

        fileType = "power_ground";
        expResult = ErrorCodes.V0003;
        result = ErrorCodeMap.getCodeForFileType(fileType);
        assertEquals(expResult, result);

        fileType = "solder_mask";
        expResult = ErrorCodes.V0004;
        result = ErrorCodeMap.getCodeForFileType(fileType);
        assertEquals(expResult, result);
        
        fileType = "silk_screen";
        expResult = ErrorCodes.V0005;
        result = ErrorCodeMap.getCodeForFileType(fileType);
        assertEquals(expResult, result);
        
        fileType = "solder_paste";
        expResult = ErrorCodes.V0006;
        result = ErrorCodeMap.getCodeForFileType(fileType);
        assertEquals(expResult, result);

        fileType = "gerber";
        expResult = ErrorCodes.V0011;
        result = ErrorCodeMap.getCodeForFileType(fileType);
        assertEquals(expResult, result);
    }

}
