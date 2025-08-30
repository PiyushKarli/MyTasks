package com.example.mytodolist;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mytodolist.databinding.ActivityAddNewTaskBinding;

public class AddNewTaskActivity extends AppCompatActivity {

    private ActivityAddNewTaskBinding binding;
    private AddNewTaskClickHandler handler;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_task);



        task = new Task();
        binding= DataBindingUtil.setContentView(this, R.layout.activity_add_new_task);

        TaskViewModel taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        handler = new AddNewTaskClickHandler(task,this,taskViewModel);
        binding.setTask(task);
        binding.setClickHandler(handler);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}