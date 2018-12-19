/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gerberfilespec.format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author pc
 */
public class GerberFileSpecificationUnit {
    public static String[] GValue=new String[2];
    public static HashMap<String,String> attributes=new HashMap<String,String>();
    public static HashMap<String,String> processLine(String line){
        
         
        if(line.startsWith("G04")==true){
            processG04(line);
            attributes.put(GValue[0],GValue[1]);
        }
                return null;
    }
    
    private static HashMap<String,String>processG04(String line){
       /* String key = null, value = null;
            int len;
        if(line.startsWith("G04 ")==true){
         StringTokenizer st1 = new StringTokenizer(line, "=");
        while (st1.hasMoreTokens()) {
            value = st1.nextToken();
            if (value.contains("G04 ") == true) {
                value = value.substring(4);
                key = value;
            } else {
                len = value.length();
                value = value.substring(0, len - 1);

            }
        }System.out.println(key+"="+value);
        //attributes.put(key, value);
        }else{
            //String data = line.substring(0, 4);
            int str=line.indexOf("*");
            line=line.substring(12, str);
            len = line.length();
            System.out.println(line);      
                    if (line.contains("|") == true) {
                    String[] temp = line.split("\\|");
                        for (String p : temp) {
                            String[] temp2 = p.split("\\=");
                            if (temp2.length == 2) {
                                GValue[0]=temp2[0]; GValue[1]=temp2[1];
                                System.out.println(GValue[0]+"="+GValue[1]);
                            }
                        }
                    }
        }
   */
       //Start
   //String part = line[i];
    //console.log("Line", line);
    String[] value;
    
    //System.out.println("Line"+line);
    if(line.startsWith("G04 ")==true){
        value=line.replace("G04"," ").replace("*","").split("=");
    	//String value = line.replace("G04 ", "").replace("*", "").split("=");
        System.out.println("Key=" + value[0] + " Value=" + value[1]);
    }else{
    	 value = line.replace("G04:AMPARAMS|", "").replace("*","").split("|");
        HashMap<String,String> returnValue =new HashMap<String,String>();
       // console.log("Value", value);
        for(int j=0; j<value.length-1; j++){
        	String[] param = value[j].split("=");
                System.out.println(param);
              //  System.out.println(param[1]);
                  //  System.out.println( returnValue.put(param[0],param[1]));
                
                
        }
        //console.log(returnValue);
    }

       //End
       return null;
    }
    
   private static HashMap<String,String> processMO(String line){
    
        int len=line.length();
        if(line.startsWith("%MOIN")==true){
            return attributes.put("MO", "IN");
        }
        return null;
    }
     
}
