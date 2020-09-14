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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.simonekarani.moraliq.R;
import com.simonekarani.moraliq.dilemma.MDilemmaResultActivity;
import com.simonekarani.moraliq.dilemma.MoralDilemmaActivity;
import com.simonekarani.moraliq.dilemma.MoralDilemmaData;
import com.simonekarani.moraliq.dilemma.MoralDilemmaModel;
import com.simonekarani.moraliq.dilemma.MoralDilemmaResult;
import com.simonekarani.moraliq.medethics.MedEthicalResultActivity;
import com.simonekarani.moraliq.model.MainScreenDataModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class TechEthicsActivity extends AppCompatActivity {

    private static final int MAX_TECHTHICS_COUNT = 15;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<MainScreenDataModel> data;
    private View.OnClickListener myOnClickListener;

    private TextView techthicsTextView = null;
    private ImageView techthicsImageView = null;
    private RadioGroup techthicsBtnGroup = null;
    private RadioButton techthicsOptBtn1 = null;
    private RadioButton techthicsOptBtn2 = null;
    private RadioButton techthicsOptBtn3 = null;
    private RadioButton techthicsOptBtn4 = null;

    private ArrayList<TechEthicsResult> mTechthicsResultList = new ArrayList<>();
    private Set<Integer> mTechthicsDataSet = new HashSet<>();
    private int userResultCount = 0;
    private int currDilemmaDataIdx = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dilemma);
        setTitle("Technology Ethics");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currDilemmaDataIdx = 0;
        userResultCount = 0;

        techthicsTextView = (TextView) findViewById(R.id.dilemmaText);
        techthicsImageView = (ImageView) findViewById(R.id.dilemmaImage);
        techthicsBtnGroup = (RadioGroup) findViewById(R.id.optButtonGroup);
        techthicsOptBtn1  = (RadioButton) findViewById(R.id.optButton1);
        techthicsOptBtn2  = (RadioButton) findViewById(R.id.optButton2);
        techthicsOptBtn3  = (RadioButton) findViewById(R.id.optButton3);
        techthicsOptBtn4  = (RadioButton) findViewById(R.id.optButton4);

        myOnClickListener = (View.OnClickListener) new TechEthicsActivity.MyOnClickListener(this);
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
        techthicsBtnGroup.clearCheck();

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
            int selectedId = techthicsBtnGroup.getCheckedRadioButtonId();
            int selectedOptIdx = -1;
            switch (selectedId) {
                case R.id.optButton1:
                    selectedOptIdx = 0;
                    break;
                case R.id.optButton2:
                    selectedOptIdx = 1;
                    break;
                case R.id.optButton3:
                    selectedOptIdx = 2;
                    break;
                case R.id.optButton4:
                    selectedOptIdx = 3;
                    break;
                default:
                    selectedOptIdx = 0;
                    break;
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