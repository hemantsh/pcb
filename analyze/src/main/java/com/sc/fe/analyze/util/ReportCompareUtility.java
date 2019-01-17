package com.sc.fe.analyze.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sc.fe.analyze.to.AdvancedReport;
import com.sc.fe.analyze.to.CustomerInputs;
import com.sc.fe.analyze.to.FileDetails;

public class ReportCompareUtility {

    private static final String DELIMITER = "~";

    public static Map<String, String> compare(AdvancedReport newReport, AdvancedReport oldReport) throws IllegalArgumentException, IllegalAccessException {
        Map<String, String> differences = new HashMap<String, String>();
        if (newReport == null || oldReport == null) {
            return null;
        }
        if (!(newReport.getOdbMatrix() == null || oldReport.getOdbMatrix() == null)) {
            if (!(newReport.getOdbMatrix().equalsIgnoreCase(oldReport.getOdbMatrix()))) {
                differences.put("odbMatrix", newReport.getOdbMatrix() + DELIMITER + oldReport.getOdbMatrix());
            }
        }

        if (!(newReport.getCustomerInputs() == null || oldReport.getCustomerInputs() == null)) {
            CustomerInputs newCI = newReport.getCustomerInputs();
            CustomerInputs oldCI = oldReport.getCustomerInputs();
            differences.putAll(FileDetailCompareUtility.compareObject(newCI, oldCI));
        }

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
