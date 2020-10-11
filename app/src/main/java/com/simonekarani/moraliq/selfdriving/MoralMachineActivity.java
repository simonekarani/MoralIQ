//
//  MoralMachineActivity.java
//  EthicalIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 EthicalIQ. All rights reserved.
//

package com.simonekarani.moraliq.selfdriving;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.simonekarani.moraliq.R;
import com.simonekarani.moraliq.dilemma.MDilemmaResultActivity;
import com.simonekarani.moraliq.dilemma.MoralDilemmaActivity;
import com.simonekarani.moraliq.dilemma.MoralDilemmaData;
import com.simonekarani.moraliq.dilemma.MoralDilemmaModel;
import com.simonekarani.moraliq.dilemma.MoralDilemmaResult;
import com.simonekarani.moraliq.model.MainScreenDataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.RecyclerView;

public class MoralMachineActivity extends AppCompatActivity {

    private static final int MAX_MMACHINE_COUNT = 5;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<MainScreenDataModel> data;
    static View.OnClickListener myOnClickListener;

    private TextView drivingOpt1TextBtn = null;
    private TextView drivingOpt2TextBtn = null;
    private ImageButton drivingOpt1ImageBtn = null;
    private ImageButton drivingOpt2ImageBtn = null;

    private int userResultCount = 0;
    private int currMachineDataIdx = 8;
    private int mMachineTestCount = 0;
    private ArrayList<MoralMachineResult> moralMachineResultList = new ArrayList<>();
    private Set<Integer> mMachineDataSet = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving);
        setTitle("Moral Machine IQ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMachineTestCount = 0;

        drivingOpt1TextBtn = (Button) findViewById(R.id.optionText1);
        drivingOpt2TextBtn = (Button) findViewById(R.id.optionText2);
        drivingOpt1ImageBtn = (ImageButton) findViewById(R.id.drivingOptImg1);
        drivingOpt2ImageBtn = (ImageButton) findViewById(R.id.drivingOptImg2);

        myOnClickListener = (View.OnClickListener) new MoralMachineActivity.MyOnClickListener(this);
        drivingOpt1ImageBtn.setOnClickListener(myOnClickListener);
        drivingOpt1TextBtn.setOnClickListener(myOnClickListener);
        drivingOpt2ImageBtn.setOnClickListener(myOnClickListener);
        drivingOpt2TextBtn.setOnClickListener(myOnClickListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateMoralMachineView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mMachineTestCount < MAX_MMACHINE_COUNT) {
            updateMoralMachineView();
        }
        else {
            Intent intent = new Intent(this, MMachineResultActivity.class);
            Bundle resultBundle = new Bundle();
            resultBundle.putParcelableArrayList("drivingResult", moralMachineResultList);
            intent.putExtras(resultBundle);
            startActivity(intent);
        }
    }

    private void updateMoralMachineView() {
        /*do {
            currMachineDataIdx = (int) (MoralMachineData.MoralMachineDataList.length * Math.random());
        } while (mMachineDataSet.contains(currMachineDataIdx));*/
        currMachineDataIdx++;
        mMachineDataSet.add(currMachineDataIdx);
        MoralMachineModel machineData = MoralMachineData.MoralMachineDataList[currMachineDataIdx];
        mMachineDataSet.add(currMachineDataIdx);

        drivingOpt1ImageBtn.setImageResource(machineData.getOption1ResId());
        drivingOpt1TextBtn.setText(machineData.getOption1());
        drivingOpt1TextBtn.setTextSize(machineData.getOptFontSize());
        drivingOpt2ImageBtn.setImageResource(machineData.getOption2ResId());
        drivingOpt2TextBtn.setText(machineData.getOption2());
        drivingOpt2TextBtn.setTextSize(machineData.getOptFontSize());
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
            mMachineTestCount++;
            int userSelectionIdx = 0;
            if (v == drivingOpt2ImageBtn || v == drivingOpt2TextBtn) {
                userSelectionIdx = 1;
            }
            MoralMachineResult result = new MoralMachineResult(currMachineDataIdx, userSelectionIdx);
            moralMachineResultList.add(result);
            onRestart();
        }
    }
}