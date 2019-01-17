/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import com.sc.fe.analyze.to.AdvancedReport;
import com.sc.fe.analyze.to.CustomerInputs;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.LayersInformation;
import java.util.HashMap;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pc
 */
public class FileDetailCompareUtilityTest {

    public FileDetailCompareUtilityTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of compare method, of class ReportCompareUtility.
     */
    @Test
    public void testCompareReportDetails1() throws Exception {
        AdvancedReport newReport = new AdvancedReport();
        AdvancedReport oldReport = new AdvancedReport();
        newReport = null;
        oldReport = null;
        Map<String, String> result = ReportCompareUtility.compare(newReport, oldReport);
        assertEquals(null, result);
    }

    //Test with instanceVariable(customerInputs) of AdvancedReport in compare method, of class ReportCompareUtility.
    @Test
    public void testCompareReportDetails2() throws Exception {
        AdvancedReport newReport = new AdvancedReport();
        AdvancedReport oldReport = new AdvancedReport();
        CustomerInputs input = new CustomerInputs();
        input.setEmailAddress("abc@gmail.com");
        input.setZipFileName("8000-4000.zip");
        input.setServiceType("Assembly");
        newReport.setCustomerInputs(input);

        CustomerInputs oldInput = new CustomerInputs();
        oldInput.setEmailAddress("xyz@gmail.com");
        oldInput.setZipFileName("7850-3200.zip");
        oldInput.setServiceType("Fabrication");
        oldReport.setCustomerInputs(oldInput);
        Map<String, String> result = ReportCompareUtility.compare(newReport, oldReport);

        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("emailAddress", "abc@gmail.com~xyz@gmail.com");
        expectedResult.put("serviceType", "Assembly~Fabrication");
        expectedResult.put("zipFileName", "8000-4000.zip~7850-3200.zip");
        assertEquals(expectedResult, result);
    }

    //Test with instanceVariable(customerInputs) of AdvancedReport in compare method, of class ReportCompareUtility.
    @Test
    public void testCompareReportDetails3() throws Exception {
        AdvancedReport newReport = new AdvancedReport();
        AdvancedReport oldReport = new AdvancedReport();
        CustomerInputs input = new CustomerInputs();
        input.setEmailAddress("abc@gmail.com");
        input.setZipFileName("8000-4000.zip");
        input.setServiceType("Assembly");
        newReport.setCustomerInputs(input);

        oldReport.setOdbMatrix("Tryy");
        Map<String, String> result = ReportCompareUtility.compare(newReport, oldReport);

        Map<String, String> expectedResult = new HashMap<>();
        assertEquals(expectedResult, result);
    }

    //Test with instanceVariable(odbMatrix) of AdvancedReport in compare method, of class ReportCompareUtility.
    @Test
    public void testCompareReportDetails4() throws Exception {
        AdvancedReport newReport = new AdvancedReport();
        AdvancedReport oldReport = new AdvancedReport();
        newReport.setOdbMatrix("newODBTEST");
        oldReport.setOdbMatrix("oldODBMatrix");

        Map<String, String> result = ReportCompareUtility.compare(newReport, oldReport);

        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("odbMatrix", "newODBTEST~oldODBMatrix");
        assertEquals(expectedResult, result);
    }

    /**
     * Test of compareFileDetails method, of class FileDetailCompareUtility.
     */
    //Test with instanceVariables(attributes) of FileDetails in compareObject method, of class FileDetailCompareUtility.
    @Test
    public void testCompareFileDetails1() throws Exception {
        FileDetails newFD = new FileDetails();
        Map<String, String> attr = new HashMap<>();
        attr.put("lpol_done", "no");
        attr.put("et_adjacency", "20.000000");
        attr.put("eda_layers", "TopOverlay");
        newFD.setAttributes(attr);

        FileDetails oldFD = new FileDetails();
        Map<String, String> oldattr = new HashMap<>();
        oldattr.put("lpol_done", "no");
        oldattr.put("et_adjacency", "10.000000");
        oldattr.put("eda_layers", "TopPaste");
        oldFD.setAttributes(oldattr);

        FileDetailCompareUtility instance = new FileDetailCompareUtility();
        Map<String, String> result = instance.compareObject(newFD, oldFD);

        //To check that object initialize with attributes or not,if YES ,then process
        if (!(oldFD.getAttributes() == null && newFD.getAttributes() == null)) {
            result.putAll(FileDetailCompareUtility.compare(newFD, oldFD));
        }
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("et_adjacency", "20.000000~10.000000");
        expectedResult.put("eda_layers", "TopOverlay~TopPaste");
        assertEquals(expectedResult, result);
    }

    //Test with null case in compareObject method, of class FileDetailCompareUtility.
    @Test
    public void testCompareFileDetails2() throws Exception {
        FileDetails newFD = null;
        FileDetails oldFD = null;
        FileDetailCompareUtility instance = new FileDetailCompareUtility();
        Map<String, String> result = instance.compareObject(newFD, oldFD);
        assertEquals(null, result);
    }

    //Test with instanceVariables(projectId,fileSize) of FileDetails in compareObject method, of class FileDetailCompareUtility.
    @Test
    public void testCompareFileDetails3() throws Exception {
        FileDetails newFD = new FileDetails();
        newFD.setProjectId("abc123");
        newFD.setFileSize("20 MB");

        FileDetails oldFD = new FileDetails();
        oldFD.setProjectId("111");
        oldFD.setFileSize("35 MB");

        FileDetailCompareUtility instance = new FileDetailCompareUtility();
        Map<String, String> result = instance.compareObject(newFD, oldFD);

        //To check that object initialize with attributes or not,if YES ,then process
        if (!(oldFD.getAttributes() == null && newFD.getAttributes() == null)) {
            result.putAll(FileDetailCompareUtility.compare(newFD, oldFD));
        }

        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("fileSize", "20 MB~35 MB");
        expectedResult.put("projectId", "abc123~111");
        assertEquals(expectedResult, result);
    }

    //Test with instanceVariables(projectId,fileFormat,name) of FileDetails in compareObject method, of class FileDetailCompareUtility.
    @Test
    public void testCompareFileDetails4() throws Exception {
        FileDetails newFD = new FileDetails();
        newFD.setProjectId("abc123");
        newFD.setFileFormat("Gerber");
        newFD.setName("New");

        FileDetails oldFD = new FileDetails();
        oldFD.setProjectId("111");
        oldFD.setFileFormat("Drill");
        oldFD.setName("OLD");
        FileDetailCompareUtility instance = new FileDetailCompareUtility();
        Map<String, String> result = instance.compareObject(newFD, oldFD);

        //To check that object initialize with attributes or not,if YES ,then process
        if (!(oldFD.getAttributes() == null && newFD.getAttributes() == null)) {
            result.putAll(FileDetailCompareUtility.compare(newFD, oldFD));
        }

        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("name", "New~OLD");
        expectedResult.put("projectId", "abc123~111");
        expectedResult.put("fileFormat", "Gerber~Drill");
        assertEquals(expectedResult, result);
    }

    //Test with instanceVariables(layerInfo and ProjectId) of FileDetails in compareObject method, of class FileDetailCompareUtility.
    @Test
    public void testCompareFileDetails5() throws Exception {
        FileDetails newFD = new FileDetails();
        LayersInformation newLayerInfo = new LayersInformation("1", "BOARD", "SIGNAL", "BOTTOM_SOLDER", "POSITIVE", "", "", "");
        newFD.setLayerInfo(newLayerInfo);
        newFD.setProjectId("new123");

        FileDetails oldFD = new FileDetails();
        LayersInformation oldLayerInfo = new LayersInformation("5", "BOARD", "SILK_SCREEN", "TOP_OVERLAY", "POSITIVE", "", "", "");
        oldFD.setLayerInfo(oldLayerInfo);
        oldFD.setProjectId("old123");

        FileDetailCompareUtility instance = new FileDetailCompareUtility();
        Map<String, String> result = instance.compareObject(newLayerInfo, oldLayerInfo);
        result.putAll(instance.compareObject(newFD, oldFD));

        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("name", "BOTTOM_SOLDER~TOP_OVERLAY");
        expectedResult.put("layerInfo", newLayerInfo + "~" + oldLayerInfo);
        expectedResult.put("row", "1~5");
        expectedResult.put("type", "SIGNAL~SILK_SCREEN");
        expectedResult.put("projectId", "new123~old123");
        assertEquals(expectedResult, result);
    }

    //Test with instanceVariables(attribute and ProjectId) of FileDetails in compareObject method, of class FileDetailCompareUtility.
    @Test
    public void testCompareFileDetails6() throws Exception {
        FileDetails newFD = new FileDetails();
        Map<String, String> attr = new HashMap<>();
        attr.put("et_adjacency", "20.000000");
        attr.put("eda_layers", "TopOverlay");
        newFD.setAttributes(attr);
        newFD.setProjectId("new123");

        FileDetails oldFD = new FileDetails();
        Map<String, String> oldattr = new HashMap<>();
        oldattr.put("et_adjacency", "10.000000");
        oldattr.put("eda_layers", "TopPaste");
        oldFD.setAttributes(oldattr);
        oldFD.setProjectId("old123");

        FileDetailCompareUtility instance = new FileDetailCompareUtility();
        Map<String, String> result = instance.compareObject(newFD, oldFD);

        //To check that object initialize with attributes or not,if YES ,then process                
        if (!(oldFD.getAttributes() == null && newFD.getAttributes() == null)) {
            result.putAll(FileDetailCompareUtility.compare(newFD, oldFD));
        }
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("et_adjacency", "20.000000~10.000000");
        expectedResult.put("eda_layers", "TopOverlay~TopPaste");
        expectedResult.put("projectId", "new123~old123");
        assertEquals(expectedResult, result);
    }

    //Test with instanceVariables(attribute,layerInfo and ProjectId) of FileDetails in compareObject method, of class FileDetailCompareUtility.
    @Test
    public void testCompareFileDetails7() throws Exception {
        FileDetails newFD = new FileDetails();
        Map<String, String> attr = new HashMap<>();
        attr.put("et_adjacency", "20.000000");
        attr.put("eda_layers", "TopOverlay");
        newFD.setAttributes(attr);
        newFD.setProjectId("new123");
        LayersInformation newLayerInfo = new LayersInformation("1", "BOARD", "SIGNAL", "BOTTOM_SOLDER", "POSITIVE", "", "", "");
        newFD.setLayerInfo(newLayerInfo);

        FileDetails oldFD = new FileDetails();
        Map<String, String> oldattr = new HashMap<>();
        oldattr.put("et_adjacency", "10.000000");
        oldattr.put("eda_layers", "TopPaste");
        oldFD.setAttributes(oldattr);
        oldFD.setProjectId("old123");
        LayersInformation oldLayerInfo = new LayersInformation("5", "BOARD", "SILK_SCREEN", "TOP_OVERLAY", "POSITIVE", "", "", "");
        oldFD.setLayerInfo(oldLayerInfo);

        FileDetailCompareUtility instance = new FileDetailCompareUtility();
        Map<String, String> result = instance.compareObject(newLayerInfo, oldLayerInfo);
        result.putAll(instance.compareObject(newFD, oldFD));

        //To check that object initialize with attributes or not,if YES ,then process                
        if (!(oldFD.getAttributes() == null && newFD.getAttributes() == null)) {
            result.putAll(FileDetailCompareUtility.compare(newFD, oldFD));
        }
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("et_adjacency", "20.000000~10.000000");
        expectedResult.put("eda_layers", "TopOverlay~TopPaste");
        expectedResult.put("projectId", "new123~old123");
        expectedResult.put("name", "BOTTOM_SOLDER~TOP_OVERLAY");
        expectedResult.put("layerInfo", newLayerInfo + "~" + oldLayerInfo);
        expectedResult.put("row", "1~5");
        expectedResult.put("type", "SIGNAL~SILK_SCREEN");
        assertEquals(expectedResult, result);
    }

    /**
     * Test of compare method, of class FileDetailCompareUtility.
     */
    @Test
    public void testCompare1() {
        FileDetails newFD = new FileDetails();
        Map<String, String> attr = new HashMap<>();
        attr.put("lpol_done", "no");
        attr.put("et_adjacency", "20.000000");
        attr.put("eda_layers", "TopOverlay");
        newFD.setAttributes(attr);

        FileDetails oldFD = new FileDetails();
        Map<String, String> oldattr = new HashMap<>();
        oldattr.put("lpol_done", "no");
        oldattr.put("et_adjacency", "10.000000");
        oldattr.put("eda_layers", "TopPaste");
        oldFD.setAttributes(oldattr);
        Map<String, String> result = FileDetailCompareUtility.compare(newFD, oldFD);

        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("et_adjacency", "20.000000~10.000000");
        expectedResult.put("eda_layers", "TopOverlay~TopPaste");
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCompare2() {
        FileDetails newFD = null;
        FileDetails oldFD = null;
        Map<String, String> result = FileDetailCompareUtility.compare(newFD, oldFD);
        assertEquals(null, result);
    }
}
