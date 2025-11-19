package com.example.workcollect.ui.tasks;

import android.media.browse.MediaBrowser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.workcollect.ui.store.ValueCalculator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskViewModel extends ViewModel {
    private Boolean filteringInProgress = false;

    private final MutableLiveData<List<Task>> _tasks = new MutableLiveData<>(new ArrayList<>());
    public LiveData<List<Task>> getTasks() {return _tasks;}
//    public LiveData<List<Task>> tasks = _tasks;

    private final MutableLiveData<LocalDate> _selectedDate = new MutableLiveData<>(LocalDate.now());
    public LiveData<LocalDate> getSelectedDate() {return _selectedDate;}

    private final MediatorLiveData<List<Task>> _dayRelevantTasks = new MediatorLiveData<>();
    public LiveData<List<Task>> getDayRelevantTasks() {return _dayRelevantTasks;}

    public TaskViewModel(){
        _dayRelevantTasks.addSource(_dayRelevantTasks, e -> updateDisplayedTasks());
        _dayRelevantTasks.addSource(_selectedDate, e -> updateDisplayedTasks());
    }

    private void updateDisplayedTasks(){
        // block triggers by this function to itself
        if(filteringInProgress) return;
        filteringInProgress = true;

        List<Task> src = _tasks.getValue();
        LocalDate date = _selectedDate.getValue();

        if(src == null || date == null) {
            _dayRelevantTasks.setValue(Collections.emptyList());
            return;
        }

        List<Task> dayRelevantTasks = new ArrayList<>();
        for(Task t : src){
            if(t.getDate().isEqual(date)){
                dayRelevantTasks.add(t);
            }
        }
        _dayRelevantTasks.setValue(dayRelevantTasks);
        filteringInProgress = false;
    }

    public void setNextDay() {_selectedDate.setValue(_selectedDate.getValue().plusDays(1));}
    public void setPreviousDay() {_selectedDate.setValue(_selectedDate.getValue().minusDays(1));}

    public void loadInitial(){
        // load content from db
        List<Task> dummyTasks = Arrays.asList(
                new Task("1L", "Water plants in living room", "TINY", LocalDate.of(2025, 11, 3)),
                new Task("2L", "Clean bathroom", "XLARGE", LocalDate.of(2025, 11, 3)),
                new Task("3L", "Bring out trash", "TINY", LocalDate.of(2025, 11, 3)),

                new Task("4L", "Bring out trash", "TINY", LocalDate.of(2025, 11, 4)),
                new Task("5L", "Vacuum in the living room", "TINY", LocalDate.of(2025, 11, 4)),

                new Task("6L", "Broom kitchen floor", "SMALL", LocalDate.of(2025, 11, 5)),
                new Task("7L", "Bring out trash", "TINY", LocalDate.of(2025, 11, 5)),

                new Task("8L", "Clean stove", "MEDIUM", LocalDate.of(2025, 11, 6)),
                new Task("9L", "Bring out trash", "TINY", LocalDate.of(2025, 11, 6)),

                new Task("10L", "Clean bedroom", "LARGE", LocalDate.of(2025, 11, 7)),
                new Task("11L", "Bring out trash", "TINY", LocalDate.of(2025, 11, 7)),

                new Task("12L", "Mow the grass on front yard", "XLARGE", LocalDate.of(2025, 11, 8)),
                new Task("13L", "Clean room", "XLARGE", LocalDate.of(2025, 11, 8)),
                new Task("14L", "Bring out trash", "TINY", LocalDate.of(2025, 11, 8))

                );
        _tasks.setValue(dummyTasks);

        // dummy for value calculator
        ValueCalculator.computePotential(dummyTasks);
    }



}