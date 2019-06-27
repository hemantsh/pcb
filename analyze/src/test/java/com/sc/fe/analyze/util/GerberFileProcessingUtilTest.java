/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import com.sc.fe.analyze.FileStorageProperties;
import com.sc.fe.analyze.to.AdvancedReport;
import com.sc.fe.analyze.to.FileDetails;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class GerberFileProcessingUtilTest {
    
    public GerberFileProcessingUtilTest() {
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
     * Test of extractFileDetails method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testExtractFileDetails() {
        System.out.println("extractFileDetails");
        AdvancedReport report = null;
        Map<String, String> extensionToFileMapping = null;
        Path folder = null;
        List<FileDetails> expResult = null;
        List<FileDetails> result = GerberFileProcessingUtil.extractFileDetails(report, extensionToFileMapping, folder);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processFile method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessFile() {
        System.out.println("processFile");
        String exfile = "";
        Map<String, String> extensionToFileMapping = null;
        Path folder = null;
        FileDetails expResult = null;
        FileDetails result = GerberFileProcessingUtil.processFile(exfile, extensionToFileMapping, folder);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processFilesByExtension method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessFilesByExtension_List_Map() {
        System.out.println("processFilesByExtension");
        List<FileDetails> fileDetails = null;
        Map<String, Set<String>> extensionToFileTypeMapping = null;
        Map<String, Set<String>> expResult = null;
        Map<String, Set<String>> result = GerberFileProcessingUtil.processFilesByExtension(fileDetails, extensionToFileTypeMapping);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processFilesByExtension method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessFilesByExtension_AdvancedReport_Map() {
        System.out.println("processFilesByExtension");
        AdvancedReport report = null;
        Map<String, Set<String>> extensionToFileMapping = null;
        Map<String, Set<String>> expResult = null;
        Map<String, Set<String>> result = GerberFileProcessingUtil.processFilesByExtension(report, extensionToFileMapping);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processLine method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessLine() {
        System.out.println("processLine");
        String line = "";
        HashMap<String, String> expResult = null;
        HashMap<String, String> result = GerberFileProcessingUtil.processLine(line);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processG04 method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessG04() {
        System.out.println("processG04");
        String line = "";
        HashMap<String, String> expResult = null;
        HashMap<String, String> result = GerberFileProcessingUtil.processG04(line);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processTFTATO method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessTFTATO() {
        System.out.println("processTFTATO");
        String line = "";
        String word = "";
        HashMap<String, String> expResult = null;
        HashMap<String, String> result = GerberFileProcessingUtil.processTFTATO(line, word);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processFSLA method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessFSLA() {
        System.out.println("processFSLA");
        String line = "";
        HashMap<String, String> expResult = null;
        HashMap<String, String> result = GerberFileProcessingUtil.processFSLA(line);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processTD method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessTD() {
        System.out.println("processTD");
        String line = "";
        HashMap<String, String> expResult = null;
        HashMap<String, String> result = GerberFileProcessingUtil.processTD(line);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processMOLPLM method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessMOLPLM() {
        System.out.println("processMOLPLM");
        String line = "";
        String word = "";
        HashMap<String, String> expResult = null;
        HashMap<String, String> result = GerberFileProcessingUtil.processMOLPLM(line, word);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processLRLS method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessLRLS() {
        System.out.println("processLRLS");
        String line = "";
        String word = "";
        HashMap<String, String> expResult = null;
        HashMap<String, String> result = GerberFileProcessingUtil.processLRLS(line, word);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processM48 method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessM48() {
        System.out.println("processM48");
        String line = "";
        Map<String, String> results = null;
        String currentKey = "";
        String expResult = "";
        String result = GerberFileProcessingUtil.processM48(line, results, currentKey);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ocrImage method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testOcrImage() throws Exception {
        System.out.println("ocrImage");
        FileStorageProperties fileStorageProperties = null;
        GerberFileProcessingUtil.ocrImage(fileStorageProperties);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseFileName method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testParseFileName_FileDetails() {
        System.out.println("parseFileName");
        FileDetails fd = null;
        GerberFileProcessingUtil.parseFileName(fd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseFileName method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testParseFileName_String() {
        System.out.println("parseFileName");
        String fileName = "";
        FileDetails expResult = null;
        FileDetails result = GerberFileProcessingUtil.parseFileName(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
