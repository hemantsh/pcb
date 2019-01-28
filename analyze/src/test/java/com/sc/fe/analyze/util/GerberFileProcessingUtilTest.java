/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sc.fe.analyze.to.FileDetails;
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

    @Test
    public void testFileParsing() {
    	FileDetails fd = new FileDetails();
    	fd.setName("88000-4890 REV A PCA, CONTROL PANEL (FAB).pdf");
    	GerberFileProcessingUtil.parseFileName( fd );
    	assertNotNull(fd.getType());
    }
    
    /********************
     * Test of TA attribute of processTFTATO() method, of class GerberFileProcessingUtil.
     */
     @Test
    public void testProcessTA1()
    {
        String line="";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TA");
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessTA2()
    {
        String line=null;
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TA");
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessTA3()
    {
        String line="%TA.AperFunction,ComponentPad*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TA");
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("AperFunction", "ComponentPad");
        assertEquals(expectedResult,check);
    }
    @Test
    public void testProcessTA4()
    {
        String line="%TA.AperFunctionComponentPad*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TA");
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("AperFunctionComponentPad", "");
        assertEquals(expectedResult,check);        
    }
    @Test
    public void testProcessTA5()
    {
        String line="%TAAperFunction,ComponentPad*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TA");
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("AperFunction", "ComponentPad");
        assertEquals(expectedResult,check);         
    }
    @Test
    public void testProcessTA6()
    {
        String line="%TAAperFunctionComponentPad*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TA");
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("AperFunctionComponentPad", "");
        assertEquals(expectedResult,check);            
    }
    @Test
    public void testProcessTA7()
    {
        String line="%AperFunctionComponentPad*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TA");
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessTA8()
    {
        String line="%TA.FlashText,XZ12ADF,B,,Code128,,Project identifier *%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TA");
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("FlashText", "XZ12ADF,B,,Code128,,Project identifier ");
        assertEquals(expectedResult,check);          
    }
    
     /**
     * Test of TF attribute of processTFTATO() method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessTF1()
    {
        String line="";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TF");
        assertEquals(null, check.get(""));            
    }
    @Test
    public void testProcessTF2()
    {
        String line=null;
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TF");
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessTF3()
    {
        String line="%TF.FilePolarityPositive*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TF");
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("FilePolarityPositive", "");
        assertEquals(expectedResult,check);                  
    }
    @Test
    public void testProcessTF4()
    {
        String line="%TFFilePolarityPositive*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TF");
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("FilePolarityPositive", "");
        assertEquals(expectedResult,check);                
    }
    @Test
    public void testProcessTF5()
    {
        String line="%TFFileFunction,Copper,L1,Top*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TF");
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("FileFunction", "Copper,L1,Top");
        assertEquals(expectedResult,check);                        
    }
     @Test    
    public void testProcessTF6()
    {
        String line="%TF.FilePolarity,Positive*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TF");
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("FilePolarity", "Positive");
        assertEquals(expectedResult,check);                                
    }
    @Test
    public void testProcessTF7()
    {
        String line="%TF.FileFunction,Copper,L1,Top*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TF");
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("FileFunction", "Copper,L1,Top");
        assertEquals(expectedResult,check);                                        
    }
    @Test
    public void testProcessTF8()
    {
        String line="%TF.CreationDate,2016-04-25T00:00:00+01:00*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TF");
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("CreationDate", "2016-04-25T00:00:00+01:00");
        assertEquals(expectedResult,check);                                                
    }
     
     /**
     * Test of TO attribute of processTFTATO() method, of class GerberFileProcessingUtil.
     */
     @Test
    public void testProcessTO1()
    {
        String line="";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TO");
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessTO2()
    {
        String line=null;
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TO");
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessTO3()
    {
        String line="%TOC,R6*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TO");
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("C", "R6");
        assertEquals(expectedResult,check);                                                        
    }
    @Test
    public void testProcessTO4()
    {
        String line="%TOP,R5,1*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TO");
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("P", "R5,1");
        assertEquals(expectedResult,check);                                                                
    }
    @Test
    public void testProcessTO5()
    {
        String line="%TO.C,R6*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TO");
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("C", "R6");
        assertEquals(expectedResult,check);                                                                        
    }
    @Test
    public void testProcessTO6()
    {
        String line="%TO.P,R5,1*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TO");
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("P", "R5,1");
        assertEquals(expectedResult,check);         
    }
    @Test
    public void testProcessTO7()
    {
        String line="%TO.N,N/C*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTFTATO(line, "%TO");
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("N", "N/C");
        assertEquals(expectedResult,check);                 
    }
        
     /*************
     * Test of processGO4() method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessGO41()
    {
        String line="";
        HashMap<String,String> check=GerberFileProcessingUtil.processG04(line);
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessGO42()
    {
        String line=null;
        HashMap<String,String> check=GerberFileProcessingUtil.processG04(line);
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessGO43()
    {
        String line="G04 Layer_Physical_Order=4*";
        HashMap<String,String> check=GerberFileProcessingUtil.processG04(line);
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("Layer_Physical_Order", "4");
        expectedResult.put("Layer", "4");
        assertEquals(expectedResult,check);                 
    }
    @Test
    public void testProcessGO44()
    {
        String line="G04 Layer_Color=16711680*";
        HashMap<String,String> check=GerberFileProcessingUtil.processG04(line);
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("Layer_Color", "16711680");
        assertEquals(expectedResult,check);                       
    }
    @Test
    public void testProcessGO45()
    {
        String line="G04:AMPARAMS|DCode=50|XSize=66mil|YSize=66mil|CornerRadius=0mil|HoleSize=0mil|Usage=FLASHONLY|Rotation=0.000|*";
        HashMap<String,String> check=GerberFileProcessingUtil.processG04(line);
        Map<String, String> expectedResult=new HashMap<>();
        expectedResult.put("D50.XSize","66mil");
        expectedResult.put("D50.YSize","66mil");
        expectedResult.put("D50.CornerRadius","0mil");
        expectedResult.put("D50.HoleSize","0mil");
        expectedResult.put("D50.Usage","FLASHONLY");
        expectedResult.put("D50.Rotation","0.000"); 
        
        assertEquals(expectedResult,check);         
   }
    
     /*************
     * Test of processFSLA() method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessFSLA1()
    {
        String line="";
        HashMap<String,String> check=GerberFileProcessingUtil.processFSLA(line);
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessFSLA2()
    {
        String line=null;
        HashMap<String,String> check=GerberFileProcessingUtil.processFSLA(line);
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessFSLA3()
    {
        String line="%FSLAX25Y25*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processFSLA(line);
        Map<String, String> expectedResult=new HashMap<>();
        expectedResult.put("X","25");
        expectedResult.put("Y","25");
        assertEquals(expectedResult,check);                
    }
    @Test
    public void testProcessFSLA4()
    {
        String line="%FSLAX2.6Y2.6*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processFSLA(line);
        Map<String, String> expectedResult=new HashMap<>();
        expectedResult.put("X","2.6");
        expectedResult.put("Y","2.6");
        assertEquals(expectedResult,check);         
    }    
    
    /*************
     * Test of processTD() method, of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessTD1()
    {
        String line="";
        HashMap<String,String> check=GerberFileProcessingUtil.processTD(line);
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessTD2()
    {
        String line=null;
        HashMap<String,String> check=GerberFileProcessingUtil.processTD(line);
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessTD3()
    {
        String line="%TD.AperFunction*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTD(line);
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("AperFunction", "");
        assertEquals(expectedResult,check); 
    }
    @Test
    public void testProcessTD4()
    {
        String line="%TDIAmATA*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTD(line);
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("IAmATA", "");
        assertEquals(expectedResult,check);        
    }
    @Test
    public void testProcessTD5()
    {
        String line="%TD*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processTD(line);
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("", "");
        assertEquals(expectedResult,check);             
    }
    
     /*************
     * Test of MO attribute of processMOLPLM() method , of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessMO1()
    {
        String line="";
        HashMap<String,String> check=GerberFileProcessingUtil.processMOLPLM(line, "MO");
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessMO2()
    {
        String line=null;
        HashMap<String,String> check=GerberFileProcessingUtil.processMOLPLM(line, "MO");
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessMO3()
    {
        String line="%MOIN*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processMOLPLM(line, "MO");
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("MO", "IN");
        assertEquals(expectedResult,check);         
    }
    @Test
    public void testProcessMO4()
    {
        String line="%MOMM*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processMOLPLM(line, "MO");
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("MO", "MM");
        assertEquals(expectedResult,check);                 
    }
    
    /*************
     * Test of LP attribute of processMOLPLM() method , of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessLP1()
    {
        String line="";
        HashMap<String,String> check=GerberFileProcessingUtil.processMOLPLM(line, "LP");
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessLP2()
    {
        String line=null;
        HashMap<String,String> check=GerberFileProcessingUtil.processMOLPLM(line, "LP");
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessLP3()
    {
        String line="%LPD*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processMOLPLM(line, "LP");
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("LP", "D");
        assertEquals(expectedResult,check);         
    }
    @Test
    public void testProcessLP4()
    {
        String line="%LPC*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processMOLPLM(line, "LP");
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("LP", "C");
        assertEquals(expectedResult,check);         
    } 
    
     /*************
     * Test of LM attribute of processMOLPLM() method , of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessLM1()
    {
        String line="";
        HashMap<String,String> check=GerberFileProcessingUtil.processMOLPLM(line, "LM");
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessLM2()
    {
        String line=null;
        HashMap<String,String> check=GerberFileProcessingUtil.processMOLPLM(line, "LM");
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessLM3()
    {
        String line="%LMX*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processMOLPLM(line, "LM");
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("LM", "X");
        assertEquals(expectedResult,check);         
    }
    @Test
    public void testProcessLM4()
    {
        String line="%LMN*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processMOLPLM(line, "LM");
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("LM", "N");
        assertEquals(expectedResult,check);         
    }
    @Test
    public void testProcessLM5()
    {
        String line="%LMY*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processMOLPLM(line, "LM");
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("LM", "Y");
        assertEquals(expectedResult,check);         
    }
    @Test
    public void testProcessLM6()
    {
        String line="%LMXY*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processMOLPLM(line, "LM");
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("LM", "XY");
        assertEquals(expectedResult,check);         
    }
    @Test
    public void testProcessLM7()
    {
        String line="%LMn*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processMOLPLM(line, "LM");
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("LM", "n");
        assertEquals(expectedResult,check);         
    }
    
    /*************
     * Test of LR attribute of processLRLS() method , of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessLR1()
    {
        String line="";
        HashMap<String,String> check=GerberFileProcessingUtil.processLRLS(line, "LR");
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessLR2()
    {
        String line=null;
        HashMap<String,String> check=GerberFileProcessingUtil.processLRLS(line, "LR");
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessLR3()
    {
        String line="%LR90.0*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processLRLS(line, "LR");
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("LR", "90.0");
        assertEquals(expectedResult,check);         
    }
    @Test
    public void testProcessLR4()
    {
        String line="%LR-90*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processLRLS(line, "LR");
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("LR", "-90");
        assertEquals(expectedResult,check);         
    }
    @Test
    public void testProcessLR5()
    {
        String line="%LR0.0*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processLRLS(line, "LR");
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("LR", "0.0");
        assertEquals(expectedResult,check);         
    }
    
    /*************
     * Test of LS attribute of processLRLS() method , of class GerberFileProcessingUtil.
     */
    @Test
    public void testProcessLS1()
    {
        String line="";
        HashMap<String,String> check=GerberFileProcessingUtil.processLRLS(line, "LS");        
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessLS2()
    {
        String line=null;
        HashMap<String,String> check=GerberFileProcessingUtil.processLRLS(line, "LS");
        assertEquals(null, check.get(null));            
    }
    @Test
    public void testProcessLS3()
    {
        String line="%LS1.5*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processLRLS(line, "LS");
        assertEquals("1.5", check.get("LS"));            
    }
    @Test
    public void testProcessLS4()
    {
        String line="%LS0.8*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processLRLS(line, "LS");
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("LS", "0.8");
        assertEquals(expectedResult,check);         
    }
    @Test
    public void testProcessLS5()
    {
        String line="%LS-90*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processLRLS(line, "LS");
        Map<String, String> expectedResult = new HashMap<>();        
        expectedResult.put("LS", "-90");
        assertEquals(expectedResult,check);         
    }
    //If user wrongly put LR instead of LS and to process LS
    @Test
    public void testProcessLS6()
    {
        String line="%LS90*%";
        HashMap<String,String> check=GerberFileProcessingUtil.processLRLS(line, "LR");
        assertEquals(null, check.get("LR"));                  
    }
}
