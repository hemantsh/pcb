package com.sc.fe.analyze.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sc.fe.analyze.data.entity.ExtensionFileType;
import com.sc.fe.analyze.data.entity.Extensions;
import com.sc.fe.analyze.data.entity.FileTypes;
import com.sc.fe.analyze.data.entity.ServiceFiles;
import com.sc.fe.analyze.data.entity.Services;
import com.sc.fe.analyze.data.repo.ExtensionFileRepo;
import com.sc.fe.analyze.data.repo.ExtensionsRepo;
import com.sc.fe.analyze.data.repo.FileTypesRepo;
import com.sc.fe.analyze.data.repo.ServiceFilesRepo;
import com.sc.fe.analyze.data.repo.ServicesRepo;

import com.sc.fe.analyze.util.MappingUtil;

@Service
public class BaseService {

	@Autowired
	private ServiceFilesRepo serviceFilesRepo;
	@Autowired
	private ExtensionFileRepo extnFileRepo;	
	@Autowired
	private ServicesRepo serviceRepo;
	@Autowired
	private FileTypesRepo fileTypesRepo;
	@Autowired
	private ExtensionsRepo extnRepo;
	
	@PostConstruct
	public void afterConst() {
		MappingUtil.init(getExtensionMap(), getFileTypeMap(), getServiceMap());
	}
	
	@Cacheable
	public Map<Integer, String> getExtensionMap() {
		
		Map<Integer, String> retMap = new HashMap<Integer, String>();
		
		List<Extensions> list = extnRepo.findAll();
		list.stream().forEach( e -> {
			retMap.put(e.getId(), e.getName());
		});
		
		return retMap;
	}
	
	@Cacheable
	public Map<Integer, String> getFileTypeMap() {
		
		Map<Integer, String> retMap = new HashMap<Integer, String>();
		
		List<FileTypes> list = fileTypesRepo.findAll();
		list.stream().forEach( e -> {
			retMap.put(e.getId(), e.getType());
		});
		
		return retMap;
	}
	
	@Cacheable
	public Map<Integer, String> getServiceMap() {
		
		Map<Integer, String> retMap = new HashMap<Integer, String>();
		
		List<Services> list = serviceRepo.findAll();
		list.stream().forEach( e -> {
			retMap.put(e.getId(), e.getName());
		});
		
		return retMap;
	}
	
	
	
	@Cacheable
	public List<String> getServiceFiles(int serviceId) {
		
		List<ServiceFiles> serviceFiles = serviceFilesRepo.findByKeyServiceId(serviceId);
		return serviceFiles.stream().map(ServiceFiles::getFile).collect(Collectors.toList());
	}
	
	@Cacheable
	public Map<String, String> getExtensionToFileMapping() {
		
		Map<String, String> returnMap = new HashMap<String, String>();
		List<ExtensionFileType> allList = extnFileRepo.findAll();
		allList.stream().forEach( e -> {
			returnMap.put(e.getExtension(), e.getFile());
		});
		
		return returnMap;
	}

	public ServiceFilesRepo getServiceFilesRepo() {
		return serviceFilesRepo;
	}

	public void setServiceFilesRepo(ServiceFilesRepo serviceFilesRepo) {
		this.serviceFilesRepo = serviceFilesRepo;
	}

	public ExtensionFileRepo getExtRepo() {
		return extnFileRepo;
	}

	public void setExtRepo(ExtensionFileRepo extRepo) {
		this.extnFileRepo = extRepo;
	}

	public ExtensionFileRepo getExtnFileRepo() {
		return extnFileRepo;
	}

	public void setExtnFileRepo(ExtensionFileRepo extnFileRepo) {
		this.extnFileRepo = extnFileRepo;
	}

	public ServicesRepo getServiceRepo() {
		return serviceRepo;
	}

	public void setServiceRepo(ServicesRepo serviceRepo) {
		this.serviceRepo = serviceRepo;
	}

	public FileTypesRepo getFileTypesRepo() {
		return fileTypesRepo;
	}

	public void setFileTypesRepo(FileTypesRepo fileTypesRepo) {
		this.fileTypesRepo = fileTypesRepo;
	}

	public ExtensionsRepo getExtnRepo() {
		return extnRepo;
	}

	public void setExtnRepo(ExtensionsRepo extnRepo) {
		this.extnRepo = extnRepo;
	}

	
}
