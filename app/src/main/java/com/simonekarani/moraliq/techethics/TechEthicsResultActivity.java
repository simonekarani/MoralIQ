//  TechEthicsResultActivity.java
//  MoralIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 MoralIQ. All rights reserved.
//

package com.simonekarani.moraliq.techethics;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.simonekarani.moraliq.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class TechEthicsResultActivity extends AppCompatActivity {
    private ListView techEthicsResults = null;
    private TextView techEthicsResultMsg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_techethics_results);
        setTitle("Tech Ethics Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle resBundle = getIntent().getExtras();
        ArrayList<TechEthicsResult> resultList = resBundle.getParcelableArrayList("techEthicalResult");

        techEthicsResultMsg = (TextView) findViewById(R.id.techthicsResultMsg);
        setDilemmaResultMsg(resultList);

        techEthicsResults = (ListView) findViewById(R.id.techthicsResultList);
        techEthicsResults.setSelector(android.R.color.transparent);
        final TechEthicsResultAdapter adapter = new TechEthicsResultAdapter(resultList, getApplicationContext());
        techEthicsResults.setAdapter(adapter);
    }

    public void setDilemmaResultMsg(ArrayList<TechEthicsResult> resultList) {
        String resultMsg = "Congratulations, you have completed \"Medical Ethics\" quiz, and you scored ";
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
        resultMsg += "" + moralCnt + "/" + totalCnt;
        String msgValue = "oohhh...";
        double resultValue = (double)moralCnt/totalCnt;
        if (resultValue > 0.6)
            msgValue = "Fabulous";
        else if (resultValue > 0.3)
            msgValue = "Groovy";
        resultMsg += ". The high school wisdom says " + msgValue + "\n";
        resultMsg += "Below are your selections with Analysis";
        techEthicsResultMsg.setText(resultMsg);
    }
}
