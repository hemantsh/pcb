/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.service;

import com.sc.fe.analyze.data.entity.ExtensionFileType;
import com.sc.fe.analyze.data.repo.ExtensionFileRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pc
 */
@Service
public class ExtensionFileService {
    @Autowired
    private ExtensionFileRepo extensionFileRepo;
    
    public List<ExtensionFileType> findAll(){
        return extensionFileRepo.findAll();
    }
    
    public void save(ExtensionFileType exFT){
        extensionFileRepo.save(exFT);
    }
    
    public List<ExtensionFileType> getExtenFileTypeById(Integer id){
    //return extensionFileRepo.findByKeyFiletypeId(id);
    //return extensionFileRepo.findByKeyFiletypeId(id);
    return extensionFileRepo.findByKeyExtensionId(id);
    }
    
}
