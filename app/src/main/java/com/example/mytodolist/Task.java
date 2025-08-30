package com.example.mytodolist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks_table")
public class Task {

    @PrimaryKey (autoGenerate = true)
    int id;
    String task;
    String description;
    boolean isCompleted;

    public Task(String task, String description, boolean isCompleted) {
        this.task = task;
        this.description = description;
        this.isCompleted = false;
    }

    public Task() {
        this.isCompleted = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
