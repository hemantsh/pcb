package com.sc.fe.analyze.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sc.fe.analyze.to.AdvancedReport;
import com.sc.fe.analyze.to.CustomerInformation;
import com.sc.fe.analyze.to.FileDetails;

/**
 *
 * @author Hemant
 */
public class ReportCompareUtility {

    private static final String DELIMITER = "~";

    /**
     * This method is compare the new Object from the old Object of AdvancedReport Class.
     * @param newReport- the new object details to set
     * @param oldReport- the old object details to set
     * @return the differences after comparing the new object from the old object 
    */
    public static Map<String, String> compare(AdvancedReport newReport, AdvancedReport oldReport) throws IllegalArgumentException, IllegalAccessException {
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
            Set<String> fileNameSet = newReport.getAllFileNames();
            fileNameSet.addAll(oldReport.getAllFileNames());
            fileNameSet.stream().forEach(fileName -> {
                FileDetails newFD = newReport.getFileDetails(fileName);
                FileDetails oldFD = oldReport.getFileDetails(fileName);

                if (newFD == null && oldFD != null) {
                    differences.put("File Removed", fileName);
                }
                if (newFD != null && oldFD == null) {
                    differences.put("File Added", fileName);
                }

                differences.putAll(FileDetailCompareUtility.compare(newFD, oldFD));
            });
        }
        return differences;
    }
}
