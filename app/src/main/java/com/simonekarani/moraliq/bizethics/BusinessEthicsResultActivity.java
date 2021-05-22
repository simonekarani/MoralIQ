package com.simonekarani.moraliq.bizethics;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.simonekarani.moraliq.R;
import com.simonekarani.moraliq.dilemma.MDilemmaResultActivity;
import com.simonekarani.moraliq.dilemma.MDilemmaResultAdapter;
import com.simonekarani.moraliq.dilemma.MoralDilemmaData;
import com.simonekarani.moraliq.dilemma.MoralDilemmaModel;
import com.simonekarani.moraliq.dilemma.MoralDilemmaResult;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class BusinessEthicsResultActivity extends AppCompatActivity {
    private final static String WRONG_KEYWORD = " (Wrong)";

    private Button prevBtn = null;
    private Button nextBtn = null;

    private TextView bizethicsResultMsg = null;
    private TextView bizUser = null;
    private TextView bizAnswerText;
    private Button bizAnsBtn1 = null;
    private Button bizAnsBtn2 = null;
    private Button bizAnsBtn3 = null;
    private Button bizAnsBtn4 = null;
    private TextView bizCorrectText = null;
    private TextView bizCorrect = null;
    private TextView bizAnalysis = null;

    private View.OnClickListener myOnClickListener;
    private int currResultDisplayIdx = 0;
    private ArrayList<BusinessEthicsResult> resultList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizethics_results);
        setTitle("Business Ethics Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle resBundle = getIntent().getExtras();
        resultList = resBundle.getParcelableArrayList("bizEthicsResult");

        prevBtn  = (Button) findViewById(R.id.bizPrevBtn);
        nextBtn  = (Button) findViewById(R.id.bizNextButton);
        myOnClickListener = (View.OnClickListener) new MyOnClickListener(this);
        prevBtn.setOnClickListener(myOnClickListener);
        nextBtn.setOnClickListener(myOnClickListener);

        bizethicsResultMsg = (TextView) findViewById(R.id.bizethicsResultMsg);
        bizUser = (TextView) findViewById(R.id.bizName);
        bizAnswerText = (TextView) findViewById(R.id.bizAnswerText);
        bizAnsBtn1  = (Button) findViewById(R.id.qBizAnswer1);
        bizAnsBtn2  = (Button) findViewById(R.id.qBizAnswer2);
        bizAnsBtn3  = (Button) findViewById(R.id.qBizAnswer3);
        bizAnsBtn4  = (Button) findViewById(R.id.qBizAnswer4);
        bizCorrectText = (TextView) findViewById(R.id.bizCorrectText);
        bizCorrect = (TextView) findViewById(R.id.bizCorrect);
        bizAnalysis = (TextView) findViewById(R.id.bizAnalysis);

        setBizEthicsResultMsg(resultList);
        currResultDisplayIdx = 0;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateBizEthicsResultMsg(resultList);
    }

    private void setBizEthicsResultMsg(ArrayList<BusinessEthicsResult> resultList) {
        int totalCnt = resultList.size();
        int moralCnt = 0;
        for (int i = 0; i < resultList.size(); i++) {
            int dataListIdx = resultList.get(i).getBizEthicsIdx();
            int userSelectionIdx = resultList.get(i).getUserOptIdx();
            BusinessEthicsModel reqData = BusinessEthicsData.BizEthicsDataList[dataListIdx];
            if (reqData.getAnalysisOpt() == userSelectionIdx) {
                moralCnt++;
            }
        }
        String resultStr = "" + moralCnt + "/" + totalCnt;
        String resultMsg = "Congratulations!!&emsp;" + "<b>" + "Score: " + resultStr + "</b>" + "<br>";
        resultMsg += "" + moralCnt + "/" + totalCnt;
        String msgValue = "Sorry...";
        double resultValue = (double)moralCnt/totalCnt;
        if (resultValue > 0.6)
            msgValue = "Fabulous";
        else if (resultValue > 0.3)
            msgValue = "Groovy";
        resultMsg += "\nHigh school wisdom: " + msgValue + "\n";
        bizethicsResultMsg.setText(Html.fromHtml(resultMsg));

        updateBizEthicsResultMsg(resultList);
    }

    private void updateBizEthicsResultMsg(ArrayList<BusinessEthicsResult> resultList) {
        if (currResultDisplayIdx == 0) {
            prevBtn.setAlpha(.5f);
        } else if (currResultDisplayIdx == resultList.size()-1) {
            nextBtn.setAlpha(.5f);
        }

        int dataListIdx = resultList.get(currResultDisplayIdx).getBizEthicsIdx();
        int userSelectionIdx = resultList.get(currResultDisplayIdx).getUserOptIdx();
        BusinessEthicsModel reqData = BusinessEthicsData.BizEthicsDataList[dataListIdx];
        Button currBtn = null;
        String currOptText = null;
        String correctAnswer = null;
        for (int j = 0; j < 4; j++) {
            switch (j) {
                case 0:
                    currBtn = bizAnsBtn1;
                    currOptText = reqData.getOption1();
                    break;
                case 1:
                    currBtn = bizAnsBtn2;
                    currOptText = reqData.getOption2();
                    break;
                case 2:
                    currBtn = bizAnsBtn3;
                    currOptText = reqData.getOption3();
                    break;
                case 3:
                    currBtn = bizAnsBtn4;
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

        String bizQuestion = "<b>" + "Question: " + "</b>" + reqData.getQuestion().replaceAll("\n", "");
        bizUser.setText(Html.fromHtml(bizQuestion));
        bizAnswerText.setText("Your Selection:");
        bizCorrectText.setText("Correct Answer:");
        bizCorrect.setText(correctAnswer);
        String bizAnalysisStr = "<b>" + "Analysis: " + "</b>" + reqData.getAnalysis();
        bizAnalysis.setText(Html.fromHtml(bizAnalysisStr));
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
