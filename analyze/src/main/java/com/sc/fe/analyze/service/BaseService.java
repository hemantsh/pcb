package com.sc.fe.analyze.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.fe.analyze.data.entity.FiletypeExtensions;
import com.sc.fe.analyze.data.entity.ServiceFiletypes;
import com.sc.fe.analyze.data.entity.Services;
import com.sc.fe.analyze.data.repo.FiletypeExtensionsRepo;
import com.sc.fe.analyze.data.repo.ServiceFiletypesRepo;
import com.sc.fe.analyze.data.repo.ServicesRepo;
import com.sc.fe.analyze.util.MappingUtil;

/**
 *
 * @author Hemant
 */
@Service
public class BaseService {

    @Autowired
    private FiletypeExtensionsRepo filetypeExtensionsRepo;
    @Autowired
    private ServiceFiletypesRepo serviceFiletypesRepo;
    @Autowired
    private ServicesRepo serviceRepo;

    /**
     * This method initialize the elements(extensionMap,fileTypeMap and
     * serviceMap) of MappingUtil class.
     */
    @PostConstruct
    public void afterConst() {
        MappingUtil.init(serviceRepo.findAll(), serviceFiletypesRepo.findAll(), filetypeExtensionsRepo.findAll());
    }

    /**
     * This method retrieves the File Extensions from database.
     *
     * @return the Map
     */
    public Map<String, String> getFileTypeByExtension() {

        Map<String, String> retMap = new HashMap<String, String>();

        List<FiletypeExtensions> list = filetypeExtensionsRepo.findAll();
        list.stream().forEach(e -> {
            for (String extn : e.getExtensions()) {
                retMap.put(extn, e.getKey().getFiletype());
            }

        });
        System.out.println(retMap);
        return retMap;
    }

    /**
     * This method retrieves the file Extensions from database.
     *
     * @return the Map
     */
    public static Map<String, Set<String>> getExtensionTofiletypeMap() {
        return MappingUtil.getExtensionTofiletypeMap();
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
     * This method retrieves the Service files from database.
     *
     * @param serviceid Takes the serviceId
     * @return the Map
     */
    public List<String> getServiceFiles(int serviceid) {
        List<ServiceFiletypes> serviceFiletypes = serviceFiletypesRepo.findByKeyServiceid(serviceid);
        return serviceFiletypes.stream().map(ServiceFiletypes::getFileType).collect(Collectors.toList());
    }

    /**
     * This method retrieves the file types from database.
     *
     * @param extension Takes the extension
     * @return the Map
     */
    public List<String> getFilesType(String extension) {
        List<FiletypeExtensions> extensionFiletypes = filetypeExtensionsRepo.findByExtensions(extension);
        return extensionFiletypes.stream().map(FiletypeExtensions::getFileType).collect(Collectors.toList());
    }

    /**
     * Gets the ServiceFileTypesRepo
     *
     * @return the serviceFileTypesRepo
     */
    public ServiceFiletypesRepo getServiceFiletypesRepo() {
        return serviceFiletypesRepo;
    }

    /**
     * Sets the ServiceFileTypesRepo
     *
     * @param serviceFiletypeRepo the serviceFileTypeRepo to set
     */
    public void setServiceFiletypesRepo(ServiceFiletypesRepo serviceFiletypeRepo) {
        this.serviceFiletypesRepo = serviceFiletypeRepo;
    }

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

}
