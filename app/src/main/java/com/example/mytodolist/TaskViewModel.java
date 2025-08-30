package com.example.mytodolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        this.repository = new TaskRepository(application);
    }

    public LiveData<List<Task>> getAllTasks(){
        allTasks = repository.getAllTasks();
        return allTasks;
    }

    public void addTask(Task task){
        repository.addTask(task);
    }

    public void deleteTask(Task task){
        repository.deleteTask(task);
    }

    public void updateTask(Task task){
        repository.updateTaskCompletion(task.getId(), task.getCompleted());
    }
}
