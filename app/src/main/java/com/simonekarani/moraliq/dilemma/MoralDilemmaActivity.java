//
//  MoralDilemmaActivity.java
//  MoralIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 MoralIQ. All rights reserved.
//

package com.simonekarani.moraliq.dilemma;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.simonekarani.moraliq.R;
import com.simonekarani.moraliq.model.MainScreenDataModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MoralDilemmaActivity extends AppCompatActivity {

    private static final int MAX_DILEMMA_COUNT = 7;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<MainScreenDataModel> data;
    private View.OnClickListener myOnClickListener;

    private TextView dilemmaTextView = null;
    private ImageView dilemmaImageView = null;
    private Button dilemmaOptBtn1 = null;
    private Button dilemmaOptBtn2 = null;
    private Button dilemmaOptBtn3 = null;
    private Button dilemmaOptBtn4 = null;

    private ArrayList<MoralDilemmaResult> mDilemmaResultList = new ArrayList<>();
    private Set<Integer> mDilemmaDataSet = new HashSet<>();
    private int userResultCount = 0;
    private int currDilemmaDataIdx = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dilemma);
        setTitle("Dilemma IQ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currDilemmaDataIdx = 0;
        userResultCount = 0;

        dilemmaTextView = (TextView) findViewById(R.id.dilemmaText);
        dilemmaImageView = (ImageView) findViewById(R.id.dilemmaImage);
        dilemmaOptBtn1  = (Button) findViewById(R.id.optButton1);
        dilemmaOptBtn2  = (Button) findViewById(R.id.optButton2);
        dilemmaOptBtn3  = (Button) findViewById(R.id.optButton3);
        dilemmaOptBtn4  = (Button) findViewById(R.id.optButton4);

        myOnClickListener = (View.OnClickListener) new MyOnClickListener(this);
        dilemmaOptBtn1.setOnClickListener(myOnClickListener);
        dilemmaOptBtn2.setOnClickListener(myOnClickListener);
        dilemmaOptBtn3.setOnClickListener(myOnClickListener);
        dilemmaOptBtn4.setOnClickListener(myOnClickListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateDilemmaView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        dilemmaOptBtn1.setPressed(false);
        dilemmaOptBtn2.setPressed(false);
        dilemmaOptBtn3.setPressed(false);
        dilemmaOptBtn4.setPressed(false);

        if (userResultCount < MAX_DILEMMA_COUNT) {
            updateDilemmaView();
        }
        else {
            Intent intent = new Intent(this, MDilemmaResultActivity.class);
            Bundle resultBundle = new Bundle();
            resultBundle.putParcelableArrayList("dilemmaResult", mDilemmaResultList);
            intent.putExtras(resultBundle);
            startActivity(intent);
        }
    }

    private void updateDilemmaView() {
        do {
            currDilemmaDataIdx = (int)(MoralDilemmaData.MoralDilemmaDataList.length * Math.random());
        } while (mDilemmaDataSet.contains(currDilemmaDataIdx));
        mDilemmaDataSet.add(currDilemmaDataIdx);
        MoralDilemmaModel dilemmaData = MoralDilemmaData.MoralDilemmaDataList[currDilemmaDataIdx];

        dilemmaTextView.setTextSize(dilemmaData.getQuestionFontSize());
        dilemmaTextView.setText(dilemmaData.getQuestion());

        dilemmaImageView.setImageResource(dilemmaData.getImageResId());
        dilemmaOptBtn1.setTextSize(dilemmaData.getOptionFontSize());
        dilemmaOptBtn1.setText(dilemmaData.getOption1());
        dilemmaOptBtn2.setTextSize(dilemmaData.getOptionFontSize());
        dilemmaOptBtn2.setText(dilemmaData.getOption2());
        dilemmaOptBtn3.setTextSize(dilemmaData.getOptionFontSize());
        dilemmaOptBtn3.setText(dilemmaData.getOption3());
        dilemmaOptBtn4.setTextSize(dilemmaData.getOptionFontSize());
        dilemmaOptBtn4.setText(dilemmaData.getOption4());

        dilemmaOptBtn1.setPadding(0, 0, 0, dilemmaData.getBtnGap());
        dilemmaOptBtn2.setPadding(0, 0, 0, dilemmaData.getBtnGap());
        dilemmaOptBtn3.setPadding(0, 0, 0, dilemmaData.getBtnGap());
        dilemmaOptBtn4.setPadding(0, 0, 0, 0);
    }

    private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int selectedOptIdx = -1;
            if (dilemmaOptBtn1.isPressed()) {
                selectedOptIdx = 0;
            } else if (dilemmaOptBtn2.isPressed()) {
                selectedOptIdx = 1;
            } else if (dilemmaOptBtn3.isPressed()) {
                selectedOptIdx = 2;
            } else if (dilemmaOptBtn4.isPressed()) {
                selectedOptIdx = 3;
            } else {
                selectedOptIdx = 0;
            }
            MoralDilemmaResult result = new MoralDilemmaResult(currDilemmaDataIdx, selectedOptIdx);
            mDilemmaResultList.add(result);
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