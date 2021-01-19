//
//  TechEthicsActivity.java
//  EthicalIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 EthicalIQ. All rights reserved.
//

package com.simonekarani.moraliq.techethics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;

import com.simonekarani.moraliq.R;
import com.simonekarani.moraliq.model.MainScreenDataModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class TechEthicsActivity extends AppCompatActivity {

    private static final int MAX_TECHTHICS_COUNT = 7;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<MainScreenDataModel> data;
    private View.OnClickListener myOnClickListener;

    private TextView techthicsTextView = null;
    private ImageView techthicsImageView = null;
    private Button techthicsOptBtn1 = null;
    private Button techthicsOptBtn2 = null;
    private Button techthicsOptBtn3 = null;
    private Button techthicsOptBtn4 = null;

    private ArrayList<TechEthicsResult> mTechthicsResultList = new ArrayList<>();
    private Set<Integer> mTechthicsDataSet = new HashSet<>();
    private int userResultCount = 0;
    private int currDilemmaDataIdx = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_techethics);
        setTitle("Technology Ethics");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currDilemmaDataIdx = 0;
        userResultCount = 0;

        techthicsTextView = (TextView) findViewById(R.id.techText);
        techthicsImageView = (ImageView) findViewById(R.id.techImage);
        techthicsOptBtn1  = (Button) findViewById(R.id.techButton1);
        techthicsOptBtn2  = (Button) findViewById(R.id.techButton2);
        techthicsOptBtn3  = (Button) findViewById(R.id.techButton3);
        techthicsOptBtn4  = (Button) findViewById(R.id.techButton4);

        myOnClickListener = (View.OnClickListener) new MyOnClickListener(this);
        techthicsOptBtn1.setOnClickListener(myOnClickListener);
        techthicsOptBtn2.setOnClickListener(myOnClickListener);
        techthicsOptBtn3.setOnClickListener(myOnClickListener);
        techthicsOptBtn4.setOnClickListener(myOnClickListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateDilemmaView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        techthicsOptBtn1.setPressed(false);
        techthicsOptBtn2.setPressed(false);
        techthicsOptBtn3.setPressed(false);
        techthicsOptBtn4.setPressed(false);

        if (userResultCount < MAX_TECHTHICS_COUNT) {
            updateDilemmaView();
        }
        else {
            Intent intent = new Intent(this, TechEthicsResultActivity.class);
            Bundle resultBundle = new Bundle();
            resultBundle.putParcelableArrayList("techEthicalResult", mTechthicsResultList);
            intent.putExtras(resultBundle);
            startActivity(intent);
        }
    }

    private void updateDilemmaView() {
        do {
            currDilemmaDataIdx = (int)(TechEthicsData.TechEthicsDataList.length * Math.random());
        } while (mTechthicsDataSet.contains(currDilemmaDataIdx));
        mTechthicsDataSet.add(currDilemmaDataIdx);
        TechEthicsModel techthicsData = TechEthicsData.TechEthicsDataList[currDilemmaDataIdx];

        techthicsTextView.setTextSize(techthicsData.getQuestionFontSize());
        techthicsTextView.setText(techthicsData.getQuestion());

        techthicsImageView.setImageResource(techthicsData.getImageResId());
        techthicsOptBtn1.setTextSize(techthicsData.getOptionFontSize());
        techthicsOptBtn1.setText(techthicsData.getOption1());
        techthicsOptBtn2.setTextSize(techthicsData.getOptionFontSize());
        techthicsOptBtn2.setText(techthicsData.getOption2());
        techthicsOptBtn3.setTextSize(techthicsData.getOptionFontSize());
        techthicsOptBtn3.setText(techthicsData.getOption3());
        techthicsOptBtn4.setTextSize(techthicsData.getOptionFontSize());
        techthicsOptBtn4.setText(techthicsData.getOption4());

        techthicsOptBtn1.setPadding(0, 0, 0, techthicsData.getBtnGap());
        techthicsOptBtn2.setPadding(0, 0, 0, techthicsData.getBtnGap());
        techthicsOptBtn3.setPadding(0, 0, 0, techthicsData.getBtnGap());
        techthicsOptBtn4.setPadding(0, 0, 0, 0);
    }

    private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int selectedOptIdx = -1;
            if (techthicsOptBtn1.isPressed()) {
                selectedOptIdx = 0;
            } else if (techthicsOptBtn2.isPressed()) {
                selectedOptIdx = 1;
            } else if (techthicsOptBtn3.isPressed()) {
                selectedOptIdx = 2;
            } else if (techthicsOptBtn4.isPressed()) {
                selectedOptIdx = 3;
            } else {
                selectedOptIdx = 0;
            }
            TechEthicsResult result = new TechEthicsResult(currDilemmaDataIdx, selectedOptIdx);
            mTechthicsResultList.add(result);
            userResultCount++;
            onRestart();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(getSupportFragmentManager().getBackStackEntryCount() > 0)
                    getSupportFragmentManager().popBackStack();
                break;
            default:
                super.onOptionsItemSelected(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}