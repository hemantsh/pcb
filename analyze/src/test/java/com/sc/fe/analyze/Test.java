package com.sc.fe.analyze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class Test {

	public static void main(String[] args) throws Exception {
		
//		Map<String, String> results = new HashMap<String, String>();
//		try (
//			Stream<String> stream = Files.lines(Paths.get("/Users/hemant/Documents/Project/pcb/analyze/uploads/1234/8000-4890CPWIZA.GP2"))) {
//	        stream.forEach( line -> {
//	        	results.putAll( GerberFileProcessingUtil.processLine(line) );
//	        });
//		}
//		
//		results.entrySet().stream().forEach( e-> {
//			e.toString();
//		});
		
//		Pattern p = Pattern.compile("^.*top.*serial.*$");
//		System.out.println( Integer.parseInt( "a1b1".replaceAll("[^0-9]+", "")) );
		
		String s = "Gerber or ODB";
		List<String> val = new ArrayList<String>();
		Arrays.asList( s.toLowerCase().split("or") ).stream().forEach( e -> {
			val.add(e.trim());
		} );
		System.out.println(val.contains("Gerber".toLowerCase()));
		
		System.out.println(val.contains("ger"));
		System.out.println(val.contains("odb".toLowerCase()));
		
		/*Required types = signal, gerber or odb, drill
		
		availableType = solder_mask, drill
		availableFormat = gerber
		
		Stept 1- Remove all avaiable fileTypes from requiredTypes
		 - RequiredType = signal, gerber or odb
		Step 2 - Remove all availableFormat from requiredTypes
		 - RequiredType = signal, gerber or odb
		Step 3 -
		loop through each availableFormat {
		 - */
		
		
	}
	
}


