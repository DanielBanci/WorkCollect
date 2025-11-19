package com.example.workcollect.ui.store;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.workcollect.ui.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreViewModel extends ViewModel {

    private final MutableLiveData<List<StoreItem>> _store_items = new MutableLiveData<>(new ArrayList<>());
    public LiveData<List<StoreItem>> storeItems = _store_items;

    public void loadInitial(){
        // load content from db
        List<StoreItem> dummyItems = Arrays.asList(
                new StoreItem(1L, "Extra 10 min TV time", "MICRO"),
                new StoreItem(2L, "Extra 15 min TV time", "QUICK"),
                new StoreItem(3L, "Extra 30 min TV time", "AVERAGE"),
                new StoreItem(4L, "Extra 60 min TV time", "PREMIUM"),
                new StoreItem(5L, "Extra 80 min PC time", "PLATINUM"),
                new StoreItem(5L, "No dishes 1 week", "LEGENDARY")
        );
        _store_items.setValue(dummyItems);

        // set item prices (dummy demo)
        for(StoreItem storeItem : dummyItems){
            storeItem.setiValue(ValueCalculator.computeValue(storeItem));
        }
    }
}