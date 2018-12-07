package com.sc.fe.analyze;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/**
	     * save file to temp
	     */
		
		String zipFilePath = "/Users/hemant/files/zip/test.zip";
		
	    File zip = File.createTempFile(UUID.randomUUID().toString(), "temp");
	    FileOutputStream o = new FileOutputStream(zip);
	    
	    IOUtils.copy(new FileInputStream(zipFilePath), o);
	    o.close();

	    /**
	     * unizp file from temp by zip4j
	     */
	    String destination = "/Users/hemant/files/pcb";
	    try {
	    	//Extract files
	         ZipFile zipFile = new ZipFile(zip);
	         zipFile.extractAll(destination);
	         
	         //
	         Map<String, String> extensionToTypeMapping = getFileTypeExtensionMapping();
	         
	         //List extracted files
	         try (Stream<Path> paths = Files.walk(Paths.get(destination))) {
	        	    paths
	        	        .filter(Files::isRegularFile)
	        	        .forEach( file -> {
	        	        	String[] nameParts = file.getFileName().toString().split("\\.");
	        	        	if(extensionToTypeMapping.containsKey(nameParts[nameParts.length-1].toLowerCase()) ) {
	        	        		System.out.println("\n===== "+ extensionToTypeMapping.get( nameParts[nameParts.length-1].toLowerCase() ) + " - "+ file.getFileName() +"\n" );
	        	        	}else {
	        	        		System.out.println(file );
	        	        	}
	        	        	
	        	        }); //System.out::println
	         } 
	         
	    } catch (ZipException e) {
	        e.printStackTrace();
	    }
	    
	}
	
	public static Map<String, String> getFileTypeExtensionMapping() {

		Map<String, String> extensionToTypeMapping = new HashMap <String, String>();
		extensionToTypeMapping.put("gto", "Top Silkscreen");
		extensionToTypeMapping.put("sst", "Top Silkscreen");
		extensionToTypeMapping.put("plc", "Top Silkscreen");
		
		extensionToTypeMapping.put("smt", "Top Soldermask");
		extensionToTypeMapping.put("stc", "Top Soldermask");
		extensionToTypeMapping.put("gts", "Top Soldermask");
		
		extensionToTypeMapping.put("gtl", "Top Copper");
		extensionToTypeMapping.put("top", "Top Copper");
		extensionToTypeMapping.put("cmp", "Top Copper");
		
		extensionToTypeMapping.put("gbl", "Bottom Copper");
		extensionToTypeMapping.put("bot", "Bottom Copper");
		extensionToTypeMapping.put("sol", "Bottom Copper");
		
		extensionToTypeMapping.put("gbs", "Bottom Soldermask");
		extensionToTypeMapping.put("smb", "Bottom Soldermask");
		extensionToTypeMapping.put("sts", "Bottom Soldermask");
		
		extensionToTypeMapping.put("gbo", "Bottom Silkscreen");
		extensionToTypeMapping.put("ssb", "Bottom Silkscreen");
		extensionToTypeMapping.put("pls", "Bottom Silkscreen");
		
		extensionToTypeMapping.put("txt", "NC Drill File");
		extensionToTypeMapping.put("thruhole.tap", "NC Drill File");
		extensionToTypeMapping.put("drd", "NC Drill File");
		
		extensionToTypeMapping.put("gm1,gko", "Board Outline");
		extensionToTypeMapping.put("gko", "Board Outline");
		
		extensionToTypeMapping.put("gp1", "GND Plane");
		extensionToTypeMapping.put("g1", "GND Plane");
		
		extensionToTypeMapping.put("gp2", "V3P3 Plane");                
		
		extensionToTypeMapping.put("pdf", "Fab Drawing");
		//extensionToTypeMapping.put("txt", "Readme");
		return extensionToTypeMapping;
	}
}


