/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import com.sc.fe.analyze.to.FileDetails;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
public class ODBProcessingTest {
    
    public ODBProcessingTest() {
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
     * Test of processODB method, of class ODBProcessing.
     */
    @Test
    public void testProcessODB() {
        System.out.println("processODB");
        Path file = Paths.get("D:\\newChanges.txt");
        List<FileDetails> expResult = new ArrayList<FileDetails>();
        List<FileDetails> result = ODBProcessing.processODB(file);
        assertEquals(expResult, result);
        
    }
    
}
