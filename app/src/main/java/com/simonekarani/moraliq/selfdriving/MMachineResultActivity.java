package com.simonekarani.moraliq.selfdriving;

import android.os.Bundle;

import com.simonekarani.moraliq.R;

import androidx.appcompat.app.AppCompatActivity;

public class MMachineResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_results);
        setTitle("Moral Machine Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
