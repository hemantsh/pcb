/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import com.sc.fe.analyze.FileStorageProperties;
import java.util.Set;
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
public class FileStoreUtilTest {
    
    public FileStoreUtilTest() {
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
     * Test of getInstance method, of class FileStoreUtil.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        FileStorageProperties fileStorageProperties = null;
        FileStoreUtil expResult = null;
        FileStoreUtil result = FileStoreUtil.getInstance(fileStorageProperties);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUploadDir method, of class FileStoreUtil.
     */
    @Test
    public void testGetUploadDir() {
        System.out.println("getUploadDir");
        FileStoreUtil instance = null;
        String expResult = "";
        String result = instance.getUploadDir();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of storeFile method, of class FileStoreUtil.
     */
    @Test
    public void testStoreFile() throws Exception {
        System.out.println("storeFile");
        String projectId = "";
        MultipartFile file = null;
        FileStoreUtil instance = null;
        String expResult = "";
        String result = instance.storeFile(projectId, file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of extractFiles method, of class FileStoreUtil.
     */
    @Test
    public void testExtractFiles() {
        System.out.println("extractFiles");
        String projectId = "";
        String fileName = "";
        FileStoreUtil instance = null;
        instance.extractFiles(projectId, fileName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listFiles method, of class FileStoreUtil.
     */
    @Test
    public void testListFiles() {
        System.out.println("listFiles");
        String projectId = "";
        FileStoreUtil instance = null;
        Set<String> expResult = null;
        Set<String> result = instance.listFiles(projectId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileStorageProperties method, of class FileStoreUtil.
     */
    @Test
    public void testGetFileStorageProperties() {
        System.out.println("getFileStorageProperties");
        FileStoreUtil instance = null;
        FileStorageProperties expResult = null;
        FileStorageProperties result = instance.getFileStorageProperties();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFileStorageProperties method, of class FileStoreUtil.
     */
    @Test
    public void testSetFileStorageProperties() {
        System.out.println("setFileStorageProperties");
        FileStorageProperties fileStorageProperties = null;
        FileStoreUtil instance = null;
        instance.setFileStorageProperties(fileStorageProperties);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
