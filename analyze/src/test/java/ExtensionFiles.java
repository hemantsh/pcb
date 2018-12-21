///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package extensionfiles;
//
//import java.io.BufferedReader;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.StringTokenizer;
//
///**
// *
// * @author pc
// */
//public class ExtensionFiles {
//
//    public static HashMap<String, String> attributes = new HashMap<String, String>();
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void setG04(String line) {
//        String key = null, value = null;
//        int len;
//        //HashMap<String,String>attributes=new HashMap<String,String>();
//        if(line.startsWith("G04")==true){
//        StringTokenizer st1 = new StringTokenizer(line, "=");
//        while (st1.hasMoreTokens()) {
//            value = st1.nextToken();
//            if (value.contains("G04") == true) {
//                value = value.substring(4);
//                key = value;
//            } else {
//                len = value.length();
//                value = value.substring(0, len - 1);
//
//            }
//        }System.out.println(key+"="+value);
//        //attributes.put(key, value);
//        }else{
//            String data = line.substring(0, 4);
//              len = line.length();
//               if (data.contains("G04:")){
//                    if (line.contains("|") == true) {
//                    String[] temp = line.split("\\|");
//                        for (String p : temp) {
//                            String[] temp2 = p.split("\\=");
//                            if (temp2.length == 2) {
//                                attributes.put(temp2[0], temp2[1]);
//                            }
//                        }
//                           //System.out.println(attributes.get("DCode"));
//
//                    }
//            /*if(line.contains("G04:")==true){
//                key=line.split*/
//            }
//        } 
//            
//    }
//
//    public static void getG04() {
//        for (String i : attributes.keySet()) {
//            System.out.println(i + "=" + attributes.get(i));
//        }
//    }
//
//    public static void setMO(String line) {
//        int len;
//        len = line.length();
//        if (line.contains("MOIN") == true) {
//            System.out.println("MO="+line.substring(3,len-2));
//            //attributes.put("MO", line.substring(3, len - 2));
//        }else {
//            System.out.println("MO="+line.substring(3,len-2));
//            //attributes.put("MO", line.substring(3, len - 2));
//        }
//    }
//
//    public static void getMO() {
//        for (String i : attributes.keySet()) {
//            System.out.println(i + "=" + attributes.get(i));
//        }
//    }
//
//    public static void setTF(String line) {
//        String key = null, value = null;
//        int len;
//
//        String[] data = line.split(",", 2);
//        key = data[0].substring(4);
//        len = data[1].length();
//        value = data[1].substring(0, len - 2);
//        //attributes.put(key, value);
//        
//        System.out.println(key+"="+value);
//        	
//    }
//
//    public static void getTF() {
//        for (String i : attributes.keySet()) {
//            System.out.println(i + "=" + attributes.get(i));
//        }
//    }
//
//    public static void setTA(String line) {
//        String key = null, value = null;
//        String[] data;
//        int len;
//        if (line.contains("%TA.") == true) {
//            if (line.contains(",") == true) {
//                data = line.split(",", 2);
//                key = data[0].substring(4);
//                len = data[1].length();
//                value = data[1].substring(0, len - 2);
//                System.out.println(key + "=" + value);
//                //attributes.put(key, value);
//            } else {
//                len = line.length();
//                key = line.substring(3, len - 2);
//                value = null;
//                System.out.println(key+"="+value);
//                //attributes.put(key, value);
//            }
//        } else {
//            if (line.contains(",") == true) {
//                data = line.split(",", 2);
//                key = data[0].substring(4);
//                len = data[1].length();
//                value = data[1].substring(0, len - 2);
//                //attributes.put(key, value);
//                System.out.println(key+"="+value);
//            } else {
//                len = line.length();
//                key = line.substring(3, len - 2);
//                value = null;
//                //attributes.put(key, value);
//               System.out.println(key+"="+value);
//            }
//
//        }
//    }
//
//    public static void getTA() {
//        for (String i : attributes.keySet()) {
//            System.out.println(i + "=" + attributes.get(i));
//        }
//    }
//
//    public static void setTO(String line) {
//        String value = null, key = null;
//        int len;
//        StringTokenizer st1
//                = new StringTokenizer(line, ",");
//        while (st1.hasMoreTokens()) {
//            value = st1.nextToken();
//            if (value.contains("%TO") == true) {
//                key = value.substring(4);
//                //System.out.println("Value is "+key);
//            } else {
//                len = value.length();
//                value = value.substring(0, len - 2);
//                // System.out.println("Value is "+value);
//            }
//
//        }
//        attributes.put(key, value);
//    }
//
//    public static void getTO() {
//        for (String i : attributes.keySet()) {
//            System.out.println(i + "=" + attributes.get(i));
//        }
//    }
//
//    public static void setFSLA(String line){
//       /*  String[] value = null, key = null;
//        int len;
//        key=line.split("X");
//      for(String p:key){
//        value=data.split("Y");
//      		for(String tst:value){
//                    len=tst.length();
//        	System.out.println(tst.substring(0,len-2));
//            }
//      }*/
//
//          //System.out.println("Key is "+key+" Value is "+value);
//       // attributes.put(key,value);	
//
//        
//     }
//    public static void main(String[] args) {
//        BufferedReader reader;
//
//        try {
//            reader = new BufferedReader(new FileReader(
//                    "D:\\testFolder\\allAttributes.txt"));
//            String line = reader.readLine();
//            while (line != null) {
//                //System.out.println(line);
//                if(line.contains("G04")==true){
//                setG04(line);
//                getG04();
//                }
//                else if (line.contains("%MO") == true) {
//                    setMO(line);
//                    getMO();
//                }
//                else if(line.contains("%TF.")==true){ 
//                setTF(line);
//                getTF();
//               }
//               else if (line.contains("%TA")==true) {
//                    setTA(line);
//                    getTA();
//                }
//                 else if(line.contains("%TO")==true){  
//                    setTO(line);
//                    getTO();
//             }
//               // setFSLA(line);
//                
//                //
//                line = reader.readLine();
//
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
