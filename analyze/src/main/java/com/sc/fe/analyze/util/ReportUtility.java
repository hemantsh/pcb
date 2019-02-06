package com.sc.fe.analyze.util;

//import com.sc.fe.analyze.to.ProjectFilesDetails;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.sc.fe.analyze.data.entity.Project;
import com.sc.fe.analyze.data.entity.ProjectFiles;
import com.sc.fe.analyze.data.entity.Report;
import com.sc.fe.analyze.to.CustomerInformation;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.ProjectDetails;

/**
 *
 * @author Hemant
 */
public class ReportUtility {

    private ReportUtility() {
    }

    ;
	
    /**
     * This method is convert the report into object.
     * @param dbData - report of any object
     * @return the report
     */
    public static com.sc.fe.analyze.to.Report convertToObject(Report dbData) {

        com.sc.fe.analyze.to.Report report = new com.sc.fe.analyze.to.Report();

        report.setErrors(dbData.getErrors());

        Map<String, Set<String>> filePurposeToNameMapping = new HashMap<String, Set<String>>();

        Map<Integer, String> dbMap = dbData.getFileTypeToFileNameMapping();
        Iterator<Integer> keyIter = dbMap.keySet().iterator();

        while (keyIter.hasNext()) {
            Integer iKey = keyIter.next();
            String key = iKey.toString();
            String value = dbMap.get(iKey);
            if (filePurposeToNameMapping.containsKey(key)) {
                filePurposeToNameMapping.get(key).add(value);
            } else {
                Set<String> set = new HashSet<String>();
                set.add(value);
                filePurposeToNameMapping.put(key, set);
            }
        }

        report.setSummary(dbData.getSummary());
        report.setValidationStatus(dbData.getStatus());

        CustomerInformation customerInputs = new CustomerInformation();
        customerInputs.setProjectId(dbData.getProjectId());
        customerInputs.setCustomerId(dbData.getCustomerId());
        //customerInputs.setServiceType(MappingUtil.getServiceName(dbData.getServiceTypeId()) );

        //report.setCustomerInputs(customerInputs );
        return report;
    }

    /**
     * This method is convert the AdvancedReport object into database object.
     *
     * @param report - object of AdvancedReport
     * @return the report
     */
    public static Report convertToDBObject(com.sc.fe.analyze.to.Report report) {

        Report dbReport = new Report();

//		CustomerInformation customerInputs = report.getProjectDetail().getCustomerInformation();
//		dbReport.setCustomerId( customerInputs.getCustomerId() );
//		dbReport.setProjectId( customerInputs.getProjectId() );
        //dbReport.setServiceTypeId(MappingUtil.getServiceId(customerInputs.getServiceType()));
        dbReport.setErrors(report.getErrors());

        Map<Integer, String> dbMap = new HashMap<Integer, String>();
        //Map<String, Set<String>> sourceMap = report.getFilePurposeToNameMapping();

//		Iterator<String> keyIter = sourceMap.keySet().iterator();
//		
//		while( keyIter.hasNext()) {
//			String key = keyIter.next();
//			Set<String> values = sourceMap.get(key);
//			values.stream().forEach( e -> { ;
//				dbMap.put(MappingUtil.getFileTypeId(key), e );
//			});
//		}
        dbReport.setFileTypeToFileNameMapping(dbMap);

        //dbReport.setProjectFiles(report.getExctractedFileNames());
        dbReport.setStatus(report.getValidationStatus());
        dbReport.setSummary(report.getSummary());
        dbReport.setVersion(1);

        return dbReport;
    }

    public static ProjectDetails convertToObject(Project project) {
        ProjectDetails dtl = new ProjectDetails();
        //TODO
        dtl.setProjectId(dtl.getProjectId());
        dtl.setVersion(dtl.getProjectId());
        dtl.setCustomerId(dtl.getCustomerId());
        dtl.setEmailAddress(dtl.getEmailAddress());
        dtl.setServiceType(dtl.getServiceType());
        dtl.setZipFileName(dtl.getZipFileName());
        dtl.setZipFileSize(dtl.getZipFileSize());
        dtl.setLayers(dtl.getLayers());
        dtl.setPcbClass(dtl.getPcbClass());
        dtl.setBoardType(dtl.getBoardType());
        dtl.setItar(dtl.isItar());
        dtl.setTurnTimeQuantity(dtl.getTurnTimeQuantity());
        dtl.setDesignSpecification(dtl.getDesignSpecification());
        dtl.setErrors(dtl.getErrors());
        dtl.setFileDetails(dtl.getFileDetails());
        dtl.setAttachReplace(dtl.isAttachReplace());
        dtl.setNewProject(dtl.isNewProject());
        return dtl;
    }

    public static FileDetails convertToObject(ProjectFiles projectFiles) {
        FileDetails dtl = new FileDetails();
        dtl.setName(dtl.getName());
        dtl.setVersion(dtl.getVersion());
        dtl.setFormat(dtl.getFormat());
        dtl.setValid(dtl.isValid());
        dtl.setModifiedDate(dtl.getModifiedDate());
        dtl.setCreateDate(dtl.getCreateDate());
        dtl.setSize(dtl.getSize());
        dtl.setStep(dtl.getStep());
        dtl.setContext(dtl.getContext());
        dtl.setType(dtl.getType());
        dtl.setPolarity(dtl.getPolarity());
        dtl.setSide(dtl.getSide());
        dtl.setLayerOrder(dtl.getLayerOrder());
        dtl.setStartName(dtl.getStartName());
        dtl.setEndName(dtl.getEndName());
        dtl.setCopperWeight(dtl.getCopperWeight());
        dtl.setLayerName(dtl.getLayerName());
        return dtl;
    }

    public static Project convertToDBObject(ProjectDetails projectDetails) {
        Project dbDetail = new Project();
        dbDetail.setCustomerEmail(projectDetails.getEmailAddress());
        dbDetail.setCustomerId(projectDetails.getCustomerId());
        dbDetail.setDesignSpecification(projectDetails.getDesignSpecification());
        dbDetail.setErrors(projectDetails.getErrors());
        dbDetail.setPcbClass(projectDetails.getPcbClass());
        dbDetail.setProjectId(projectDetails.getProjectId());
        dbDetail.setVersion(UUID.fromString(projectDetails.getVersion()));

        dbDetail.setTurnTimeQuantity(projectDetails.getTurnTimeQuantity());
        dbDetail.setServiceType(projectDetails.getServiceType());
        dbDetail.setZipFileName(projectDetails.getZipFileName());
        dbDetail.setZipFileSize(projectDetails.getZipFileSize());
        dbDetail.setLayerCount(projectDetails.getLayers());
        dbDetail.setModifiedDate(new Date());
        dbDetail.setCreateDate(new Date());
        dbDetail.setCustomerName("ABC");

        return dbDetail;
    }

    public static ProjectFiles convertToDBObject(FileDetails fileDetails) {
        ProjectFiles filesDbDetails = new ProjectFiles();

        filesDbDetails.setVersion(fileDetails.getVersion());
        filesDbDetails.setName(fileDetails.getName());
        filesDbDetails.setSize(fileDetails.getSize());
        filesDbDetails.setFileDate(new Date());
        filesDbDetails.setFormat(fileDetails.getFormat());
        filesDbDetails.setStep(fileDetails.getStep());
        filesDbDetails.setContext(fileDetails.getContext());
        filesDbDetails.setPolarity(fileDetails.getPolarity());
        filesDbDetails.setSide(fileDetails.getSide());
        filesDbDetails.setLayerOrder(fileDetails.getLayerOrder());
        filesDbDetails.setStartName(fileDetails.getStartName());
        filesDbDetails.setEndName(fileDetails.getEndName());
        filesDbDetails.setCopperWeight(fileDetails.getCopperWeight());
        filesDbDetails.setLayerName(fileDetails.getLayerName());
        filesDbDetails.setAttributes(fileDetails.getAttributes());
        filesDbDetails.setCreateDate(new Date());
        filesDbDetails.setModifiedDate(new Date());
        filesDbDetails.setErrors(fileDetails.getErrors());
        return filesDbDetails;
    }

}
