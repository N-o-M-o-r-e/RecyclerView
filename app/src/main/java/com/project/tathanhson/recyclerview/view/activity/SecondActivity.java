package com.project.tathanhson.recyclerview.view.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.project.tathanhson.recyclerview.MyApplication;
import com.project.tathanhson.recyclerview.databinding.ActivitySecondBinding;
import com.project.tathanhson.recyclerview.view.adapter.DetaiIconAdapter;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        DetaiIconAdapter adapter = new DetaiIconAdapter(this, MyApplication.getInstance().getStorage().listIconModel);
        binding.viewPager.setAdapter(adapter);

        binding.actionBar.btnBack.setOnClickListener(view -> {
            actionClick();
        });

        int selectedIndex = MyApplication.getInstance().getStorage().listIconModel.indexOf(MyApplication.getInstance().getStorage().iconModel);
        binding.viewPager.setCurrentItem(selectedIndex, true);

        Log.e("AAAAAAA", "index: " + selectedIndex);
    }

    private void actionClick() {
        getOnBackPressedDispatcher().onBackPressed();
    }
}