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

import com.sc.fe.analyze.data.entity.Services;
import com.sc.fe.analyze.data.repo.ServicesRepo;

/**
 *
 * @author pc
 */
@Service
@Transactional
public class FileServices {
    @Autowired
    private ServicesRepo serviceRepo;
    
    @Autowired
    private CachingService cacheService;
    
    @Cacheable(value="Services")
    public List<Services>findAll(){
        return serviceRepo.findAll();
    }
    
    public void save(Services services){
        serviceRepo.save(services);
        cacheService.evictAllCacheValues("Services");
    }
    
    public void delete(Services services){
    	serviceRepo.delete(services);	
		cacheService.evictAllCacheValues("Services");
    }
    
    public Services getServicesById(int id){
    	Services srv = null;
    	List<Services> retList = findAll();
    	retList = retList.stream().filter( e -> e.getId() == id ).collect(Collectors.toList());
    	if( retList != null ) {
    		srv = retList.get(0);
    	}
    	return srv;
    }
    
}
