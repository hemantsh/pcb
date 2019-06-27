/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import com.sc.fe.analyze.FileStorageProperties;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author pc
 */
public class S3FileUtilityTest {
    
    public S3FileUtilityTest() {
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
     * Test of getInstance method, of class S3FileUtility.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        FileStorageProperties fileStorageProperties = null;
        S3FileUtility expResult = null;
        S3FileUtility result = S3FileUtility.getInstance(fileStorageProperties);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of storeFile method, of class S3FileUtility.
     */
    @Test
    public void testStoreFile() throws Exception {
        System.out.println("storeFile");
        String projectId = "";
        MultipartFile file = null;
        S3FileUtility instance = null;
        instance.storeFile(projectId, file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listObjects method, of class S3FileUtility.
     */
    @Test
    public void testListObjects() {
        System.out.println("listObjects");
        String projectId = "";
        S3FileUtility instance = null;
        List<String> expResult = null;
        List<String> result = instance.listObjects(projectId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
