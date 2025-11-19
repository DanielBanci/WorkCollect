package com.example.workcollect.ui.tasks;

import java.util.EnumMap;

public enum DifficultyLevel {
    TINY,
    SMALL,
    MEDIUM,
    LARGE,
    XLARGE;

    private static final EnumMap<DifficultyLevel, Integer> enumMapPoints = new EnumMap<>(DifficultyLevel.class);

    static {
        enumMapPoints.put(TINY, 3);
        enumMapPoints.put(SMALL, 5);
        enumMapPoints.put(MEDIUM, 8);
        enumMapPoints.put(LARGE, 13);
        enumMapPoints.put(XLARGE, 21);
    }

    public Integer getPoints(){
        return enumMapPoints.get(this);
    }
    public void setPoints(Integer iNewValue){
        enumMapPoints.put(this, iNewValue);
    }

    public static void resetToDefault(){
        enumMapPoints.put(TINY, 3);
        enumMapPoints.put(SMALL, 5);
        enumMapPoints.put(MEDIUM, 8);
        enumMapPoints.put(LARGE, 13);
        enumMapPoints.put(XLARGE, 21);
    }
}
