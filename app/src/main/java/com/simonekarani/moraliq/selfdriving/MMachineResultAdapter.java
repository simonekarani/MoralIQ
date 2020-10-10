package com.simonekarani.moraliq.selfdriving;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.simonekarani.moraliq.R;

import java.util.ArrayList;

public class MMachineResultAdapter extends ArrayAdapter<MoralMachineResult> {
    private final static String WRONG_KEYWORD = " (Wrong)";

    private ArrayList<MoralMachineResult> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView qText;
        TextView qAnalysisText;
        TextView qAnalysis;
        RadioButton drivingOpt1;
        RadioButton drivingOpt2;
    }

    public MMachineResultAdapter(ArrayList<MoralMachineResult> data, Context context) {
        super(context, R.layout.selfdriving_results_row, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        MoralMachineResult dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.selfdriving_results_row, parent, false);
            viewHolder.qAnalysisText = (TextView) convertView.findViewById(R.id.driveAnalysisText);
            viewHolder.qAnalysis = (TextView) convertView.findViewById(R.id.driveAnalysis);
            viewHolder.drivingOpt1 = (RadioButton) convertView.findViewById(R.id.drivingOptAnswer1);
            viewHolder.drivingOpt2 = (RadioButton) convertView.findViewById(R.id.drivingOptAnswer2);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        int dataListIdx = dataModel.getmMachineDataIdx();
        int userSelectionIdx = dataModel.getUserOptIdx();
        MoralMachineModel reqData = MoralMachineData.MoralMachineDataList[dataListIdx];
        viewHolder.drivingOpt1.setBackground(
                convertView.getResources().getDrawable(reqData.getOption1ResId(), null));
        viewHolder.drivingOpt2.setBackground(
                convertView.getResources().getDrawable(reqData.getOption2ResId(), null));
        if (userSelectionIdx == 0) {
            if (reqData.getAnalysisOptId() == 0) {
                viewHolder.drivingOpt1.setText("Your Selection (Correct)");
                viewHolder.drivingOpt1.setTextColor(Color.GREEN);

                viewHolder.drivingOpt2.setText("");
            }
            else {
                viewHolder.drivingOpt1.setText("Your Selection (Wrong)");
                viewHolder.drivingOpt1.setTextColor(Color.MAGENTA);

                viewHolder.drivingOpt2.setText("Correct");
                viewHolder.drivingOpt2.setTextColor(Color.GREEN);
            }
        }
        else {
            if (reqData.getAnalysisOptId() == 0) {
                viewHolder.drivingOpt1.setText("Correct");
                viewHolder.drivingOpt1.setTextColor(Color.GREEN);

                viewHolder.drivingOpt2.setText("Your Selection (Wrong)");
                viewHolder.drivingOpt2.setTextColor(Color.MAGENTA);
            }
            else {
                viewHolder.drivingOpt1.setText("");

                viewHolder.drivingOpt2.setText("Your Selection (Correct)");
                viewHolder.drivingOpt2.setTextColor(Color.GREEN);
            }
        }

        viewHolder.qAnalysisText.setText("Analysis:");
        viewHolder.qAnalysis.setText(reqData.getAnalysis());

        // Return the completed view to render on screen
        return convertView;
    }
}
