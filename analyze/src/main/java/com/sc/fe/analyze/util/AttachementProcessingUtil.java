package com.sc.fe.analyze.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sc.fe.analyze.to.AttachementDetails;
import com.sc.fe.analyze.to.Directory;
import com.sc.fe.analyze.to.FileDetails;

import io.micrometer.core.instrument.util.StringUtils;
import io.netty.util.internal.StringUtil;

public class AttachementProcessingUtil {

	private static final String FILE_TYPE_NETLIST = "netlist";
	private static final String FILE_TYPE_IPC = "IPC2581";
	private static final String FILE_TYPE_IPC_B = "IPC2581-B";
	public static final String FILE_TYPE_DRILL = "drill";
	private static final String FILE_TYPE_DOCUMENT = "document";
	private static final String FILE_TYPE_GERBER = "gerber";
	
	private static final String UNSUPORTED_IPC2581 = "Unsupported version of IPC2581 File Found";
	private static final String MULTIPLE_DATA_FORMATS_FOUND = "Multiple Data Formats found.";
	private static final String MULTIPLE_GERBER_DATA_FOUND = "Multiple Gerber Data found.";
	private static final String MULTIPLE_IPC356_NETLIST_DATA_FOUND = "Multiple IPC356 Netlist Data found.";
	private static final String MULTIPLE_IPC2581_B_DATA_FOUND = "Multiple IPC2581-B Data found.";
	private static final String MULTIPLE_ODB_DATA_FOUND = "Multiple ODB Data found.";
	private static final String ODB_DATA_FOUND = "ODB Data found.";
	private static final String IPC356_NETLIST_DATA_FOUND = "IPC356 Netlist Data found.";
	private static final String IPC2581_DATA_FOUND = "IPC2581-B Data found.";
	private static final String GERBER_DATA_FOUND = "Gerber Data found.";
	private static final String NO_DRILL_DATA_FOUND = "No Excellon Drill Data Found.";
	
	private static final String ODB = "ODB";
	private static final String GERBER = "GERBER";

	private static final String IPC_COUNT = "ipcCount";
	private static final String NET_LIST_COUNT = "netListCount";

	private static final Logger logger = LoggerFactory.getLogger(AttachementProcessingUtil.class);
	
	public static String getFileType(String filePath) {
		String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
		String type = "File";
		if( isDocument(fileName)) {
			return FILE_TYPE_DOCUMENT;
		}
		if( isGerber(fileName)) {
			
			return GerberFileProcessingUtil.findGerberType(filePath);
		}
		if( isDrill(fileName)) {
			
			return GerberFileProcessingUtil.parseDrill(filePath);
		}
		if( isIPC(fileName)) {
			return FILE_TYPE_IPC;
		}
		if( isNetList(fileName) ) {
			return FILE_TYPE_NETLIST;
		}
		return type;
	}
	public static boolean isDocument(String fileName) {
		
		boolean retVal = false;
		
		String extn = ".xlsx,.rep,.doc,.pdf,.xls,.ppt,.xps,.png,.docx,.exif,.bmp,.gif,.jpeg,.jpg,.dwg,.dxf,.csv";
		
		Set<String> docExtension= new HashSet<String>(Arrays.asList(extn.split(",")));
		
		for (Iterator<String> iterator = docExtension.iterator(); iterator.hasNext();) {
			if( fileName.toUpperCase().contains(iterator.next().toString().toUpperCase() )) {
				retVal = true;
				break;
			}
		}
		
		return retVal;
	}
	
	public static boolean isGerber(String fileName) {
		
		boolean retVal = false;
		
		String extn = ".fph,.g,.cr,.dim,.st,.slk,.pl,.oln,.as,.in,.sm,.sp,.ss,.fab,"
				+ ".pho,.gtl,.gbl,.gbx,.top,.bot,.art,.gdo,.cmp,.sol,.ly,.gerb,.txt";
		
		Set<String> gerberExtn= new HashSet<String>(Arrays.asList(extn.split(",")));
		
		for (Iterator<String> iterator = gerberExtn.iterator(); iterator.hasNext();) {
			if( fileName.toUpperCase().contains(iterator.next().toString().toUpperCase() )) {
				retVal = true;
				break;
			}
		}
		
		return retVal;
	}
	
	public static boolean isIPC(String fileName) {
		
		boolean retVal = false;
		
		String extn = ".xml,.xsd,.cvg";
		
		Set<String> ipcExtn= new HashSet<String>(Arrays.asList(extn.split(",")));
	
		for (Iterator<String> iterator = ipcExtn.iterator(); iterator.hasNext();) {
			if( fileName.toUpperCase().contains(iterator.next().toString().toUpperCase() )) {
				retVal = true;
				break;
			}
		}
		
		return retVal;
	}
	
	public static boolean isDrill(String fileName) {
		
		boolean retVal = false;

		String extn = ".txt,.dr,.tap,.ncr,nc,drill";
		
		Set<String> drillExtn= new HashSet<String>(Arrays.asList(extn.split(",")));

		for (Iterator<String> iterator = drillExtn.iterator(); iterator.hasNext();) {
			if( fileName.toUpperCase().contains(iterator.next().toString().toUpperCase() )) {
				retVal = true;
				break;
			}
		}
		
		return retVal;
	}

	public static boolean isNetList(String fileName) {
		
		boolean retVal = false;
		
		String extn = ".ipc";
		Set<String> netListExtn= new HashSet<String>(Arrays.asList(extn.split(",")));
		
		for (Iterator<String> iterator = netListExtn.iterator(); iterator.hasNext();) {
			if( fileName.toUpperCase().contains(iterator.next().toString().toUpperCase() )) {
				retVal = true;
				break;
			}
		}
		
		return retVal;
	}
	
	public static AttachementDetails getAttachementDetails(Path folder, String originalZipName, Map<String, Set<String>> extMap ) {

    	AttachementDetails attDetail = new AttachementDetails();
    	Set<String> odbDirSet = new HashSet<String>();
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        List<FileDetails> fileDetails = new ArrayList<FileDetails>();
        
        Map<String, Directory> dirMap = new HashMap<String, Directory>();
        
        attDetail.addWarningMessage(NO_DRILL_DATA_FOUND);
        
		try (Stream<Path> paths = Files.walk(Paths.get(folder.toString()))) {

			paths.forEach(file -> {
				
				FileDetails dtl = new FileDetails();
				
				boolean isODB = false;
				String fileName = file.getFileName().toString();
				String path = file.getParent().toString().replace(folder.toString(), "");
				com.sc.fe.analyze.to.Directory.FileDetail fd =  null;
				String currDirectory = path;
				
				if(odbDirSet != null) {
					for (String entry : odbDirSet) {
						if( path.contains( entry )) {
							isODB = true;
							break;
						}
					}
				}
				
				if( file.toFile().isDirectory() ) {
					
					path = file.toString().replace(folder.toString(), "");
					//logger.info( "Files in directory " + path);
					currDirectory = path;
					Directory dir = null;
					if( dirMap.get(currDirectory) == null) {
						dir = new Directory();
					}
					if( StringUtils.isNotBlank(currDirectory)) {
					
						dirMap.put(currDirectory, dir);
						dir.setName(currDirectory);
						
						if (fileName.equalsIgnoreCase(ODB) ) {
							odbDirSet.add(currDirectory);
							dir.setType(ODB);
							attDetail.addDirectory(dir);
							attDetail.addInfoMessage(ODB_DATA_FOUND);
							isODB = true;
						}
						
						if(! isODB ) {
							attDetail.addDirectory(dir);
						}
					}
				} else {
				
					Directory dir = dirMap.get(currDirectory);
					
					if (StringUtils.isNotBlank(fileName) && !fileName.equalsIgnoreCase(originalZipName) && !fileName.toLowerCase().endsWith("email.txt") ) {
						
						int len = fileName.length();
						if( len > 64 && ! isODB) {
							//File name for xxxxxx.xxx Has 74 Characters. This exceeds the Maximum Character Length of 64 Characters. You need to Trim 10 Characters
							//attDetail.addWarningMessage("File name greater than 64 char, remove " + (len-64) + " characters from file name: "+fullName);
							attDetail.addErrorMessage("File name '"+fileName + "' has " + len + 
									" Characters. This exceeds the Maximum Character Length of 64 Characters. You need to Trim "+ (len-64) + 
									" Characters.");
						}
						
						File f = file.toFile();
						fd =  dir.getFileDetailInstance();
						fd.setType(AttachementProcessingUtil.getFileType(f.getAbsolutePath()));
						fd.setName(f.getName());
						
						dtl.setName(currDirectory+File.separator +fd.getName());
						dtl.setType(fd.getType());

						if (FILE_TYPE_DRILL.equalsIgnoreCase(fd.getType())) {
							attDetail.getWarningMessages().remove(NO_DRILL_DATA_FOUND);
							if( StringUtil.isNullOrEmpty(dir.getType()) ) {
								dir.setType(FILE_TYPE_DRILL);
							}
						}
	
						if (FILE_TYPE_IPC.equalsIgnoreCase(fd.getType())) {
							
							if( len <= 64) {
								Map<String, String> attributes = IPC2581ProcessingUtil.processFile( f.getAbsolutePath());
								
								if( attributes.containsKey(IPC2581ProcessingUtil.PROPER_IPC) ) {
									
									if( attributes.containsKey(IPC2581ProcessingUtil.REVISION)) {
										String rev = attributes.get(IPC2581ProcessingUtil.REVISION);
										if("B".equalsIgnoreCase(rev)) {
											attDetail.addInfoMessage(IPC2581_DATA_FOUND);
											dir.setType(FILE_TYPE_IPC_B);
											fd.setType(FILE_TYPE_IPC_B);
											updateCount(countMap, IPC_COUNT);
										}else {
											attDetail.addErrorMessage(UNSUPORTED_IPC2581);
										}
									}
									
									if( StringUtil.isNullOrEmpty(dir.getType()) ) {
										dir.setType(FILE_TYPE_IPC);
									}
								}else {
									fd.setType("File");
								}
							}
						}
	
						if (FILE_TYPE_NETLIST.equalsIgnoreCase(fd.getType())) {
							attDetail.addInfoMessage(IPC356_NETLIST_DATA_FOUND);
							updateCount(countMap, NET_LIST_COUNT);
						}
	
						if (FILE_TYPE_GERBER.equalsIgnoreCase(fd.getType())) {
							
							attDetail.addInfoMessage(GERBER_DATA_FOUND);
							dir.setType(GERBER);
						}
						if ("gerber x2".equalsIgnoreCase(fd.getType())) {
							attDetail.addWarningMessage("Unsuported version of Gerber file Found.");
							dir.setType("GERBER X2");
						}

					}
					if( fd != null && ! isODB ) {
						dir.addFileDetail(fd);
					}
				}
				
				fileDetails.add(dtl);

			});
			//////New logic to combine both
			Set<String> ignoreTypes = new HashSet<String>();
			ignoreTypes.add(FILE_TYPE_DRILL);
			ignoreTypes.add(FILE_TYPE_IPC);
			GerberFileProcessingUtil.processFilesByExtension(fileDetails, extMap);
			Map<String,String> ruleMap = new HashMap<String,String>();
	        //For each file that is gerber format
	        fileDetails.stream()
	                .forEach(fd -> {
	                    //Apply rules by name pattern
	                    GerberFileProcessingUtil.parseFileName(fd);
	                    String typ = fd.getType();
	                    if( StringUtils.isNotBlank(typ) ) {
	                    	for (String igt : ignoreTypes) {
	                    		typ = typ.replace(igt, "");
							}
	                    
		                    if(typ.endsWith(",")) {
		                    	typ = typ.substring(0, typ.length()-1);
		                    }
		                    if(typ.startsWith(",")) {
		                    	typ = typ.substring(1);
		                    }
		                    ruleMap.put(fd.getName(), typ);
	                    }
	                });
	        
	        attDetail.setRemainingType(ruleMap);
	        //////New logic to combine both
	        
			if( attDetail.gerberDirectoryCount() == 0 ) {
				attDetail.getWarningMessages().remove(NO_DRILL_DATA_FOUND);
			}
			
			if ( attDetail.odbDirectoryCount() > 1 ) {
				attDetail.addWarningMessage(MULTIPLE_ODB_DATA_FOUND);

			}
			if (countMap.get(IPC_COUNT) != null && countMap.get(IPC_COUNT) > 1) {
				attDetail.addWarningMessage(MULTIPLE_IPC2581_B_DATA_FOUND);
			}
			if (countMap.get(NET_LIST_COUNT) != null && countMap.get(NET_LIST_COUNT) > 1) {
				attDetail.addWarningMessage(MULTIPLE_IPC356_NETLIST_DATA_FOUND);
			}
			if ( attDetail.gerberDirectoryCount() > 1) {
				attDetail.addWarningMessage(MULTIPLE_GERBER_DATA_FOUND);
			}
			if (attDetail.isMultiFormat() ) {
				attDetail.addWarningMessage(MULTIPLE_DATA_FORMATS_FOUND);
			}
			// Delete folder - All processing done
			FileUtil.deleteFolder(folder.toFile());

		} catch (IOException e) {
			logger.error("Error in zip files processing.", e);
			e.printStackTrace();
		}

        return attDetail;

    }
	private static void updateCount(Map<String, Integer> countMap, String countKey) {
		Integer count = countMap.get(countKey);
		if (count == null) {
			count = 1;
		} else {
			count++;
		}
		countMap.put(countKey, count);
	}

}
