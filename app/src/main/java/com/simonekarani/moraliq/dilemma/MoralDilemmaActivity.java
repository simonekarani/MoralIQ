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
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.simonekarani.moraliq.MainActivity;
import com.simonekarani.moraliq.R;
import com.simonekarani.moraliq.model.MainScreenDataModel;

import java.util.ArrayList;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MoralDilemmaActivity extends AppCompatActivity {

    private static final int MAX_DILEMMA_COUNT = 35;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<MainScreenDataModel> data;
    private View.OnClickListener myOnClickListener;

    private TextView dilemmaTextView = null;
    private ImageView dilemmaImageView = null;
    private RadioGroup dilemmaBtnGroup = null;
    private RadioButton dilemmaOptBtn1 = null;
    private RadioButton dilemmaOptBtn2 = null;
    private RadioButton dilemmaOptBtn3 = null;
    private RadioButton dilemmaOptBtn4 = null;
    private Button radioBtnSelect = null;
    private RadioButton selectedOptBtn = null;

    private MoralDilemmaResult[] userDilemmaResults;
    private int userResultCount = 0;
    private int currDilemmaDataIdx = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dilemma);
        setTitle("Moral Dilemma IQ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currDilemmaDataIdx = 0;
        userDilemmaResults = new MoralDilemmaResult[MAX_DILEMMA_COUNT];
        userResultCount = 0;

        dilemmaTextView = (TextView) findViewById(R.id.dilemmaText);
        dilemmaImageView = (ImageView) findViewById(R.id.dilemmaImage);
        dilemmaBtnGroup = (RadioGroup) findViewById(R.id.optButtonGroup);
        dilemmaOptBtn1  = (RadioButton) findViewById(R.id.optButton1);
        dilemmaOptBtn2  = (RadioButton) findViewById(R.id.optButton2);
        dilemmaOptBtn3  = (RadioButton) findViewById(R.id.optButton3);
        dilemmaOptBtn4  = (RadioButton) findViewById(R.id.optButton4);
        radioBtnSelect = (Button) findViewById(R.id.radioBtnSelect);

        myOnClickListener = (View.OnClickListener) new MyOnClickListener(this);
        radioBtnSelect.setOnClickListener(myOnClickListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateDilemmaView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        dilemmaBtnGroup.clearCheck();

        updateDilemmaView();
    }

    private void updateDilemmaView() {
        //currDilemmaDataIdx = (int)(MoralDilemmaData.MoralDilemmaDataList.length * Math.random());
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
    }

    private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int selectedId = dilemmaBtnGroup.getCheckedRadioButtonId();
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
            userDilemmaResults[userResultCount++] = new MoralDilemmaResult(currDilemmaDataIdx,
                    selectedOptIdx);
            currDilemmaDataIdx++;

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