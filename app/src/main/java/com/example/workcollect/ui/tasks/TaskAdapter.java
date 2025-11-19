package com.example.workcollect.ui.tasks;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workcollect.databinding.TaskItemBinding;

public class TaskAdapter extends ListAdapter<Task, TaskAdapter.TaskViewHolder> {
    static class TaskViewHolder extends RecyclerView.ViewHolder{
        final TaskItemBinding taskItemBinding;
        TaskViewHolder(TaskItemBinding binding){
            super((binding.getRoot()));
            taskItemBinding = binding;
        }
    }

    public TaskAdapter(){
        super(new TaskDiff());
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        TaskItemBinding taskItemBinding = TaskItemBinding.inflate(LayoutInflater.from(
                viewGroup.getContext()), viewGroup, false);
        return new TaskViewHolder(taskItemBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = getItem(position);
        holder.taskItemBinding.tVTaskName.setText(task.getStrTitle());
        holder.taskItemBinding.tVPoints.setText(task.getPoints().toString() +
                " (" + task.getStrDifficultyLevelKey() + ")");
    }

    @Override
    public long getItemId(int position){
        return getItem(position).getStrId().hashCode();
    }
}
