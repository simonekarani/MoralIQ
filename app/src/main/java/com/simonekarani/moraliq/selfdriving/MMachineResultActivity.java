package com.simonekarani.moraliq.selfdriving;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.simonekarani.moraliq.R;
import com.simonekarani.moraliq.dilemma.MoralDilemmaResult;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MMachineResultActivity extends AppCompatActivity {

    private final static String DRIVING_ANALYSIS_MSG = "The self driving car is designed to follow " +
            "the programmed rules without human reflexes, and is aware of the entire scene - " +
            "obstacles, signals, people in the car, and the pedestrians. Analysis presented here " +
            "is what a high schooler learns in books. I believe no one should be hurt, but these " +
            "are constrainted situations where any decision leads to death.";

    private ListView drivingResults = null;
    private TextView drivingResultMsg = null;
    private TextView drivingAnalysisMsg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_results);
        setTitle("Moral Machine Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle resBundle = getIntent().getExtras();
        ArrayList<MoralMachineResult> resultList = resBundle.getParcelableArrayList("drivingResult");

        drivingResultMsg = (TextView) findViewById(R.id.drivingResultMsg);
        drivingAnalysisMsg = (TextView) findViewById(R.id.drivingAnalysisMsg);
        setMMachineResultMsg(resultList);
        drivingAnalysisMsg.setText(DRIVING_ANALYSIS_MSG);

        drivingResults = (ListView) findViewById(R.id.drivingResultList);
        drivingResults.setSelector(android.R.color.transparent);
        final MMachineResultAdapter adapter = new MMachineResultAdapter(resultList, getApplicationContext());
        drivingResults.setAdapter(adapter);
    }

    public void setMMachineResultMsg(ArrayList<MoralMachineResult> resultList) {
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
        resultMsg += "\nHigh school wisdom: " + msgValue + "\n";
        drivingResultMsg.setText(resultMsg);
    }
}
