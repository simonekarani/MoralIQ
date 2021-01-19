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
import android.widget.Button;
import android.widget.TextView;

import com.simonekarani.moraliq.R;
import com.simonekarani.moraliq.model.MainScreenDataModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class BusinessEthicsActivity extends AppCompatActivity {

    private static final int MAX_BIZTHICS_COUNT = 7;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<MainScreenDataModel> data;
    private View.OnClickListener myOnClickListener;

    private TextView bizthicsTextView = null;
    private ImageView bizthicsImageView = null;
    private Button bizthicsOptBtn1 = null;
    private Button bizthicsOptBtn2 = null;
    private Button bizthicsOptBtn3 = null;
    private Button bizthicsOptBtn4 = null;

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
        bizthicsOptBtn1  = (Button) findViewById(R.id.bizOptButton1);
        bizthicsOptBtn2  = (Button) findViewById(R.id.bizOptButton2);
        bizthicsOptBtn3  = (Button) findViewById(R.id.bizOptButton3);
        bizthicsOptBtn4  = (Button) findViewById(R.id.bizOptButton4);

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
        bizthicsOptBtn1.setPressed(false);
        bizthicsOptBtn2.setPressed(false);
        bizthicsOptBtn3.setPressed(false);
        bizthicsOptBtn4.setPressed(false);

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
            int selectedOptIdx = -1;
            if (bizthicsOptBtn1.isPressed()) {
                selectedOptIdx = 0;
            } else if (bizthicsOptBtn2.isPressed()) {
                selectedOptIdx = 1;
            } else if (bizthicsOptBtn3.isPressed()) {
                selectedOptIdx = 2;
            } else if (bizthicsOptBtn4.isPressed()) {
                selectedOptIdx = 3;
            } else {
                selectedOptIdx = 0;
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