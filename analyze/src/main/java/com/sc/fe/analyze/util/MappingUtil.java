package com.sc.fe.analyze.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
//import com.sc.fe.analyze.to.FileTypeExtensions;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sc.fe.analyze.data.entity.FiletypeExtensions;
import com.sc.fe.analyze.data.entity.ServiceFiletypes;
import com.sc.fe.analyze.data.entity.Services;

/**
 *
 * @author Hemant
 */
public class MappingUtil {

    private static Map<Integer, Set<String>> serviceFiletypesMap = new HashMap<Integer, Set<String>>();//Key is ServiceId and value is filetype.
    private static Map<Integer, String> serviceMap = new HashMap<Integer, String>();//Key is serviceId and value is Service Name.
    private static Map<String, Set<String>> extensionTofiletypeMap = new HashMap<String, Set<String>>();//Key is extension and value is filetype.

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
            if (serviceFiletypesMap.containsKey(row.getKey().getServiceid())) {
                Set<String> filetypes = serviceFiletypesMap.get(row.getKey().getServiceid());
                filetypes.add(row.getFileType());
                serviceFiletypesMap.put(row.getKey().getServiceid(), filetypes);
            } else {
                Set<String> file = new HashSet<String>();
                file.add(row.getFileType());
                serviceFiletypesMap.put(row.getKey().getServiceid(), file);
            }
        });
    }

    public static void extensionToFileMap(List<FiletypeExtensions> filetypeExtensions) {

        filetypeExtensions.stream().forEach(row -> {
            if (row.getExtensions().size() > 0) {

                for (String extn : row.getExtensions()) {
                    Set<String> filetypeSet = extensionTofiletypeMap.get(extn);
                    if (filetypeSet == null) {
                        filetypeSet = new HashSet<String>();
                    }
                    filetypeSet.add(row.getKey().getFiletype());
                    extensionTofiletypeMap.put(extn, filetypeSet);

//                if (!extensionTofiletypeMap.containsKey(extn)) {
//                    if (!filetypeSet.contains(row.getKey().getFiletype())) {
//                        filetypeSet.add(row.getKey().getFiletype());
//                    }
//                    extensionTofiletypeMap.put(extn, filetypeSet);
//                }
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

    /**
     * @return the serviceFiletypesMap
     */
    public static Map<Integer, Set<String>> getServiceFiletypesMap() {
        return serviceFiletypesMap;
    }

    /**
     * @param serviceFiletypesMap the serviceFiletypesMap to set
     */
    public static void setServiceFiletypesMap(Map<Integer, Set<String>> serviceFiletypesMap) {
        MappingUtil.serviceFiletypesMap = serviceFiletypesMap;
    }

    /**
     * @return the extensionTofiletypeMap
     */
    public static Map<String, Set<String>> getExtensionTofiletypeMap() {
        return extensionTofiletypeMap;
    }

    /**
     * @param extensionTofiletypeMap the extensionTofiletypeMap to set
     */
    public static void setExtensionTofiletypeMap(Map<String, Set<String>> extensionTofiletypeMap) {
        MappingUtil.extensionTofiletypeMap = extensionTofiletypeMap;
    }

}
