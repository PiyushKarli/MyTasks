package com.example.mytodolist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 2)
public abstract class TaskDatabase extends RoomDatabase {

    public abstract TaskDAO getTaskDAO();

    private static TaskDatabase dbInstance;

    public static synchronized TaskDatabase getInstance(Context context){
        if (dbInstance == null){
            dbInstance = Room.databaseBuilder(context.getApplicationContext(), TaskDatabase.class,"tasks_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }
}
