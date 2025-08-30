package com.example.mytodolist;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class AddNewTaskClickHandler {
    Task task;
    Context context;
    TaskViewModel viewModel;

    public AddNewTaskClickHandler(Task task, Context context, TaskViewModel viewModel) {
        this.task = task;
        this.context = context;
        this.viewModel = viewModel;
    }

    public void onAddBtnClicked(View view){
        if (task.getTask() == null || task.getDescription() == null){
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }else{
            Intent i = new Intent(context, MainActivity.class);

            Task t = new Task(task.getTask(), task.getDescription(), task.getCompleted());
            viewModel.addTask(t);
            context.startActivity(i);
        }
    }
}
