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

import com.sc.fe.analyze.data.entity.FileTypes;
import com.sc.fe.analyze.data.repo.FileTypesRepo;

/**
 *
 * @author Hemant
 */
@Service
@Transactional
public class FileTypeService {

    @Autowired
    private FileTypesRepo fileTypesRepo;
    @Autowired
    private CachingService cacheService;

    /**
     * This method find the file type services
     *
     * @return all the file type services which found in the database
     */
    @Cacheable(value = "FileTypes")
    public List<FileTypes> findAll() {
        return fileTypesRepo.findAll();
    }

    /**
     *
     * @param ft - the fileType to save in a database
     */
    public void save(FileTypes ft) {
        fileTypesRepo.save(ft);
        cacheService.evictAllCacheValues("FileTypes");
    }

    /**
     *
     * @param id - the specific FileType service to find from database by id
     * @return the fileType
     */
    public FileTypes getTypeById(Integer id) {
        return fileTypesRepo.findById(id).get();
    }
}
