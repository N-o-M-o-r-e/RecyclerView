package com.project.tathanhson.recyclerview.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.project.tathanhson.recyclerview.MyApplication;
import com.project.tathanhson.recyclerview.databinding.ActivityListIconBinding;
import com.project.tathanhson.recyclerview.model.IconModel;
import com.project.tathanhson.recyclerview.view.adapter.IconAdapter;
import com.project.tathanhson.recyclerview.viewmodel.MainViewModel;

import java.util.List;

public class ListIconActivity extends AppCompatActivity {
    private ActivityListIconBinding binding;
    private final String TAG = "AAAAAAAAAAA";
    private MainViewModel viewModel;

    private IconAdapter adapter;
    List<IconModel> listItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListIconBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
    }

    private void initData() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.readStoryFile(getAssets());

        listItem = viewModel.getListStory();
        adapter = new IconAdapter(this, listItem);
        binding.rcvIcon.setAdapter(adapter);

        adapter.getStoryLivaData().observe(this, storyModel -> {
            if (storyModel == null) return;
            openStoryDetail(storyModel);
        });

    }

    private void openStoryDetail(IconModel iconModel) {
        MyApplication.getInstance().getStorage().listIconModel = viewModel.getListStory();
        MyApplication.getInstance().getStorage().iconModel = iconModel;


        Intent i = new Intent(this, DetailIconActivity.class);
        startActivity(i);
    }

}