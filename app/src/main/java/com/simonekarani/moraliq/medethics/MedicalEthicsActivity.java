//
//  MedicalEthicsActivity.java
//  EthicalIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 EthicalIQ. All rights reserved.
//

package com.simonekarani.moraliq.medethics;

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
import com.simonekarani.moraliq.model.MainScreenDataModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MedicalEthicsActivity extends AppCompatActivity {

    private static final int MAX_MED_ETHICAL_COUNT = 15;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<MainScreenDataModel> data;
    static View.OnClickListener myOnClickListener;

    private TextView medTextView = null;
    private ImageView medImageView = null;
    private TextView medOptTextView = null;
    private RadioGroup medBtnGroup = null;
    private RadioButton medOptBtn1 = null;
    private RadioButton medOptBtn2 = null;
    private RadioButton medOptBtn3 = null;
    private RadioButton medOptBtn4 = null;

    private ArrayList<MedEthicalResult> medEthicalResultList = new ArrayList<>();
    private Set<Integer> medEthicalDataSet = new HashSet<>();
    private int userResultCount = 0;
    private int currMedDataIdx = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medethics);
        setTitle("Medical Moral IQ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        medTextView = (TextView) findViewById(R.id.medEthicalText);
        medImageView = (ImageView) findViewById(R.id.medEthicalImage);
        medOptTextView = (TextView) findViewById(R.id.medOptionText);
        medBtnGroup = (RadioGroup) findViewById(R.id.medOptGroup);
        medOptBtn1  = (RadioButton) findViewById(R.id.medOpt1);
        medOptBtn2  = (RadioButton) findViewById(R.id.medOpt2);
        medOptBtn3  = (RadioButton) findViewById(R.id.medOpt3);
        medOptBtn4  = (RadioButton) findViewById(R.id.medOpt4);

        myOnClickListener = (View.OnClickListener) new MedicalEthicsActivity.MyOnClickListener(this);
        medOptBtn1.setOnClickListener(myOnClickListener);
        medOptBtn2.setOnClickListener(myOnClickListener);
        medOptBtn3.setOnClickListener(myOnClickListener);
        medOptBtn4.setOnClickListener(myOnClickListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        userResultCount = 0;
        currMedDataIdx = -1;
        updateMedEthicalView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        medBtnGroup.clearCheck();

        if (userResultCount < MAX_MED_ETHICAL_COUNT) {
            updateMedEthicalView();
        }
        else {
            Intent intent = new Intent(this, MedEthicalResultActivity.class);
            Bundle resultBundle = new Bundle();
            resultBundle.putParcelableArrayList("medEthicalResult", medEthicalResultList);
            intent.putExtras(resultBundle);
            startActivity(intent);
        }
    }

    private void updateMedEthicalView() {
        do {
            currMedDataIdx = (int)(MedicalEthicalData.MED_ETHICAL_DATA_LIST.length * Math.random());
        } while (medEthicalDataSet.contains(currMedDataIdx));
        medEthicalDataSet.add(currMedDataIdx);
        MedicalEthicalModel medData = MedicalEthicalData.MED_ETHICAL_DATA_LIST[currMedDataIdx];

        medTextView.setTextSize(medData.getQuestionFontSize());
        medTextView.setText(medData.getQuestion());

        medImageView.setImageResource(medData.getImageResId());
        if (medData.getButtonCount() == 3) {
            medOptTextView.setTextSize(24);
        }
        else {
            medOptTextView.setTextSize(22);
        }
        medOptBtn1.setTextSize(medData.getOptionFontSize());
        medOptBtn1.setText(medData.getOption1());
        medOptBtn2.setTextSize(medData.getOptionFontSize());
        medOptBtn2.setText(medData.getOption2());
        medOptBtn3.setTextSize(medData.getOptionFontSize());
        medOptBtn3.setText(medData.getOption3());
        int paddingGap = 20;
        if (medData.getButtonCount() == 3) {
            medOptBtn4.setVisibility(View.INVISIBLE);
        }
        else {
            paddingGap = 7;
            medOptBtn4.setVisibility(View.VISIBLE);
            medOptBtn4.setTextSize(medData.getOptionFontSize());
            medOptBtn4.setText(medData.getOption4());
        }

        medOptBtn1.setPadding(0, 0, 0, paddingGap);
        medOptBtn2.setPadding(0, 0, 0, paddingGap);
        medOptBtn3.setPadding(0, 0, 0, paddingGap);
        medOptBtn4.setPadding(0, 0, 0, 0);
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

    private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int selectedId = medBtnGroup.getCheckedRadioButtonId();
            int selectedOptIdx = -1;
            switch (selectedId) {
                case R.id.medOpt1:
                    selectedOptIdx = 0;
                    break;
                case R.id.medOpt2:
                    selectedOptIdx = 1;
                    break;
                case R.id.medOpt3:
                    selectedOptIdx = 2;
                    break;
                case R.id.medOpt4:
                    selectedOptIdx = 3;
                    break;
                default:
                    selectedOptIdx = 0;
                    break;
            }
            MedEthicalResult result = new MedEthicalResult(currMedDataIdx, selectedOptIdx);
            medEthicalResultList.add(result);
            userResultCount++;
            onRestart();
        }
    }
}