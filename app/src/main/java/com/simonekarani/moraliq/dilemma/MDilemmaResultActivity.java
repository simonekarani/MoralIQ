package com.simonekarani.moraliq.dilemma;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

import com.simonekarani.moraliq.R;

import androidx.appcompat.app.AppCompatActivity;

public class MDilemmaResultActivity extends AppCompatActivity {

    private ListView dilemmaResults = null;
    private TextView dilemmaResultMsg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dilemma_results);
        setTitle("Moral Dilemma Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle resBundle = getIntent().getExtras();
        ArrayList<MoralDilemmaResult> resultList = resBundle.getParcelableArrayList("dilemmaResult");

        dilemmaResultMsg = (TextView) findViewById(R.id.dilemmaResultMsg);
        setDilemmaResultMsg(resultList);

        dilemmaResults = (ListView) findViewById(R.id.dilemmaResultList);
        dilemmaResults.setSelector(android.R.color.transparent);
        ArrayList<String> questionList = new ArrayList<String>();
        final MDilemmaResultAdapter adapter = new MDilemmaResultAdapter(resultList, getApplicationContext());
        dilemmaResults.setAdapter(adapter);
    }

    public void setDilemmaResultMsg(ArrayList<MoralDilemmaResult> resultList) {
        String resultMsg = "Congratulations, you have completed \"Moral Dilemma\" quiz, and you scored ";
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
        String msgValue = "oohhh...";
        double resultValue = (double)moralCnt/totalCnt;
        if (resultValue > 0.6)
            msgValue = "Fabulous";
        else if (resultValue > 0.3)
            msgValue = "Groovy";
        resultMsg += ". The high school wisdom says " + msgValue + "\n";
        resultMsg += "Below are your selections with Analysis";
        dilemmaResultMsg.setText(resultMsg);
    }
}
