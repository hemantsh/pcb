/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.service;

import com.sc.fe.analyze.data.entity.ServiceFiletypes;
import com.sc.fe.analyze.data.repo.ServiceFiletypesRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pc
 */
@Service
public class ServiceFiletypesService {

    @Autowired
    private ServiceFiletypesRepo serviceFiletypeRepo;

    public List<ServiceFiletypes> findAll() {
        return serviceFiletypeRepo.findAll();
    }

    public List<ServiceFiletypes> findByKeyServiceId(int serviceId) {
        return serviceFiletypeRepo.findByKeyServiceid(serviceId);
    }

    public void save(List<ServiceFiletypes> serviceFileType) {
        serviceFiletypeRepo.saveAll(serviceFileType);
    }

    public void delete(List<ServiceFiletypes> serviceFiletype) {
        serviceFiletypeRepo.deleteAll(serviceFiletype);
    }
}
