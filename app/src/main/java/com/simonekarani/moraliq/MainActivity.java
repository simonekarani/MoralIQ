//
//  MainScreenActivity.java
//  EthicalIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 EthicalIQ. All rights reserved.
//

package com.simonekarani.moraliq;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.simonekarani.moraliq.bizethics.BusinessEthicsActivity;
import com.simonekarani.moraliq.dilemma.MoralDilemmaActivity;
import com.simonekarani.moraliq.medethics.MedicalEthicsActivity;
import com.simonekarani.moraliq.model.MainScreenData;
import com.simonekarani.moraliq.model.MainScreenDataModel;
import com.simonekarani.moraliq.selfdriving.MoralMachineActivity;
import com.simonekarani.moraliq.techethics.TechEthicsActivity;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.simonekarani.moraliq.model.MainScreenData.*;

public class MainActivity extends AppCompatActivity implements MainScreenDataAdapter.OnMoralTopicListener {

    private static final String TAG = "MainActivity";

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<MainScreenDataModel> data;
    static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myOnClickListener = new MyOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<MainScreenDataModel>();
        for (int i = 0; i < MainScreenData.nameArray.length; i++) {
            data.add(new MainScreenDataModel(
                    MainScreenData.nameArray[i],
                    MainScreenData.id_[i],
                    MainScreenData.drawableArray[i]
            ));
        }

        adapter = new MainScreenDataAdapter(data, this);
        recyclerView.setAdapter(adapter);
    }

    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "iconImageViewOnClick at position ");
        }
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        return true;
    }

    @Override
    public void onTopicClick(int position) {
        Intent intent = new Intent(this, MoralMachineActivity.class);
        switch (position) {
            case MORAL_DILEMMA_ID:
                intent = new Intent(this, MoralDilemmaActivity.class);
                break;
            case MORAL_MEDICAL_ETHICS_ID:
                intent = new Intent(this, MedicalEthicsActivity.class);
                break;
            case MORAL_TECH_ETHICS_ID:
                intent = new Intent(this, TechEthicsActivity.class);
                break;
            case MORAL_BIZ_ETHICS_ID:
                intent = new Intent(this, BusinessEthicsActivity.class);
                break;
            case MORAL_SELF_DRIVING_ID:
                intent = new Intent(this, MoralMachineActivity.class);
                break;
            default:
                intent = new Intent(this, MoralMachineActivity.class);
                break;
        }
        startActivity(intent);
    }
}