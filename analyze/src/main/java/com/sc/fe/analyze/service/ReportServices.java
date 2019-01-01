/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.service;

import com.sc.fe.analyze.data.repo.ReportRepo;
import com.sc.fe.analyze.data.entity.Report;
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
public class ReportServices {
    @Autowired
    private ReportRepo reportRepo;
    
    public List<Report> findAll(){
        return reportRepo.findAll();
    }
    
    public void save(Report report){
        reportRepo.save(report);
    }
    
}
