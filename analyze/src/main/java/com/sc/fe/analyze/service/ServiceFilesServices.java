/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.fe.analyze.data.entity.ServiceFiles;
import com.sc.fe.analyze.data.repo.ServiceFilesRepo;

/**
 *
 * @author Hemant
 */
@Service
@Transactional
public class ServiceFilesServices {

    @Autowired
    private ServiceFilesRepo serviceFileRepo;
    @Autowired
    private CachingService cacheService;

    /**
     * This method find the services_files
     *
     * @return all the service_files which found in the database
     */
    @Cacheable(value = "ServiceFiles")
    public List<ServiceFiles> findAll() {
        return serviceFileRepo.findAll();
    }

    /**
     *
     * @param sf - the service_files to store in a database
     */
    public void save(ServiceFiles sf) {
        serviceFileRepo.save(sf);
        cacheService.evictAllCacheValues("ServiceFiles");
    }

    /**
     *
     * @param sf - the list of services_files to store in a database
     */
    public void saveAll(List<ServiceFiles> sf) {
        serviceFileRepo.saveAll(sf);
        cacheService.evictAllCacheValues("ServiceFiles");
    }

    /**
     *
     * @param service_id - the specific service_file to find from database
     * @return the service_files
     */
    public List<ServiceFiles> getFilesByService(Integer service_id) {
        return serviceFileRepo.findByKeyServiceId(service_id);
    }

    /**
     *
     * @param serviceFiles - - the list of services_files which will be deleted from the
     * database
     */
    public void deleteAll(List<ServiceFiles> serviceFiles) {
        serviceFileRepo.deleteAll(serviceFiles);
    }

}
