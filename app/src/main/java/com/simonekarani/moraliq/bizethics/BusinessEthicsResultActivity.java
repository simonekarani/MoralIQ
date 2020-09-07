package com.simonekarani.moraliq.bizethics;

import android.os.Bundle;

import com.simonekarani.moraliq.R;

import androidx.appcompat.app.AppCompatActivity;

public class BusinessEthicsResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizethics_results);
        setTitle("Business Ethics Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
