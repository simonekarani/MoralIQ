package com.simonekarani.moraliq.medethics;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.simonekarani.moraliq.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MedEthicalResultActivity extends AppCompatActivity {
    private ListView medEthicsResults = null;
    private TextView medEthicsResultMsg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medethics_results);
        setTitle("Medical Ethics Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle resBundle = getIntent().getExtras();
        ArrayList<MedEthicalResult> resultList = resBundle.getParcelableArrayList("medEthicalResult");

        medEthicsResultMsg = (TextView) findViewById(R.id.medethicsResultMsg);
        setDilemmaResultMsg(resultList);

        medEthicsResults = (ListView) findViewById(R.id.medethicsResultList);
        medEthicsResults.setSelector(android.R.color.transparent);
        final MedEthicalResultAdapter adapter = new MedEthicalResultAdapter(resultList, getApplicationContext());
        medEthicsResults.setAdapter(adapter);
    }

    public void setDilemmaResultMsg(ArrayList<MedEthicalResult> resultList) {
        String resultMsg = "Congratulations, you have completed \"Medical Ethics\" quiz, and you scored ";
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
        String msgValue = "oohhh...";
        double resultValue = (double)moralCnt/totalCnt;
        if (resultValue > 0.6)
            msgValue = "Fabulous";
        else if (resultValue > 0.3)
            msgValue = "Groovy";
        resultMsg += ". The high school wisdom says " + msgValue + "\n";
        resultMsg += "Below are your selections with Analysis";
        medEthicsResultMsg.setText(resultMsg);
    }
}
