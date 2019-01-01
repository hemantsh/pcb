/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.service;

import org.springframework.stereotype.*;
//import com.sc.fe.analyze.service.*;
//import com.sc.fe.analyze.util.*;
import com.sc.fe.analyze.data.entity.*;
import com.sc.fe.analyze.data.repo.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
        @Service("ExtensionService")
        @Transactional
public class FileExtensionService {
    @Autowired
    private ExtensionsRepo extensionRepo;
    
            
   
    public List<Extensions> findAll(){
    return extensionRepo.findAll();
    }
    
    public void save(Extensions ext){
        extensionRepo.save(ext);
    }
    
//    public void delete(Extensions extn){
//    extensionRepo.delete(extn);
//    }
   
    public Extensions getExtensionById(Integer id){
        return extensionRepo.findById(id).get();
    }
    
}
