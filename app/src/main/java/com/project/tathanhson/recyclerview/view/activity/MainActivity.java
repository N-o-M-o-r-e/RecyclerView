package com.project.tathanhson.recyclerview.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.project.tathanhson.recyclerview.MyApplication;
import com.project.tathanhson.recyclerview.databinding.ActivityMainBinding;
import com.project.tathanhson.recyclerview.model.IconModel;
import com.project.tathanhson.recyclerview.view.adapter.IconAdapter;
import com.project.tathanhson.recyclerview.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final String TAG = "AAAAAAAAAAA";
    private MainViewModel viewModel;

    private IconAdapter adapter;
    List<IconModel> listItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
    }

    private void initData() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class); //khởi tạo viewModel
        viewModel.readStoryFile(getAssets());

        listItem = viewModel.getListStory(); //từ viewmodel lấy ra danh sách
        adapter = new IconAdapter(this, listItem); // đổ dữ liệu vào Adapter cần context và list
        binding.rcvIcon.setAdapter(adapter); // setAdapter cho recyclerView

        adapter.getStoryLivaData().observe(this, storyModel -> {
            if (storyModel == null) return;
            openStoryDetail(storyModel);
        });

    }

    private void openStoryDetail(IconModel iconModel) {
        MyApplication.getInstance().getStorage().listIconModel = viewModel.getListStory();
        MyApplication.getInstance().getStorage().iconModel = iconModel;
        MyApplication.getInstance().getStorage().title = viewModel.getTopicName();


        Intent i = new Intent(this, SecondActivity.class);
        startActivity(i);
    }

}