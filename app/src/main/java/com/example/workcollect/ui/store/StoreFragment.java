package com.example.workcollect.ui.store;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workcollect.R;
import com.example.workcollect.databinding.FragmentStoreBinding;

public class StoreFragment extends Fragment {

    private FragmentStoreBinding binding;
    private StoreItemAdapter storeItemAdapter;
    private StoreViewModel storeViewModel;

    public StoreFragment(){
        super(R.layout.fragment_store);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        binding = FragmentStoreBinding.bind(view);

        storeViewModel = new ViewModelProvider(this).get(StoreViewModel.class);

        storeItemAdapter = new StoreItemAdapter();

        binding.rVStoreItems.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rVStoreItems.setHasFixedSize(true);
        binding.rVStoreItems.setAdapter(storeItemAdapter);
        binding.rVStoreItems.addItemDecoration(new DividerItemDecoration(requireContext(),
                RecyclerView.VERTICAL));

        storeViewModel.storeItems.observe(getViewLifecycleOwner(), storeItemAdapter::submitList);

        //demo load
        if(storeViewModel.storeItems == null || storeViewModel.storeItems.getValue().isEmpty()){
            storeViewModel.loadInitial();
        }
    }

//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        StoreViewModel dashboardViewModel =
//                new ViewModelProvider(this).get(StoreViewModel.class);
//
//        binding = FragmentStoreBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
////        final TextView textView = binding.textDashboard;
////        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}