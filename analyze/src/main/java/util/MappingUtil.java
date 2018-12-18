package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MappingUtil {
	
	private static Map<Integer, String> extensionMap;
	private static Map<Integer, String> fileTypeMap;
	private static Map<Integer, String> serviceMap;
	
	private static Map<String, Integer> extensionReverseMap;
	private static Map<String, Integer> fileTypeReverseMap;
	private static Map<String, Integer> serviceReverseMap;
	

	private MappingUtil() {};
	
	public static void init(Map<Integer, String> p_extensionMap, 
			Map<Integer, String> p_fileTypeMap,
			Map<Integer, String> p_serviceMap) {
		
		extensionMap = p_extensionMap;
		fileTypeMap =  p_fileTypeMap;
		serviceMap = p_serviceMap;
		
		extensionReverseMap = new HashMap<String, Integer>(extensionMap.size());
		
		Iterator<Integer> iKeys = extensionMap.keySet().iterator();
		
		while(iKeys.hasNext()) {
			Integer key = iKeys.next();
			extensionReverseMap.put(extensionMap.get(key), key);
		}
		
		fileTypeReverseMap = new HashMap<String, Integer>(fileTypeMap.size());
		iKeys = fileTypeMap.keySet().iterator();		
		while(iKeys.hasNext()) {
			Integer key = iKeys.next();
			fileTypeReverseMap.put(fileTypeMap.get(key), key);
		}
		
		serviceReverseMap = new HashMap<String, Integer>(serviceMap.size());
		iKeys = serviceMap.keySet().iterator();		
		while(iKeys.hasNext()) {
			Integer key = iKeys.next();
			serviceReverseMap.put(serviceMap.get(key), key);
		}
		
	}
	
	public static Integer getExtensionId(String extension) {
		return extensionReverseMap.get(extension);
	}
	
	public static String getExtension(Integer extId) {
		return extensionMap.get(extId);
	}
	
	public static Integer getFileTypeId(String fileType) {
		return fileTypeReverseMap.get(fileType);
	}
	
	public static String getFileType(Integer fileId) {
		return fileTypeMap.get(fileId);
	}
	
	public static Integer getServiceId(String serviceName) {
		return serviceReverseMap.get(serviceName);
	}
	
	public static String getServiceName(Integer serviceId) {
		return serviceMap.get(serviceId);
	}
	
}
