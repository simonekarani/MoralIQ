package com.simonekarani.moraliq.selfdriving;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.simonekarani.moraliq.R;
import com.simonekarani.moraliq.dilemma.MDilemmaResultActivity;
import com.simonekarani.moraliq.dilemma.MoralDilemmaResult;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MMachineResultActivity extends AppCompatActivity {

    private final static String DRIVING_ANALYSIS_MSG = "The self driving car runs without human interaction, " +
            "and is aware of the obstacles, signals on road, people in the car, and pedestrians.";

    private TextView drivingAnalysisMsg = null;

    private Button prevBtn = null;
    private Button nextBtn = null;

    private TextView drivingResultMsg = null;
    private TextView drivingQText = null;
    private TextView drivingUser = null;
    private TextView drivingAnswerText;
    private RadioButton dilemmaAnsBtn1 = null;
    private RadioButton dilemmaAnsBtn2 = null;
    private TextView drivingCorrectText = null;
    private TextView drivingCorrect = null;
    private TextView drivingAnalysisText = null;
    private TextView drivingAnalysis = null;

    private View.OnClickListener myOnClickListener;
    private int currResultDisplayIdx = 0;
    private ArrayList<MoralMachineResult> resultList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_results);
        setTitle("Moral Machine Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prevBtn  = (Button) findViewById(R.id.drivingPrevBtn);
        nextBtn  = (Button) findViewById(R.id.drivingNextButton);
        myOnClickListener = (View.OnClickListener) new MyOnClickListener(this);
        prevBtn.setOnClickListener(myOnClickListener);
        nextBtn.setOnClickListener(myOnClickListener);

        Bundle resBundle = getIntent().getExtras();
        resultList = resBundle.getParcelableArrayList("drivingResult");

        drivingResultMsg = (TextView) findViewById(R.id.drivingResultMsg);
        drivingAnalysisMsg = (TextView) findViewById(R.id.drivingAnalysisMsg);
        dilemmaAnsBtn1 = (RadioButton) findViewById(R.id.drivingOptAnswer1);
        dilemmaAnsBtn2 = (RadioButton) findViewById(R.id.drivingOptAnswer2);
        drivingCorrect = (TextView) findViewById(R.id.drivingCorrect);
        drivingAnalysisText = (TextView) findViewById(R.id.driveAnalysisText);
        drivingAnalysis = (TextView) findViewById(R.id.driveAnalysis);

        currResultDisplayIdx = 0;
        drivingAnalysisMsg.setText(DRIVING_ANALYSIS_MSG);
        setMMachineResultMsg(resultList);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateMMachineResultMsg(resultList);
    }

    private void setMMachineResultMsg(ArrayList<MoralMachineResult> resultList) {
        String resultMsg = "Congratulations!!\nScore: ";
        int totalCnt = resultList.size();
        int moralCnt = 0;
        for (int i = 0; i < resultList.size(); i++) {
            int dataListIdx = resultList.get(i).getmMachineDataIdx();
            int userSelectionIdx = resultList.get(i).getUserOptIdx();
            MoralMachineModel reqData = MoralMachineData.MoralMachineDataList[dataListIdx];
            if (reqData.getAnalysisOptId() == userSelectionIdx) {
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
        resultMsg += "\nHigh school wisdom: " + msgValue;
        drivingResultMsg.setText(resultMsg);

        updateMMachineResultMsg(resultList);
    }

    private void updateMMachineResultMsg(ArrayList<MoralMachineResult> resultList) {
        if (currResultDisplayIdx == 0) {
            prevBtn.setAlpha(.5f);
        } else if (currResultDisplayIdx == resultList.size()-1) {
            nextBtn.setAlpha(.5f);
        }

        int dataListIdx = resultList.get(currResultDisplayIdx).getmMachineDataIdx();
        int userSelectionIdx = resultList.get(currResultDisplayIdx).getUserOptIdx();
        MoralMachineModel reqData = MoralMachineData.MoralMachineDataList[dataListIdx];
        dilemmaAnsBtn1.setBackground(
                getResources().getDrawable(reqData.getOption1ResId(), null));
        dilemmaAnsBtn2.setBackground(
                getResources().getDrawable(reqData.getOption2ResId(), null));
        if (userSelectionIdx == 0) {
            if (reqData.getAnalysisOptId() == 0) {
                dilemmaAnsBtn1.setText("Your Selection (Correct)");
                dilemmaAnsBtn2.setText("");
                drivingCorrect.setText("Left Image");
            }
            else {
                dilemmaAnsBtn1.setText("Your Selection (Wrong)");
                dilemmaAnsBtn2.setText("");
                drivingCorrect.setText("Right Image");
            }
        }
        else {
            if (reqData.getAnalysisOptId() == 0) {
                dilemmaAnsBtn1.setText("");
                dilemmaAnsBtn2.setText("Your Selection (Wrong)");
                drivingCorrect.setText("Left Image");
            }
            else {
                dilemmaAnsBtn1.setText("");
                dilemmaAnsBtn2.setText("Your Selection (Correct)");
                drivingCorrect.setText("Right Image");
            }
        }

        drivingAnalysisText.setText("Analysis:");
        drivingAnalysis.setText(reqData.getAnalysis());
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
