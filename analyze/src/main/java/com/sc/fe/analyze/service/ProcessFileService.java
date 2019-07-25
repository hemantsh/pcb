package com.sc.fe.analyze.service;

import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.util.GerberFileProcessingUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hemant
 */
public class ProcessFileService {

    private static final Logger logger = LoggerFactory.getLogger(ProcessFileService.class);

    /**
     * This method process the file if path exists
     *
     * @param folder path where file exists
     * @param filename Name of the file which we will process
     * @return the fileDetails of the file
     */
    public static FileDetails fileValidation(Path folder, String filename) {
        Map<String, String> errorToFileMapping = new HashMap<String, String>();
        FileDetails result = new FileDetails();

        File file = new File(folder + "\\" + filename);
        //If file exists in the folder,then proceed further.
        if (file.exists()) {
            result = processFile(filename, folder);
        } else {
            errorToFileMapping.put("V0000", "Path not correct.Please give the correct path !!");
            result.setErrors(errorToFileMapping);
        }
        return result;
    }

    public static FileDetails processFile(String exfile, Path folder) {

        Map<String, String> flagMap = new HashMap<String, String>();
        flagMap.put("isDrillFile", "N");
        flagMap.put("currentKey", "");
        
        FileDetails fileDet = new FileDetails();
        fileDet.setName(exfile);

        // we will process it line by line to get attributes
        // Results for the whole file stored in results variable
        Map<String, String> results = new HashMap<String, String>();

        String filePath = folder + File.separator + exfile;
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            // For each line in file
            stream.forEach(line -> {

                String currentKey = flagMap.get("currentKey");
                if (line.startsWith("M48")) {
                    flagMap.put("isDrillFile", "Y");
                    fileDet.setType("Drill");
                }
                if (line.startsWith("%") || line.startsWith("M95")) {
                    flagMap.put("isDrillFile", "N");
                }

                if ("Y".equals(flagMap.get("isDrillFile"))) {
                    // Drill file line attributes
                    currentKey = GerberFileProcessingUtil.processM48(line, results, currentKey);
                    flagMap.put("currentKey", currentKey);

                } else {
                    // Regular attribute line                                        
                    results.putAll(GerberFileProcessingUtil.processLine(line));
                }
            });

        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        // All file attributes
        fileDet.setAttributes(results);

        // Return fileDetail with processed attributes set
        return fileDet;
    }
}
