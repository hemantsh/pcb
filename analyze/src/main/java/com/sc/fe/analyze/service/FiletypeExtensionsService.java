/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.service;

import com.sc.fe.analyze.data.entity.FiletypeExtensions;
import com.sc.fe.analyze.data.repo.FiletypeExtensionsRepo;
import com.sc.fe.analyze.to.FileTypeExtensions;
import com.sc.fe.analyze.util.ReportUtility;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 *
 * @author pc
 */
@Service
public class FiletypeExtensionsService {

    @Autowired
    private FiletypeExtensionsRepo filetypeExtensionsRepo;

    public List<FileTypeExtensions> findAll() {
        List<FiletypeExtensions> allRecords = filetypeExtensionsRepo.findAll();
        return convertSet(allRecords);
    }

    private List<FileTypeExtensions> convertSet(List<FiletypeExtensions> allRecords) {
        List<FileTypeExtensions> retList = new ArrayList<FileTypeExtensions>();
        allRecords.stream().forEach(row -> {
            retList.add(ReportUtility.convertToObject(row));
        });
        return retList;
    }

    public void save(FileTypeExtensions filetypeExtensions) {
//        List<FileTypeExtensions> returnList = new ArrayList<FileTypeExtensions>();
//        filetypeExtensions.stream().forEach(row -> {
//            returnList.add(ReportUtility.convertToDBObject(row));
//        });
        FiletypeExtensions fe = ReportUtility.convertToDBObject(filetypeExtensions);
        filetypeExtensionsRepo.save(fe);

    }

    public void deletebyid(final String filetypeExtensions) {
        filetypeExtensionsRepo.deleteByKeyId(UUID.fromString(filetypeExtensions));
    }

    public void deleteFiletype(FileTypeExtensions filetypeExtensions) {
//        List<FileTypeExtensions> returnList = new ArrayList<FileTypeExtensions>();
//        
//        filetypeExtensions.stream().forEach(row -> {
        filetypeExtensionsRepo.delete(ReportUtility.convertToDBObject(filetypeExtensions));
//        });
    }

    public FiletypeExtensions getFileExtenions(String extension) {
        return filetypeExtensionsRepo.findByExtensions(extension).get(0);
    }

}
