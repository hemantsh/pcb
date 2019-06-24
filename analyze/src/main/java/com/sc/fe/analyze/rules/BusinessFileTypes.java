package com.sc.fe.analyze.rules;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Hemant
 */
public class BusinessFileTypes {

	public static List<String> getAllBusinessFileTypes() {
		
		List<String> businessFileTypes = new ArrayList<String>();
		businessFileTypes.add("Top Silkscreen");
		businessFileTypes.add("Top Soldermask");
		businessFileTypes.add("Top Copper");
		businessFileTypes.add("Bottom Copper");
		businessFileTypes.add("Bottom Soldermask");
		businessFileTypes.add("Bottom Silkscreen");
		businessFileTypes.add("NC Drill File");
		businessFileTypes.add("Board Outline");
		businessFileTypes.add("GND Plane");
		businessFileTypes.add("V3P3 Plane");
		businessFileTypes.add("Fab Drawing");
		businessFileTypes.add("Readme");
		
		return businessFileTypes;
	}
}
