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

import com.sc.fe.analyze.data.entity.ExtensionFileType;
import com.sc.fe.analyze.data.repo.ExtensionFileRepo;

/**
 *
 * @author pc
 */
@Service
public class ExtensionFileService {
    @Autowired
    private ExtensionFileRepo extensionFileRepo;
    @Autowired
    private CachingService cacheService;
    
    @Cacheable(value="ExtnFileTypes")
    public List<ExtensionFileType> findAll(){
        return extensionFileRepo.findAll();
    }
    
    public void save(ExtensionFileType exFT){
        extensionFileRepo.save(exFT);
        cacheService.evictAllCacheValues("ExtnFileTypes");
    }
    public void saveAll(List<ExtensionFileType> exFT){
        extensionFileRepo.saveAll(exFT);
        cacheService.evictAllCacheValues("ExtnFileTypes");
    }
    
    public List<ExtensionFileType> getExtenFileTypeById(Integer id){
    	
    	List<ExtensionFileType> retList = findAll();
    	return retList.stream().filter( e -> e.getFiletypeId() == id ).collect(Collectors.toList());
    	
    }
    
}
