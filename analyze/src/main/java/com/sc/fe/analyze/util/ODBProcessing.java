package com.sc.fe.analyze.util;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ODBProcessing Class is used to process the ODB Folder
 *
 * @author Hemant
 */
public class ODBProcessing {
    private static final Logger logger = LoggerFactory.getLogger(ODBProcessing.class);
    /**
     * This method read and processes the ODB file.
     *
     * @param file the path of folder where matrix file exists
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
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return fileDetlList;
    }

    /**
     * It process the attributes of the attr file in the layerFolderName
     *
     * @param folder the name of the folder
     * @param layerFolderName the name of the layer Folder
     * @param topFolder the top most folder name
     * @return
     */
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
                //stored the information in FileDetails attribute object
                fileDetail.setAttributes(fileAttributes);
                fileDetail.setName(file.getName());
                brr.close();
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return fileDetail;
    }
}
