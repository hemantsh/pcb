/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import com.sc.fe.analyze.data.entity.FiletypeExtensions;
import com.sc.fe.analyze.data.entity.Project;
import com.sc.fe.analyze.data.entity.ProjectFiles;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.FileTypeExtensions;
import com.sc.fe.analyze.to.ProjectDetails;
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
public class ReportUtilityTest {
    
    public ReportUtilityTest() {
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
     * Test of convertToObject method, of class ReportUtility.
     */
    @Test
    public void testConvertToObject_Project() {
        System.out.println("convertToObject");
        Project project = null;
        ProjectDetails expResult = null;
        ProjectDetails result = ReportUtility.convertToObject(project);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertToObject method, of class ReportUtility.
     */
    @Test
    public void testConvertToObject_ProjectFiles() {
        System.out.println("convertToObject");
        ProjectFiles projectFiles = null;
        FileDetails expResult = null;
        FileDetails result = ReportUtility.convertToObject(projectFiles);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertToObject method, of class ReportUtility.
     */
    @Test
    public void testConvertToObject_FiletypeExtensions() {
        System.out.println("convertToObject");
        FiletypeExtensions filetypeExtn = null;
        FileTypeExtensions expResult = null;
        FileTypeExtensions result = ReportUtility.convertToObject(filetypeExtn);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertToDBObject method, of class ReportUtility.
     */
    @Test
    public void testConvertToDBObject_FileTypeExtensions() {
        System.out.println("convertToDBObject");
        FileTypeExtensions fe = null;
        FiletypeExtensions expResult = null;
        FiletypeExtensions result = ReportUtility.convertToDBObject(fe);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertToDBObject method, of class ReportUtility.
     */
    @Test
    public void testConvertToDBObject_ProjectDetails() {
        System.out.println("convertToDBObject");
        ProjectDetails projectDetails = null;
        Project expResult = null;
        Project result = ReportUtility.convertToDBObject(projectDetails);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertToDBObject method, of class ReportUtility.
     */
    @Test
    public void testConvertToDBObject_FileDetails() {
        System.out.println("convertToDBObject");
        FileDetails fileDetails = null;
        ProjectFiles expResult = null;
        ProjectFiles result = ReportUtility.convertToDBObject(fileDetails);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
