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
import java.util.Date;
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
    public void testCompareReportDetails1()
    {
        AdvancedReport newReport=new AdvancedReport();
        AdvancedReport oldReport=new AdvancedReport();
        newReport=null;
        oldReport=null;
        Map<String, String> result=ReportCompareUtility.compare(newReport, oldReport);
        assertEquals(null,result);
    }
    @Test
    public void testCompareReportDetails2()
    {
        AdvancedReport newReport=new AdvancedReport();
        AdvancedReport oldReport=new AdvancedReport();
        CustomerInputs input=new CustomerInputs();
        input.setEmailAddress("abc@gmail.com");
        input.setZipFileName("8000-4000.zip");
        input.setServiceType("Assembly");
        newReport.setCustomerInputs(input);
        
        CustomerInputs oldInput=new CustomerInputs();
        oldInput.setEmailAddress("xyz@gmail.com"); 
        oldInput.setZipFileName("8000-4000.zip");
        oldInput.setServiceType("Fabrication");
        oldReport.setCustomerInputs(oldInput);
        Map<String, String> result=ReportCompareUtility.compare(newReport, oldReport);
                
        Map<String, String> expectedResult=new HashMap<>();
        expectedResult.put("Email ID", "abc@gmail.com~xyz@gmail.com");
        expectedResult.put("Service Type", "Assembly~Fabrication");
        assertEquals(expectedResult,result);            
    }    

    /**
     * Test of compareFileDetails method, of class FileDetailCompareUtility.
     */
    @Test
    public void testCompareFileDetails() throws Exception {
        FileDetails newFD=new FileDetails();         
        Map<String,String> attr=new HashMap<>();
        attr.put("lpol_done", "no");
        attr.put("et_adjacency", "20.000000");
        attr.put("eda_layers", "TopOverlay");
        newFD.setAttributes(attr);        
       
        FileDetails oldFD=new FileDetails();
        Map<String,String> oldattr=new HashMap<>();
        oldattr.put("lpol_done", "no");
        oldattr.put("et_adjacency", "10.000000");
        oldattr.put("eda_layers", "TopPaste");
        oldFD.setAttributes(oldattr);           
        
        FileDetailCompareUtility instance = new FileDetailCompareUtility();       
        Map<String, String> result = instance.compareFileDetails(newFD, oldFD);
        
        Map<String, String> expectedResult=new HashMap<>();
        expectedResult.put("et_adjacency", "20.000000~10.000000");
        expectedResult.put("eda_layers", "TopOverlay~TopPaste");
        assertEquals(expectedResult,result);
    }
  
    @Test
    public void testCompareFileDetails2() throws Exception{
        FileDetails newFD = null;
        FileDetails oldFD = null;
        FileDetailCompareUtility instance = new FileDetailCompareUtility();
        Map<String, String> result = instance.compareFileDetails(newFD, oldFD);
        assertEquals(null,result);
    }
    
    @Test
    public void testCompareFileDetails3() throws Exception 
    {
        FileDetails newFD=new FileDetails();
        newFD.setProjectId("abc123");   
        newFD.setFileSize("20 MB");
               
        FileDetails oldFD=new FileDetails();
        oldFD.setProjectId("111"); 
        oldFD.setFileSize("35 MB");
        
        FileDetailCompareUtility instance = new FileDetailCompareUtility();
        Map<String, String> result = instance.compareFileDetails(newFD, oldFD);
        
        Map<String, String> expectedResult=new HashMap<>();
        expectedResult.put("fileSize", "20 MB~35 MB");
        expectedResult.put("projectId", "abc123~111");
        assertEquals(expectedResult,result);        
    }
    
    @Test
    public void testCompareFileDetails4() throws Exception 
    {
        FileDetails newFD=new FileDetails();
        newFD.setProjectId("abc123");
        newFD.setFileFormat("Gerber");
        newFD.setName("New");
        
        FileDetails oldFD=new FileDetails();
        oldFD.setProjectId("111");
        oldFD.setFileFormat("Drill");       
        oldFD.setName("OLD");
        FileDetailCompareUtility instance = new FileDetailCompareUtility();
        Map<String, String> result = instance.compareFileDetails(newFD, oldFD);
        
         Map<String, String> expectedResult=new HashMap<>();
        expectedResult.put("name", "New~OLD");
        expectedResult.put("projectId", "abc123~111");
        expectedResult.put("fileFormat", "Gerber~Drill");
        assertEquals(expectedResult,result);
    }
    
    /**
     * Test of compare method, of class FileDetailCompareUtility.
     */
    @Test
    public void testCompare1() {
        FileDetails newFD=new FileDetails();
        Map<String,String> attr=new HashMap<>();
        attr.put("lpol_done", "no");
        attr.put("et_adjacency", "20.000000");        
        attr.put("eda_layers", "TopOverlay");
        newFD.setAttributes(attr);
       
        FileDetails oldFD=new FileDetails();
        Map<String,String> oldattr=new HashMap<>();
        oldattr.put("lpol_done", "no");
        oldattr.put("et_adjacency", "10.000000");
        oldattr.put("eda_layers", "TopPaste");
        oldFD.setAttributes(oldattr);       
        Map<String, String> result = FileDetailCompareUtility.compare(newFD, oldFD);
        
         Map<String, String> expectedResult=new HashMap<>();
        expectedResult.put("et_adjacency", "20.000000~10.000000");
        expectedResult.put("eda_layers", "TopOverlay~TopPaste");      
        assertEquals(expectedResult,result);       
    }
    
    @Test
    public void testCompare2() 
    {
        FileDetails newFD = null;
        FileDetails oldFD = null;
        Map<String, String> result = FileDetailCompareUtility.compare(newFD, oldFD);
        assertEquals(null,result);
    }    
}
