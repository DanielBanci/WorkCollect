package com.example.workcollect.ui.tasks;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workcollect.R;
import com.example.workcollect.databinding.FragmentTasksBinding;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Locale;

public class TasksFragment extends Fragment {

    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("EEEE, d MMMM",Locale.getDefault());
    private FragmentTasksBinding binding;
    private TaskAdapter taskAdapter;
    private TaskViewModel taskViewModel;

    public TasksFragment(){
        super(R.layout.fragment_tasks);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        binding = FragmentTasksBinding.bind(view);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        taskAdapter = new TaskAdapter();

        binding.rVTaskList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rVTaskList.setHasFixedSize(true);
        binding.rVTaskList.setAdapter(taskAdapter);
        binding.rVTaskList.addItemDecoration(new DividerItemDecoration(requireContext(),
                RecyclerView.VERTICAL));

        // buttons
        binding.bNextDay.setOnClickListener(e -> taskViewModel.setNextDay());
        binding.bPrevDay.setOnClickListener(e -> taskViewModel.setPreviousDay());

        taskViewModel.getSelectedDate().observe(getViewLifecycleOwner(), d -> {
            binding.tVCurrentDay.setText(formatDate(d));
        });

        taskViewModel.getDayRelevantTasks().observe(getViewLifecycleOwner(), taskAdapter::submitList);

        //demo load
        if(taskViewModel.getTasks() == null || taskViewModel.getTasks().getValue().isEmpty()){
            taskViewModel.loadInitial();
        }

        // FAB action
        binding.fABAddTask.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.navigate_to_add_task_action));
    }

    private String formatDate(LocalDate d){
        return d == null ? "-" : dateTimeFormatter.format(d);
    }

//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        TaskViewModel taskViewModel =
//                new ViewModelProvider(this).get(TaskViewModel.class);
//
//        binding = FragmentTasksBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
////        final TextView textView = binding.textHome;
////        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}