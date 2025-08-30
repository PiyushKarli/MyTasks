package com.example.mytodolist;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodolist.databinding.TaskItemLayoutBinding;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    ArrayList<Task> taskArrayList;
    TaskCheckedListener taskCheckedListener;

    public TaskAdapter(ArrayList<Task> taskArrayList, TaskCheckedListener taskCheckedListener) {
        this.taskArrayList = taskArrayList;
        this.taskCheckedListener = taskCheckedListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TaskItemLayoutBinding taskItemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.task_item_layout,parent,false);

        return new TaskViewHolder(taskItemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task currentTask = taskArrayList.get(position);

        holder.taskItemLayoutBinding.setTask(currentTask);//bind data to layout

        holder.taskItemLayoutBinding.isCompleted.setChecked(currentTask.getCompleted());//set intial state of checkbox


        if (currentTask.getCompleted()){
            holder.taskItemLayoutBinding.textView.setPaintFlags(holder.taskItemLayoutBinding.textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG) ;
            holder.taskItemLayoutBinding.textView2.setPaintFlags(holder.taskItemLayoutBinding.textView2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }else {
            holder.taskItemLayoutBinding.textView.setPaintFlags(holder.taskItemLayoutBinding.textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.taskItemLayoutBinding.textView2.setPaintFlags(holder.taskItemLayoutBinding.textView2.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

        }




        holder.taskItemLayoutBinding.isCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CompoundButton) view).isChecked();
                currentTask.setCompleted(isChecked);       //update the task object anf notify the listener
                taskCheckedListener.onTaskChecked(currentTask);
            }
        });



    }

    @Override
    public int getItemCount() {
       if (taskArrayList != null){
           return taskArrayList.size();
       }else {
           return 0;
       }
    }

    public void setTaskArrayList(ArrayList<Task> taskArrayList){
        this.taskArrayList = taskArrayList;
        notifyDataSetChanged();
    }

    public Task getTaskAtPos(int position){
        return taskArrayList.get(position);
    }


    class TaskViewHolder extends RecyclerView.ViewHolder{

        private TaskItemLayoutBinding taskItemLayoutBinding;

        public TaskViewHolder(@NonNull  TaskItemLayoutBinding taskItemLayoutBinding) {
            super(taskItemLayoutBinding.getRoot());
            this.taskItemLayoutBinding = taskItemLayoutBinding;
        }
    }
}
