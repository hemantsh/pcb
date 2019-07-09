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
     * Test of fullCompare method with oldRecord value is null, of class
     * CompareUtility.
     */
    @Test
    public void testFullCompareWithBothNull() {
        System.out.println("Both Null");

        ProjectDetails newRecord = null;

        ProjectDetails oldRecord = null;

        Map<String, String> result = CompareUtility.fullCompare(newRecord, oldRecord);
        assertEquals(result.size(), 0);

    }

    /**
     * Test of fullCompare method with oldRecord value is null, of class
     * CompareUtility.
     */
    @Test
    public void testFullCompareWithOldRecordNull() {
        System.out.println("oldRecord Null");

        ProjectDetails newRecord = new ProjectDetails();
        newRecord.setCustomerId("bcd2");
        ProjectDetails oldRecord = null;

        Map<String, String> result = CompareUtility.fullCompare(newRecord, oldRecord);
        assertEquals(result.size(), 0);

    }

    /**
     * Test of fullCompare method, of class CompareUtility.
     */
    @Test
    public void testFullCompareWithNewRecordNull() {
        System.out.println("newRecord Null");

        ProjectDetails newRecord = new ProjectDetails();

        ProjectDetails oldRecord = new ProjectDetails();
        oldRecord.setCustomerId("bcd2");

        Map<String, String> result = CompareUtility.fullCompare(newRecord, oldRecord);

        assertEquals(1, result.size());
        assertEquals(result.get("customerId"), "REMOVED~bcd2");
        assertEquals(result.containsKey("customerId"), true);

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

        Map<String, String> result = CompareUtility.fullCompare(newRecord, oldRecord);

        assertEquals(1, result.size());
        assertEquals(result.get("customerId"), "abc1~bcd2");
        assertEquals(result.containsValue("abc1~bcd2"), true);
    }

    /**
     * Test of compareObject method with both null value, of class
     * CompareUtility.
     */
    @Test
    public void testCompareObjectWithBothNull() throws Exception {
        System.out.println("compareObject With Both Null");
        FileTypeExtensions newFD = null;

        FileTypeExtensions oldFD = null;

        Map<String, String> result = CompareUtility.compareObject(newFD, oldFD);

        assertEquals(result.size(), 0);

    }

    /**
     * Test of compareObject method with NewFD null value, of class
     * CompareUtility.
     */
    @Test
    public void testCompareObjectWithNewFDNull() throws Exception {
        System.out.println("compareObject with NewFD Null");
        FileTypeExtensions newFD = null;

        FileTypeExtensions oldFD = new FileTypeExtensions();
        oldFD.setFile_type("drill");
        Map<String, String> result = CompareUtility.compareObject(newFD, oldFD);

        assertEquals(result.size(), 0);

    }

    /**
     * Test of compareObject method with OldFD null value, of class
     * CompareUtility.
     */
    @Test
    public void testCompareObjectWithOldFDNull() throws Exception {
        System.out.println("compareObject with OldFD Null");
        FileTypeExtensions newFD = new FileTypeExtensions();

        FileTypeExtensions oldFD = null;
        Map<String, String> result = CompareUtility.compareObject(newFD, oldFD);

        assertEquals(result.size(), 0);

    }

    /**
     * Test of compareObject method, of class CompareUtility.
     */
    @Test
    public void testCompareObject() throws Exception {
        System.out.println("compareObject");

        FileTypeExtensions newFD = new FileTypeExtensions();
        newFD.setExtensions("pdf,xls");
        newFD.setFile_type("newFile");
        newFD.setId("50554d6e-29bb-11e5-b345-feff819cdc9f");

        FileTypeExtensions oldFD = new FileTypeExtensions();
        oldFD.setExtensions("pdf,gko");
        oldFD.setFile_type("oldFile");
        oldFD.setId("50554d6e-29bb-11e5-b345-feff819cdc9f");

        Map<String, String> result = CompareUtility.compareObject(newFD, oldFD);
        assertEquals(result.containsKey("file_type"), true);

        assertEquals(result.size(), 2);
        assertEquals(result.get("file_type"), "newFile~oldFile");
        assertEquals(result.get("extensions"), "pdf,xls~pdf,gko");

    }

    /**
     * Test of compareFileDetails method with null objects, of class
     * CompareUtility.
     */
    @Test
    public void testCompareFileDetailsWithNullObjects() {
        System.out.println("compareFileDetails with null objects");
        ProjectDetails newProject = new ProjectDetails();

        ProjectDetails oldProject = new ProjectDetails();

        Map<String, String> result = CompareUtility.compareFileDetails(newProject, oldProject);
        assertEquals(result.size(), 0);
        assertNotEquals(result, null);
    }

    /**
     * Test of compareFileDetails method, of class CompareUtility.
     */
    @Test
    public void testCompareFileDetails() {
        System.out.println("compareFileDetails");
        ProjectDetails newProject = new ProjectDetails();
        newProject.setProjectId("newProject");
        newProject.setCompany("Biz4Group");

        FileDetails file = new FileDetails();
        file.setFormat("gerber");
        file.setName("mantisFile.pdf");

        List<FileDetails> fileList = new ArrayList<>();
        fileList.add(file);

        newProject.setFileDetails(fileList);

        ProjectDetails oldProject = new ProjectDetails();
        oldProject.setProjectId("oldProject");
        oldProject.setCompany("Google");

        Map<String, String> result = CompareUtility.compareFileDetails(newProject, oldProject);
        assertEquals(result.size(), 1);
        assertEquals(result.get("MANTISFILE.PDF"), "File got Added.");

    }

    /**
     * Test of compare method, of class CompareUtility.
     */
    @Test
    public void testCompare() throws Exception {
        System.out.println("compare");

        FileDetails newFD = new FileDetails();
        newFD.setFormat("gerber");
        newFD.setName("MantisFile.xls");

        FileDetails oldFD = new FileDetails();
        oldFD.setFormat("odb");
        oldFD.setName("MantisFile.xls");

        Map<String, String> result = CompareUtility.compare(newFD, oldFD);
        assertEquals(result.get("MANTISFILE.XLS.format"), "gerber~odb");
        assertEquals(1, result.size());
        assertNotEquals(result, null);

    }

    /**
     * Test of compareMaps method, of class CompareUtility.
     */
    @Test
    public void testCompareMaps() {
        System.out.println("compareMaps");

        Map<String, String> newMap = new HashMap<>();
        newMap.put("Company", "Biz4Group");

        Map<String, String> oldMap = new HashMap<>();
        oldMap.put("Company", "Google");

        Map<String, String> result = CompareUtility.compareMaps(newMap, oldMap);

        assertEquals(result.containsKey("Company"), true);
        assertEquals(1, result.size());

    }

    /**
     * Test of compareObjectMaps method, of class CompareUtility.
     */
    @Test
    public void testCompareObjectMaps() {
        System.out.println("compareObjectMaps");
        Map newMap = new HashMap<>();
        newMap.put("format", "gerber");

        Map oldMap = new HashMap<>();
        oldMap.put("format", "odb");

        Map<String, String> result = CompareUtility.compareObjectMaps(newMap, oldMap);

        assertEquals(result.containsKey("format"), true);
        assertEquals(1, result.size());

    }

    

    /**
     * Test of findMissingItems method, of class CompareUtility.
     */
    @Test
    public void testFindMissingItems() {
        System.out.println("findMissingItems");

        List<String> reqiredTypes = new ArrayList<String>();
        reqiredTypes.add("requiredType");

        List<String> availTypes = new ArrayList<String>();
        availTypes.add("availType");

        List<String> result = CompareUtility.findMissingItems(reqiredTypes, availTypes);
        assertEquals(result, null);
//        Conditon To be improved.
    }

}
