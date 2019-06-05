/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.service;

import com.sc.fe.analyze.data.entity.FiletypeExtensions;
import com.sc.fe.analyze.data.repo.FiletypeExtensionsRepo;
import java.util.List;
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

    public List<FiletypeExtensions> findAll() {
        return filetypeExtensionsRepo.findAll();
    }

    public void save(FiletypeExtensions filetypeExtensions) {
        filetypeExtensionsRepo.save(filetypeExtensions);
    }

    public void delete(FiletypeExtensions filetypeExtensions) {
        filetypeExtensionsRepo.delete(filetypeExtensions);
    }

    public FiletypeExtensions getFileExtenions(String extension) {
        return filetypeExtensionsRepo.findByExtensions(extension).get(0);
    }

}
