/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pc
 */
public class ODBProcessing {   
                
           public static HashMap<String, String> processODB(Path folder) 
           {                     
                Integer counter=0;
               
               Map<String, String> results = new HashMap<String, String>();
               HashMap<String, String> returnMap = new HashMap<>();
               try
               {
                Path odbfilepath = Paths.get(folder+ File.separator + "\\odb\\matrix\\matrix").toAbsolutePath().normalize();
                BufferedReader br = new BufferedReader(new FileReader(odbfilepath.toFile())); 
                String line;
                //process the matrix file
                while ((line = br.readLine()) != null) 
                {
                    if(line.startsWith("LAYER"))
                    {          
                        counter++;
                        while ((line = br.readLine())!=null) 
                        {
                            if(line.endsWith("}"))
                                break;
                           
                            if(line.contains("TYPE=COMPONENT") || line.contains("TYPE=DRILL") || line.contains("TYPE=DOCUMENT"))
                            {    
                                   counter=counter-1;
                                   //Remove the row and context value because it is not physical order
                                    results.remove("ROW");
                                    results.remove("CONTEXT");
                                    break;
                            }
                            else
                                   results.putAll(GerberFileProcessingUtil.processODB(counter,line));                            
                                    
                         }
                        if(results.containsKey("ROW"))
                            System.out.println("Layer Information"+results);
                        
                        //Process the attrFile Information
                        if(results.containsKey("NAME"))
                        {
                              String st;
                              Path odbInfopath = Paths.get(folder+ File.separator + "odb\\steps\\pcb\\layers" +File.separator+ results.get("NAME")+ File.separator+"attrlist").toAbsolutePath().normalize();
                             
                             BufferedReader brr = new BufferedReader(new FileReader(odbInfopath.toFile())); 
                              while((st = brr.readLine()) != null)
                              {         
                                  if(st.isEmpty())
                                      break;
                                    String[] splitValue = st.replace(".", "").split("=",2);
                                    returnMap.put(splitValue[0],splitValue[1] );                           
                                      
                              }
                               System.out.println("Values of attrList file -"+returnMap + "\n");
                        }
                    }
                }
               }
               catch(IOException e)
               {
                   e.printStackTrace();
               }
                return returnMap;
           }
           
}
