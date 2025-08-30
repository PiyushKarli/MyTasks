package com.example.mytodolist;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodolist.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskCheckedListener{

    private TaskDatabase taskDatabase;
    private ArrayList<Task> taskArrayList = new ArrayList<>();
    private TaskAdapter taskAdapter;
    private MainActivityClickHandler clickHandler;
    private ActivityMainBinding mainBinding;
    private TaskViewModel taskViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        clickHandler = new MainActivityClickHandler(this);
        mainBinding.setClickHandler(clickHandler);

        RecyclerView recyclerView = mainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        taskDatabase = TaskDatabase.getInstance(this);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        taskAdapter = new TaskAdapter(taskArrayList, this);

        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {

                taskAdapter.setTaskArrayList(new ArrayList<>(tasks));
            }


        });

        recyclerView.setAdapter(taskAdapter);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {

            @Override
            public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                Task task = taskAdapter.getTaskAtPos(viewHolder.getAbsoluteAdapterPosition());
                if (task.getCompleted()){
                    return ItemTouchHelper.LEFT;
                }else
                    return 0;
                }



            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                Task task = taskAdapter.getTaskAtPos(viewHolder.getAbsoluteAdapterPosition());
                taskViewModel.deleteTask(task);
            }
        }).attachToRecyclerView(recyclerView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onTaskChecked(Task task) {

        taskViewModel.updateTask(task);
    }
}