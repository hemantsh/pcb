package com.sc.fe.analyze.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IPC2581ProcessingUtil {

	public static final String PROPER_IPC = "PROPER_IPC";
	public static final String REVISION = "REVISION";

	public static Map<String, String> processFile(String filePath ) {
		Map<String, String> propMap = new HashMap<String, String>();
		BufferedReader reader = null;
		try {
			int i=0;
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			while (line != null && i< 20) {
				
				String temp = line.toUpperCase();
				if( temp.contains("IPC") && temp.contains("2581")) {
					propMap.put(PROPER_IPC, "Y");
					if(temp.contains(REVISION)) {
						//xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" revision="A" xmlns="http://webstds.ipc.org/2581"
						String part = line.substring(temp.indexOf(REVISION));
						String[] parts = part.split(" ");
						String value = parts[0].split("=")[1];
						value = value.replace("<","").replace(">", "");
						value = value.replace("\"","").trim();
						propMap.put(REVISION, value);
						
					}
					break;
				}
				line = reader.readLine();
				i++;
			}
			
		} catch (Exception e) {
            //logger.error(e.getMessage());
            e.printStackTrace();
        }finally {
        	try {
        		if(reader != null) {
        			reader.close();
        		}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return propMap;
	}
}
