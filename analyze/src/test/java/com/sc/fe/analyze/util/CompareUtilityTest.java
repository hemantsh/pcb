/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import com.sc.fe.analyze.to.FileDetails;
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
        ProjectDetails newRecord = null;
        ProjectDetails oldRecord = null;
        Map<String, String> expResult = new HashMap<String, String>();
        Map<String, String> result = CompareUtility.fullCompare(newRecord, oldRecord);
        assertEquals(expResult, result);
        assertEquals(0, result.size());

    }

    /**
     * Test of compareObject method, of class CompareUtility.
     */
    @Test
    public void testCompareObject() throws Exception {
        System.out.println("compareObject");
        Object newFD = null;
        Object oldFD = null;
        Map<String, String> expResult = new HashMap<String, String>();
        Map<String, String> result = CompareUtility.compareObject(newFD, oldFD);
        assertEquals(expResult, result);
        assertEquals(expResult.size(), 0);

    }

    /**
     * Test of compareFileDetails method, of class CompareUtility.
     */
    @Test
    public void testCompareFileDetails() {
        System.out.println("compareFileDetails");
        ProjectDetails newProject = new ProjectDetails();
        ProjectDetails oldProject = new ProjectDetails();
        Map<String, String> expResult = new HashMap<String, String>(0);
        Map<String, String> result = CompareUtility.compareFileDetails(newProject, oldProject);
        assertEquals(expResult, result);
        assertEquals(expResult.size(), 0);

    }

    /**
     * Test of compare method, of class CompareUtility.
     */
    @Test
    public void testCompare() throws Exception {
        System.out.println("compare");
        FileDetails newFD = new FileDetails();
        FileDetails oldFD = new FileDetails();
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
        Map<String, String> newMap = null;
        Map<String, String> oldMap = null;
        Map<String, String> expResult = new HashMap<String, String>(0);
        Map<String, String> result = CompareUtility.compareMaps(newMap, oldMap);
        assertEquals(expResult, result);
        assertEquals(0, result.size());

    }

    /**
     * Test of compareObjectMaps method, of class CompareUtility.
     */
    @Test
    public void testCompareObjectMaps() {
        System.out.println("compareObjectMaps");
        Map newMap = null;
        Map oldMap = null;
        Map<String, String> expResult = new HashMap<String, String>();
        Map<String, String> result = CompareUtility.compareObjectMaps(newMap, oldMap);
        assertEquals(expResult, result);
        assertEquals(0, result.size());

    }

    /**
     * Test of formatedError method, of class CompareUtility.
     */
    @Test
    public void testFormatedError() {
        System.out.println("formatedError");
        Map<String, String> errors = new HashMap<String, String>();
        Set<String> expResult = new HashSet<String>();
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
        List<String> availTypes = new ArrayList<String>();
        List<String> expResult = new ArrayList<String>();
        List<String> result = CompareUtility.findMissingItems(reqiredTypes, availTypes);
        assertEquals(expResult, result);
        assertEquals(expResult.size(), result.size());
    }

}
