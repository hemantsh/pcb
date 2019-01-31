/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.util;

import com.sc.fe.analyze.to.CustomerInformation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.LayersInformation;

/**
 *
 * @author Hemant
 */
public class ODBProcessing {

    /**
     * This method read and processes the ODB file.
     *
     * @param file - the path of folder where matrix file exists
     * @return the list of fileDetails
     */
    public static List<FileDetails> processODB(Path file) {
        //LayersInformation layerInfo = new LayersInformation();
        List<FileDetails> fileDetlList = new ArrayList<FileDetails>();
        //TODO: return list FileDetails        
        String folderName = "";
        HashMap<String, String> results = new HashMap<String, String>();
        try {                       
          //Path odbfilepath = Paths.get(file + File.separator + "odb" + File.separator + "matrix" + File.separator + "matrix").toAbsolutePath().normalize();                                  
            BufferedReader br = new BufferedReader(new FileReader(file.toFile()));
            String line;
            //process the matrix file
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) { 
                    continue;
                }
                if (line.startsWith("STEP")) {
                    while ((line = br.readLine()) != null) {
                        if (line.endsWith("}")) {
                            break;
                        }
                        if (line.contains("NAME")) {
                            String[] splitValue = line.replaceAll("    ", "").split("=", 2);
                            folderName = splitValue[1];
                        }
                    }
                }
                if (line.startsWith("LAYER")) {                    
                    while ((line = br.readLine()) != null) {
                        if (line.endsWith("}")) {
                            //Print layer Information of file
//                            results.keySet().forEach((p)->
//                                System.out.println(p+ " = "+results.get(p)));
                            break;
                        }
                        //Set the attribute values in HashMap
                        String[] splitedValue = line.trim().split("=", 2);
                        results.put(splitedValue[0], splitedValue[1]);
                    }
                    if (results.containsKey("ROW")) {
                        FileDetails fd = new FileDetails();
                        fd.setName(results.get("NAME"));
                        fd.setFormat("odb");
                        fd.setContext(results.get("CONTEXT"));
                        fd.setPolarity(results.get("POLARITY"));
                        fd.setStartName(results.get("START_NAME"));
                        fd.setEndName(results.get("END_NAME"));
                        fd.setType(results.get("TYPE"));

                        fileDetlList.add(fd);                        
                        //processAttribute(folder, results.get("NAME"), folderName);
//                        if (fd != null) {
//
//                            fileDetlList.add(fd);
//
//                            if (fd.getAttributes() == null || fd.getAttributes().isEmpty()) {
//                                counter = counter - 1;
//                            } else {
//                                layerInfo = new LayersInformation(results.get("ROW"),
//                                        results.get("CONTEXT"), results.get("TYPE"),
//                                        results.get("NAME"), results.get("POLARITY"),
//                                        results.get("START_NAME"), results.get("END_NAME"),
//                                        results.get("OLD_NAME"));
//
//                                //fd.setLayerInfo(layerInfo);
//                            }
//                        }                        
                    }
                }
            }
            br.close();            
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return fileDetlList;
    }

    private static FileDetails processAttribute(Path folder, String layerFolderName, String topFolder) {
        HashMap<String, String> fileAttributes = new HashMap<>();
        FileDetails fileDetail = new FileDetails(); //RETURN this
        try {
            //Process the attrFile Information
            String st;
            Path odbInfopath = Paths.get(folder + File.separator + "odb" + File.separator + "steps" + File.separator + topFolder.toLowerCase() + File.separator + "layers" + File.separator + layerFolderName + File.separator + "attrlist").toAbsolutePath().normalize();
            File file = new File(odbInfopath.toString());
            if (file.exists()) {
                BufferedReader brr = new BufferedReader(new FileReader(file));

                while ((st = brr.readLine()) != null) {
                    if (st.isEmpty()) {
                        break;
                    }
                    String[] splitValue = st.replaceFirst(".", "").split("=", 2);
                    fileAttributes.put(splitValue[0], splitValue[1]);
                }
                //System.out.println("Values of attrList file - "+returnMap + "\n");
                //stored the information in FileDetails attribute object
                fileDetail.setAttributes(fileAttributes);
                fileDetail.setName(file.getName());
                brr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileDetail;
    }
}