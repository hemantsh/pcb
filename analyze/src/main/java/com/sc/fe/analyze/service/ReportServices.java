package com.sc.fe.analyze.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.fe.analyze.data.entity.Report;
import com.sc.fe.analyze.data.repo.ReportRepo;

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

    /**
     * Retrieve the report by id
     *
     * @param projectId takes the projectId
     * @return the list of Report
     */
    public List<Report> getReportById(String projectId) {
        return reportRepo.findByKeyProjectId(projectId);
    }

}
