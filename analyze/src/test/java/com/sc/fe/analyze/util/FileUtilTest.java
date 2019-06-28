/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import com.sc.fe.analyze.FileStorageProperties;
import java.io.File;
import java.io.FileInputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author pc
 */
public class FileUtilTest {

    public FileUtilTest() {
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
     * Test of saveUploadedZipFile method, of class FileUtil.
     */
    @Test
    public void testSaveUploadedZipFile() throws Exception {
        System.out.println("saveUploadedZipFile");
        String projectId = "testProject";
        MultipartFile file = new MockMultipartFile("newChanges.txt", new FileInputStream(new File("D://newChanges.txt")));
        FileStorageProperties fileStorageProperties = new FileStorageProperties();
        fileStorageProperties.setBucket("myBucket");
        String expResult = "";
        String result = FileUtil.saveUploadedZipFile(projectId, file, fileStorageProperties);
        assertEquals(expResult, result);

    }

    /**
     * Test of saveAndExtractZip method, of class FileUtil.
     */
    @Test
    public void testSaveAndExtractZip() throws Exception {
        System.out.println("saveAndExtractZip");
        String projectId = "testProject";
        MultipartFile file = new MockMultipartFile("newChanges.txt", new FileInputStream(new File("D://newChanges.txt")));
        FileStorageProperties fileStorageProperties =  new FileStorageProperties();
        fileStorageProperties.setBucket("myBucket");
        FileUtil.saveAndExtractZip(projectId, file, fileStorageProperties);

    }

    /**
     * Test of deleteFolder method, of class FileUtil.
     */
    @Test
    public void testDeleteFolder() {
        System.out.println("deleteFolder");
        File file = null;
        FileUtil.deleteFolder(file);

    }

}
