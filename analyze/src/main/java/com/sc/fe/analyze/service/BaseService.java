package com.sc.fe.analyze.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.fe.analyze.data.entity.ServiceFiletypes;
import com.sc.fe.analyze.data.entity.Services;

/**
 *
 * @author Hemant
 */
@Service
public class BaseService {

	@Autowired
	private FileServices fileServices;
	
	@Autowired
	private ServiceFiletypesService serviceFiletypesService;
	
	@Autowired
    private FiletypeExtensionsService filetypeExtensionsService;
	
    /**
     * This method retrieves the Service files from database.
     *
     * @param serviceid Takes the serviceId
     * @return the Map
     */
    public List<String> getServiceFiles(int serviceid) {
        List<ServiceFiletypes> serviceFiletypes = serviceFiletypesService.findByKeyServiceId(serviceid);
        return serviceFiletypes.stream().map(ServiceFiletypes::getFileType).collect(Collectors.toList());
    }

    public int getServiceId(String serviceName) {
    	int id = 0;
    	List<Services> services = fileServices.findAll();
    	if( services !=null && services.size() > 0) {
    		for (Services serv : services) {
				if( serv.getName().equalsIgnoreCase( serviceName)) {
					id = serv.getId();
					break;
				}
			}
    	}
    	return id;
    }
    
    
    public Map<String, Set<String>> extensionToFileMap() {
    	return filetypeExtensionsService.extensionToFileMap();
    }
    
}
