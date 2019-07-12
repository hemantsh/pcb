package com.sc.fe.analyze.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.fe.analyze.data.entity.ServiceFiletypes;
import com.sc.fe.analyze.data.entity.Services;
import com.sc.fe.analyze.data.repo.ServiceFiletypesRepo;
import com.sc.fe.analyze.data.repo.ServicesRepo;

/**
 *
 * @author Hemant
 */
@Service
@Transactional
public class FileServices {

    @Autowired
    private ServicesRepo serviceRepo;
    
    @Autowired
    private ServiceFiletypesRepo serviceFileTypeRepo;

    @Autowired
    private CachingService cacheService;

    /**
     * This method find the services
     *
     * @return all the services which found in the database
     */
    @Cacheable(value = "Services")
    public List<Services> findAll() {
        return serviceRepo.findAll();
    }

    /**
     *
     * @param services the services to store in a database
     */
    public void save(Services services) {
        if (services.getId() == 0) {
            services.setId(getMaxServiceId().getId() + 1);
        }
        serviceRepo.save(services);
        cacheService.evictAllCacheValues("Services");
        cacheService.evictSingleCacheValue("Service", services.getId());
    }

    public Services getMaxServiceId() {
        List<Services> service = findAll();
        Services max = new Services();
        if (service.isEmpty()) {
            return max;
        }
        max = Collections.max(service, Comparator.comparing(s -> s.getId()));
        return max;
    }

    /**
     * Removes List of services from the database.
     *
     * @param services the services to remove from a database
     */
    public void deleteAll(List<Services> services) {
        
        if(services != null && services.size() > 0) {
        	serviceRepo.deleteAll(services);
        	
        	services.stream().forEach( service -> {
        		List<ServiceFiletypes> fTypes = serviceFileTypeRepo.findByKeyServiceid(service.getId());
                serviceFileTypeRepo.deleteAll(fTypes);
        	});
            
            cacheService.evictAllCacheValues("Services");
            cacheService.evictAllCacheValues("Service");
            
        }
        
    }

    /**
     *
     * @param id the specific service to find from database by id
     * @return the services
     */
    @Cacheable(value = "Service")
    public Services getServicesById(int id) {
        return serviceRepo.findById(id).get();
    }

}
