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
 * @author Hemant
 */
@Service
public class ExtensionFileService {
    @Autowired
    private ExtensionFileRepo extensionFileRepo;
    @Autowired
    private CachingService cacheService;
    
    /**
     * This method find the extension_file
     * @return all the extension_file which found in the database
     */
    @Cacheable(value="ExtnFileTypes")
    public List<ExtensionFileType> findAll(){
        return extensionFileRepo.findAll();
    }
    
    /**
     *
     * @param exFT - the extension_file to store in a database
     */
    public void save(ExtensionFileType exFT){
        extensionFileRepo.save(exFT);
        cacheService.evictAllCacheValues("ExtnFileTypes");
    }

    /**
     *
     * @param exFT - the list of extension_file to store in a database
     */
    public void saveAll(List<ExtensionFileType> exFT){
        extensionFileRepo.saveAll(exFT);
        cacheService.evictAllCacheValues("ExtnFileTypes");
    }
    
    /**
     *
     * @param id - the specific extension_file to find from database by id
     * @return the list of extension_file
     */
    public List<ExtensionFileType> getExtenFileTypeById(Integer id){
    	
//    	List<ExtensionFileType> retList = findAll();
//    	return retList.stream().filter( e -> e.getFiletypeId() == id ).collect(Collectors.toList());
    	return extensionFileRepo.findByKeyFiletypeId(id);
    }
    
}
