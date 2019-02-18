package com.sc.fe.analyze.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.ProjectDetails;

/**
 *
 * @author Hemant
 */
public class CompareUtility {

    private static final String NA = "NA";
	private static final String DELIMITER = "~";
    private static Set<String> DO_NOT_COMPARE = initDoNotCompare();

    //Field names that will not be compared
    private static Set<String> initDoNotCompare() {
        Set<String> set = new HashSet<String>();
        set.add("version");
        set.add("serialVersionUID");
        set.add("modifiedDate");
        set.add("valid");
        set.add("createDate");
        set.add("newProject");
        set.add("attachReplace");
        return set;
    }

    /**
     * This method is compare the new Record from the old Record details
     *
     * @param newRecord the new Record of the ProjectDetails to set
     * @param oldRecord the old Record of the ProjectDetails to set
     * @return the differences after comparing the new record from the old
     * record
     */
    public static Map<String, String> fullCompare(ProjectDetails newRecord, ProjectDetails oldRecord) {

        Map<String, String> differences = new HashMap<String, String>();

        if (oldRecord == null) {
            return new HashMap<String, String>();
        }
        try {
            differences.putAll(compareObject(newRecord, oldRecord));

            differences.putAll(compareObjectMaps(newRecord.getTurnTimeQuantity(), oldRecord.getTurnTimeQuantity()));
            //Comparing the Validation errors with previous validation errors
            differences.putAll(compareMaps(newRecord.getErrors(), oldRecord.getErrors()));

            //Compare the FileDetails of the file
            differences.putAll(compareFileDetails(newRecord, oldRecord));

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return differences;
    }

    /**
     * This method is compare the new Object from the old Object details.
     *
     * @param newFD the new object details to set
     * @param oldFD the old object details to set
     * @return the differences after comparing the new object from the old
     * object
     */
    public static Map<String, String> compareObject(Object newFD, Object oldFD) throws IllegalArgumentException, IllegalAccessException {

        //Map: Field name - newValue ~ oldValue
        Map<String, String> differences = new HashMap<String, String>();
        if (newFD == null || oldFD == null) {
            return differences;
        }
        for (Field field : newFD.getClass().getDeclaredFields()) {
            //Do not compare collections and specific fields
            if (isCollection(field) || DO_NOT_COMPARE.contains(field.getName())) {
                continue;
            }
            // You might want to set modifier to public first (if it is not public yet)
            field.setAccessible(true);

            Object newVal = field.get(newFD);
            Object oldVal = field.get(oldFD);
            //If both set has value
            if (newVal != null && oldVal != null) {
                if (!Objects.equals(newVal, oldVal)) {
                    differences.put(field.getName(), newVal + DELIMITER + oldVal);
                }
            }
            //Only old set has value
            if (newVal == null && oldVal != null) {
                differences.put(field.getName(), "REMOVED" + DELIMITER + oldVal);
            }
            //Only new set has value
            if (newVal != null && oldVal == null) {
                differences.put(field.getName(), newVal + DELIMITER + "ADDED");
            }
        }
        return differences;
    }

    /**
     * This method is compare the new project file details from the old project
     * file details
     *
     * @param newProject the new project of the ProjectDetails to set
     * @param oldProject the old project of the ProjectDetails to set
     * @return the differences after comparing the new project from the old
     * project
     */
    public static Map<String, String> compareFileDetails(ProjectDetails newProject, ProjectDetails oldProject) {
        //FileDetail objects are compared if they have same file name
        Map<String, String> differences = new HashMap<String, String>();
        Set<String> combinedKeys = new HashSet<String>();
        Set<String> newFileNameSet = newProject.getAllFileNames();
        Set<String> oldFileNameSet = oldProject.getAllFileNames();

        if (newProject != null && newProject.getAllFileNames() != null) {
            newFileNameSet = newProject.getAllFileNames();
        }
        if (oldProject != null && oldProject.getAllFileNames() != null) {
            oldFileNameSet = oldProject.getAllFileNames();
        }
        //Collect all filenames in set for uniqueness
        combinedKeys.addAll(newFileNameSet);
        combinedKeys.addAll(oldFileNameSet);

        //Reading All fileNames 
        combinedKeys.stream().forEach(fileName -> {
            //Get FileDetail from 2 sets by same filename    
            FileDetails newFD = newProject.getFileDetails(fileName);
            FileDetails oldFD = oldProject.getFileDetails(fileName);

            try {
                //Now compare
                differences.putAll(compare(newFD, oldFD));

            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        });

        return differences;
    }

    /**
     * This method is compare the new project file details from the old project
     * file details
     *
     * @param newFD the new FileDetails object details to set
     * @param oldFD the old FileDetails object details to set
     * @return the differences after comparing the new FileDetails object from
     * the old FileDetails object
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Map<String, String> compare(FileDetails newFD, FileDetails oldFD) throws IllegalArgumentException, IllegalAccessException {
        Map<String, String> differences = new HashMap<String, String>();
        Map<String, String> returnMap = new HashMap<String, String>();
        if (newFD == null) {
            newFD = new FileDetails();
        }
        if (oldFD == null) {
            oldFD = new FileDetails();
        }

        //First compare as simple object
        differences.putAll(compareObject(newFD, oldFD));
        //Now compare collection attributes for FileDetails 
        differences.putAll(compareMaps(newFD.getAttributes(), oldFD.getAttributes()));
        String fileName = StringUtils.isEmpty(newFD.getName()) ? oldFD.getName() : newFD.getName();

        differences.keySet().stream().forEach(key
                -> {
            returnMap.put(fileName.toUpperCase() + "." + key, differences.get(key));
        });
        newFD.setErrors(differences);
        return returnMap;
    }

    /**
     * This method is compare the new project Map object from the old project
     * Map object
     *
     * @param newMap the new Map object details to set
     * @param oldMap the old Map object details to set
     * @return the differences after comparing the newMap object from the oldMap
     * object
     */
    public static Map<String, String> compareMaps(Map<String, String> newMap, Map<String, String> oldMap) {
        Map<String, String> differences = new HashMap<String, String>();

        //Get all keys from both sets
        Set<String> combinedKeys = new HashSet<String>();
        if (newMap != null) {
            combinedKeys.addAll(newMap.keySet());
        }
        if (oldMap != null) {
            combinedKeys.addAll(oldMap.keySet());
        }
        //For each key, compare old and new
        combinedKeys.stream().forEach(key -> {
            String oldValue = NA;
            String newValue = NA;
            if (oldMap != null && oldMap.get(key) != null) {
                oldValue = oldMap.get(key);
            }
            if (newMap != null && newMap.get(key) != null) {
                newValue = newMap.get(key);
            }
            if (!Objects.equals(oldValue, newValue)) {
                differences.put(key, newValue + DELIMITER + oldValue);
            }
        });

        return differences;
    }

    /**
     * This method is compare the new project Map object from the old project
     * Map object
     *
     * @param newMap the new Map object details to set
     * @param oldMap the old Map object details to set
     * @return the differences after comparing the newMap object from the oldMap
     * object
     */
    public static Map<String, String> compareObjectMaps(Map newMap, Map oldMap) {
        Map<String, String> differences = new HashMap<String, String>();

        //Get all keys from both sets
        Set<Object> combinedKeys = new HashSet<Object>();
        if (newMap != null) {
            combinedKeys.addAll(newMap.keySet());
        }
        if (oldMap != null) {
            combinedKeys.addAll(oldMap.keySet());
        }
        //For each key, compare old and new
        combinedKeys.stream().forEach(key -> {
            Object oldValue = NA;
            Object newValue = NA;
            if (oldMap != null && oldMap.get(key) != null) {
                oldValue = oldMap.get(key);
            }
            if (newMap != null && newMap.get(key) != null) {
                newValue = newMap.get(key);
            }

            if (!Objects.equals(oldValue, newValue)) {
                differences.put(String.valueOf(key), newValue + DELIMITER + oldValue);
            }
        });

        return differences;
    }
    
    public static Set<String> formatedError( Map<String, String> errors) {
    	Set<String> formatedErrorSet = new HashSet<String>();
    	if(errors != null && errors.size() > 0) {
    		
    		errors.keySet().stream().forEach( errorKey -> {
    			
    			String[] values = errors.get(errorKey).split(DELIMITER);
    			if( values.length == 2 ) {
	    			StringBuffer message = new StringBuffer("Value '" + errorKey + "' changed. ");
	    			if( NA.equals(values[0]) ) {
	    				message.append("Current set does not have value. ");
	    			}else {
	    				message.append("Current set value '" + values[0] + "'. ");
	    			}
	    			
	    			if( NA.equals(values[1]) ) {
	    				message.append("Last set did not have value. ");
	    			}else {
	    				message.append("Old set value '" + values[1] + "'.");
	    			}
	    			
	    			formatedErrorSet.add(message.toString());
    			}
    			
    		});
    	}
    	
    	return formatedErrorSet;
    }

    /**
     * Check if given field is a collection type of not
     *
     * @param field
     * @return the boolean value
     */
    private static boolean isCollection(Field field) {
        // TODO Auto-generated method stub
        boolean retVal = false;
        if (Collection.class.isAssignableFrom(field.getType())) {
            return true;
        }
        if (Map.class.isAssignableFrom(field.getType())) {
            return true;
        }
        if (Set.class.isAssignableFrom(field.getType())) {
            return true;
        }
        return retVal;
    }
}
