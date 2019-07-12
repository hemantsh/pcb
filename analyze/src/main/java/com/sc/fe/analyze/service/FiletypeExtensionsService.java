package com.sc.fe.analyze.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.amazonaws.util.StringUtils;
import com.sc.fe.analyze.data.entity.FiletypeExtensions;
import com.sc.fe.analyze.data.entity.ServiceFiletypes;
import com.sc.fe.analyze.data.repo.FiletypeExtensionsRepo;
import com.sc.fe.analyze.data.repo.ServiceFiletypesRepo;
import com.sc.fe.analyze.to.FileTypeExtensions;
import com.sc.fe.analyze.util.ReportUtility;

/**
 *
 * @author Hemant
 */
@Service
public class FiletypeExtensionsService {

    @Autowired
    private FiletypeExtensionsRepo filetypeExtensionsRepo;
    @Autowired
    private ServiceFiletypesRepo serviceFiletypeRepo;
    @Autowired
    private CachingService cacheService;

    /**
     * Displays all the records in the filetype_extensions tables and returns
     * data of fileTypeExtensions Type.
     *
     * @return the list of FileTypeExtensions
     */
    public List<FileTypeExtensions> findAll() {
        List<FiletypeExtensions> allRecords = filetypeExtensionsRepo.findAll();
        return convertSet(allRecords);
    }

    /**
     * Converts the FiletypeExtensions data into FileTypeExtensions
     *
     * @param allRecords takes List of FiletypeExtensions records
     * @return the list of FileTypeExtensions
     */
    private List<FileTypeExtensions> convertSet(List<FiletypeExtensions> allRecords) {
        List<FileTypeExtensions> retList = new ArrayList<FileTypeExtensions>();
        allRecords.stream().forEach(row -> {
            retList.add(ReportUtility.convertToObject(row));
        });
        return retList;
    }

    /**
     * Saves FiletypeExtensions data in database.
     *
     * @param filetypeExtensions has filetypeExtensions information that needs
     * to saved into database.
     */
    public void save(FileTypeExtensions filetypeExtensions) {
        FiletypeExtensions fe = ReportUtility.convertToDBObject(filetypeExtensions);
        filetypeExtensionsRepo.save(fe);
        cacheService.evictAllCacheValues("ExtnFileMap");

    }

    /**
     * Delete the record by id and fileType
     *
     * @param id Takes the id
     * @param file_type Takes the file_type
     */
    public void deletebyid(final String id, final String file_type) {
        FiletypeExtensions filetypeObj = new FiletypeExtensions();
        filetypeObj.getKey().setId(UUID.fromString(id));
        filetypeObj.getKey().setFiletype(file_type);
        filetypeExtensionsRepo.delete(filetypeObj);

        ServiceFiletypes serviceFiletype = serviceFiletypeRepo.findByKeyFiletype(file_type);
        serviceFiletypeRepo.delete(serviceFiletype);

        cacheService.evictAllCacheValues("ExtnFileMap");
    }

    /**
     * Delete the record by fileType
     *
     * @param filetypeExtensions
     */
    public void deleteFiletype(FileTypeExtensions filetypeExtensions) {

        FiletypeExtensions filetypeExtn = ReportUtility.convertToDBObject(filetypeExtensions);
        filetypeExtensionsRepo.delete(filetypeExtn);

        cacheService.evictAllCacheValues("ExtnFileMap");
    }

    /**
     * Retrieve the record by extension from the database.
     *
     * @param extension Takes the extension
     * @return the FiletypeExtensions
     */
    public FiletypeExtensions getFileExtenions(String extension) {
        return filetypeExtensionsRepo.findByExtensions(extension).get(0);
    }

    /**
     * Return a map of extension to fileType mapping.
     *
     * @return Map Key=extension, value = set of file types
     */
    @Cacheable(value = "ExtnFileMap")
    public Map<String, Set<String>> extensionToFileMap() {

        Map<String, Set<String>> extensionTofiletypeMap = new HashMap<String, Set<String>>();
        List<FileTypeExtensions> filetypeExtensions = findAll();

        if (filetypeExtensions == null) {
            return extensionTofiletypeMap;
        }
        filetypeExtensions.stream().forEach(row -> {
            if (StringUtils.hasValue(row.getExtensions())) {
                String[] extns = row.getExtensions().split(",");
                for (String extn : extns) {
                    Set<String> filetypeSet = extensionTofiletypeMap.get(extn);
                    if (filetypeSet == null) {
                        filetypeSet = new HashSet<String>();
                    }
                    filetypeSet.add(row.getFile_type());
                    extensionTofiletypeMap.put(extn, filetypeSet);
                }
            }
        });
        return extensionTofiletypeMap;
    }

}
