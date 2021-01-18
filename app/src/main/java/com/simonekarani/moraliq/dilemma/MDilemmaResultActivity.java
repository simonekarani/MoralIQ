package com.simonekarani.moraliq.dilemma;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

import com.simonekarani.moraliq.R;

import androidx.appcompat.app.AppCompatActivity;

public class MDilemmaResultActivity extends AppCompatActivity {

    private final static String WRONG_KEYWORD = " (Wrong)";

    private Button prevBtn = null;
    private Button nextBtn = null;

    private TextView dilemmaResultMsg = null;
    private TextView dilemmaQText = null;
    private TextView dilemmaUser = null;
    private TextView dilemmaAnswerText;
    private Button dilemmaAnsBtn1 = null;
    private Button dilemmaAnsBtn2 = null;
    private Button dilemmaAnsBtn3 = null;
    private Button dilemmaAnsBtn4 = null;
    private TextView dilemmaCorrectText = null;
    private TextView dilemmaCorrect = null;
    private TextView dilemmaAnalysisText = null;
    private TextView dilemmaAnalysis = null;

    private View.OnClickListener myOnClickListener;
    private int currResultDisplayIdx = 0;
    private ArrayList<MoralDilemmaResult> resultList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dilemma_results);
        setTitle("Moral Dilemma Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle resBundle = getIntent().getExtras();
        resultList = resBundle.getParcelableArrayList("dilemmaResult");

        prevBtn  = (Button) findViewById(R.id.dilPrevBtn);
        nextBtn  = (Button) findViewById(R.id.dilNextButton);
        myOnClickListener = (View.OnClickListener) new MyOnClickListener(this);
        prevBtn.setOnClickListener(myOnClickListener);
        nextBtn.setOnClickListener(myOnClickListener);

        dilemmaResultMsg = (TextView) findViewById(R.id.dilemmaResultMsg);
        dilemmaQText = (TextView) findViewById(R.id.dilemmaQText);
        dilemmaUser = (TextView) findViewById(R.id.dilemmaName);
        dilemmaAnswerText = (TextView) findViewById(R.id.dilemmaAnswerText);
        dilemmaAnsBtn1  = (Button) findViewById(R.id.qDilAnswer1);
        dilemmaAnsBtn2  = (Button) findViewById(R.id.qDilAnswer2);
        dilemmaAnsBtn3  = (Button) findViewById(R.id.qDilAnswer3);
        dilemmaAnsBtn4  = (Button) findViewById(R.id.qDilAnswer4);
        dilemmaCorrectText = (TextView) findViewById(R.id.dilemmaCorrectText);
        dilemmaCorrect = (TextView) findViewById(R.id.dilemmaCorrect);
        dilemmaAnalysisText = (TextView) findViewById(R.id.dilemmaAnalysisText);
        dilemmaAnalysis = (TextView) findViewById(R.id.dilemmaAnalysis);

        setDilemmaResultMsg(resultList);
        currResultDisplayIdx = 0;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateDilemmaResultMsg(resultList);
    }

    private void setDilemmaResultMsg(ArrayList<MoralDilemmaResult> resultList) {
        String resultMsg = "Congratulations!!\nScore: ";
        int totalCnt = resultList.size();
        int moralCnt = 0;

        for (int i = 0; i < resultList.size(); i++) {
            int dataListIdx = resultList.get(i).getDilemmaIdx();
            int userSelectionIdx = resultList.get(i).getUserOptIdx();
            MoralDilemmaModel reqData = MoralDilemmaData.MoralDilemmaDataList[dataListIdx];
            if (reqData.getAnalysisOpt() == userSelectionIdx) {
                moralCnt++;
            }
        }
        resultMsg += "" + moralCnt + "/" + totalCnt;
        String msgValue = "Sorry...";
        double resultValue = (double)moralCnt/totalCnt;
        if (resultValue > 0.6)
            msgValue = "Fabulous";
        else if (resultValue > 0.3)
            msgValue = "Groovy";
        resultMsg += "\nHigh school wisdom: " + msgValue + "\n";
        dilemmaResultMsg.setText(resultMsg);

        updateDilemmaResultMsg(resultList);
    }

    private void updateDilemmaResultMsg(ArrayList<MoralDilemmaResult> resultList) {
        if (currResultDisplayIdx == 0) {
            prevBtn.setAlpha(.5f);
            prevBtn.setClickable(false);
            prevBtn.setEnabled(false);
        } else if (currResultDisplayIdx == resultList.size()-1) {
            nextBtn.setAlpha(.5f);
            nextBtn.setClickable(false);
            nextBtn.setEnabled(false);
        }

        int dataListIdx = resultList.get(currResultDisplayIdx).getDilemmaIdx();
        int userSelectionIdx = resultList.get(currResultDisplayIdx).getUserOptIdx();
        MoralDilemmaModel reqData = MoralDilemmaData.MoralDilemmaDataList[dataListIdx];
        Button currBtn = null;
        String currOptText = null;
        String correctAnswer = null;
        for (int j = 0; j < 4; j++) {
            switch (j) {
                case 0:
                    currBtn = dilemmaAnsBtn1;
                    currOptText = reqData.getOption1();
                    break;
                case 1:
                    currBtn = dilemmaAnsBtn2;
                    currOptText = reqData.getOption2();
                    break;
                case 2:
                    currBtn = dilemmaAnsBtn3;
                    currOptText = reqData.getOption3();
                    break;
                case 3:
                    currBtn = dilemmaAnsBtn3;
                    currOptText = reqData.getOption3();
                    break;
                default:
            }
            if (j == reqData.getAnalysisOpt()) {
                correctAnswer = "\"" + currOptText + "\"";
            }

            if ((j == userSelectionIdx) && (userSelectionIdx == reqData.getAnalysisOpt())) {
                currBtn.setBackgroundColor(Color.rgb(144,238,144));
                currBtn.setText(currOptText);
            } else if (j == userSelectionIdx) {
                currOptText += WRONG_KEYWORD;
                currBtn.setBackgroundColor(Color.rgb(220,20,60));
                currBtn.setTextColor(Color.WHITE);
                currBtn.setText(currOptText);
            } else {
                currBtn.setText(currOptText);
            }
        }

        dilemmaQText.setText("Question:");
        dilemmaUser.setText(reqData.getQuestion().replaceAll("\n", ""));
        dilemmaAnswerText.setText("Your Selection:");
        dilemmaCorrectText.setText("Correct Answer:");
        dilemmaCorrect.setText(correctAnswer);
        dilemmaAnalysisText.setText("Analysis:");
        dilemmaAnalysis.setText(reqData.getAnalysis());
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
                    nextBtn.setClickable(false);
                    nextBtn.setEnabled(false);
                }
                currResultDisplayIdx = (currResultDisplayIdx == 0) ?
                        currResultDisplayIdx : currResultDisplayIdx-1;
            } else if (nextBtn.isPressed()) {
                if (currResultDisplayIdx == 0) {
                    prevBtn.setAlpha(1f);
                    prevBtn.setClickable(true);
                    prevBtn.setEnabled(true);
                }
                currResultDisplayIdx = (currResultDisplayIdx == resultList.size()-1) ?
                        currResultDisplayIdx: currResultDisplayIdx+1;
            }
            onRestart();
        }
    }
}
