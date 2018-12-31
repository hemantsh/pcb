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
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
        @Service("ExtensionService")
        @Transactional
public class FileExtensionImple implements FileExtensionService{
    @Autowired
    private ExtensionsRepo extensionRepo;
    
            
    @Override
    public Iterable<Extensions> findAll(){
    return extensionRepo.findAll();
    }
    
}
