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

import com.sc.fe.analyze.data.entity.FileTypes;
import com.sc.fe.analyze.data.repo.FileTypesRepo;

/**
 *
 * @author pc
 */
@Service
@Transactional
public class FileTypeService {
    @Autowired
    private FileTypesRepo fileTypesRepo;
    @Autowired
    private CachingService cacheService;
    
    @Cacheable(value="FileTypes")
    public List<FileTypes> findAll() {
        return fileTypesRepo.findAll(); 
    }
    
    public void save(FileTypes ft) {
    	fileTypesRepo.save(ft);
    	cacheService.evictAllCacheValues("FileTypes");
    }
    
    public FileTypes getTypeById(Integer id) {
    	FileTypes fileTyp = null;
    	List<FileTypes> retList = findAll();
    	retList = retList.stream().filter( e -> e.getId() == id ).collect(Collectors.toList());
    	if( retList != null ) {
    		fileTyp = retList.get(0);
    	}
    	return fileTyp;
    }
}
