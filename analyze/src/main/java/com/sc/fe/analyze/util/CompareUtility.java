package com.sc.fe.analyze.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.sc.fe.analyze.to.AttributeChange;
import com.sc.fe.analyze.to.Change;
import com.sc.fe.analyze.to.FileChange;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.ProjectDetails;
import com.sc.fe.analyze.to.TurnTimeQuantity;

/**
 * Utility class that has methods to compare various objects that are used in
 * this project along with generic object comparison.
 *
 * @author Hemant
 */
public class CompareUtility {

    private static final Logger logger = LoggerFactory.getLogger(CompareUtility.class);
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
        set.add("status");
        set.add("setId");
        set.add("context");
        set.add("selected");
        set.add("fileDate");
        set.add("fullName");
        set.add("path");
        return set;
    }

    /**
     * This method is compare the new ProjectDetails with the old ProjectDetails
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

            //Compare the FileDetails of the file
            differences.putAll(compareFileDetails(newRecord, oldRecord));

            //CompareTrunTimes
            differences.putAll(compareTurnTimes(newRecord.getAssemblyTurnTimeQuantity(), oldRecord.getAssemblyTurnTimeQuantity(), "AssemblyTurnTimes"));
            differences.putAll(compareTurnTimes(newRecord.getFabricationTurnTimeQuantity(), oldRecord.getFabricationTurnTimeQuantity(), "FabricationTurnTimes"));

        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return differences;
    }

    /**
     * Compare TurnTimeQuantity objects
     *
     * @param newRecord
     * @param oldRecord
     * @param field
     * @return
     */
    private static Map<String, String> compareTurnTimes(List<TurnTimeQuantity> newRecord, List<TurnTimeQuantity> oldRecord, String field) {

        Set<String> newSet, oldSet = null;
        if (newRecord == null) {
            newSet = new HashSet<String>();
        } else {
            newSet = newRecord.stream()
                    .map(TurnTimeQuantity::stringData)
                    .collect(Collectors.toSet());
        }

        if (oldRecord == null) {
            oldSet = new HashSet<String>();
        } else {
            oldSet = oldRecord.stream()
                    .map(TurnTimeQuantity::stringData)
                    .collect(Collectors.toSet());
        }

        Set<String> deletion = new HashSet<String>();
        deletion.addAll(oldSet);
        deletion.removeAll(newSet);

        Set<String> addition = new HashSet<String>();
        addition.addAll(newSet);
        addition.removeAll(oldSet);

        Map<String, String> differences = new HashMap<String, String>();
        if (deletion != null && deletion.size() > 0) {
            differences.put(field + "(R)", "REMOVED" + DELIMITER + deletion);
        }

        if (addition != null && addition.size() > 0) {
            differences.put(field + "(A)", addition + DELIMITER + "ADDED");
        }

        return differences;
    }

    /**
     * This generic method compares the new Object with the old Object details.
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
     * This method compares the new ProjectDetails with the old ProjectDetails
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
        Set<String> newFileNameSet = new HashSet<String>();
        Set<String> oldFileNameSet = new HashSet<String>();

        if (newProject != null && newProject.getAllFileNames() != null) {
            newFileNameSet = newProject.getAllFileNames();
        }
        if (oldProject != null && oldProject.getAllSelectedFileNames() != null) {
            oldFileNameSet = oldProject.getAllSelectedFileNames();
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
                logger.error(ex.getMessage());
                ex.printStackTrace();
            }
        });

        return differences;
    }

    /**
     * Prepare list of changes in FileChange list structure.
     *
     * @param differences
     * @return
     */
    public static List<FileChange> createFileChangeList(Map<String, String> differences) {
        Map<String, FileChange> changeList = new HashMap<String, FileChange>();

        if (differences != null && differences.size() > 0) {

            differences.keySet().stream().forEach(key -> {
                String fileName = key;
                FileChange fChange = null;
                String attName = null;

                String[] tmp = key.split("<>");
                fileName = tmp[0];
                if (tmp.length == 2) {
                    attName = tmp[1];
                }

                if (!StringUtils.isEmpty(attName) || !fileName.contains(".")) {
                    //Update-Attributes
                    if (!fileName.contains(".")) {
                        attName = new String(fileName);
                        fileName = "ProjectDetails";
                    }
                    fChange = changeList.get(fileName);
                    if (fChange == null) {
                        fChange = new FileChange();
                    }
                    fChange.setFileName(fileName);
                    fChange.setAction(Change.UPDATED);
                    fChange.addAttribute(getChangedAttribute(attName, differences.get(key)));
                } else {
                    //Add/Remove "File got Removed." "File got Added."
                    fChange = changeList.get(fileName);
                    if (fChange == null) {
                        fChange = new FileChange();
                    }
                    fChange.setFileName(fileName);
                    String val = differences.get(key);
                    fChange.setAction(Change.valueOf(val));

                }
                changeList.put(fileName, fChange);
            });
        }
        return changeList.values().stream().collect(Collectors.toList());
    }

    private static AttributeChange getChangedAttribute(String attName, String diffValue) {
        AttributeChange change = new AttributeChange();
        //Update-Attribute
        change.setName(attName);

        if (diffValue.contains(DELIMITER)) {
            String[] vals = diffValue.split(DELIMITER);
            change.setNewValue(vals[0]);
            change.setOldValue(vals[1]);
        }

        return change;
    }

    /**
     * This method compares the new FileDetails with the old FileDetails
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

        if (newFD != null && oldFD != null) {
            if (oldFD.isSelected() && !newFD.isSelected()) {
                returnMap.put(oldFD.getName().toUpperCase(), Change.NOTINCLUDED.name());
                return returnMap;
            }
            if (oldFD.getFileDate().after(newFD.getFileDate())) {
                returnMap.put(oldFD.getName().toUpperCase(), Change.OLDER.name());
                return returnMap;
            }
            if (oldFD.getFileDate().before(newFD.getFileDate())) {
                returnMap.put(oldFD.getName().toUpperCase(), Change.UPDATED.name());
                return returnMap;
            }

            //First compare as simple object
            differences.putAll(compareObject(newFD, oldFD));
            //Now compare collection attributes for FileDetails 
            differences.putAll(compareMaps(newFD.getAttributes(), oldFD.getAttributes()));

            if (differences.size() <= 0) {
                returnMap.put(oldFD.getName().toUpperCase(), Change.SAME.name());
            }
        }

        if (newFD == null && oldFD != null) {
            if (!oldFD.isSelected()) {
                return returnMap;
            }
            returnMap.put(oldFD.getName().toUpperCase(), Change.MISSING.name());
            newFD = new FileDetails();
        }
        if (newFD != null && oldFD == null) {
            if (!newFD.isSelected()) {
                return returnMap;
            }
            returnMap.put(newFD.getName().toUpperCase(), Change.NEW.name());
            oldFD = new FileDetails();
        }

        String fileName = StringUtils.isEmpty(newFD.getName()) ? oldFD.getName() : newFD.getName();

        differences.keySet().stream().forEach(key -> {
            returnMap.put(fileName.toUpperCase() + "<>" + key, differences.get(key));
        });
        if (newFD != null) {
            newFD.setErrors(differences);
        }
        return returnMap;
    }

    /**
     * This method compares the objects
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
            String oldValue = NA, newValue = NA;
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
            Object oldValue = NA, newValue = NA;
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

    /**
     * Check if given field is a collection type of not
     *
     * @param field
     * @return the boolean value
     */
    private static boolean isCollection(Field field) {

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

    /**
     * Find missing items from reqTypes by checking items in availTypes
     *
     * @param reqiredTypes These types are required for the serviceType
     * @param availTypes These types are available in the JSON request
     * @return the list required types
     */
    public static List<String> findMissingItems(final List<String> reqiredTypes, final List<String> availTypes) {
        //Collect all elements to be removed from requiredTypes. 
        //These are the elements which are common with availableTypes
        List<String> toRemove = new ArrayList<String>();

        //Making copies of input params. We don't want to update/change parameters passed
        List<String> requiredTypes = new ArrayList<String>();
        requiredTypes.addAll(reqiredTypes);

        List<String> availableTypes = new ArrayList<String>();
        availableTypes.addAll(availTypes);

        availableTypes.stream().forEach(type -> {

            toRemove.addAll(requiredTypes.stream()
                    .filter(e -> toList(e).contains(type.toLowerCase()))
                    .collect(Collectors.toList())
            );
        });

        requiredTypes.removeAll(toRemove);
        return requiredTypes;
    }

    private static List<String> toList(String e) {
        if (StringUtils.isEmpty(e)) {
            return new ArrayList<String>();
        }
        //Split by 'or'
        List<String> temp = Arrays.asList(e.toLowerCase().split("or"));
        if (temp.size() > 0) {
            //remove white spaces
            for (int i = 0; i < temp.size(); i++) {
                temp.set(i, temp.get(i).trim());
            }
        }
        return temp;
    }

}
