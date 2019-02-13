/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.service;

import com.sc.fe.analyze.data.repo.ReportRepo;
import com.sc.fe.analyze.data.entity.Report;
import com.sc.fe.analyze.util.MappingUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hemant
 */
@Service
@Transactional
public class ReportServices {

    @Autowired
    private ReportRepo reportRepo;

    /**
     * This method find the report
     *
     * @return all the reports which found in the database
     */
    public List<Report> findAll() {
        return reportRepo.findAll();
    }

    /**
     *
     * @param report - the report to store in a database
     */
    public void save(Report report) {
        reportRepo.save(report);
    }

    public List<Report> getReportById(String projectId) {
        return reportRepo.findByKeyProjectId(projectId);
    }

    public String getFileType(Integer id) {
        return MappingUtil.getFileType(id);
    }
}
