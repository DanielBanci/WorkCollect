package com.example.workcollect.ui.tasks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.workcollect.R;
import com.example.workcollect.databinding.FragmentBottomSheetAddTaskBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class AddTaskBottomSheetFragment extends BottomSheetDialogFragment {
    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.getDefault());
    private AddTaskViewModel addTaskViewModel;
    private FragmentBottomSheetAddTaskBinding binding;
    private EditText eTTitle;
    private Spinner sDifficultyLevel;

//    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstance){
        View root = inflater.inflate(R.layout.fragment_bottom_sheet_add_task, viewGroup, false);
        ArrayList<String> levels = new ArrayList<>(DifficultyLevelMap.map.keySet());
        sDifficultyLevel = root.findViewById(R.id.sDifficultyLevel);
        sDifficultyLevel.setAdapter(new ArrayAdapter<>(requireContext(),R.layout.simple_list_item,levels));
        eTTitle = root.findViewById(R.id.eTTitle);
        ((Button) root.findViewById(R.id.bAddTask)).setOnClickListener(v -> saveTask());

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstance){
        addTaskViewModel = new ViewModelProvider(this).get(AddTaskViewModel.class);

        addTaskViewModel.getSaving().observe(getViewLifecycleOwner(),
                saving -> view.findViewById(R.id.bAddTask).setEnabled(!saving));
        addTaskViewModel.getError().observe(getViewLifecycleOwner(),
                e -> {if(e != null) Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                });

        binding = FragmentBottomSheetAddTaskBinding.bind(view);
        binding.tVDate.setText(formatDate(addTaskViewModel.task.getDate()));
        binding.bEditTaskDate.setOnClickListener(v -> EditTaskDateAction());
        binding.taskDatePicker.init(addTaskViewModel.task.getDate().getYear(),
                addTaskViewModel.task.getDate().getMonthValue() - 1,
                addTaskViewModel.task.getDate().getDayOfMonth(), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                        LocalDate newDate = LocalDate.of(i, i1 + 1, i2);
                        binding.tVDate.setText(formatDate(newDate));
                        addTaskViewModel.task.setDate(newDate);
                    }
                });

//        ArrayList<String> levels = new ArrayList<>(DifficultyLevelMap.map.keySet());
//        binding.sDifficultyLevel.setAdapter(new ArrayAdapter<>(requireContext(),R.layout.simple_list_item,levels));
//
//        binding.bAddTask.setOnClickListener(v -> saveTask());
    }

    private void EditTaskDateAction(){
        if(binding.taskDatePicker.getVisibility() == View.GONE){
            binding.taskDatePicker.setVisibility(View.VISIBLE);
        }else {
            binding.taskDatePicker.setVisibility(View.GONE);
        }

    }

    private String formatDate(LocalDate d){
        return d == null ? "-" : dateTimeFormatter.format(d);
    }

    @Override
    public int getTheme(){
        return R.style.ThemeOverlay_BottomSheet_Fullscreen;
    }

    private void saveTask(){
        Task task = new Task("l", eTTitle.getText().toString(),
                sDifficultyLevel.getSelectedItem().toString(), addTaskViewModel.task.getDate());

        addTaskViewModel.addTask("1", task, () -> {
            Toast.makeText(requireContext(), "Task created", Toast.LENGTH_LONG).show();
            dismiss();
        });

        System.out.println("Task successfully created " + task);
    }
}
