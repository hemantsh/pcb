package com.sc.fe.analyze.util;

import java.util.HashMap;
import java.util.Map;

public class GerberFileProcessingUtil {

//	G04 Layer_Physical_Order=4*
//	G04 Layer_Color=16711680*
			
//  G04:AMPARAMS|DCode=50|XSize=66mil|YSize=66mil|CornerRadius=0mil|HoleSize=0mil|Usage=FLASHONLY|Rotation=0.000|XOffset=0mil|YOffset=0mil|HoleType=Round|Shape=Octagon|*
//	%TF.FilePolarity,Negative*%
//	%TF.FileFunction,Copper,L1,Top*%
//	%TA.AperFunction,ComponentPad*%
//	%TAMyApertureAttributeWithValue,value*%
//	%TAMyApertureAttributeWithoutValue*%
//	%MOIN*%
	// %FSLAX2523Y25*%
	public static Map<String, String>  processLine(String line) {
		HashMap<String, String> attributes = new HashMap<String, String>();
		if(line.startsWith("G04")) {
			attributes.putAll(processG04(line));
		}
		return attributes;
	}
	
	private static HashMap<String, String> processG04(String line) {
		
		HashMap<String, String> returnMap = new HashMap<String, String>();
		String[] splitedValue = line.split("[|]");
		for(int i=0; i< splitedValue.length; i++) {
			String currentValue = splitedValue[i];
			if(currentValue.contains(":") || currentValue.contains("*")) {
				continue;
			}
			String[] temp = currentValue.split("=");
			returnMap.put(temp[0], temp[1]);
		}
		return returnMap;
	}
	
//	private static HashMap<String, String> processMO() {
//		return null;
//	}
	
}
