package com.sc.fe.analyze.util;

import java.io.File;
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
import java.util.stream.Stream;

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
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.LayersInformation;
import com.sc.fe.analyze.to.Report;
import java.util.function.Consumer;

public class GerberFileProcessingUtil {
     
	   public static void findLayerInformation(Report report, Map<String, String> extensionToFileMapping, Path folder) {

        List<LayersInformation> layerInfo = new ArrayList<>();

        report.getExctractedFileNames().forEach((String exfile) -> {
            // Call processFile() method
            FileDetails fdetails = processFile(exfile, extensionToFileMapping, folder);

            if (fdetails != null && fdetails.getAttributes() != null)
            {

                if (fdetails.getAttributes().containsKey("Layer")) {
                    String polarity;
                    if (fdetails.getAttributes().containsKey("FilePolarity"))
                    {
                        polarity = fdetails.getAttributes().get("FilePolarity");
                    }
                    else 
                    {
                        polarity = "";
                    }

                    layerInfo.add(new LayersInformation(fdetails.getAttributes().get("Layer"), exfile, polarity));

                }
            }
        });
    }
	public static FileDetails processFile(String exfile, Map<String, String> extensionToFileMapping, Path folder) {

		String[] nameParts = exfile.split("\\.");
		String extn = nameParts[nameParts.length - 1].toLowerCase();

		Map<String, String> flagMap = new HashMap<String, String>();
		flagMap.put("isDrillFile", "N");
		flagMap.put("currentKey", "");
		
		FileDetails fileDet = new FileDetails();
		fileDet.setName(exfile);

		if (extensionToFileMapping.containsKey(extn) && !extn.toLowerCase().equals("pdf")) 
                {
                // we will process it line by line to get attributes
                // Results for the whole file
                Map<String, String> results = new HashMap<String, String>();

                String filePath = folder + File.separator + exfile;
                // System.out.println("FileName is---------"+exfile);
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
                    e.printStackTrace();
                }

			// All file attributes
			fileDet.setAttributes(results);

		}
		// Return fileDetail with processed attributes set
		return fileDet;
	}

	public static Map<String, Set<String>> processFilesByExtension(Report report,
			Map<String, String> extensionToFileMapping, Set<String> foundFiles) {

		Map<String, Set<String>> filePurposeToNameMapping = new HashMap<String, Set<String>>();
		report.getExctractedFileNames().forEach((String exfile) -> {
			String[] nameParts = exfile.split("\\.");
			String extn = nameParts[nameParts.length - 1].toLowerCase();

			if (extensionToFileMapping.containsKey(extn)) {
				Set<String> currentMapping = filePurposeToNameMapping.get(extensionToFileMapping.get(extn));
				if (currentMapping == null)
					currentMapping = new HashSet<String>();

				currentMapping.add(exfile);
				String fileType = extensionToFileMapping.get(extn);
				filePurposeToNameMapping.put(fileType, currentMapping);
				foundFiles.add(fileType);
			}
		});
		return filePurposeToNameMapping;
	}
        // Below method will be call if line starts with Layer
       

	public static HashMap<String, String> processLine(String line) {
		HashMap<String, String> attributes = new HashMap<>();

		if (line.startsWith("G04")) {
			attributes.putAll(processG04(line));
		} else if (line.startsWith("%TO")) {
			attributes.putAll(processTFTATO(line,"%TO"));
		} else if (line.startsWith("%TF")) {
			attributes.putAll(processTFTATO(line,"%TF"));
		} else if (line.startsWith("%TA")) {
			attributes.putAll(processTFTATO(line,"%TA"));
		} else if (line.startsWith("%FSLA")) {
			attributes.putAll(processFSLA(line));
		} else if (line.startsWith("%TD")) {
			attributes.putAll(processTD(line));
		} else if (line.startsWith("%MO")) {
			attributes.putAll(processMOLPLM(line,"MO"));
		}else if (line.startsWith("%LP")) {
			attributes.putAll(processMOLPLM(line,"LP"));
		} else if (line.startsWith("%LM")) {
			attributes.putAll(processMOLPLM(line,"LM"));
		} else if (line.startsWith("%LR")) {
			attributes.putAll(processLRLS(line,"LR"));
		} else if (line.startsWith("%LS")) {
			attributes.putAll(processLRLS(line,"LS"));
		}
		/*
		 * attributes.keySet().forEach((p) -> {
		 * System.out.println(p+" = "+attributes.get(p)); });
		 */
		return attributes;
	}

	// Below Code will process the line that has G04
	private static HashMap<String, String> processG04(String line) {
                HashMap<String, String> returnMap = new HashMap<String, String>();
                if (line == null || line.isEmpty()) {
                return returnMap;
                }
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
		return returnMap;
	}
        
	// Below method will be call if line starts with %TF or %TA or %TO
	private static HashMap<String, String> processTFTATO(String line,String word) {
		HashMap<String, String> returnMap = new HashMap<>();
                if(line==null || line.isEmpty())
                {
                    return returnMap;
                }
                String dotword=word+".";
		// If block will exetues if it is a reserved attribute name otherwise else block will execute
                if (line.startsWith(dotword)) {
                    if (line.contains(",")) {
                        line = line.replace(dotword, "").replace("*%", "");
                        String[] splitValue = line.split(",", 2);
                        returnMap.put(splitValue[0], splitValue[1]);
                    } else {
                        line = line.replace(dotword, "").replace("*%", "");
                        returnMap.put(line, "");
                    }
                }
                else {
                    if (line.contains(",")) {
                        line = line.replace(word, "").replace("*%", "");
                        String[] splitValue = line.split(",", 2);
                        returnMap.put(splitValue[0], splitValue[1]);
                    } else {
                        line = line.replace(word, "").replace("*%", "");
                        returnMap.put(line, "");
                    }
                }
		return returnMap;
	}

	
	// This code will execute if line starts with %FSLA	 
	private static HashMap<String, String> processFSLA(String line) {
		HashMap<String, String> returnMap = new HashMap<>();
                if (line == null || line.isEmpty()) {
                    return returnMap;
                }
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
                // Below lines will put the x value and y value into the hashmap along with  there keys.
                returnMap.put("X", Xvalue);
                returnMap.put("Y", Yvalue);

                return returnMap;
	}

	private static HashMap<String, String> processTD(String line) {
                HashMap<String, String> returnMap = new HashMap<>();
                if (line == null || line.isEmpty()) {
                    return returnMap;
                }
                if (line.startsWith("%TD.")) {
                    line = line.replace("%TD.", "").replace("*%", "").trim();
                } else {
                    line = line.replace("%TD", "").replace("*%", "").trim();
                }
                returnMap.put(line, "");
                return returnMap;
	}

        // Below method will be call if line starts with %MO or %LP or %LM
	private static HashMap<String, String> processMOLPLM(String line,String word) {
		HashMap<String, String> returnMap = new HashMap<>();
                if (line == null || line.isEmpty()) {
                    return returnMap;
                }
                String percentword = "%" + word;
                line = line.replace(percentword, "").replace("*%", "").trim();
                returnMap.put(word, line);
                return returnMap;
	}

        // Below method will be call if line starts with %LR or %LS
	private static HashMap<String, String> processLRLS(String line,String word) {
		HashMap<String, String> returnMap = new HashMap<>();
                if(line==null || line.isEmpty())  {
                    return returnMap;
                }
                String percentWord="%"+word;
		line = line.replace(percentWord, "").replace("*%", "").trim();
		returnMap.put(word, line);
		return returnMap;
	}
	
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

			System.out.println("Detected lines and words for " + photo);
			for (TextDetection text : textDetections) {
				if ("LINE".equals(text.getType())) {
					System.out.println("Detected: " + text.getDetectedText());
					System.out.println("Confidence: " + text.getConfidence().toString());
					System.out.println("Id : " + text.getId());
					System.out.println("Parent Id: " + text.getParentId());
					System.out.println("Type: " + text.getType());
					System.out.println();
				}
			}

		} catch (AmazonRekognitionException e) {
			e.printStackTrace();
		}

	}

//	private void createAdvancedReport(AdvancedReport report, 
//	Map<String, String> extensionToFileMapping,
//	Set<String> allFiles) {
//
////Map<String, Set<String>> filePurposeToNameMapping = new HashMap<String, Set<String>>();
//
//allFiles.forEach( exfile -> {
//	
//	String[] nameParts = exfile.split("\\.");
//	String extn = nameParts[nameParts.length-1].toLowerCase();
//	
//	if(extensionToFileMapping.containsKey( extn ) && ! extn.toLowerCase().equals("pdf")) {
//			
//		//TODO: Now we have found the file that we are interested in, 
//		//we will proecess it line by line to get attributes from our utility
//		FileDetails fileDet = new FileDetails();
//		fileDet.setName(exfile);
//		
//		String folder = util.getUploadDir() + File.separator + report.getCustomerInputs().getProjectId() + File.separator;
//		
//		Map<String, String> results = new HashMap<String, String>();
//		try (
//			Stream<String> stream = Files.lines(Paths.get(folder+exfile))) { 
//			stream.forEach( line -> {
//	        	results.putAll( GerberFileProcessingUtil.processLine(line) );
//	        });
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		fileDet.setAttributes(results);
//		report.addFileDetail(fileDet);
//		
//	}
//});
//
//}
}