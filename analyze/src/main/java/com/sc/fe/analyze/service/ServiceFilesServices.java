/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.service;

import com.sc.fe.analyze.data.entity.ServiceFiles;
import com.sc.fe.analyze.data.repo.ServiceFilesRepo;
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
public class ServiceFilesServices {
    
    @Autowired
    private ServiceFilesRepo serviceFileRepo;
    
    public List<ServiceFiles> findAll(){
    return serviceFileRepo.findAll();
    }
    
    public void save(ServiceFiles sf){
    serviceFileRepo.save(sf);
    }
    public void saveAll(List<ServiceFiles> sf){
        serviceFileRepo.saveAll(sf);
    }
    public List<ServiceFiles> getFilesByService(Integer service_id){
        
    return serviceFileRepo.findByKeyServiceId(service_id);
    }
    
    
}
