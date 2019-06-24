package com.sc.fe.analyze.service;

import com.sc.fe.analyze.data.entity.ServiceFiletypes;
import com.sc.fe.analyze.data.repo.ServiceFiletypesRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hemant
 */
@Service
public class ServiceFiletypesService {

    @Autowired
    private ServiceFiletypesRepo serviceFiletypeRepo;

    /**
     * Displays all the records in the service_filetypes tables
     *
     * @return the list of ServiceFileTypes
     */
    public List<ServiceFiletypes> findAll() {
        return serviceFiletypeRepo.findAll();
    }

    /**
     * Retrieve the records by serviceId
     *
     * @param serviceId Takes the serviceId
     * @return the list of ServiceFileTypes
     */
    public List<ServiceFiletypes> findByKeyServiceId(int serviceId) {
        return serviceFiletypeRepo.findByKeyServiceid(serviceId);
    }

    /**
     * Saves serviceFileTypes data in database.
     *
     * @param serviceFileType has serviceFileType that needs to saved into
     * database.
     */
    public void save(List<ServiceFiletypes> serviceFileType) {
        serviceFiletypeRepo.saveAll(serviceFileType);
    }

    /**
     * Deletes the Records from the Database.
     *
     * @param serviceFiletype has values that needs to delete from the database.
     */
    public void delete(List<ServiceFiletypes> serviceFiletype) {
        serviceFiletypeRepo.deleteAll(serviceFiletype);
    }
}
