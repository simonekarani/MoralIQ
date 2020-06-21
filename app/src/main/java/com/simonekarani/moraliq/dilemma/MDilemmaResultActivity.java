package com.simonekarani.moraliq.dilemma;

import android.os.Bundle;

import com.simonekarani.moraliq.R;

import androidx.appcompat.app.AppCompatActivity;

public class MDilemmaResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dilemma_results);
        setTitle("Moral Dilemma Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
