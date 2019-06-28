/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import com.sc.fe.analyze.FileStorageProperties;
import java.util.HashSet;
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
        FileStorageProperties fileStorageProperties = new FileStorageProperties();
        fileStorageProperties.setBucket("myUploadDir");
        FileStoreUtil expResult = FileStoreUtil.getInstance(fileStorageProperties);
        FileStoreUtil result = FileStoreUtil.getInstance(fileStorageProperties);
        assertEquals(expResult, result);
        assertEquals(result.getUploadDir(), null);
        
    }

    /**
     * Test of getUploadDir method, of class FileStoreUtil.
     */
    @Test
    public void testGetUploadDir() {
        System.out.println("getUploadDir");
        FileStorageProperties fileStorageProperties = new FileStorageProperties();
        fileStorageProperties.setUploadDir("myUploadDir");
        FileStoreUtil instance = FileStoreUtil.getInstance(fileStorageProperties);
        String expResult = "myUploadDir";
        String result = instance.getUploadDir();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of storeFile method, of class FileStoreUtil.
     */
    @Test
    public void testStoreFile() throws Exception {
        System.out.println("storeFile");
        
        String projectId = "newProject";
        MultipartFile file = null;
        FileStoreUtil instance = null;
        String expResult = "";
        String result = instance.storeFile(projectId, file);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of extractFiles method, of class FileStoreUtil.
     */
    @Test
    public void testExtractFiles() {
        System.out.println("extractFiles");
        FileStorageProperties fileStorageProperties = new FileStorageProperties();
        String projectId = "newProject";
        String fileName = "myNewFile";
        FileStoreUtil instance = FileStoreUtil.getInstance(fileStorageProperties);
        instance.extractFiles(projectId, fileName);
//To be validated further.
    }

    /**
     * Test of listFiles method, of class FileStoreUtil.
     */
    @Test
    public void testListFiles() {
        System.out.println("listFiles");
        FileStorageProperties fileStorageProperties = new FileStorageProperties();
        fileStorageProperties.setBucket("myNewBucket");
        String projectId = "newProject";
        FileStoreUtil instance = FileStoreUtil.getInstance(fileStorageProperties);
        Set<String> expResult = new HashSet<>();
        Set<String> result = instance.listFiles(projectId);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getFileStorageProperties method, of class FileStoreUtil.
     */
    @Test
    public void testGetFileStorageProperties() {
        System.out.println("getFileStorageProperties");
        FileStorageProperties fileStorageProperties = new FileStorageProperties();
        FileStoreUtil instance = FileStoreUtil.getInstance(fileStorageProperties);
        FileStorageProperties expResult = null;
        FileStorageProperties result = instance.getFileStorageProperties();
        assertEquals(expResult, result);
//To be validated further.
    }

    /**
     * Test of setFileStorageProperties method, of class FileStoreUtil.
     */
    @Test
    public void testSetFileStorageProperties() {
        System.out.println("setFileStorageProperties");
        FileStorageProperties fileStorageProperties = new FileStorageProperties();
        fileStorageProperties.setAccessKey("123456789");
        FileStoreUtil instance = FileStoreUtil.getInstance(fileStorageProperties);
        instance.setFileStorageProperties(fileStorageProperties);
        
    }
    
}
