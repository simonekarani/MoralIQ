package com.simonekarani.moraliq.medethics;

import android.os.Bundle;

import com.simonekarani.moraliq.R;

import androidx.appcompat.app.AppCompatActivity;

public class MedEthicalResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medethics_results);
        setTitle("Moral Dilemma Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
