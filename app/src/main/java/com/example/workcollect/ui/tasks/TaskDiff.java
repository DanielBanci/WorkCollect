package com.example.workcollect.ui.tasks;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class TaskDiff extends DiffUtil.ItemCallback<Task> {
    @Override
    public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
        if(oldItem.getStrId() == null || newItem.getStrId() == null) return false;
        return oldItem.getStrId().equals(newItem.getStrId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
        //TODO: maybe not the best as getPoints can return null
        return oldItem.getStrTitle().equals(newItem.getStrTitle()) &&
                oldItem.getStrDifficultyLevelKey().equals(
                        newItem.getStrDifficultyLevelKey()) &&
                oldItem.getPoints().equals(newItem.getPoints()) ;/*&&
                        oldItem.getDate().isEqual(newItem.getDate());*/
    }
}
