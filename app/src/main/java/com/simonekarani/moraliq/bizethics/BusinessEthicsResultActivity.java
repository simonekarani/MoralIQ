package com.simonekarani.moraliq.bizethics;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.simonekarani.moraliq.R;
import com.simonekarani.moraliq.dilemma.MDilemmaResultAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class BusinessEthicsResultActivity extends AppCompatActivity {
    private ListView bizethicsResults = null;
    private TextView bizethicsResultMsg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizethics_results);
        setTitle("Business Ethics Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle resBundle = getIntent().getExtras();
        ArrayList<BusinessEthicsResult> resultList = resBundle.getParcelableArrayList("bizEthicsResult");

        bizethicsResultMsg = (TextView) findViewById(R.id.bizethicsResultMsg);
        setDilemmaResultMsg(resultList);

        bizethicsResults = (ListView) findViewById(R.id.bizethicsResultList);
        bizethicsResults.setSelector(android.R.color.transparent);
        final BusinessEthicsResultAdapter adapter = new BusinessEthicsResultAdapter(resultList, getApplicationContext());
        bizethicsResults.setAdapter(adapter);
    }

    public void setDilemmaResultMsg(ArrayList<BusinessEthicsResult> resultList) {
        String resultMsg = "Congratulations\nScore:";
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
        resultMsg += "" + moralCnt + "/" + totalCnt;
        String msgValue = "Sorry...";
        double resultValue = (double)moralCnt/totalCnt;
        if (resultValue > 0.6)
            msgValue = "Fabulous";
        else if (resultValue > 0.3)
            msgValue = "Groovy";
        resultMsg += "\nHigh school wisdom: " + msgValue + "\n";
        bizethicsResultMsg.setText(resultMsg);
    }
}
