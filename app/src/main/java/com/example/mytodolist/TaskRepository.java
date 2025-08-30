package com.example.mytodolist;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepository {
    private final TaskDAO taskDAO;

    ExecutorService service;
    Handler handler;
    public TaskRepository(Application application) {
        TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
        this.taskDAO = taskDatabase.getTaskDAO();

        service = Executors.newSingleThreadExecutor();//background database operations
        handler = new Handler(Looper.getMainLooper());//updating the ui
    }

    public void addTask(Task task){
        service.execute(new Runnable() {
            @Override
            public void run() {
                taskDAO.insert(task);
            }
        });
    }

    public void deleteTask(Task task){
        service.execute(new Runnable() {
            @Override
            public void run() {
                taskDAO.delete(task);
            }
        });
    }

    public void updateTaskCompletion(int taskId, Boolean isCompleted){
        service.execute(new Runnable() {
            @Override
            public void run() {
                taskDAO.updateCompletionStatus(taskId, isCompleted);
            }
        });
    }

    public LiveData<List<Task>> getAllTasks(){
        return taskDAO.getAllTasks();
    }


}
