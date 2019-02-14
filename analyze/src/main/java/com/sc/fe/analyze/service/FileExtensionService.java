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

//import com.sc.fe.analyze.service.*;
//import com.sc.fe.analyze.util.*;
import com.sc.fe.analyze.data.entity.Extensions;
import com.sc.fe.analyze.data.repo.ExtensionsRepo;

/**
 *
 * @author Hemant
 */
@Service("ExtensionService")
@Transactional
public class FileExtensionService {

    @Autowired
    private ExtensionsRepo extensionRepo;

    @Autowired
    private CachingService cacheService;

    /**
     * This method find the extensions
     *
     * @return all the extensions which found in the database
     */
    @Cacheable(value = "Extensions")
    public List<Extensions> findAll() {
        return extensionRepo.findAll();
    }

    /**
     *
     * @param ext -the extensions to store in a database
     */
    public void save(Extensions ext) {
        extensionRepo.save(ext);
        cacheService.evictAllCacheValues("Extensions");
    }

//    public void delete(Extensions extn){
//    extensionRepo.delete(extn);
//    }
    /**
     *
     * @param id - the specific extension to find from database by id
     * @return the extension
     */
    public Extensions getExtensionById(Integer id) {
        return extensionRepo.findById(id).get();
    }

}
