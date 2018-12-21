package com.sc.fe.analyze;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.sc.fe.analyze.util.GerberFileProcessingUtil;


public class Test {

	public static void main(String[] args) throws Exception {
		
		Map<String, String> results = new HashMap<String, String>();
		try (
			Stream<String> stream = Files.lines(Paths.get("/Users/hemant/8000-4890CPWIZA.GBL"))) {
	        stream.forEach( line -> {
	        	results.putAll( GerberFileProcessingUtil.processLine(line) );
	        });
		}
		
		
		results.entrySet().stream().forEach( e-> {
			e.toString();
		});
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


