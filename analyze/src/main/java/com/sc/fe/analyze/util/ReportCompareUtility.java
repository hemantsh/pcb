package com.sc.fe.analyze.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sc.fe.analyze.to.AdvancedReport;
import com.sc.fe.analyze.to.CustomerInputs;
import com.sc.fe.analyze.to.FileDetails;

public class ReportCompareUtility {
	
	private static final String DELIMITER = "~";

	public static Map<String, String> compare(AdvancedReport newReport , AdvancedReport oldReport) {
		Map<String, String> differences = new HashMap<String, String>();
		if(newReport == null || oldReport == null)
                {
			return null;
		}
//		if( ! newReport.getOdbMatrix().equalsIgnoreCase( oldReport.getOdbMatrix() )) {
//			differences.put("ODB Matrix", "Matrix is changed");
//		}
              if(!(newReport.getCustomerInputs()==null))
              {
                CustomerInputs newCI = newReport.getCustomerInputs();
                CustomerInputs oldCI = oldReport.getCustomerInputs();
		if( ! newCI.getServiceType().equalsIgnoreCase(oldCI.getServiceType())) {
			differences.put("Service Type", newCI.getServiceType() + DELIMITER + oldCI.getServiceType() );
		}
		if( ! newCI.getZipFileName().equalsIgnoreCase(oldCI.getZipFileName())) {
			differences.put("Zip file name", newCI.getZipFileName() + DELIMITER + oldCI.getZipFileName() );
		}
//		if( ! newCI.getZipFileSize().equalsIgnoreCase(oldCI.getZipFileSize())) {
//			differences.put("Zip file size", newCI.getZipFileSize() + DELIMITER + oldCI.getZipFileSize() );
//		}
		if( ! newCI.getEmailAddress().equalsIgnoreCase(oldCI.getEmailAddress())) {
			differences.put("Email ID", newCI.getEmailAddress() + DELIMITER + oldCI.getEmailAddress() );
		}
              }
                if(!(newReport.getAllFileNames()==null))
                {               
                    Set<String>  fileNameSet = newReport.getAllFileNames();
                    System.out.println("fileNameSet----"+fileNameSet);
                    fileNameSet.addAll( oldReport.getAllFileNames() );
		
                    fileNameSet.stream().forEach( fileName -> {
			
			FileDetails newFD = newReport.getFileDetails(fileName);
                        FileDetails oldFD = oldReport.getFileDetails(fileName);
			
			if( newFD == null && oldFD != null) {
				differences.put("File Removed", fileName);
			}
			if( newFD != null && oldFD == null) {
				differences.put("File Added", fileName);
			}
			
			differences.putAll ( FileDetailCompareUtility.compare( newFD, oldFD) );
		});
             }
            return differences;    
	}
}
