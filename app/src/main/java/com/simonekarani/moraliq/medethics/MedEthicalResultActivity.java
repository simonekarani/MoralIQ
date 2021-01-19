package com.simonekarani.moraliq.medethics;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;

import com.simonekarani.moraliq.R;
import androidx.appcompat.app.AppCompatActivity;

public class MedEthicalResultActivity extends AppCompatActivity {
    private final static String WRONG_KEYWORD = " (Wrong)";

    private Button prevBtn = null;
    private Button nextBtn = null;

    private TextView medEthicsResultMsg = null;
    private TextView medQText = null;
    private TextView medUser = null;
    private TextView medAnswerText;
    private Button medAnsBtn1 = null;
    private Button medAnsBtn2 = null;
    private Button medAnsBtn3 = null;
    private Button medAnsBtn4 = null;
    private TextView medCorrectText = null;
    private TextView medCorrect = null;
    private TextView medAnalysisText = null;
    private TextView medAnalysis = null;

    private View.OnClickListener myOnClickListener;
    private int currResultDisplayIdx = 0;
    private ArrayList<MedEthicalResult> resultList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medethics_results);
        setTitle("Medical Ethics Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle resBundle = getIntent().getExtras();
        resultList = resBundle.getParcelableArrayList("medEthicalResult");

        prevBtn  = (Button) findViewById(R.id.medPrevBtn);
        nextBtn  = (Button) findViewById(R.id.medNextButton);
        myOnClickListener = (View.OnClickListener) new MyOnClickListener(this);
        prevBtn.setOnClickListener(myOnClickListener);
        nextBtn.setOnClickListener(myOnClickListener);

        medEthicsResultMsg = (TextView) findViewById(R.id.medethicsResultMsg);
        medQText = (TextView) findViewById(R.id.medQText);
        medUser = (TextView) findViewById(R.id.medName);
        medAnswerText = (TextView) findViewById(R.id.medAnswerText);
        medAnsBtn1  = (Button) findViewById(R.id.medAnswer1);
        medAnsBtn2  = (Button) findViewById(R.id.medAnswer2);
        medAnsBtn3  = (Button) findViewById(R.id.medAnswer3);
        medAnsBtn4  = (Button) findViewById(R.id.medAnswer4);
        medCorrectText = (TextView) findViewById(R.id.medCorrectText);
        medCorrect = (TextView) findViewById(R.id.medCorrect);
        medAnalysisText = (TextView) findViewById(R.id.medAnalysisText);
        medAnalysis = (TextView) findViewById(R.id.medAnalysis);

        setMedEthicsResultMsg(resultList);
        currResultDisplayIdx = 0;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateMedEthicsResultMsg(resultList);
    }

    private void setMedEthicsResultMsg(ArrayList<MedEthicalResult> resultList) {
        String resultMsg = "Congratulations!!\nScore: ";
        int totalCnt = resultList.size();
        int moralCnt = 0;
        for (int i = 0; i < resultList.size(); i++) {
            int dataListIdx = resultList.get(i).getMedEthicalIdx();
            int userSelectionIdx = resultList.get(i).getUserOptIdx();
            MedicalEthicalModel reqData = MedicalEthicalData.MED_ETHICAL_DATA_LIST[dataListIdx];
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
        medEthicsResultMsg.setText(resultMsg);

        updateMedEthicsResultMsg(resultList);
    }

    private void updateMedEthicsResultMsg(ArrayList<MedEthicalResult> resultList) {
        if (currResultDisplayIdx == 0) {
            prevBtn.setAlpha(.5f);
        } else if (currResultDisplayIdx == resultList.size()-1) {
            nextBtn.setAlpha(.5f);
        }

        int dataListIdx = resultList.get(currResultDisplayIdx).getMedEthicalIdx();
        int userSelectionIdx = resultList.get(currResultDisplayIdx).getUserOptIdx();
        MedicalEthicalModel reqData = MedicalEthicalData.MED_ETHICAL_DATA_LIST[dataListIdx];
        Button currBtn = null;
        String currOptText = null;
        String correctAnswer = null;
        for (int j = 0; j < 4; j++) {
            if (j == 3 && reqData.getButtonCount() == 3) {
                medAnsBtn4.setVisibility(View.INVISIBLE);
                continue;
            }
            else {
                medAnsBtn4.setVisibility(View.VISIBLE);
            }

            switch (j) {
                case 0:
                    currBtn = medAnsBtn1;
                    currOptText = reqData.getOption1();
                    break;
                case 1:
                    currBtn = medAnsBtn2;
                    currOptText = reqData.getOption2();
                    break;
                case 2:
                    currBtn = medAnsBtn3;
                    currOptText = reqData.getOption3();
                    break;
                case 3:
                    currBtn = medAnsBtn4;
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

        medQText.setText("Question:");
        medUser.setText(reqData.getQuestion().replaceAll("\n", ""));
        medAnswerText.setText("Your Selection:");
        medCorrectText.setText("Correct Answer:");
        medCorrect.setText(correctAnswer);
        medAnalysisText.setText("Analysis:");
        medAnalysis.setText(reqData.getAnalysis());
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
