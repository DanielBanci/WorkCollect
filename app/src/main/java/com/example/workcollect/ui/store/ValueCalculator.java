package com.example.workcollect.ui.store;

import com.example.workcollect.ui.tasks.Task;

import java.util.List;

public class ValueCalculator {
    private static Double _dPotential = 0.0;
    public static Double computePotential(List<Task> taskList){
        Double dPotential = 0.0;

        /// TODO: Add more logic to match the average weekly potential based on a 4 week period
        for(Task task : taskList){
            dPotential += task.getPoints();
        }
        System.out.println("dPotential = " + dPotential);
        // calculate average value
        // calculation based on a week period
        //dPotential /= 7;
        System.out.println("dPotential weekly avg = " + dPotential);
        // update potential
        _dPotential = dPotential;
        return dPotential;
    }

    public static Integer computeValue(StoreItem storeItem){
        Double dFactor = PrizeTier.map.get(storeItem.getStrItemTypeMapKey());
        Double dPrice = _dPotential * dFactor;

        return Math.toIntExact(Math.round(dPrice));
    }
    public static List<StoreItem> compute(List<StoreItem> storeItemList, List<Task> taskList){



        return storeItemList;
    }
}
