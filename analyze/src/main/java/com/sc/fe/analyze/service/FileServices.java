/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.service;

import com.sc.fe.analyze.data.entity.Services;
import com.sc.fe.analyze.data.repo.ServicesRepo;
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
public class FileServices {
    @Autowired
    private ServicesRepo serviceRepo;
    
    public List<Services>findAll(){
        return serviceRepo.findAll();
    }
    public void save(Services services){
        serviceRepo.save(services);
    }
    public void delete(Services services){
    serviceRepo.delete(services);
    }
    public Services getServicesById(int id){
    return serviceRepo.findById(id).get();
    }
    
}
