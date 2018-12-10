package com.sc.fe.analyze.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IFileOperations {

	public String storeFile(String projectId, MultipartFile file) throws IOException;
	public void extractZipFiles(String projectId, String fileName);
	public List<String> listFiles(String projectId);
	public void zipFiles(String projectId, String fileName);
	
}
