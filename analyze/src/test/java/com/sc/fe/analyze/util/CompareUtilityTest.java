/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.ProjectDetails;
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
        Map<String, String> expResult = null;
        Map<String, String> result = CompareUtility.fullCompare(newRecord, oldRecord);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareObject method, of class CompareUtility.
     */
    @Test
    public void testCompareObject() throws Exception {
        System.out.println("compareObject");
        Object newFD = null;
        Object oldFD = null;
        Map<String, String> expResult = null;
        Map<String, String> result = CompareUtility.compareObject(newFD, oldFD);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of compareFileDetails method, of class CompareUtility.
     */
    @Test
    public void testCompareFileDetails() {
        System.out.println("compareFileDetails");
        ProjectDetails newProject = null;
        ProjectDetails oldProject = null;
        Map<String, String> expResult = null;
        Map<String, String> result = CompareUtility.compareFileDetails(newProject, oldProject);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of compare method, of class CompareUtility.
     */
    @Test
    public void testCompare() throws Exception {
        System.out.println("compare");
        FileDetails newFD = null;
        FileDetails oldFD = null;
        Map<String, String> expResult = null;
        Map<String, String> result = CompareUtility.compare(newFD, oldFD);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
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
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of compareObjectMaps method, of class CompareUtility.
     */
    @Test
    public void testCompareObjectMaps() {
        System.out.println("compareObjectMaps");
        Map newMap = null;
        Map oldMap = null;
        Map<String, String> expResult = null;
        Map<String, String> result = CompareUtility.compareObjectMaps(newMap, oldMap);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of formatedError method, of class CompareUtility.
     */
    @Test
    public void testFormatedError() {
        System.out.println("formatedError");
        Map<String, String> errors = null;
        Set<String> expResult = null;
        Set<String> result = CompareUtility.formatedError(errors);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of findMissingItems method, of class CompareUtility.
     */
    @Test
    public void testFindMissingItems() {
        System.out.println("findMissingItems");
        List<String> reqiredTypes = null;
        List<String> availTypes = null;
        List<String> expResult = null;
        List<String> result = CompareUtility.findMissingItems(reqiredTypes, availTypes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
