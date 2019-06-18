package com.sc.fe.analyze.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.sc.fe.analyze.data.entity.ExtensionFileType;
//import com.sc.fe.analyze.data.entity.Extensions;
//import com.sc.fe.analyze.data.entity.FileTypes;
import com.sc.fe.analyze.data.entity.ServiceFiles;
import com.sc.fe.analyze.data.entity.Services;
//import com.sc.fe.analyze.data.repo.ExtensionFileRepo;
//import com.sc.fe.analyze.data.repo.ExtensionsRepo;
//import com.sc.fe.analyze.data.repo.FileTypesRepo;
//import com.sc.fe.analyze.data.repo.ServiceFilesRepo;
import com.sc.fe.analyze.data.repo.ServicesRepo;
import com.sc.fe.analyze.util.MappingUtil;
import java.util.HashMap;

/**
 *
 * @author Hemant
 */
@Service
public class BaseService {

//    @Autowired
//    private ServiceFilesRepo serviceFilesRepo;
//    @Autowired
//    private ExtensionFileRepo extnFileRepo;
    @Autowired
    private ServicesRepo serviceRepo;
//    @Autowired
//    private FileTypesRepo fileTypesRepo;
//    @Autowired
//    private ExtensionsRepo extnRepo;

    /**
     * This method initialize the elements(extensionMap,fileTypeMap and
     * serviceMap) of MappingUtil class.
     */
//    @PostConstruct
//    public void afterConst() {
//        MappingUtil.init(getExtensionMap(), getFileTypeMap(), getServiceMap());
//    }

    /**
     * This method retrieves file Extensions from database.
     *
     * @return the Map
     */
//    public Map<Integer, String> getExtensionMap() {
//
//        Map<Integer, String> retMap = new HashMap<Integer, String>();
//
//        List<Extensions> list = extnRepo.findAll();
//        list.stream().forEach(e -> {
//            retMap.put(e.getId(), e.getName());
//        });
//
//        return retMap;
//    }

    /**
     * This method retrieves file FileTypes from database.
     *
     * @return the Map
     */
    public Map<Integer, String> getFileTypeMap() {

//        Map<Integer, String> retMap = new HashMap<Integer, String>();
//
//        List<FileTypes> list = fileTypesRepo.findAll();
//        list.stream().forEach(e -> {
//            retMap.put(e.getId(), e.getType());
//        });
//
//        return retMap;
return null;
    }

    /**
     * This method retrieves the Services from database.
     *
     * @return the Map
     */
    public Map<Integer, String> getServiceMap() {

        Map<Integer, String> retMap = new HashMap<Integer, String>();

        List<Services> list = serviceRepo.findAll();
        list.stream().forEach(e -> {
            retMap.put(e.getId(), e.getName());
        });

        return retMap;
    }

    /**
     * This method retrieves the service files from the database.
     *
     * @param serviceId The serviceId to get
     * @return the serviceFiles
     */
//    public List<String> getServiceFiles(int serviceId) {
//
//        List<ServiceFiles> serviceFiles = serviceFilesRepo.findByKeyServiceId(serviceId);
//        return serviceFiles.stream().map(ServiceFiles::getFile).collect(Collectors.toList());
//    }

    /**
     * This method list file extension with their FileType.
     *
     * @return the Map in (key-fileExtension,value-fileType)
     */
//    public Map<String, String> getExtensionToFileMapping() {
//
//        Map<String, String> returnMap = new HashMap<String, String>();
//        List<ExtensionFileType> allList = extnFileRepo.findAll();
//        allList.stream().forEach(e -> {
//            returnMap.put(e.getExtension(), e.getFile());
//        });
//
//        return returnMap;
//    }

    /**
     *
     * @return the serviceFilesReport
     */
//    public ServiceFilesRepo getServiceFilesRepo() {
//        return serviceFilesRepo;
//    }

    /**
     *
     * @param serviceFilesRepo The serviceFilesRepo to set
     */
//    public void setServiceFilesRepo(ServiceFilesRepo serviceFilesRepo) {
//        this.serviceFilesRepo = serviceFilesRepo;
//    }

    /**
     * Gets the extension Report
     *
     * @return the extensionFileReport
     */
//    public ExtensionFileRepo getExtRepo() {
//        return extnFileRepo;
//    }

    /**
     * Sets the extensionFileReport into the extnFileRepo instance variable.
     *
     * @param extRepo The extnFileRepo to set
     */
//    public void setExtRepo(ExtensionFileRepo extRepo) {
//        this.extnFileRepo = extRepo;
//    }

    /**
     * Gets the extension FileRepo
     *
     * @return the ExtensionFileRepo
     */
//    public ExtensionFileRepo getExtnFileRepo() {
//        return extnFileRepo;
//    }

    /**
     * Sets the extensionFile
     *
     * @param extnFileRepo the extensionFileRepo to set
     */
//    public void setExtnFileRepo(ExtensionFileRepo extnFileRepo) {
//        this.extnFileRepo = extnFileRepo;
//    }

    /**
     * Gets the ServiceRepo
     *
     * @return the serviceRepo
     */
    public ServicesRepo getServiceRepo() {
        return serviceRepo;
    }

    /**
     * Sets the serviceRepo
     *
     * @param serviceRepo the serviceRepo to set
     */
    public void setServiceRepo(ServicesRepo serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    /**
     * Gets the fileTypesRepo
     *
     * @return the fileTypesRepo
     */
//    public FileTypesRepo getFileTypesRepo() {
//        return fileTypesRepo;
//    }

    /**
     * Sets the fileTypesRepo
     *
     * @param fileTypesRepo the fileTypesRepo to set
     */
//    public void setFileTypesRepo(FileTypesRepo fileTypesRepo) {
//        this.fileTypesRepo = fileTypesRepo;
//    }

    /**
     * Gets the extensionRepo
     *
     * @return the extensionRepo
     */
//    public ExtensionsRepo getExtnRepo() {
//        return extnRepo;
//    }

    /**
     * Sets the extensionRepo
     *
     * @param extnRepo the extnensionRepo to set
     */
//    public void setExtnRepo(ExtensionsRepo extnRepo) {
//        this.extnRepo = extnRepo;
//    }

}
