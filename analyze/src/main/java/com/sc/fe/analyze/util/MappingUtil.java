package com.sc.fe.analyze.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Hemant
 */
public class MappingUtil {

    private static Map<Integer, String> extensionMap;
    private static Map<Integer, String> fileTypeMap;
    private static Map<Integer, String> serviceMap;

    private static Map<String, Integer> extensionReverseMap;
    private static Map<String, Integer> fileTypeReverseMap;
    private static Map<String, Integer> serviceReverseMap;

    private MappingUtil() {
    }

    ;
	
    /**
     *
     * @param p_extensionMap initialize the extensionMap
     * @param p_fileTypeMap  initialize the fileTypeMap
     * @param p_serviceMap initialize the serviceMap
     */
    public static void init(Map<Integer, String> p_extensionMap,
            Map<Integer, String> p_fileTypeMap,
            Map<Integer, String> p_serviceMap) {

        extensionMap = p_extensionMap;
        fileTypeMap = p_fileTypeMap;
        serviceMap = p_serviceMap;

        extensionReverseMap = new HashMap<String, Integer>(extensionMap.size());

        Iterator<Integer> iKeys = extensionMap.keySet().iterator();

        while (iKeys.hasNext()) {
            Integer key = iKeys.next();
            extensionReverseMap.put(extensionMap.get(key), key);
        }

        fileTypeReverseMap = new HashMap<String, Integer>(fileTypeMap.size());
        iKeys = fileTypeMap.keySet().iterator();
        while (iKeys.hasNext()) {
            Integer key = iKeys.next();
            fileTypeReverseMap.put(fileTypeMap.get(key), key);
        }

        serviceReverseMap = new HashMap<String, Integer>(serviceMap.size());
        iKeys = serviceMap.keySet().iterator();
        while (iKeys.hasNext()) {
            Integer key = iKeys.next();
            serviceReverseMap.put(serviceMap.get(key), key);
        }

    }

    /**
     *
     * @param extension retrieve the id by extension
     * @return the extension id
     */
    public static Integer getExtensionId(String extension) {
        return extensionReverseMap.get(extension);
    }

    /**
     *
     * @param extId retrieve the extension by id
     * @return the extension
     */
    public static String getExtension(Integer extId) {
        return extensionMap.get(extId);
    }

    /**
     *
     * @param fileType retrieve the id by fileType
     * @return the fileTypeId
     */
    public static Integer getFileTypeId(String fileType) {
        return fileTypeReverseMap.get(fileType);
    }

    /**
     *
     * @param fileId retrieve the fileTypeby id
     * @return the fileType
     */
    public static String getFileType(Integer fileId) {
        return fileTypeMap.get(fileId);
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
