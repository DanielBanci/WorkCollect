package com.example.workcollect.ui.store;

import java.util.HashMap;
import java.util.Map;

public class PrizeTier {
    public static final Map<String, Double> map = new HashMap<>();

    static {
        map.put("MICRO",    0.05);  // 5%
        map.put("QUICK",    0.10);  // 10%
        map.put("AVERAGE",  0.20);  // 20%
        map.put("PREMIUM",  0.35);  // 35%
        map.put("PLATINUM", 0.60);  // 60%
        map.put("LEGENDARY",0.90);  // 90%
    }

    public void setDefaults(){
        map.put("MICRO",    0.05);
        map.put("QUICK",    0.10);
        map.put("AVERAGE",  0.20);
        map.put("PREMIUM",  0.35);
        map.put("PLATINUM", 0.60);
        map.put("LEGENDARY",0.90);
    }

    public void addType(String strType, Double dFactor){
        map.put(strType, dFactor);
    }

    public void removeType(String strType){
        map.remove(strType);
    }

    public void setFactor(String strType, Double dNewFactor){
        map.put(strType, dNewFactor);
    }
}
