package com.example.workcollect.ui.tasks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.workcollect.database.TaskRepository;

public class AddTaskViewModel extends ViewModel {
    private final TaskRepository taskRepository = new TaskRepository();
    private final MutableLiveData<Boolean> _saving = new MutableLiveData<>(false);
    public LiveData<Boolean> getSaving() {return _saving;}
    private final MutableLiveData<Exception> _error = new MutableLiveData<>(null);
    public LiveData<Exception> getError() {return _error;}
    public Task task = new Task();

    public void addTask(String strGroupId, Task task, Runnable runnable){
        _saving.setValue(true);
        taskRepository.addTask(strGroupId, task, new TaskRepository.Callback() {
            @Override
            public void onSuccess() {
                _saving.postValue(false);
                _error.postValue(null);
                if(runnable != null) runnable.run();
            }

            @Override
            public void onError(Exception e) {
                _saving.postValue(false);
                _error.postValue(e);
            }
        });
    }

}
