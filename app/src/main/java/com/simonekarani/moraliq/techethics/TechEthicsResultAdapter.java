package com.simonekarani.moraliq.techethics;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.simonekarani.moraliq.R;

import java.util.ArrayList;

public class TechEthicsResultAdapter extends ArrayAdapter<TechEthicsResult> {
    private final static String WRONG_KEYWORD = " (Wrong)";

    private ArrayList<TechEthicsResult> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView qText;
        TextView qUser;
        TextView qAnswerText;
        RadioButton qSelection1;
        RadioButton qSelection2;
        RadioButton qSelection3;
        RadioButton qSelection4;
        TextView qAnalysisText;
        TextView qAnalysis;
    }

    public TechEthicsResultAdapter(ArrayList<TechEthicsResult> data, Context context) {
        super(context, R.layout.dilemma_results_row, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TechEthicsResult dataModel = getItem(position);
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
            viewHolder.qSelection1 = (RadioButton) convertView.findViewById(R.id.qOptAnswer1);
            viewHolder.qSelection2 = (RadioButton) convertView.findViewById(R.id.qOptAnswer2);
            viewHolder.qSelection3 = (RadioButton) convertView.findViewById(R.id.qOptAnswer3);
            viewHolder.qSelection4 = (RadioButton) convertView.findViewById(R.id.qOptAnswer4);
            viewHolder.qAnalysisText = (TextView) convertView.findViewById(R.id.qAnalysisText);
            viewHolder.qAnalysis = (TextView) convertView.findViewById(R.id.qAnalysis);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        int dataListIdx = dataModel.getTechEthicsIdx();
        int userSelectionIdx = dataModel.getUserOptIdx();
        TechEthicsModel reqData = TechEthicsData.TechEthicsDataList[dataListIdx];
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0: viewHolder.qSelection1.setText(reqData.getOption1());
                    if (i == userSelectionIdx) {
                        if (userSelectionIdx == reqData.getAnalysisOpt()) {
                            viewHolder.qSelection1.setText(reqData.getOption1());
                            viewHolder.qSelection1.setTextColor(Color.parseColor("#000000"));
                            viewHolder.qSelection1.setTypeface(null, Typeface.BOLD);
                        }
                        else {
                            String textDisplay = reqData.getOption1() + WRONG_KEYWORD;
                            viewHolder.qSelection1.setTypeface(null, Typeface.NORMAL);
                            viewHolder.qSelection1.setText(textDisplay);
                            viewHolder.qSelection1.setTextColor(Color.parseColor("#ba160c"));
                        }
                        viewHolder.qSelection1.setHighlightColor(Color.parseColor("#ba160c"));
                        viewHolder.qSelection1.setChecked(true);
                    }
                    else if (i == reqData.getAnalysisOpt()) {
                        viewHolder.qSelection1.setText(reqData.getOption1());
                        viewHolder.qSelection1.setTypeface(null, Typeface.BOLD);
                        viewHolder.qSelection1.setTextColor(Color.parseColor("#0000ff"));
                        viewHolder.qSelection1.setHighlightColor(Color.parseColor("#0000ff"));
                        viewHolder.qSelection1.setChecked(true);
                    }
                    else {
                        viewHolder.qSelection1.setChecked(false);
                        viewHolder.qSelection1.setTypeface(null, Typeface.NORMAL);
                        viewHolder.qSelection1.setText(reqData.getOption1());
                    }
                    viewHolder.qSelection1.setClickable(false);
                    break;
                case 1: viewHolder.qSelection2.setText(reqData.getOption2());
                    if (i == userSelectionIdx) {
                        if (userSelectionIdx == reqData.getAnalysisOpt()) {
                            viewHolder.qSelection2.setText(reqData.getOption2());
                            viewHolder.qSelection2.setTextColor(Color.parseColor("#000000"));
                            viewHolder.qSelection2.setTypeface(null, Typeface.BOLD);
                        }
                        else {
                            String textDisplay = reqData.getOption2() + WRONG_KEYWORD;
                            viewHolder.qSelection2.setTypeface(null, Typeface.NORMAL);
                            viewHolder.qSelection2.setText(textDisplay);
                            viewHolder.qSelection2.setTextColor(Color.parseColor("#ba160c"));
                        }
                        viewHolder.qSelection2.setHighlightColor(Color.parseColor("#ba160c"));
                        viewHolder.qSelection2.setChecked(true);
                    }
                    else if (i == reqData.getAnalysisOpt()) {
                        viewHolder.qSelection2.setText(reqData.getOption2());
                        viewHolder.qSelection2.setTextColor(Color.parseColor("#0000ff"));
                        viewHolder.qSelection2.setTypeface(null, Typeface.BOLD);
                        viewHolder.qSelection2.setHighlightColor(Color.parseColor("#0000ff"));
                        viewHolder.qSelection2.setChecked(true);
                    }
                    else {
                        viewHolder.qSelection2.setChecked(false);
                        viewHolder.qSelection2.setTypeface(null, Typeface.NORMAL);
                        viewHolder.qSelection2.setText(reqData.getOption2());
                    }
                    viewHolder.qSelection2.setClickable(false);
                    break;
                case 2: viewHolder.qSelection3.setText(reqData.getOption3());
                    if (i == userSelectionIdx) {
                        if (userSelectionIdx == reqData.getAnalysisOpt()) {
                            viewHolder.qSelection3.setText(reqData.getOption3());
                            viewHolder.qSelection3.setTextColor(Color.parseColor("#000000"));
                            viewHolder.qSelection3.setTypeface(null, Typeface.BOLD);
                        }
                        else {
                            String textDisplay = reqData.getOption3() + WRONG_KEYWORD;
                            viewHolder.qSelection3.setTypeface(null, Typeface.NORMAL);
                            viewHolder.qSelection3.setText(textDisplay);
                            viewHolder.qSelection3.setTextColor(Color.parseColor("#ba160c"));
                        }
                        viewHolder.qSelection3.setHighlightColor(Color.parseColor("#ba160c"));
                        viewHolder.qSelection3.setChecked(true);
                    }
                    else if (i == reqData.getAnalysisOpt()) {
                        viewHolder.qSelection3.setText(reqData.getOption3());
                        viewHolder.qSelection3.setTextColor(Color.parseColor("#0000ff"));
                        viewHolder.qSelection3.setTypeface(null, Typeface.BOLD);
                        viewHolder.qSelection3.setHighlightColor(Color.parseColor("#0000ff"));
                        viewHolder.qSelection3.setChecked(true);
                    }
                    else {
                        viewHolder.qSelection3.setChecked(false);
                        viewHolder.qSelection3.setTypeface(null, Typeface.NORMAL);
                        viewHolder.qSelection3.setText(reqData.getOption3());
                    }
                    viewHolder.qSelection3.setClickable(false);
                    break;
                case 3: viewHolder.qSelection4.setText(reqData.getOption4());
                    if (i == userSelectionIdx) {
                        if (userSelectionIdx == reqData.getAnalysisOpt()) {
                            viewHolder.qSelection4.setText(reqData.getOption4());
                            viewHolder.qSelection4.setTextColor(Color.parseColor("#000000"));
                            viewHolder.qSelection4.setTypeface(null, Typeface.BOLD);
                        }
                        else {
                            String textDisplay = reqData.getOption4() + WRONG_KEYWORD;
                            viewHolder.qSelection4.setTypeface(null, Typeface.NORMAL);
                            viewHolder.qSelection4.setText(textDisplay);
                            viewHolder.qSelection4.setTextColor(Color.parseColor("#ba160c"));
                        }
                        viewHolder.qSelection4.setHighlightColor(Color.parseColor("#ba160c"));
                        viewHolder.qSelection4.setChecked(true);
                    }
                    else if (i == reqData.getAnalysisOpt()) {
                        viewHolder.qSelection4.setText(reqData.getOption4());
                        viewHolder.qSelection4.setTextColor(Color.parseColor("#0000ff"));
                        viewHolder.qSelection4.setTypeface(null, Typeface.BOLD);
                        viewHolder.qSelection4.setHighlightColor(Color.parseColor("#0000ff"));
                        viewHolder.qSelection4.setChecked(true);
                    }
                    else {
                        viewHolder.qSelection4.setChecked(false);
                        viewHolder.qSelection4.setTypeface(null, Typeface.NORMAL);
                        viewHolder.qSelection4.setText(reqData.getOption4());
                    }
                    viewHolder.qSelection4.setClickable(false);
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
