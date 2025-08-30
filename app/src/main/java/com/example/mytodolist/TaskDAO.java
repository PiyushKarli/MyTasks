package com.example.mytodolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDAO {

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM tasks_table")
    LiveData<List<Task>> getAllTasks();

    @Query("UPDATE tasks_table SET isCompleted = :isCompleted WHERE id = :taskId")
    void updateCompletionStatus(int taskId, boolean isCompleted);

}
