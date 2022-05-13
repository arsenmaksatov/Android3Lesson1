package com.example.android3lesson1.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.android3lesson1.R;
import com.example.android3lesson1.databinding.ActivityMainBinding;
import com.example.android3lesson1.models.NameModel;
import com.example.android3lesson1.ui.adapter.ListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private ListAdapter listAdapter = new ListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialization();
        setupListener();
    }

    private void initialization() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.recycler1.setAdapter(listAdapter);
    }
    private void addObserver(){
        viewModel.mutableLiveData.observe(this, new Observer<List<NameModel>>() {
            @Override
            public void onChanged(List<NameModel> nameModels) {
                listAdapter.setData(nameModels);
            }
        });
    }

    private void setupListener() {
        binding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addObserver();
                viewModel.getList();
                binding.btnShow.setVisibility(View.GONE);
            }
        });
    }
}