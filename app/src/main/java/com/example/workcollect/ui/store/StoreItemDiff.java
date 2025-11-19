package com.example.workcollect.ui.store;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class StoreItemDiff extends DiffUtil.ItemCallback<StoreItem> {
    @Override
    public boolean areItemsTheSame(@NonNull StoreItem oldItem, @NonNull StoreItem newItem) {
        return oldItem.getlId().equals(newItem.getlId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull StoreItem oldItem, @NonNull StoreItem newItem) {                   //TODO: update needed
        return oldItem.getStrTitle().equals(newItem.getStrTitle());
    }
}
