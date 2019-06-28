/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.FileTypeExtensions;
import com.sc.fe.analyze.to.ProjectDetails;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
public class CompareUtilityTest {

    public CompareUtilityTest() {
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
     * Test of fullCompare method, of class CompareUtility.
     */
    @Test
    public void testFullCompare() {
        System.out.println("fullCompare");
        ProjectDetails newRecord = new ProjectDetails();
        newRecord.setCustomerId("abc1");
        ProjectDetails oldRecord = new ProjectDetails();
        oldRecord.setCustomerId("bcd2");
        Map<String, String> expResult = new HashMap<String, String>();
        expResult.put("customerId", "abc1~bcd2");
        Map<String, String> result = CompareUtility.fullCompare(newRecord, oldRecord);
        assertEquals(expResult, result);
        assertEquals(1, result.size());
        assertEquals(result.containsKey("customerId"), true);

    }

    /**
     * Test of compareObject method, of class CompareUtility.
     */
    @Test
    public void testCompareObject() throws Exception {
        System.out.println("compareObject");
        FileTypeExtensions newFD = new FileTypeExtensions();
        newFD.setFile_type("newFile");
        FileTypeExtensions oldFD = new FileTypeExtensions();
        oldFD.setFile_type("oldFile");
        Map<String, String> expResult = new HashMap<String, String>();
        expResult.put("file_type", "newFile~oldFile");
        Map<String, String> result = CompareUtility.compareObject(newFD, oldFD);
        assertEquals(result.containsKey("file_type"), true);
        assertEquals(result.size(), 1);
        assertEquals(result.get("file_type"), expResult.get("file_type"));

    }

    /**
     * Test of compareFileDetails method, of class CompareUtility.
     */
    @Test
    public void testCompareFileDetails() {
        System.out.println("compareFileDetails");
        ProjectDetails newProject = new ProjectDetails();
        newProject.setCompany("Google");
        ProjectDetails oldProject = new ProjectDetails();
        oldProject.setCompany("Apple");
        Map<String, String> expResult = new HashMap<String, String>(0);
//        expResult.put("company", "Google");
        Map<String, String> result = CompareUtility.compareFileDetails(newProject, oldProject);
        assertEquals(expResult, result);
        assertEquals(expResult.size(), 0);
        assertEquals(result.containsKey("company"), false);

    }

    /**
     * Test of compare method, of class CompareUtility.
     */
    @Test
    public void testCompare() throws Exception {
        System.out.println("compare");
        FileDetails newFD = new FileDetails();
        newFD.setFormat("gerber");
        FileDetails oldFD = new FileDetails();
        oldFD.setFormat("gerber");
        Map<String, String> expResult = new HashMap<String, String>();
        Map<String, String> result = CompareUtility.compare(newFD, oldFD);
        assertEquals(expResult, result);
        assertEquals(0, result.size());

    }

    /**
     * Test of compareMaps method, of class CompareUtility.
     */
    @Test
    public void testCompareMaps() {
        System.out.println("compareMaps");
        Map<String, String> newMap = new HashMap<>();
        newMap.put("Cars", "BMW");
        Map<String, String> oldMap = new HashMap<>();
        oldMap.put("Cars", "Porsche");
        Map<String, String> expResult = new HashMap<String, String>(0);
        expResult.put("Cars", "BMW~Porsche");
        Map<String, String> result = CompareUtility.compareMaps(newMap, oldMap);
        assertEquals(expResult, result);
        assertEquals(result.containsKey("Cars"), true);
        assertEquals(1, result.size());

    }

    /**
     * Test of compareObjectMaps method, of class CompareUtility.
     */
    @Test
    public void testCompareObjectMaps() {
        System.out.println("compareObjectMaps");
        Map newMap = new HashMap<>();
        newMap.put("Key1", "newMap");
        Map oldMap = new HashMap<>();
        oldMap.put("Key1", "oldMap");
        Map<String, String> expResult = new HashMap<String, String>();
        expResult.put("Key1", "newMap~oldMap");
        Map<String, String> result = CompareUtility.compareObjectMaps(newMap, oldMap);
        assertEquals(result.containsKey("Key1"), true);
        assertEquals(expResult, result);
        assertEquals(1, result.size());

    }

    /**
     * Test of formatedError method, of class CompareUtility.
     */
    @Test
    public void testFormatedError() {
        System.out.println("formatedError");
        Map<String, String> errors = new HashMap<String, String>();
        errors.put("Error1", "TestError");
        Set<String> expResult = new HashSet<String>();
        expResult.add("Error1 TestError");
        Set<String> result = CompareUtility.formatedError(errors);
        assertEquals(expResult, result);
        assertEquals(expResult.size(), result.size());

    }

    /**
     * Test of findMissingItems method, of class CompareUtility.
     */
    @Test
    public void testFindMissingItems() {
        System.out.println("findMissingItems");
        List<String> reqiredTypes = new ArrayList<String>();
        reqiredTypes.add("type1");
        List<String> availTypes = new ArrayList<String>();
        availTypes.add("type1");
        List<String> expResult = new ArrayList<String>();
        List<String> result = CompareUtility.findMissingItems(reqiredTypes, availTypes);
        assertEquals(expResult, result);
        assertEquals(expResult.size(), result.size());
    }

}
