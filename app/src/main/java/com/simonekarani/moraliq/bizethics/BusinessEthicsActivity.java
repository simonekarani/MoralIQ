//
//  BusinessEthicsActivity.java
//  MoralIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 MoralIQ. All rights reserved.
//

package com.simonekarani.moraliq.bizethics;

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
import com.simonekarani.moraliq.model.MainScreenDataModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class BusinessEthicsActivity extends AppCompatActivity {

    private static final int MAX_BIZTHICS_COUNT = 25;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<MainScreenDataModel> data;
    private View.OnClickListener myOnClickListener;

    private TextView bizthicsTextView = null;
    private ImageView bizthicsImageView = null;
    private RadioGroup bizthicsBtnGroup = null;
    private RadioButton bizthicsOptBtn1 = null;
    private RadioButton bizthicsOptBtn2 = null;
    private RadioButton bizthicsOptBtn3 = null;
    private RadioButton bizthicsOptBtn4 = null;

    private ArrayList<BusinessEthicsResult> mBizthicsResultList = new ArrayList<>();
    private Set<Integer> mBizthicsDataSet = new HashSet<>();
    private int userResultCount = 0;
    private int currBizthicsDataIdx = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizethics);
        setTitle("Business Ethics IQ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currBizthicsDataIdx = 0;
        userResultCount = 0;

        bizthicsTextView = (TextView) findViewById(R.id.bizthicsText);
        bizthicsImageView = (ImageView) findViewById(R.id.bizthicsImage);
        bizthicsBtnGroup = (RadioGroup) findViewById(R.id.optButtonGroup);
        bizthicsOptBtn1  = (RadioButton) findViewById(R.id.optButton1);
        bizthicsOptBtn2  = (RadioButton) findViewById(R.id.optButton2);
        bizthicsOptBtn3  = (RadioButton) findViewById(R.id.optButton3);
        bizthicsOptBtn4  = (RadioButton) findViewById(R.id.optButton4);

        myOnClickListener = (View.OnClickListener) new MyOnClickListener(this);
        bizthicsOptBtn1.setOnClickListener(myOnClickListener);
        bizthicsOptBtn2.setOnClickListener(myOnClickListener);
        bizthicsOptBtn3.setOnClickListener(myOnClickListener);
        bizthicsOptBtn4.setOnClickListener(myOnClickListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateDilemmaView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        bizthicsBtnGroup.clearCheck();

        if (userResultCount < MAX_BIZTHICS_COUNT) {
            updateDilemmaView();
        }
        else {
            Intent intent = new Intent(this, BusinessEthicsResultActivity.class);
            Bundle resultBundle = new Bundle();
            resultBundle.putParcelableArrayList("bizEthicsResult", mBizthicsResultList);
            intent.putExtras(resultBundle);
            startActivity(intent);
        }
    }

    private void updateDilemmaView() {
        do {
            currBizthicsDataIdx = (int)(BusinessEthicsData.BizEthicsDataList.length * Math.random());
        } while (mBizthicsDataSet.contains(currBizthicsDataIdx));
        mBizthicsDataSet.add(currBizthicsDataIdx);
        BusinessEthicsModel bizthicsData = BusinessEthicsData.BizEthicsDataList[currBizthicsDataIdx];

        bizthicsTextView.setTextSize(bizthicsData.getQuestionFontSize());
        bizthicsTextView.setText(bizthicsData.getQuestion());

        bizthicsImageView.setImageResource(bizthicsData.getImageResId());
        bizthicsOptBtn1.setTextSize(bizthicsData.getOptionFontSize());
        bizthicsOptBtn1.setText(bizthicsData.getOption1());
        bizthicsOptBtn2.setTextSize(bizthicsData.getOptionFontSize());
        bizthicsOptBtn2.setText(bizthicsData.getOption2());
        bizthicsOptBtn3.setTextSize(bizthicsData.getOptionFontSize());
        bizthicsOptBtn3.setText(bizthicsData.getOption3());
        bizthicsOptBtn4.setTextSize(bizthicsData.getOptionFontSize());
        bizthicsOptBtn4.setText(bizthicsData.getOption4());

        bizthicsOptBtn1.setPadding(0, 0, 0, bizthicsData.getBtnGap());
        bizthicsOptBtn2.setPadding(0, 0, 0, bizthicsData.getBtnGap());
        bizthicsOptBtn3.setPadding(0, 0, 0, bizthicsData.getBtnGap());
        bizthicsOptBtn4.setPadding(0, 0, 0, 0);
    }

    private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int selectedId = bizthicsBtnGroup.getCheckedRadioButtonId();
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
            BusinessEthicsResult result = new BusinessEthicsResult(currBizthicsDataIdx, selectedOptIdx);
            mBizthicsResultList.add(result);
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