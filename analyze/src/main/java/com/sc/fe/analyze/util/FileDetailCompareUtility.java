package com.sc.fe.analyze.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.sc.fe.analyze.to.FileDetails;

public class FileDetailCompareUtility {

    private static final String DELIMITER = "~";

    public static Map<String, String> compareObject(Object newFD, Object oldFD) throws IllegalArgumentException, IllegalAccessException {
        if (newFD == null || oldFD == null) {
            return null;
        }
        //Field name - newValue, oldValue
        Map<String, String> differences = new HashMap<String, String>();
        for (Field field : newFD.getClass().getDeclaredFields()) {
            if (isCollection(field)) {
                continue;
            }
            // You might want to set modifier to public first (if it is not public yet)
            field.setAccessible(true);
            Object value1 = field.get(newFD);
            Object value2 = field.get(oldFD);
            if (value1 != null && value2 != null) {
                if (!Objects.equals(value1, value2)) {
                    differences.put(field.getName(), value1 + DELIMITER + value2);
                }
            }
        }
        return differences;
    }

    public static Map<String, String> compare(FileDetails newFD, FileDetails oldFD) {
        //Field name - newValue, oldValue
        Map<String, String> differences = new HashMap<String, String>();

        if (newFD == null || oldFD == null) {
            return null;
        }
        Set<String> combinedKeys = new HashSet<String>();
        combinedKeys.addAll(newFD.getAttributes().keySet());
        combinedKeys.addAll(oldFD.getAttributes().keySet());
        Map<String, String> oldAttr = oldFD.getAttributes();
        Map<String, String> newAttr = newFD.getAttributes();

        combinedKeys.stream().forEach(key -> {
            String oldValue = oldAttr.get(key) == null ? "" : oldAttr.get(key);
            String newValue = newAttr.get(key) == null ? "" : newAttr.get(key);
            if (!Objects.equals(oldValue, newValue)) {
                differences.put(key, newValue + DELIMITER + oldValue);
            }
        });
        return differences;
    }

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
