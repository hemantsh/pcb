package com.sc.fe.analyze.util;

import com.sc.fe.analyze.data.entity.FiletypeExtensions;

import com.sc.fe.analyze.data.entity.ServiceFiletypes;
import com.sc.fe.analyze.data.entity.Services;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
//import com.sc.fe.analyze.to.FileTypeExtensions;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Hemant
 */
public class MappingUtil {

//    private static Map<Integer, String> extensionMap;
//    private static Map<Integer, String> fileTypeMap;
    private static Map<Integer, Set<String>> serviceFiletypesMap = new HashMap<Integer, Set<String>>();//Key is ServiceId and value is filetype.
    private static Map<Integer, String> serviceMap = new HashMap<Integer, String>();//Key is serviceId and value is Service Name.
    private static Map<String, Set<String>> extensionTofiletypeMap = new HashMap<String, Set<String>>();//Key is extension and value is filetype.

//    private static Map<String, Integer> extensionReverseMap;
//    private static Map<String, Integer> fileTypeReverseMap;
    private static Map<String, Integer> serviceReverseMap;

    private MappingUtil() {
    }

    public static void init(List<Services> pServices,
            List<ServiceFiletypes> pServiceFiletypes,
            List<FiletypeExtensions> pFiletypeExtensions) {        

        servicesMap(pServices);
        servicesFilesMap(pServiceFiletypes);
        extensionToFileMap(pFiletypeExtensions);
        
        serviceReverseMap = new HashMap<String, Integer>(serviceMap.size());
        Iterator<Integer> iKeys = serviceMap.keySet().iterator();
        while (iKeys.hasNext()) {
            Integer key = iKeys.next();
            serviceReverseMap.put(serviceMap.get(key).toLowerCase(), key);
        }


    }

    public static void servicesMap(List<Services> services) {
        services.stream().forEach(row -> {
            serviceMap.put(row.getId(), row.getName());
        });
    }

    public static void servicesFilesMap(List<ServiceFiletypes> serviceFilesMap) {
        serviceFilesMap.stream().forEach(row -> {
            if(serviceFiletypesMap.containsKey(row.getKey().getServiceid())){
                Set<String> filetypes =serviceFiletypesMap.get(row.getKey().getServiceid());
                filetypes.add(row.getFileType());
                serviceFiletypesMap.put(row.getKey().getServiceid(), filetypes);
            }
            else{
                  Set<String> file=new HashSet<String>();
                  file.add(row.getFileType());
                  serviceFiletypesMap.put(row.getKey().getServiceid(), file);                  
            }
        });
    }

    public static void extensionToFileMap(List<FiletypeExtensions> filetypeExtensions) {
        Set<String> filetypeSet = new HashSet<String>();
        filetypeExtensions.stream().forEach(row -> {
            for (String extn : row.getExtensions()) {
                if (!extensionTofiletypeMap.containsKey(extn)) {
                    if (!filetypeSet.contains(row.getKey().getFiletype())) {
                        filetypeSet.add(row.getKey().getFiletype());
                    }
                    extensionTofiletypeMap.put(extn, filetypeSet);
                }
            }
        });
    }

    
    /**
     *
     * @param serviceName retrieve the id by serviceName
     * @return the service id
     */
    public static Integer getServiceId(String serviceName) {                
        return serviceReverseMap.get(serviceName);
        
    }

    /**
     *
     * @param serviceId retrieve the serviceName by id
     * @return the service Name
     */
    public static String getServiceName(Integer serviceId) {
        return serviceMap.get(serviceId);
    }

}
