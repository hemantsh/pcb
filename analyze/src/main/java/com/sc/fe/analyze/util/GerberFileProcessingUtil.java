package com.sc.fe.analyze.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectTextRequest;
import com.amazonaws.services.rekognition.model.DetectTextResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.services.rekognition.model.TextDetection;
import com.sc.fe.analyze.FileStorageProperties;
import com.sc.fe.analyze.service.FileExtractUploadService;
import com.sc.fe.analyze.to.AdvancedReport;
import com.sc.fe.analyze.to.FileDetails;

/**
 *
 * @author Hemant
 */
public class GerberFileProcessingUtil {

	private static final Logger logger = LoggerFactory.getLogger(GerberFileProcessingUtil.class);
    
    /**
     * This method retrieves the file from the report once at a time and store
     * the layerInformation details of the file in the database.
     *
     * @param report object of AdvancedReport
     * @param extensionToFileMapping the extensionToFileMapping store the file
     * information in (key-extension of file,value-fileName) pair
     * @param folder path of folder
     * @return the list of fileDeatils
     */
    public static List<FileDetails> extractFileDetails(final AdvancedReport report, Map<String, String> extensionToFileMapping, Path folder) {

        List<FileDetails> fileDetails = new ArrayList<FileDetails>();

        //TODO: fileDetails can be in report already
        report.getExctractedFileNames().forEach((String exfile) -> {
            // Call processFile() method
            FileDetails fdetails = processFile(exfile, extensionToFileMapping, folder);

            if (fdetails != null && fdetails.getAttributes() != null) {

                if (fdetails.getAttributes().containsKey("Layer")) {
                    String polarity;
                    if (fdetails.getAttributes().containsKey("FilePolarity")) {
                        polarity = fdetails.getAttributes().get("FilePolarity");
                    } else {
                        polarity = "";
                    }

                    //LayersInformation layerInfo = new LayersInformation(fdetails.getAttributes().get("Layer"), exfile, polarity);
                    //fdetails.setLayerInfo(layerInfo);
                }
            }

            fileDetails.add(fdetails);
        });
        return fileDetails;
    }

    /**
     * This method process the file and store the file information in
     * FileDeatils object.
     *
     * @param exfile fileName of the file
     * @param extensionToFileMapping the extensionToFileMapping store the file
     * information in (key-extension of file,value-fileName) pair
     * @param folder the path of folder under the project
     * @return the fileDeatils
     */
    public static FileDetails processFile(String exfile, Map<String, String> extensionToFileMapping, Path folder) {

        String[] nameParts = exfile.split("\\.");
        String extn = nameParts[nameParts.length - 1].toLowerCase();

        Map<String, String> flagMap = new HashMap<String, String>();
        flagMap.put("isDrillFile", "N");
        flagMap.put("currentKey", "");

        FileDetails fileDet = new FileDetails();
        fileDet.setName(exfile);

        if (extensionToFileMapping.containsKey(extn) && !extn.toLowerCase().equals("pdf")) {
            // we will process it line by line to get attributes
            // Results for the whole file
            Map<String, String> results = new HashMap<String, String>();

            String filePath = folder + File.separator + exfile;
            try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
                // For each line in file
                stream.forEach(line -> {

                    String currentKey = flagMap.get("currentKey");
                    if (line.startsWith("M48")) {
                        flagMap.put("isDrillFile", "Y");
                    }
                    if (line.startsWith("%") || line.startsWith("M95")) {
                        flagMap.put("isDrillFile", "N");
                    }

                    if ("Y".equals(flagMap.get("isDrillFile"))) {
                        // Drill file line attributes
                        currentKey = processM48(line, results, currentKey);
                        flagMap.put("currentKey", currentKey);

                    } else {
                        // Regular attribute line
                        results.putAll(processLine(line));
                    }

                });

            } catch (IOException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }

            // All file attributes
            fileDet.setAttributes(results);

        }
        // Return fileDetail with processed attributes set
        return fileDet;
    }

    /**
     * This method process the files by their extension.
     *
     * @param fileDetails It stored the fileTypes of file
     * @param extensionToFileMapping the extensionToFileMapping store the file
     * @return the filePurposeToNameMapping
     */
    public static Map<String, Set<String>> processFilesByExtension(List<FileDetails> fileDetails,
            Map<String, Set<String>> extensionToFileTypeMapping) {

        Map<String, Set<String>> filePurposeToNameMapping = new HashMap<String, Set<String>>();
        //Key:fileType , Value = set of fileNames from zip that match to be of this fileType
        Set<String> ignoreList = new HashSet<String>();
        ignoreList.add("pdf");
        
        fileDetails.forEach(fileDetail -> {

            if (fileDetail.getType() == null) {
                String fileName = fileDetail.getName();
                String[] nameParts = fileName.split("\\.");
                String extn = nameParts[nameParts.length - 1].toLowerCase();
                
                Set<String> fileTypes = extensionToFileTypeMapping.get(extn);// All the types by the extension

                if (fileTypes != null && ! ignoreList.contains( extn)) {
                    fileTypes.stream().forEach(fileType -> {

                        Set<String> currentMapping = filePurposeToNameMapping.get(fileType);
                        if (currentMapping == null) {
                            currentMapping = new HashSet<String>();
                        }
                        currentMapping.add(fileName);
                        filePurposeToNameMapping.put(fileType, currentMapping);
                        //TODO: One extension can map to many fileTypes so we are adding 
                        //all types as comma separated. This will be useful later
                        
                        fileDetail.addType(fileType);
                    });
                }

            }
        });
        return filePurposeToNameMapping;
    }

    public static Map<String, Set<String>> processFilesByExtension(AdvancedReport report,
            Map<String, Set<String>> extensionToFileMapping) {

        return processFilesByExtension(report.getFileDetails(), extensionToFileMapping);
    }

    /**
     * This method process each line in the file if line starts with Layer
     *
     * @param line a single line in the file
     * @return the information of line in (key,value) pair
     */
    public static HashMap<String, String> processLine(String line) {
        HashMap<String, String> attributes = new HashMap<>();
        if (StringUtils.isEmpty(line)) {
            return attributes;
        }
        if (line.startsWith("G04")) {
            attributes.putAll(processG04(line));
        } else if (line.startsWith("%TO")) {
            attributes.putAll(processTFTATO(line, "%TO"));
        } else if (line.startsWith("%TF")) {
            attributes.putAll(processTFTATO(line, "%TF"));
        } else if (line.startsWith("%TA")) {
            attributes.putAll(processTFTATO(line, "%TA"));
        } else if (line.startsWith("%FSLA")) {
            attributes.putAll(processFSLA(line));
        } else if (line.startsWith("%TD")) {
            attributes.putAll(processTD(line));
        } else if (line.startsWith("%MO")) {
            attributes.putAll(processMOLPLM(line, "MO"));
        } else if (line.startsWith("%LP")) {
            attributes.putAll(processMOLPLM(line, "LP"));
        } else if (line.startsWith("%LM")) {
            attributes.putAll(processMOLPLM(line, "LM"));
        } else if (line.startsWith("%LR")) {
            attributes.putAll(processLRLS(line, "LR"));
        } else if (line.startsWith("%LS")) {
            attributes.putAll(processLRLS(line, "LS"));
        }
        /*
		 * attributes.keySet().forEach((p) -> {
		 * System.out.println(p+" = "+attributes.get(p)); });
         */
        return attributes;
    }

    /**
     * This method process those lines in the line which starts with GO4.
     *
     * @param line a single line in the file
     * @return the information of line in (key,value) pair.
     */
    public static HashMap<String, String> processG04(String line) {
        HashMap<String, String> returnMap = new HashMap<String, String>();
        if (line == null || line.isEmpty()) {
            return returnMap;
        }
        if (line.startsWith("G04")) {
            String keyQualifier = null;
            String[] splitedValue = line.split("[|]");

            for (String currentValue : splitedValue) {
                if (!currentValue.contains("=")) {
                    continue;
                }
                String[] temp = currentValue.split("=");

                if ("dcode".equals(temp[0].toLowerCase())) {
                    keyQualifier = "D" + temp[1] + ".";
                    continue;
                }
                if (temp[0].startsWith("G04")) {
                    temp[0] = temp[0].replace("G04", "").trim();
                }
                if (temp[0].toLowerCase().contains("order")) {
                    returnMap.put("Layer", temp[1].replace("*", ""));

                }
                returnMap.put(temp[0], temp[1].replace("*", ""));
            }
            if (keyQualifier != null) {
                HashMap<String, String> tempMap = (HashMap<String, String>) returnMap.clone();
                Iterator<String> keyItr = returnMap.keySet().iterator();

                while (keyItr.hasNext()) {
                    String key = keyItr.next();
                    tempMap.put(keyQualifier + key, tempMap.remove(key));
                }
                returnMap = tempMap;
            }
        }
        return returnMap;
    }

    /**
     * This method process those lines in the line which starts with %TA or %TF
     * or %TO.
     *
     * @param line a single line in the file
     * @param word It contains - TA or TF or TO
     * @return the information of the line in (key,value) pair.
     */
    public static HashMap<String, String> processTFTATO(String line, String word) {
        HashMap<String, String> returnMap = new HashMap<>();
        if (line == null || line.isEmpty()) {
            return returnMap;
        }
        String dotword = word + ".";

        if (line.startsWith(word)) {
            // If block will exetues if it is a reserved attribute name otherwise else block
            // will execute
            if (line.startsWith(dotword)) {
                if (line.contains(",")) {
                    line = line.replace(dotword, "").replace("*%", "");
                    String[] splitValue = line.split(",", 2);
                    returnMap.put(splitValue[0], splitValue[1]);
                } else {
                    line = line.replace(dotword, "").replace("*%", "");
                    returnMap.put(line, "");
                }
            } else {
                if (line.contains(",")) {
                    line = line.replace(word, "").replace("*%", "");
                    String[] splitValue = line.split(",", 2);
                    returnMap.put(splitValue[0], splitValue[1]);
                } else {
                    line = line.replace(word, "").replace("*%", "");
                    returnMap.put(line, "");
                }
            }
        }
        return returnMap;
    }

    /**
     * This method process those lines in the line which starts with %FSLA.
     *
     * @param line a single line in the file
     * @return the information of the line in (key,value) pair.
     */
    public static HashMap<String, String> processFSLA(String line) {
        HashMap<String, String> returnMap = new HashMap<>();
        if (line == null || line.isEmpty()) {
            return returnMap;
        }
        if (line.startsWith("%FSLA")) {
            int x, y, star;
            String Xvalue, Yvalue;
            x = line.indexOf("X");
            y = line.indexOf("Y");
            star = line.indexOf("*");
            /*
			 * In below code we'll store the substring into two different variables which
			 * needs to be put into the hashmap with the help of x and y index that we have
			 * extracted above.
             */
            Xvalue = line.substring(x + 1, y);
            Yvalue = line.substring(y + 1, star);
            // Below lines will put the x value and y value into the hashmap along with
            // there keys.
            returnMap.put("X", Xvalue);
            returnMap.put("Y", Yvalue);
        }
        return returnMap;
    }

    /**
     * This method process those lines in the line which starts with %TD.
     *
     * @param line a single line in the file
     * @return the information of the line in (key,value) pair.
     */
    public static HashMap<String, String> processTD(String line) {
        HashMap<String, String> returnMap = new HashMap<>();
        if (line == null || line.isEmpty()) {
            return returnMap;
        }
        if (line.startsWith("%TD")) {
            if (line.startsWith("%TD.")) {
                line = line.replace("%TD.", "").replace("*%", "").trim();
            } else {
                line = line.replace("%TD", "").replace("*%", "").trim();
            }
            returnMap.put(line, "");
        }
        return returnMap;
    }

    /**
     * This method process those lines in the line which starts with %MO or %LP
     * or %LM.
     *
     * @param line a single line in the file
     * @param word It contains - MO or LP or LM.
     * @return the information of the line in (key,value) pair.
     */
    public static HashMap<String, String> processMOLPLM(String line, String word) {
        HashMap<String, String> returnMap = new HashMap<>();
        if (line == null || line.isEmpty()) {
            return returnMap;
        }
        String percentword = "%" + word;
        if (line.startsWith(percentword)) {
            line = line.replace(percentword, "").replace("*%", "").trim();
            returnMap.put(word, line);
        }
        return returnMap;
    }

    /**
     * This method process those lines in the line which starts with %LR or %LS
     *
     * @param line a single line in the file
     * @param word It contains - LR or LS.
     * @return the information of the line in (key,value) pair.
     */
    public static HashMap<String, String> processLRLS(String line, String word) {
        HashMap<String, String> returnMap = new HashMap<>();
        if (line == null || line.isEmpty()) {
            return returnMap;
        }
        String percentWord = "%" + word;
        if (line.startsWith(percentWord)) {
            line = line.replace(percentWord, "").replace("*%", "").trim();
            returnMap.put(word, line);
        }
        return returnMap;
    }

    /**
     * This method process those lines in the line which starts with M48.
     *
     * @param line a single line in the file
     * @param results store the information of the line in (key,value) pair.
     * @param currentKey key of the line
     * @return the information of the line in (key,value) pair.
     */
    public static String processM48(String line, Map<String, String> results, String currentKey) {
        if (line.startsWith("%") || line.startsWith("M95")) {
            return currentKey;
        }

        if (line.startsWith(";")) {
            // End of old key and start of new attribute
            String key = "";
            String value = "";
            String[] splitValues = null;

            if (line.contains("=")) {
                splitValues = line.split("=");
            }

            if (line.startsWith(";TYPE")) {
                key = splitValues[1];
            } else {
                key = splitValues[0].replace(";", "");
                value = splitValues[1];
            }
            currentKey = key;
            results.put(key, value);
        } else if (currentKey.length() > 0) {
            // Line is continuation of previous value so we append the value for current key
            String currVal = results.get(currentKey);
            if (currVal == null || currVal.equals("")) {
                results.put(currentKey, line);
            } else {
                results.put(currentKey, currVal + "," + line);
            }
        }
        return currentKey;
    }

    /**
     * This method reads the image(from S3 bucket) by OCR tool.
     *
     * @param fileStorageProperties property file containing file upload options
     */
    public static void ocrImage(FileStorageProperties fileStorageProperties) throws Exception {

        String photo = "bus.png";
        String bucket = "test.hemant";

        AWSCredentials credentials = new BasicAWSCredentials(fileStorageProperties.getAccessKey(), // Key is defined in
                // ENV variable
                fileStorageProperties.getSecretKey() // Key is defined in ENV variable
        );

        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_WEST_2).build();

        DetectTextRequest request = new DetectTextRequest()
                .withImage(new Image().withS3Object(new S3Object().withName(photo).withBucket(bucket)));

        try {

            DetectTextResult result = rekognitionClient.detectText(request);
            List<TextDetection> textDetections = result.getTextDetections();

            logger.info("Detected lines and words for " + photo);
            for (TextDetection text : textDetections) {
                if ("LINE".equals(text.getType())) {
                    logger.info("Detected: " + text.getDetectedText());
                    logger.info("Confidence: " + text.getConfidence().toString());
                    logger.info("Id : " + text.getId());
                    logger.info("Parent Id: " + text.getParentId());
                    logger.info("Type: " + text.getType());
                    System.out.println();
                }
            }

        } catch (AmazonRekognitionException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * It parse the lyr_rule file
     *
     * @param fd It contains the fileDetails
     */
    public static void parseFileName(FileDetails fd) {
        if (fd == null 
        		|| "odb".equalsIgnoreCase(fd.getFormat()) ) {
            return;
        }

        FileDetails parsedFD = parseFileName(fd.getName());
        if (parsedFD != null) {
            if (StringUtils.isEmpty(fd.getContext())) {
                fd.setContext(parsedFD.getContext());
            }
            if (StringUtils.isEmpty(fd.getLayerName())) {
                fd.setLayerName(parsedFD.getLayerName());
            }
            if (StringUtils.isEmpty(fd.getLayerOrder())) {
                fd.setLayerOrder(parsedFD.getLayerOrder());
            }
            if (StringUtils.isEmpty(fd.getPolarity())) {
                fd.setPolarity(parsedFD.getPolarity());
            }
            if (StringUtils.isEmpty(fd.getSide())) {
                fd.setSide(parsedFD.getSide());
            }
            
            fd.addType( parsedFD.getType() );
        }
    }

    /**
     * It parse the lyr_rule file in the ODB folder
     *
     * @param fileName the name of the file in the lyr_rule file
     * @return the fileDetails of the file
     */
    public static FileDetails parseFileName(String fileName) {
        String filePath = "lyr_rule";
        BufferedReader br;
        if (fileName == null) {
            return null;
        }
        fileName = fileName.replaceAll("/", "@");
        		
        String[] tmp = fileName.split("@");
        if(tmp.length > 0) {
        	fileName = tmp[tmp.length-1];
        }
        FileDetails fd = new FileDetails();
        try {
            br = new BufferedReader(new FileReader(filePath));
            fileName = fileName.toLowerCase();

            String line;
            while ((line = br.readLine()) != null) {
                // It checks that line is comment or not.
                if (line.contains("->") && !(line.startsWith("#"))) {
                    String[] splitValue = line.split("->");
                    splitValue[0] = splitValue[0].trim();

                    if (!splitValue[0].endsWith("$")) {
                        splitValue[0] += ".*";
                    }
                    if (!splitValue[0].startsWith("^")) {
                        splitValue[0] = ".*" + splitValue[0];
                    }

                    // It matches the filename with regular expression
                    Pattern p = Pattern.compile(splitValue[0].trim());

                    //if (splitValue[0].endsWith("$") ? p.matcher(fileName).matches() :p.matcher(fileName).find()) {
                    if (p.matcher(fileName).matches()) {
                        // It separate all right side values by " " and stores in an array.
                        String[] splitRSide = splitValue[1].split(" ");
                        //System.out.println("File Found--Filename--"+fileName+"--RegularExpression--"+p);                        
                        fd.setLayerName(splitRSide[0].replaceAll("[^A-Za-z]*", ""));
                        fd.setContext(splitRSide[1]);

 
                        fd.addType(splitRSide[2]);

                        fd.setPolarity(splitRSide[3]);
                        fd.setSide(splitRSide[4]);
                        
                        if( splitRSide.length > 5) {
	                        splitRSide[5] = splitRSide[5].replace("\\", "").replaceAll("[^0-9]*", "");
	                        
	                        if(splitRSide[5].matches("-?\\d+(\\.\\d+)?")) {
	                        	fd.setLayerOrder(Integer.parseInt(splitRSide[5].replaceAll("[^0-9]*", "")));
	                        }
                        }
                        logger.info("======File name: pattern match-"+fileName+":"+splitValue[0]);
                        break;
                    }
                }
            }
            br.close();
        } catch (IOException ex) {
        	logger.error(ex.getMessage());
            ex.printStackTrace();
        }

        return fd;
    }

}
