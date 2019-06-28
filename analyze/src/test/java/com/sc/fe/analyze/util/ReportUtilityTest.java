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
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
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
        Project project = new Project();
        project.setCompany("Apple");
        ProjectDetails expResult = new ProjectDetails();
        expResult.setCompany("Apple");
        ProjectDetails result = ReportUtility.convertToObject(project);
        assertEquals(expResult, result);
//        This method have to be reviewed because it is not giving success.
    }

    /**
     * Test of convertToObject method, of class ReportUtility.
     */
    @Test
    public void testConvertToObject_ProjectFiles() {
        System.out.println("convertToObject");
        ProjectFiles projectFiles = new ProjectFiles();
        projectFiles.setFormat("gerber");
        FileDetails expResult = new FileDetails();
        expResult.setFormat("gerber");
        FileDetails result = ReportUtility.convertToObject(projectFiles);
        assertEquals(expResult, result);

    }

    /**
     * Test of convertToObject method, of class ReportUtility.
     */
    @Test
    public void testConvertToObject_FiletypeExtensions() {
        System.out.println("convertToObject");
        FiletypeExtensions filetypeExtn = new FiletypeExtensions();
        filetypeExtn.getKey().setId(UUID.fromString("50554d6e-29bb-11e5-b345-feff819cdc9f"));
        Set<String> extn = new HashSet<>();
        extn.add("xls");
        extn.add("pdf");
        filetypeExtn.setExtensions(extn);
        filetypeExtn.setFileType("drill");
        FileTypeExtensions expResult = new FileTypeExtensions();
        expResult.setFile_type("drill");
        expResult.setExtensions("pdf,xls");
        expResult.setId("50554d6e-29bb-11e5-b345-feff819cdc9f");
        FileTypeExtensions result = ReportUtility.convertToObject(filetypeExtn);
        assertEquals(expResult, result);
        /*        to be reviewed for output comparison.
        .datastax.driver.core.utils.UUIDs - PID obtained through native call to getpid(): 
         */
    }

    /**
     * Test of convertToDBObject method, of class ReportUtility.
     */
    @Test
    public void testConvertToDBObject_FileTypeExtensions() {
        System.out.println("convertToDBObject");
        FileTypeExtensions fe = new FileTypeExtensions();
        FiletypeExtensions expResult = new FiletypeExtensions();
        FiletypeExtensions result = ReportUtility.convertToDBObject(fe);
        assertEquals(expResult, result);
        /*
            .datastax.driver.core.utils.UUIDs - PID obtained through native call to getpid(): 
         */
    }

    /**
     * Test of convertToDBObject method, of class ReportUtility.
     */
    @Test
    public void testConvertToDBObject_ProjectDetails() {
        System.out.println("convertToDBObject");
        ProjectDetails projectDetails = new ProjectDetails();
        Project expResult = new Project();
        Project result = ReportUtility.convertToDBObject(projectDetails);
        assertEquals(expResult, result);

    }

    /**
     * Test of convertToDBObject method, of class ReportUtility.
     */
    @Test
    public void testConvertToDBObject_FileDetails() {
        System.out.println("convertToDBObject");
        FileDetails fileDetails = new FileDetails();
        ProjectFiles expResult = new ProjectFiles();
        ProjectFiles result = ReportUtility.convertToDBObject(fileDetails);
        assertEquals(expResult, result);

    }

}
