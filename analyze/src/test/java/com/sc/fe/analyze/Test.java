package com.sc.fe.analyze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
//              Arrays.asList( s.toLowerCase().split("or") ).stream().forEach( e -> {
//			val.add(e.trim());                        
//		});
        //DESCRIPTION --------
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
        
        //Required FileType is ---
        List<String> requiredTypes = new ArrayList<String>();
        requiredTypes.add("Signal");
        requiredTypes.add("Gerber or ODB");
        requiredTypes.add("Drill");

        //Available FileType is --
        List<String> availableTypes = new ArrayList<String>();
        availableTypes.add("silk_screen");
        availableTypes.add("Drill");

        //Available Format is --                
        String format = "ger";     //I tried with different formats - test123,ger,odb ,etc
        String fformat = format.substring(0, 1).toUpperCase() + format.substring(1, format.length()).toLowerCase();

        //Print the all values                
        System.out.println("Required FileTypes - " + requiredTypes);
        System.out.println("Available FileTypes - " + availableTypes);
        System.out.println("Available Format - " + fformat);

        //Step:1 - Remove all the avaiable fileTypes from requiredTypes
        requiredTypes.removeAll(availableTypes);

        //Step:2 - Process the avaiable format
        //It checks that in the required FileTypes ,the format(which user gives) it contains
        //or not,if it(the format) contains as a sub-part of the type,it also handles and stored in the availFormat.
        List<String> availFormat = requiredTypes.stream()
                .filter(str -> str.trim().contains(fformat))
                .collect(Collectors.toList());

        System.out.println("Available full format found in the required FileTypes- " + availFormat);
        //Step:2 - Remove all avaiable format from requiredTypes
        requiredTypes.removeAll(availFormat);
        //After removing all available FileType and available Format from requiredType is
        System.out.println("Required Type after removing Available Format and Available File Types - " + requiredTypes);
    }
}
