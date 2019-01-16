/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.fe.analyze.data.entity.ServiceFiles;
import com.sc.fe.analyze.data.repo.ServiceFilesRepo;

/**
 *
 * @author pc
 */
@Service
@Transactional
public class ServiceFilesServices {
    
    @Autowired
    private ServiceFilesRepo serviceFileRepo;
    @Autowired
    private CachingService cacheService;
    
    @Cacheable(value="ServiceFiles")
    public List<ServiceFiles> findAll(){
    	return serviceFileRepo.findAll();
    }
    
    public void save(ServiceFiles sf){
    	serviceFileRepo.save(sf);
    	cacheService.evictAllCacheValues("ServiceFiles");
    }
    
    public void saveAll(List<ServiceFiles> sf){
        serviceFileRepo.saveAll(sf);
    	cacheService.evictAllCacheValues("ServiceFiles");
    }
    
    public List<ServiceFiles> getFilesByService(Integer service_id) {
        return serviceFileRepo.findByKeyServiceId(service_id);
    }
    public void delete(ServiceFiles df){
        serviceFileRepo.delete(df);
    }
    
}
