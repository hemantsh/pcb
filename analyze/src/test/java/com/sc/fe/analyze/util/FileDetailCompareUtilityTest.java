/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import com.sc.fe.analyze.to.AdvancedReport;
import com.sc.fe.analyze.to.CustomerInformation;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.LayersInformation;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hemant
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
<<<<<<< HEAD
     */      
=======
     */
   
    //Test with instanceVariables(layerInfo and ProjectId) of FileDetails in compareObject method, of class FileDetailCompareUtility.
    @Test
    public void testCompareFileDetails5() throws Exception {
        FileDetails newFD = new FileDetails();
        LayersInformation newLayerInfo = new LayersInformation("1", "BOARD", "SIGNAL", "BOTTOM_SOLDER", "POSITIVE", "", "", "");
//        newFD.setLayerInfo(newLayerInfo);
//        newFD.setProjectId("new123");

        FileDetails oldFD = new FileDetails();
        LayersInformation oldLayerInfo = new LayersInformation("5", "BOARD", "SILK_SCREEN", "TOP_OVERLAY", "POSITIVE", "", "", "");
//        oldFD.setLayerInfo(oldLayerInfo);
//        oldFD.setProjectId("old123");

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

>>>>>>> upstream/hemant
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
        CustomerInformation input = new CustomerInformation();
        input.setEmailAddress("abc@gmail.com");
//        input.setZipFileName("8000-4000.zip");
//        input.setServiceType("Assembly");
//        newReport.setCustomerInputs(input);

        CustomerInformation oldInput = new CustomerInformation();
        oldInput.setEmailAddress("xyz@gmail.com");
//        oldInput.setZipFileName("7850-3200.zip");
//        oldInput.setServiceType("Fabrication");
//        oldReport.setCustomerInputs(oldInput);
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
        CustomerInformation input = new CustomerInformation();
        input.setEmailAddress("abc@gmail.com");
//        input.setZipFileName("8000-4000.zip");
//        input.setServiceType("Assembly");
//        newReport.setCustomerInputs(input);

//        oldReport.setOdbMatrix("Tryy");
        Map<String, String> result = ReportCompareUtility.compare(newReport, oldReport);

        Map<String, String> expectedResult = new HashMap<>();
        assertEquals(expectedResult, result);
    }

    //Test with instanceVariable(odbMatrix) of AdvancedReport in compare method, of class ReportCompareUtility.
    @Test
    public void testCompareReportDetails4() throws Exception {
        AdvancedReport newReport = new AdvancedReport();
        AdvancedReport oldReport = new AdvancedReport();
//        newReport.setOdbMatrix("newODBTEST");
//        oldReport.setOdbMatrix("oldODBMatrix");

        Map<String, String> result = ReportCompareUtility.compare(newReport, oldReport);

        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("odbMatrix", "newODBTEST~oldODBMatrix");
        assertEquals(expectedResult, result);
    }    

    //Test with object of AdvancedReport in compare method, of class ReportCompareUtility.
    @Test
    public void testCompareReportDetails5() throws Exception 
    {
        AdvancedReport oldReport = new AdvancedReport();
        CustomerInputs custInputs = new CustomerInputs();
        custInputs.setProjectId("1234");
        custInputs.setZipFileName("8000-4890.zip");
        custInputs.setZipFileSize("4.6 MB");
        custInputs.setServiceType("Assembly");
        custInputs.setCustomerId("CustId");        
        custInputs.setQuantity(100);
        custInputs.setTurnTime(5);
        oldReport.setCustomerInputs(custInputs);
        
        FileDetails fd = new FileDetails();
        fd.setFileFormat("Gerber");
        fd.setName("uploads\\abc11\\8000-4890CPWIZA.GBS");
        fd.setFileSize("125 MB");
        fd.setModifiedDate(new Date());
        fd.setCopperWeight(".95");
        fd.setLayerSequence(1);
        fd.setEndName("endName");
        fd.setStartName("startName");

        oldReport.addFileDetail(fd);

        //Add new AdvancedReport object
        AdvancedReport newReport = new AdvancedReport();
        CustomerInputs newcustInputs = new CustomerInputs();
        newcustInputs.setProjectId("abc123");
        newcustInputs.setZipFileName("8000-4890.zip");
        newcustInputs.setZipFileSize("5.2 MB");
        newcustInputs.setServiceType("Fabrication");
        newcustInputs.setCustomerId("newCustId");
        newcustInputs.setQuantity(150);
        newcustInputs.setTurnTime(15);
        newReport.setCustomerInputs(newcustInputs);

        FileDetails newfd = new FileDetails();
        newfd.setFileFormat("ODB");        
        newfd.setName("uploads\\abc11\\8000-4890CPWIZA.GBO");
        newfd.setFileSize("350 MB");
        newfd.setModifiedDate(new Date());
        newfd.setCopperWeight("1.95");
        newfd.setLayerSequence(0);
        newfd.setEndName("endNamee");
        newfd.setStartName("startNamee");
        newReport.addFileDetail(newfd);
        Map<String, String> result = ReportCompareUtility.compare(newReport, oldReport);
        
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("serviceType", "Fabrication~Assembly");
        expectedResult.put("quantity", "150~100");
        expectedResult.put("copperWeight", "1.95~.95");
        expectedResult.put("zipFileSize", "5.2 MB~4.6 MB");
        expectedResult.put("turnTime", "15~5");
        expectedResult.put("fileSize", "350 MB~125 MB");
        expectedResult.put("endName", "endNamee~endName");
        expectedResult.put("customerId", "newCustId~CustId");
        expectedResult.put("name", "uploads\\abc11\\8000-4890CPWIZA.GBO~uploads\\abc11\\8000-4890CPWIZA.GBS");
        expectedResult.put("projectId", "abc123~1234");
        expectedResult.put("fileFormat", "ODB~Gerber");
        expectedResult.put("layerSequence", "0~1");
        expectedResult.put("startName", "startNamee~startName");        
        
        assertEquals(expectedResult, result);
    }
    
    //Test with object(fileDetails) of AdvancedReport in compare method, of class ReportCompareUtility.
    @Test
    public void testCompareReportDetails6() throws Exception 
    {
        AdvancedReport oldReport = new AdvancedReport();   
        FileDetails fd = new FileDetails();
        fd.setFileFormat("Gerber");
        fd.setName("uploads\\abc11\\8000-4890CPWIZA.GBS");
        fd.setFileSize("125 MB");
        fd.setCopperWeight(".95");
        fd.setLayerSequence(1);        
        oldReport.addFileDetail(fd);

        //Add new AdvancedReport object
        AdvancedReport newReport = new AdvancedReport();       
        FileDetails newfd = new FileDetails();
        newfd.setFileFormat("ODB");        
        newfd.setName("uploads\\abc11\\8000-4890CPWIZA.GBS");
        newfd.setFileSize("350 MB");        
        newfd.setCopperWeight("1.95");
        newfd.setLayerSequence(0);
        newReport.addFileDetail(newfd);
        Map<String, String> result = ReportCompareUtility.compare(newReport, oldReport);
        
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("copperWeight", "1.95~.95");
        expectedResult.put("fileFormat", "ODB~Gerber");
        expectedResult.put("layerSequence", "0~1");
        expectedResult.put("fileSize","350 MB~125 MB");        
        assertEquals(expectedResult, result);
    }
    
    //Test with object(odbMatrix and fileDetails) of AdvancedReport in compare method, of class ReportCompareUtility.
    @Test
    public void testCompareReportDetails7() throws Exception 
    {
        AdvancedReport oldReport = new AdvancedReport();  
        oldReport.setOdbMatrix("oldODB");
        FileDetails fd = new FileDetails();
        fd.setFileFormat("Gerber");
        fd.setName("uploads\\abc11\\8000-4890CPWIZA.GBS");       
        fd.setCopperWeight(".95");        
        oldReport.addFileDetail(fd);       

        //Add new AdvancedReport object
        AdvancedReport newReport = new AdvancedReport();       
        newReport.setOdbMatrix("newODB");
        FileDetails newfd = new FileDetails();
        newfd.setFileFormat("ODB");        
        newfd.setName("uploads\\abc11\\8000-4890CPWIZA.GBS");        
        newfd.setCopperWeight("1.95");        
        newReport.addFileDetail(newfd);
        Map<String, String> result = ReportCompareUtility.compare(newReport, oldReport);        
        
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("copperWeight", "1.95~.95");
        expectedResult.put("fileFormat", "ODB~Gerber");        
        expectedResult.put("odbMatrix","newODB~oldODB");        
        assertEquals(expectedResult, result);
    }
    //Test with object(attributes in fileDetails) of AdvancedReport in compare method, of class ReportCompareUtility.
    @Test
    public void testCompareReportDetails8() throws Exception 
    {
        AdvancedReport oldReport = new AdvancedReport();          
        FileDetails fd = new FileDetails();
        fd.setFileFormat("Gerber");
        fd.setName("uploads\\abc11\\8000-4890CPWIZA.GBS");       
        
        Map<String, String> attr = new HashMap<>();       
        attr.put("et_adjacency", "20.000000");
        attr.put("eda_layers", "TopOverlay");
        fd.setAttributes(attr);
        oldReport.addFileDetail(fd);       

        //Add new AdvancedReport object
        AdvancedReport newReport = new AdvancedReport();               
        FileDetails newfd = new FileDetails();
        newfd.setFileFormat("Gerber");        
        newfd.setName("uploads\\abc11\\8000-4890CPWIZA.GBL");                
        newReport.addFileDetail(newfd);
        
        Map<String, String> newAttr = new HashMap<>();
        newAttr.put("lpol_done", "no");
        newAttr.put("et_adjacency", "10.000000");
        newAttr.put("eda_layers", "TopPaste");
        newfd.setAttributes(newAttr);
        
        Map<String, String> result = ReportCompareUtility.compare(newReport, oldReport);          
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("name", "uploads\\abc11\\8000-4890CPWIZA.GBL~uploads\\abc11\\8000-4890CPWIZA.GBS");        
        expectedResult.put("et_adjacency", "10.000000~20.000000");        
        expectedResult.put("lpol_done", "no~");        
        expectedResult.put("eda_layers", "TopPaste~TopOverlay");                
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

        //To check that FileDetailobject initialize with attributes or not,if YES ,then process
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
//        newFD.setProjectId("abc123");
        newFD.setFileSize("20 MB");

        FileDetails oldFD = new FileDetails();
//        oldFD.setProjectId("111");
        oldFD.setFileSize("35 MB");

        FileDetailCompareUtility instance = new FileDetailCompareUtility();
        Map<String, String> result = instance.compareObject(newFD, oldFD);

        //To check that FileDetailobject initialize with attributes or not,if YES ,then process
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
//        newFD.setProjectId("abc123");
//        newFD.setFileFormat("Gerber");
        newFD.setName("New");

        FileDetails oldFD = new FileDetails();
//        oldFD.setProjectId("111");
//        oldFD.setFileFormat("Drill");
        oldFD.setName("OLD");
        FileDetailCompareUtility instance = new FileDetailCompareUtility();
        Map<String, String> result = instance.compareObject(newFD, oldFD);

        //To check that FileDetailobject initialize with attributes or not,if YES ,then process
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
//        newFD.setProjectId("new123");

        FileDetails oldFD = new FileDetails();
        Map<String, String> oldattr = new HashMap<>();
        oldattr.put("et_adjacency", "10.000000");
        oldattr.put("eda_layers", "TopPaste");
        oldFD.setAttributes(oldattr);
//        oldFD.setProjectId("old123");

        FileDetailCompareUtility instance = new FileDetailCompareUtility();
        Map<String, String> result = instance.compareObject(newFD, oldFD);

        //To check that FileDetailobject initialize with attributes or not,if YES ,then process                
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
//        newFD.setProjectId("new123");
        LayersInformation newLayerInfo = new LayersInformation("1", "BOARD", "SIGNAL", "BOTTOM_SOLDER", "POSITIVE", "", "", "");
//        newFD.setLayerInfo(newLayerInfo);

        FileDetails oldFD = new FileDetails();
        Map<String, String> oldattr = new HashMap<>();
        oldattr.put("et_adjacency", "10.000000");
        oldattr.put("eda_layers", "TopPaste");
        oldFD.setAttributes(oldattr);
//        oldFD.setProjectId("old123");
        LayersInformation oldLayerInfo = new LayersInformation("5", "BOARD", "SILK_SCREEN", "TOP_OVERLAY", "POSITIVE", "", "", "");
//        oldFD.setLayerInfo(oldLayerInfo);

        FileDetailCompareUtility instance = new FileDetailCompareUtility();
        Map<String, String> result = instance.compareObject(newLayerInfo, oldLayerInfo);
        result.putAll(instance.compareObject(newFD, oldFD));

        //To check that FileDetailobject initialize with attributes or not,if YES ,then process                
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
