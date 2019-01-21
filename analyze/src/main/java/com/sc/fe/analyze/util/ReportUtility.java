package com.sc.fe.analyze.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.sc.fe.analyze.data.entity.Report;
import com.sc.fe.analyze.to.AdvancedReport;
import com.sc.fe.analyze.to.CustomerInputs;

/**
 *
 * @author Hemant
 */
public class ReportUtility {

	private ReportUtility() {};
	
    /**
     * This method is convert the report into object.
     * @param dbData - report of any object
     * @return the report
     */
    public static com.sc.fe.analyze.to.Report convertToObject(Report dbData) {
		
		com.sc.fe.analyze.to.Report report = new com.sc.fe.analyze.to.Report();
		report.setAdditionalNotes(dbData.getNotes());
		report.setErrors(dbData.getErrors());
		report.setExctractedFileNames(dbData.getProjectFiles());
		
		Map<String, Set<String>> filePurposeToNameMapping = new HashMap<String, Set<String>>();
		
		Map<Integer, String> dbMap = dbData.getFileTypeToFileNameMapping();
		Iterator<Integer> keyIter = dbMap.keySet().iterator();
		
		while( keyIter.hasNext()) {
			Integer iKey = keyIter.next();
			String key = iKey.toString();
			String value = dbMap.get(iKey);
			if( filePurposeToNameMapping.containsKey(key) ) {
				filePurposeToNameMapping.get(key).add(value);
			}else {
				Set<String> set = new HashSet<String>();
				set.add(value);
				filePurposeToNameMapping.put(key, set);
			}
		}
		
		report.setFilePurposeToNameMapping(filePurposeToNameMapping);
		report.setSummary(dbData.getSummary());
		report.setValidationStatus(dbData.getStatus());
		
		CustomerInputs customerInputs = new CustomerInputs();
		customerInputs.setProjectId(dbData.getProjectId());
		customerInputs.setCustomerId(dbData.getCustomerId());
		customerInputs.setServiceType(MappingUtil.getServiceName(dbData.getServiceTypeId()) );
		
		report.setCustomerInputs(customerInputs );
		
		return report;
	}
	
    /**
     * This method is convert the AdvancedReport object into database object.
     * @param report - object of AdvancedReport
     * @return the report
     */
    public static Report convertToDBObject(AdvancedReport report) {
		
		Report dbReport = new Report();
		
		CustomerInputs customerInputs = report.getCustomerInputs();
		dbReport.setCustomerId( customerInputs.getCustomerId() );
		dbReport.setProjectId( customerInputs.getProjectId() );
		dbReport.setServiceTypeId(MappingUtil.getServiceId(customerInputs.getServiceType()));
		
		dbReport.setErrors(report.getErrors());
		
		Map<Integer, String> dbMap = new HashMap<Integer, String> ();
		Map<String, Set<String>> sourceMap = report.getFilePurposeToNameMapping();
		
		Iterator<String> keyIter = sourceMap.keySet().iterator();
		
		while( keyIter.hasNext()) {
			String key = keyIter.next();
			Set<String> values = sourceMap.get(key);
			values.stream().forEach( e -> { ;
				dbMap.put(MappingUtil.getFileTypeId(key), e );
			});
		}
		
		dbReport.setFileTypeToFileNameMapping(dbMap);
		
		dbReport.setNotes(report.getAdditionalNotes());
		dbReport.setProjectFiles(report.getExctractedFileNames());
		dbReport.setStatus(report.getValidationStatus());
		dbReport.setSummary(report.getSummary());
		dbReport.setVersion(1);
		
		return dbReport;
	}
	

}
