package com.sc.fe.analyze.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sc.fe.analyze.to.AdvancedReport;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.ProjectDetails;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hemant
 */
public class ReportCompareUtility {

    private static final String DELIMITER = "~";

    /**
     * This method is compare the new Object from the old Object of
     * AdvancedReport Class.
     *
     * @param newReport- the new object details to set
     * @param oldReport- the old object details to set
     * @return the differences after comparing the new object from the old
     * object
     */
    public static Map<String, String> compare(AdvancedReport newReport, AdvancedReport oldReport) throws IllegalAccessException {
        Map<String, String> differences = new HashMap<String, String>();
        if (newReport == null || oldReport == null) {
            return null;
        }
//        if (!(newReport.getOdbMatrix() == null || oldReport.getOdbMatrix() == null)) {
//            if (!(newReport.getOdbMatrix().equalsIgnoreCase(oldReport.getOdbMatrix()))) {
//                differences.put("odbMatrix", newReport.getOdbMatrix() + DELIMITER + oldReport.getOdbMatrix());
//            }
//        }
//
//        if (!(newReport.getCustomerInputs() == null || oldReport.getCustomerInputs() == null)) {
//            CustomerInformation newCI = newReport.getCustomerInputs();
//            CustomerInformation oldCI = oldReport.getCustomerInputs();
//            differences.putAll(FileDetailCompareUtility.compareObject(newCI, oldCI));
//        }

        if (!(newReport.getAllFileNames() == null || oldReport.getAllFileNames() == null)) {
            Set<String> newFileNameSet = newReport.getAllFileNames();
            Set<String> oldFileNameSet = oldReport.getAllFileNames();

            //Reading All fileNames from new Report object
            newFileNameSet.stream().forEach(newFileName -> {
                //Reading All fileNames from old Report object
                oldFileNameSet.stream().forEach(oldFileName -> {

                    FileDetails newFD = newReport.getFileDetails(newFileName);
                    FileDetails oldFD = oldReport.getFileDetails(oldFileName);

                    try {
                        differences.putAll(FileDetailCompareUtility.compareObject(newFD, oldFD));
                        //To check that FileDetailobject initialize with attributes or not,if YES ,then process                        
                        if (!(newFD.getAttributes() == null && oldFD.getAttributes() == null)) {
                            differences.putAll(FileDetailCompareUtility.compare(newFD, oldFD));
                        }
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                });
            });
        }
        return differences;
    }

    //compare every attribute of ProjectDetails and FileDetails object
    public static Map<String, String> compare(ProjectDetails newRecord, ProjectDetails oldRecord) {
        Map<String, String> regularDifferences = new HashMap<String, String>();
        Map<String, String> validationDifferences = new HashMap<String, String>();
        if (oldRecord == null || newRecord == null) {
            return null;
        }
        try {
            regularDifferences.putAll(FileDetailCompareUtility.compareObject(newRecord, oldRecord));
            //Comparing the Validation errors with previous one  
            validationDifferences.putAll(FileDetailCompareUtility.compareObject(newRecord.getErrors(), oldRecord.getErrors()));
            validationDifferences.put("Errors", validationDifferences.remove("tail"));

            newRecord.getFileDetails().stream().forEach(nnewFD -> {
                oldRecord.getFileDetails().stream().forEach(ooldFD -> {
                    try {
                        regularDifferences.putAll(FileDetailCompareUtility.compareObject(nnewFD, ooldFD));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
            });

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return regularDifferences;
    }
}
