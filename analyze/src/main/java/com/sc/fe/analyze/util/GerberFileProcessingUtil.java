package com.sc.fe.analyze.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GerberFileProcessingUtil {
    
	public static HashMap<String, String> processLine(String line) {
        HashMap<String, String> attributes = new HashMap<>();


        if(line.startsWith("G04")){
            attributes.putAll(processG04(line));
        }
        else if(line.startsWith("%MO")){
            attributes.putAll(processMO(line));
        }
        else if(line.startsWith("%TO")){
              attributes.putAll(processTO(line));
        }
        else if (line.startsWith("%TF")) {
            attributes.putAll(processTF(line));
        }
        else if (line.startsWith("%TA")){
            attributes.putAll(processTA(line));
        }
		    else if (line.startsWith("%FSLA")){
            attributes.putAll(processFSLA(line));
        }
        else  if(line.startsWith("%TD")){
            attributes.putAll(processTD(line));
        }
        else if(line.startsWith("%LP")){
            attributes.putAll(processLP(line));
        }
        else if(line.startsWith("%LM")){
            attributes.putAll(processLM(line));
        }
        else if(line.startsWith("%LR")){    
            attributes.putAll(processLR(line));
        }
        else if(line.startsWith("%LS")){
            attributes.putAll(processLS(line));
        }
        attributes.keySet().forEach((p) -> {
            System.out.println(p+" = "+attributes.get(p));
        });
        return attributes;
    }
    
    //Below Code will process the line that has G04   
    private static HashMap<String, String> processG04(String line) {
        HashMap<String, String> returnMap = new HashMap<String, String>();
        String keyQualifier = null;
        String[] splitedValue = line.split("[|]");
        for (String currentValue : splitedValue) {
            if ( ! currentValue.contains("=") ) {
                continue;
            }
            String[] temp = currentValue.split("=");
            
            if("dcode".equals(temp[0].toLowerCase()) ) {
            	keyQualifier = "D"+temp[1]+".";
            	continue;
            }
            if(temp[0].startsWith("G04") ) {
            	temp[0] = temp[0].replace("G04", "").trim();
            }
            if(temp[0].toLowerCase().contains("order")) {
            	returnMap.put("Layer", temp[1].replace("*", ""));
            }
            returnMap.put(temp[0], temp[1].replace("*", ""));
        }
        
        if( keyQualifier != null ) {
        	HashMap<String, String> tempMap = (HashMap<String, String>)returnMap.clone();
        	Iterator<String> keyItr = returnMap.keySet().iterator();
        	while( keyItr.hasNext() ) {  
        		String key = keyItr.next();
        		tempMap.put(keyQualifier+key, tempMap.remove(key));
        	}
        	returnMap = tempMap;
        }

        return returnMap;
    }

    //Below method will be call if line starts with %MO  
    private static HashMap<String, String> processMO(String line) {
        HashMap<String, String> returnMap = new HashMap<>();

        line=line.replace("%MO", "").replace("*%", "").trim();
        returnMap.put("MO", line);
		
        return returnMap;
    }

    //Below method will be call if line starts with %TO 
    private static HashMap<String, String> processTO(String line) {
        HashMap<String, String> returnMap = new HashMap<>();
        //If block will exetues if it is a reserved attribute name otherwise else block will execute
        if (line.startsWith("%TO.") == true) {
            if(line.contains(",")==true){
            line = line.replace("%TO.", "").replace("*%", "");
            String[] splitValue = line.split(",", 2);
            returnMap.put(splitValue[0], splitValue[1]);
            }
            else{
                line=line.replace("%TO.", "").replace("*%", "");
                returnMap.put(line, "");
            }
            
        } else {
            /*If the block contains " , " in the line then IF block will gets executed otherwise
            *else will gets executed and w'll send empty string hardcoded in the hashmap.
            */
            if (line.contains(",") == true) {
                line = line.replace("%TO", "").replace("*%", "");
                String[] splitValue = line.split(",", 2);
                returnMap.put(splitValue[0], splitValue[1]);
            }
            else{
                line = line.replace("%TO", "").replace("*%", "");
                returnMap.put(line, "");
            }

        }

        return returnMap;
    }

    
   //Below method will be call if line starts with %TF 
    private static HashMap<String, String> processTF(String line) {
        HashMap<String, String> returnMap = new HashMap<>();

        //If block will exetues if it is a reserved attribute name otherwise else block will execute
        if (line.startsWith("%TF.") == true) {
            if(line.contains(",")==true){
            line = line.replace("%TF.", "").replace("*%", "");
            String[] splitValue = line.split(",", 2);
            returnMap.put(splitValue[0], splitValue[1]);
            }
            else{
                line=line.replace("%TF.", "").replace("*%", "");
                returnMap.put(line, "");
            }
            
        } else {
            /*If the block contains " , " in the line then IF block will gets executed otherwise
            *else will gets executed and w'll send empty string hardcoded in the hashmap.
            */
            if (line.contains(",") == true) {
                line = line.replace("%TF", "").replace("*%", "");
                String[] splitValue = line.split(",", 2);
                returnMap.put(splitValue[0], splitValue[1]);
            }
            else{
                line = line.replace("%TF", "").replace("*%", "");
                returnMap.put(line, "");
            }

        }

        return returnMap;
    }
    //Below method will be call if line starts with %TA 
   
    private static HashMap<String,String>processTA(String line){
        HashMap<String, String> returnMap = new HashMap<>();
        //If block will exetues if it is a reserved attribute name otherwise else block will execute
        if (line.startsWith("%TA.") == true) {

            line = line.replace("%TA.", "").replace("*%", "");
            String[] splitValue = line.split(",", 2);
            returnMap.put(splitValue[0], splitValue[1]);
        } else {
            /*If the block contains " , " in the line then IF block will gets executed otherwise
            *else will gets executed and w'll send empty string hardcoded in the hashmap.
            */
            if (line.contains(",") == true) {
                line = line.replace("%TA", "").replace("*%", "");
                String[] splitValue = line.split(",", 2);
                returnMap.put(splitValue[0], splitValue[1]);
            }
            else{
                line = line.replace("%TA", "").replace("*%", "");
                returnMap.put(line, "");
            }

        }
        return returnMap;
    }
    /*
        This code will execute if line starts with %FSLA
    */
    private static HashMap<String,String>processFSLA(String line){
        HashMap<String, String> returnMap = new HashMap<>();
        int x,y,star;
        String Xvalue,Yvalue;
        x=line.indexOf("X");
        y=line.indexOf("Y");
        star=line.indexOf("*");
        /*In below code we'll store the substring into two different variables which needs to be put 
        into the hashmap with the help of x and y index that we have extracted above.*/
        Xvalue=line.substring(x+1,y);
        Yvalue=line.substring(y+1,star);
        //Below lines will put the x value and y value into the hashmap along with there keys.
        returnMap.put("X",Xvalue);
        returnMap.put("Y", Yvalue);
        
        return returnMap;
    }

    private static HashMap<String,String>processTD(String line){
         HashMap<String,String> returnMap=new HashMap<>();
         line=line.replace("%TD.", "").replace("*%", "").trim();
         returnMap.put(line, "");
        return returnMap;
    }
    private static HashMap<String,String>processLP(String line){
    HashMap<String,String> returnMap=new HashMap<>();
    line=line.replace("%LP","").replace("*%", "").trim();
    returnMap.put("LP", line);
        return returnMap;
    }
    private static HashMap<String,String>processLM(String line){
    HashMap<String,String> returnMap=new HashMap<>();
    line=line.replace("%LM","").replace("*%", "").trim();
    returnMap.put("LM", line);
        return returnMap;
    }
    private static HashMap<String,String>processLR(String line){
        HashMap<String,String> returnMap=new HashMap<>();
        line=line.replace("%LR","").replace("*%", "").trim();
        returnMap.put("LR",line);
        return returnMap;
    }
    private static HashMap<String,String>processLS(String line){
        HashMap<String,String> returnMap=new HashMap<>();
        line=line.replace("%LS", "").replace("*%", "").trim();
        returnMap.put("LS",line);
        return returnMap;
    }
    
    public static String processM48(String line, Map<String,String> results, String currentKey) {
    	
    	HashMap<String,String> returnMap = new HashMap<String,String>();
    	
    	if(line.startsWith("%") || line.startsWith("M95") ) {
    		return currentKey;
    	}
    	
    	if(line.startsWith(";")) {     
    		//End of old key and start of new attribute
    		String key = "";
    		String value = "";
    		String[] splitValues = null;
    		
    		if(line.contains("=")) {
    			splitValues = line.split("=");
    		}
    		
    		if(line.startsWith(";TYPE") ) {
    			
    			key = splitValues[1];
    			 
    		}else {
    			key = splitValues[0].replace(";", "");
    			value = splitValues[1];
    		}
    		currentKey = key;
    		returnMap.put(key, value);
        }
    	else {
        	//Line is continuation of previous value so we append the value for current key
    		String currVal = returnMap.get(currentKey);
    		if( currVal == null || currVal.equals("")) {
    			returnMap.put(currentKey,  line);
    		}else {
    			returnMap.put(currentKey, currVal + "," + line);
    		}
        }
    	
    	return currentKey;
    }
}