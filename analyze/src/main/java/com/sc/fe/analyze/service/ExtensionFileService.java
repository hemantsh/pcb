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
     *
     * @return all the extension_file which found in the database
     */
    @Cacheable(value = "ExtnFileTypes")
    public List<ExtensionFileType> findAll() {
        return extensionFileRepo.findAll();
    }

    /**
     * This method saves the extension_file into the database.
     *
     * @param exFT The extension_file to store in a database
     */
    public void save(ExtensionFileType exFT) {
        extensionFileRepo.save(exFT);
        cacheService.evictAllCacheValues("ExtnFileTypes");
    }

    /**
     * This method saves all the extension_file types into the database.
     *
     * @param exFT The list of extension_file to store in a database
     */
    public void saveAll(List<ExtensionFileType> exFT) {
        extensionFileRepo.saveAll(exFT);
        cacheService.evictAllCacheValues("ExtnFileTypes");
    }

    /**
     * It returns the list of extension_file types from the database.
     *
     * @param id The specific extension_file to find from database by id
     * @return the list of extension_file
     */
    public List<ExtensionFileType> getExtenFileTypeById(Integer id) {
        return extensionFileRepo.findByKeyFiletypeId(id);
    }

    /**
     * It deletes all the extension file Types from the database.
     *
     * @param extensionFileType the list of extensionFileType which have to be
     * delete from the database.
     */
    public void deleteAll(List<ExtensionFileType> extensionFileType) {
        extensionFileRepo.deleteAll(extensionFileType);
    }
}
