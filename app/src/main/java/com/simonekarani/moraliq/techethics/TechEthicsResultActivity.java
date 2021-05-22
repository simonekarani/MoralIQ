//  TechEthicsResultActivity.java
//  MoralIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 MoralIQ. All rights reserved.
//

package com.simonekarani.moraliq.techethics;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.simonekarani.moraliq.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class TechEthicsResultActivity extends AppCompatActivity {

    private final static String WRONG_KEYWORD = " (Wrong)";

    private Button prevBtn = null;
    private Button nextBtn = null;

    private TextView techEthicsResultMsg = null;
    private TextView techUser = null;
    private TextView techAnswerText;
    private Button techAnsBtn1 = null;
    private Button techAnsBtn2 = null;
    private Button techAnsBtn3 = null;
    private Button techAnsBtn4 = null;
    private TextView techCorrectText = null;
    private TextView techCorrect = null;
    private TextView techAnalysis = null;

    private View.OnClickListener myOnClickListener;
    private int currResultDisplayIdx = 0;
    private ArrayList<TechEthicsResult> resultList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_techethics_results);
        setTitle("Tech Ethics Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prevBtn  = (Button) findViewById(R.id.techPrevBtn);
        nextBtn  = (Button) findViewById(R.id.techNextButton);
        myOnClickListener = (View.OnClickListener) new MyOnClickListener(this);
        prevBtn.setOnClickListener(myOnClickListener);
        nextBtn.setOnClickListener(myOnClickListener);

        Bundle resBundle = getIntent().getExtras();
        resultList = resBundle.getParcelableArrayList("techEthicalResult");

        techEthicsResultMsg = (TextView) findViewById(R.id.techthicsResultMsg);
        techUser = (TextView) findViewById(R.id.techName);
        techAnswerText = (TextView) findViewById(R.id.techAnswerText);
        techAnsBtn1  = (Button) findViewById(R.id.qTechAnswer1);
        techAnsBtn2  = (Button) findViewById(R.id.qTechAnswer2);
        techAnsBtn3  = (Button) findViewById(R.id.qTechAnswer3);
        techAnsBtn4  = (Button) findViewById(R.id.qTechAnswer4);
        techCorrectText = (TextView) findViewById(R.id.techCorrectText);
        techCorrect = (TextView) findViewById(R.id.techCorrect);
        techAnalysis = (TextView) findViewById(R.id.techAnalysis);

        setTechEthicsResultMsg(resultList);
        currResultDisplayIdx = 0;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateTechEthicsResultMsg(resultList);
    }

    public void setTechEthicsResultMsg(ArrayList<TechEthicsResult> resultList) {
        int totalCnt = resultList.size();
        int moralCnt = 0;
        for (int i = 0; i < resultList.size(); i++) {
            int dataListIdx = resultList.get(i).getTechEthicsIdx();
            int userSelectionIdx = resultList.get(i).getUserOptIdx();
            TechEthicsModel reqData = TechEthicsData.TechEthicsDataList[dataListIdx];
            if (reqData.getAnalysisOpt() == userSelectionIdx) {
                moralCnt++;
            }
        }
        String resultStr = "" + moralCnt + "/" + totalCnt;
        String resultMsg = "Congratulations!!&emsp;" + "<b>" + "Score: " + resultStr + "</b>" + "<br>";
        String msgValue = "Sorry...";
        double resultValue = (double)moralCnt/totalCnt;
        if (resultValue > 0.6)
            msgValue = "Fabulous";
        else if (resultValue > 0.3)
            msgValue = "Groovy";
        resultMsg += "\nHigh school wisdom: " + msgValue;
        techEthicsResultMsg.setText(Html.fromHtml(resultMsg));

        updateTechEthicsResultMsg(resultList);
    }

    private void updateTechEthicsResultMsg(ArrayList<TechEthicsResult> resultList) {
        if (currResultDisplayIdx == 0) {
            prevBtn.setAlpha(.5f);
        } else if (currResultDisplayIdx == resultList.size()-1) {
            nextBtn.setAlpha(.5f);
        }

        int dataListIdx = resultList.get(currResultDisplayIdx).getTechEthicsIdx();
        int userSelectionIdx = resultList.get(currResultDisplayIdx).getUserOptIdx();
        TechEthicsModel reqData = TechEthicsData.TechEthicsDataList[dataListIdx];
        Button currBtn = null;
        String currOptText = null;
        String correctAnswer = null;
        for (int j = 0; j < 4; j++) {
            switch (j) {
                case 0:
                    currBtn = techAnsBtn1;
                    currOptText = reqData.getOption1();
                    break;
                case 1:
                    currBtn = techAnsBtn2;
                    currOptText = reqData.getOption2();
                    break;
                case 2:
                    currBtn = techAnsBtn3;
                    currOptText = reqData.getOption3();
                    break;
                case 3:
                    currBtn = techAnsBtn4;
                    currOptText = reqData.getOption4();
                    break;
                default:
            }
            if (j == reqData.getAnalysisOpt()) {
                correctAnswer = "\"" + currOptText + "\"";
            }

            if ((j == userSelectionIdx) && (userSelectionIdx == reqData.getAnalysisOpt())) {
                currBtn.setBackgroundColor(Color.rgb(34,139,34));
                currBtn.setTextColor(Color.WHITE);
                currBtn.setText(currOptText);
            } else if (j == userSelectionIdx) {
                currOptText += WRONG_KEYWORD;
                currBtn.setBackgroundColor(Color.rgb(220,20,60));
                currBtn.setTextColor(Color.WHITE);
                currBtn.setText(currOptText);
            } else {
                currBtn.setBackgroundColor(Color.rgb(169,169,169));
                currBtn.setTextColor(Color.BLACK);
                currBtn.setText(currOptText);
            }
        }

        String techQuestion = "<b>" + "Question: " + "</b>" + reqData.getQuestion().replaceAll("\n", "");
        techUser.setText(Html.fromHtml(techQuestion));
        techAnswerText.setText("Your Selection:");
        techCorrectText.setText("Correct Answer:");
        techCorrect.setText(correctAnswer);
        String techAnalysisStr = "<b>" + "Analysis: " + "</b>" + reqData.getAnalysis();
        techAnalysis.setText(Html.fromHtml(techAnalysisStr));
    }

    private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            if (prevBtn.isPressed()) {
                if (currResultDisplayIdx == resultList.size()-1) {
                    nextBtn.setAlpha(1f);
                }
                currResultDisplayIdx = (currResultDisplayIdx == 0) ?
                        currResultDisplayIdx : currResultDisplayIdx-1;
            } else if (nextBtn.isPressed()) {
                if (currResultDisplayIdx == 0) {
                    prevBtn.setAlpha(1f);
                }
                currResultDisplayIdx = (currResultDisplayIdx == resultList.size()-1) ?
                        currResultDisplayIdx: currResultDisplayIdx+1;
            }
            onRestart();
        }
    }
}
