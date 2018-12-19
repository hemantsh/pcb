/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extensionfiles;

import java.util.HashMap;

/**
 *
 * @author pc
 */
public class GerberFileSpecificationUnit {

    public static HashMap<String, String> processLine(String line) {
        HashMap<String, String> attributes = new HashMap<String, String>();

        if(line.startsWith("G04")==true){
            attributes.putAll(processG04(line));
        }
        else if(line.startsWith("%MO")==true){
            attributes.putAll(processMO(line));
        }
        else if(line.startsWith("%TO")==true){
              attributes.putAll(processTO(line));
        }
        else if (line.startsWith("%TF") == true) {
            attributes.putAll(processTF(line));
        }
        
        else if (line.startsWith("%TA")==true){
            attributes.putAll(processTA(line));
        }
        else if (line.startsWith("%FSLA")==true){
            attributes.putAll(processFSLA(line));
            for(String p:attributes.keySet()){
            System.out.println("Key is "+p+"Value is "+attributes.get(p));
            
        }
        }
        return null;
    }

    //Below Code will process the line that has G04   
    private static HashMap<String, String> processG04(String line) {
        HashMap<String, String> returnMap = new HashMap<String, String>();
        if (line.startsWith("G04 ") == true) {
            line = line.replace("G04", "").replace("*", "");
            String[] splitValue = line.split("=");
            returnMap.put(splitValue[0], splitValue[1]);
            /*This IF part will execute only if it has a G04 in the start 
                    of the line otherwise else part will execute
             */
        } else {
            /*This else part will only execute if line has G04: in the line and splits the line through pipe " | " 
      and further splits the line through " = " and stores into an array and put into a hashmap.
             */
            String[] splitedValue = line.split("[|]");
            for (int i = 0; i < splitedValue.length; i++) {
                String currentValue = splitedValue[i];
                if (currentValue.contains(":") || currentValue.contains("*")) {
                    continue;
                }
                String[] temp = currentValue.split("=");
                returnMap.put(temp[0], temp[1]);
            }
        }

        return returnMap;
    }

    /*Below method will be call if line starts with %MO with the possiblity of 
        two values i.e IN or MM so we have hard coded it because of limited posiblities.
     */
    private static HashMap<String, String> processMO(String line) {
        HashMap<String, String> returnMap = new HashMap<String, String>();

        if (line.startsWith("%MOIN") == true) {
            returnMap.put("MO", "IN");
        } else {
            returnMap.put("MO", "MM");
        }
        return returnMap;
    }

    /*Below method will be call if line starts with %TO 
   so we will replaced the string with empty string that we don't want which makes the
   splitting process more easier and then put the key value pair into hashmap.
     */
    private static HashMap<String, String> processTO(String line) {
        HashMap<String, String> returnMap = new HashMap<String, String>();
        try {
            line = line.replace("%TO.", "").replace("*%", "");
            String[] splitValue = line.split(",");
            returnMap.put(splitValue[0], splitValue[1]);
            // ERROR ERROR ERROR Here i am facing NullPointerException Error...... ERROR ERROR ERROR 
            System.out.println("Size is:" + returnMap.size());
        } catch (Exception e) {
            System.out.println(e + "I am in catch ");
        }

        return returnMap;
    }

    /*
   Below method will be call if line starts with %TF 
   so we will replaced the string with empty string that we don't want which makes the
   splitting process more easier and then put the key value pair into hashmap.
     */
    private static HashMap<String, String> processTF(String line) {
        HashMap<String, String> returnMap = new HashMap<String, String>();

        //If block will exetues if it is a reserved attribute name otherwise else block will execute
        if (line.startsWith("%TF.") == true) {

            line = line.replace("%TF.", "").replace("*%", "");
            String[] splitValue = line.split(",", 2);
            System.out.println(splitValue[0] + splitValue[1]);
            returnMap.put(splitValue[0], splitValue[1]);
        } else {
            /*If the block contains " , " in the line then IF block will gets executed otherwise
            *else will gets executed and w'll send empty string hardcoded in the hashmap.
            */
            if (line.contains(",") == true) {
                line = line.replace("%TF", "").replace("*%", "");
                String[] splitValue = line.split(",", 2);
                //System.out.println(splitValue[0]+"");
                returnMap.put(splitValue[0], splitValue[1]);
            }
            else{
                line = line.replace("%TF", "").replace("*%", "");
                System.out.println(line+"");
                returnMap.put(line, "");
            }

        }

        return returnMap;
    }
    /*
   Below method will be call if line starts with %TA 
   so we will replaced the string with empty string that we don't want which makes the
   splitting process more easier and then put the key value pair into hashmap.
     */
    private static HashMap<String,String>processTA(String line){
        HashMap<String, String> returnMap = new HashMap<String, String>();
        //If block will exetues if it is a reserved attribute name otherwise else block will execute
        if (line.startsWith("%TA.") == true) {

            line = line.replace("%TA.", "").replace("*%", "");
            String[] splitValue = line.split(",", 2);
            System.out.println(splitValue[0] +" = "+splitValue[1]);
             // ERROR ERROR ERROR Here i am facing NullPointerException Error...... ERROR ERROR ERROR 
            returnMap.put(splitValue[0], splitValue[1]);
        } else {
            /*If the block contains " , " in the line then IF block will gets executed otherwise
            *else will gets executed and w'll send empty string hardcoded in the hashmap.
            */
            if (line.contains(",") == true) {
                line = line.replace("%TA", "").replace("*%", "");
                String[] splitValue = line.split(",", 2);
                //System.out.println(splitValue[0]+"");
                returnMap.put(splitValue[0], splitValue[1]);
            }
            else{
                line = line.replace("%TA", "").replace("*%", "");
                //String[] splitValue = line.split(",", 2);
                System.out.println(line+"");
                returnMap.put(line, "");
            }

        }
        return returnMap;
    }
    /*
        This code will execute if line starts with %FSLA
    */
    private static HashMap<String,String>processFSLA(String line){
        HashMap<String, String> returnMap = new HashMap<String, String>();
        int x,y,star;
        String Xvalue,Yvalue;
        System.out.println(line);
        x=line.indexOf("X");//here we'll store the index of x
        y=line.indexOf("Y");//here we'll store the index of y
        star=line.indexOf("*");//here we'll store the index of *
        /*In below code we'll store the substring into two different variables which needs to be put 
        into the hashmap with the help of x and y index that we have extracted above.*/
        Xvalue=line.substring(x+1,y);
        Yvalue=line.substring(y+1,star);
        //Below lines will put the x value and y value into the hashmap along with there keys.
        returnMap.put("X",Xvalue);
        returnMap.put("Y", Yvalue);
        
        return returnMap;
    }
    

}
