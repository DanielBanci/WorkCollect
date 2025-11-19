package com.example.workcollect.ui.store;

import java.util.EnumMap;

public enum ItemType {
    MICRO,
    QUICK,
    AVERAGE,
    PREMIUM,
    PLATINUM;

    private static final EnumMap<ItemType, Double> enumMapType = new EnumMap<>(ItemType.class);

    static {
        enumMapType.put(MICRO, 1.0/3);
        enumMapType.put(QUICK, 1.0/2);
        enumMapType.put(AVERAGE, 1.0);
        enumMapType.put(PREMIUM, 1.5);
        enumMapType.put(PLATINUM, 2.5);
    }

    public Double getFactor(){
        return enumMapType.get(this);
    }
    public void setFactor(Double dNewFactor){
        enumMapType.put(this, dNewFactor);
    }

    public static void resetToDefault(){
        enumMapType.put(MICRO, 1.0/3);
        enumMapType.put(QUICK, 1.0/2);
        enumMapType.put(AVERAGE, 1.0);
        enumMapType.put(PREMIUM, 1.5);
        enumMapType.put(PLATINUM, 2.5);
    }
}
