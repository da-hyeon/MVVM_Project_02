package com.example.hwangdahyeon.mvvm_project_02;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hwangdahyeon.mvvm_project_02.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private InputViewModel model = new InputViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this , R.layout.activity_main);

        binding.setModel(model);

        model.onCreate();
    }
}
