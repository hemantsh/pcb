/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.service;


import com.sc.fe.analyze.data.entity.FileTypes;
import com.sc.fe.analyze.data.repo.FileTypesRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Service
@Transactional
public class FileTypeService {
    @Autowired
    private FileTypesRepo fileTypesRepo;
    
    public List<FileTypes> findAll(){
        return fileTypesRepo.findAll(); 
    }
    public void save(FileTypes ft){
    fileTypesRepo.save(ft);
    }
    public FileTypes getTypeById(Integer id){
    return fileTypesRepo.findById(id).get();
    }
}
