package com.example.workcollect.ui.tasks;

import java.util.HashMap;
import java.util.Map;

public class DifficultyLevelMap {
    public static final Map<String, Integer> map = new HashMap<>();

    static {
        map.put("TINY", 3);
        map.put("SMALL", 5);
        map.put("MEDIUM", 8);
        map.put("LARGE", 13);
        map.put("XLARGE", 21);
    }

    public void setDefaults(){
        map.put("TINY", 3);
        map.put("SMALL", 5);
        map.put("MEDIUM", 8);
        map.put("LARGE", 13);
        map.put("XLARGE", 21);
    }

    public void addType(String strType, Integer iValue){
        map.put(strType, iValue);
    }

    public void removeType(String strType){
        map.remove(strType);
    }

    public void setFactor(String strType, Integer iNewValue){
        map.put(strType, iNewValue);
    }
}
