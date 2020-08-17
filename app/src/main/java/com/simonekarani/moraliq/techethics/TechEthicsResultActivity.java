//  TechEthicsResultActivity.java
//  MoralIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 MoralIQ. All rights reserved.
//

package com.simonekarani.moraliq.techethics;

import android.os.Bundle;

import com.simonekarani.moraliq.R;

import androidx.appcompat.app.AppCompatActivity;

public class TechEthicsResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_techethics_results);
        setTitle("Tech Ethics Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
