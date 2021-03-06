package com.simonekarani.moraliq.medethics;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.simonekarani.moraliq.R;

import java.util.ArrayList;

public class MedEthicalResultAdapter extends ArrayAdapter<MedEthicalResult> {
    private ArrayList<MedEthicalResult> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView qText;
        TextView qUser;
        TextView qAnswerText;
        Button qSelection1;
        Button qSelection2;
        Button qSelection3;
        Button qSelection4;
        TextView qAnalysisText;
        TextView qAnalysis;
    }

    public MedEthicalResultAdapter(ArrayList<MedEthicalResult> data, Context context) {
        super(context, R.layout.dilemma_results_row, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        MedEthicalResult dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dilemma_results_row, parent, false);
            viewHolder.qText = (TextView) convertView.findViewById(R.id.qText);
            viewHolder.qUser = (TextView) convertView.findViewById(R.id.qName);
            viewHolder.qAnswerText = (TextView) convertView.findViewById(R.id.qAnswerText);
            viewHolder.qSelection1 = (Button) convertView.findViewById(R.id.qOptAnswer1);
            viewHolder.qSelection2 = (Button) convertView.findViewById(R.id.qOptAnswer2);
            viewHolder.qSelection3 = (Button) convertView.findViewById(R.id.qOptAnswer3);
            viewHolder.qSelection4 = (Button) convertView.findViewById(R.id.qOptAnswer4);
            viewHolder.qAnalysisText = (TextView) convertView.findViewById(R.id.qAnalysisText);
            viewHolder.qAnalysis = (TextView) convertView.findViewById(R.id.qAnalysis);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        int dataListIdx = dataModel.getMedEthicalIdx();
        int userSelectionIdx = dataModel.getUserOptIdx();
        MedicalEthicalModel reqData = MedicalEthicalData.MED_ETHICAL_DATA_LIST[dataListIdx];
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0: viewHolder.qSelection1.setText(reqData.getOption1());
                    viewHolder.qSelection1.setClickable(false);
                    if (i == userSelectionIdx) {
                        viewHolder.qSelection1.setPressed(true);
                        viewHolder.qSelection1.setTypeface(null, Typeface.BOLD);
                    }
                    break;
                case 1: viewHolder.qSelection2.setText(reqData.getOption2());
                    viewHolder.qSelection2.setClickable(false);
                    if (i == userSelectionIdx) {
                        viewHolder.qSelection2.setPressed(true);
                        viewHolder.qSelection2.setTypeface(null, Typeface.BOLD);
                    }
                    break;
                case 2: viewHolder.qSelection3.setText(reqData.getOption3());
                    viewHolder.qSelection3.setClickable(false);
                    if (i == userSelectionIdx) {
                        viewHolder.qSelection3.setPressed(true);
                        viewHolder.qSelection3.setTypeface(null, Typeface.BOLD);
                    }
                    break;
                case 3:
                    if (reqData.getButtonCount() > 3) {
                        viewHolder.qSelection4.setVisibility(View.VISIBLE);
                        viewHolder.qSelection4.setText(reqData.getOption4());
                        viewHolder.qSelection4.setClickable(false);
                        if (i == userSelectionIdx) {
                            viewHolder.qSelection4.setPressed(true);
                            viewHolder.qSelection4.setTypeface(null, Typeface.BOLD);
                        }
                    }
                    else {
                        viewHolder.qSelection4.setVisibility(View.INVISIBLE);
                    }
                    break;
            }
        }

        viewHolder.qText.setText("Question:");
        viewHolder.qUser.setText(reqData.getQuestion().replaceAll("\n", ""));
        viewHolder.qAnswerText.setText("Your Selection:");
        viewHolder.qAnalysisText.setText("Analysis:");
        viewHolder.qAnalysis.setText(reqData.getAnalysis());

        // Return the completed view to render on screen
        return convertView;
    }
}
