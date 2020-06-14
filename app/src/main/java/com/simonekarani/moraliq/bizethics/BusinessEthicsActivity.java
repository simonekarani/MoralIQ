//
//  BusinessEthicsActivity.java
//  MoralIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 MoralIQ. All rights reserved.
//

package com.simonekarani.moraliq.bizethics;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.simonekarani.moraliq.R;
import com.simonekarani.moraliq.model.MainScreenDataModel;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class BusinessEthicsActivity extends AppCompatActivity {


    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<MainScreenDataModel> data;
    static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizethics);
        setTitle("Business Moral IQ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(getSupportFragmentManager().getBackStackEntryCount() > 0)
                    getSupportFragmentManager().popBackStack();
                break;
            default:
                super.onOptionsItemSelected(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}