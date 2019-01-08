/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import com.sc.fe.analyze.to.AdvancedReport;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.LayersInformation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pc
 */
public class ODBProcessing {   
                
           public static HashMap<String, String> processODB(Path folder) 
           {                       
               List<LayersInformation> layerInfo = new ArrayList<>();
               Integer counter=0;   
               String folderName="";
               HashMap<String, String> results = new HashMap<String, String>();
             try
             {
                Path odbfilepath = Paths.get(folder+ File.separator + "\\odb\\matrix\\matrix").toAbsolutePath().normalize();
                BufferedReader br = new BufferedReader(new FileReader(odbfilepath.toFile())); 
                String line;
                //process the matrix file
                while ((line = br.readLine()) != null) 
                {
                    if(line.isEmpty())
                    {
                        continue;
                    }
                    if(line.startsWith("STEP"))
                    {
                        while ((line = br.readLine())!=null) 
                        {
                            if(line.endsWith("}"))
                            {
                                break;
                            } 
                            if(line.contains("NAME"))
                            {
                                 String[] splitValue = line.replaceAll("    ", "").split("=",2);
                                 folderName=splitValue[1];
                            }
                        }
                    }
                    if(line.startsWith("LAYER"))
                    {          
                        counter++;
                        while ((line = br.readLine())!=null) 
                        {
                            if(line.endsWith("}"))
                            {
                                break;
                            }                            
                            results.putAll(processLayer(counter,line));                                     
                                   
                         }
                         if(results.containsKey("ROW")) 
                         {
                           // System.out.println("Layer Information"+results);
                            HashMap attr=processAttribute(folder,results.get("NAME"),folderName);                            
                            if(attr.isEmpty())
                            {
                                counter=counter-1;                                 
                            }
                            else
                            {   
                               layerInfo.add(new LayersInformation(results.get("ROW"),results.get("CONTEXT"),results.get("TYPE"),results.get("NAME"),results.get("POLARITY"),results.get("START_NAME"),results.get("END_NAME"),results.get("OLD_NAME")));                               
                             }
                         }            
                    }
                 } 
                //Print on console layerInformations from layerInfo list
                System.out.println("ROW CONTEXT   TYPE         NAME       POLARITY    STARTNAME   ENDNAME     OLDNAME");
                for (LayersInformation t : layerInfo) {
			System.out.println(t.row+"  "+t.context+ "  "+t.type + "    " +t.name + "   "+ t.polarity + "   "+ t.start_name 
                        + "   "+t.end_name+ "   "+t.old_name);
		}                
             
               }
               catch(IOException e)
               {
                   e.printStackTrace();
               }
                return results;
           }
       
         private static HashMap<String, String> processAttribute(Path folder,String layerFolderName,String topFolder) 
         {
            HashMap<String, String> returnMap = new HashMap<>();
            FileDetails attrInfo=new FileDetails();
            try
            {
                //Process the attrFile Information
                String st;
                Path odbInfopath = Paths.get(folder+ File.separator + "odb" + File.separator +"steps" + File.separator+ topFolder.toLowerCase() +File.separator+"layers" +File.separator+ layerFolderName+ File.separator+"attrlist").toAbsolutePath().normalize();
                File file=new File(odbInfopath.toString());
                if(file.exists())
                {
                     BufferedReader brr = new BufferedReader(new FileReader(file)); 
               
                     while((st = brr.readLine()) != null)
                     {         
                        if(st.isEmpty())
                        {
                            break;
                        }                   
                        String[] splitValue = st.replaceFirst(".", "").split("=",2); 
                        returnMap.put(splitValue[0],splitValue[1] );                                                                
                     }
                     //System.out.println("Values of attrList file - "+returnMap + "\n");
                     //stored the information in FileDetails attribute object
                     attrInfo.setAttributes(returnMap);
                }
            }                
            catch(IOException e)
            {
                e.printStackTrace();
            }
            return returnMap;
         }
         
        private static HashMap<String, String> processLayer(Integer row,String line) 
        {
            HashMap<String, String> returnMap = new HashMap<String, String>();
            String[] splitedValue = line.trim().split("=",2);
            if(splitedValue[0].equals("ROW"))
            {
                splitedValue[1]=row.toString();
                returnMap.put(splitedValue[0],row.toString());
            }
            else
            {
                  returnMap.put(splitedValue[0], splitedValue[1]);                    
            }          
           return returnMap;
        }         
        
}
