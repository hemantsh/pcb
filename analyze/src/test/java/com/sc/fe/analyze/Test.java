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
			Stream<String> stream = Files.lines(Paths.get("/Users/hemant/Documents/Project/pcb/analyze/uploads/1234/8000-4890CPWIZA.GP2"))) {
	        stream.forEach( line -> {
	        	results.putAll( GerberFileProcessingUtil.processLine(line) );
	        });
		}
		
		results.entrySet().stream().forEach( e-> {
			e.toString();
		});
	}
	
}


