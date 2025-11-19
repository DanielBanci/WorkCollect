package com.example.workcollect.ui.store;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workcollect.databinding.StoreItemBinding;
import com.example.workcollect.databinding.TaskItemBinding;
import com.example.workcollect.ui.tasks.Task;
import com.example.workcollect.ui.tasks.TaskAdapter;
import com.example.workcollect.ui.tasks.TaskDiff;

public class StoreItemAdapter extends ListAdapter<StoreItem, StoreItemAdapter.StoreItemViewHolder> {
    static class StoreItemViewHolder extends RecyclerView.ViewHolder{
        final StoreItemBinding storeItemBinding;
        StoreItemViewHolder(StoreItemBinding binding){
            super((binding.getRoot()));
            storeItemBinding = binding;
        }
    }

    public StoreItemAdapter(){
        super(new StoreItemDiff());
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public StoreItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        StoreItemBinding storeItemBinding = StoreItemBinding.inflate(LayoutInflater.from(
                viewGroup.getContext()), viewGroup, false);
        return new StoreItemViewHolder(storeItemBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StoreItemViewHolder holder, int position) {
        StoreItem storeItem = getItem(position);
        holder.storeItemBinding.tVStoreItemTitle.setText(storeItem.getStrTitle());
        holder.storeItemBinding.tVStoreItemValue.setText(storeItem.getiValue().toString() +
                " (" + storeItem.getStrItemTypeMapKey() + ")");
    }

    @Override
    public long getItemId(int position){
        return getItem(position).getlId().hashCode();
    }
}
