package com.edu.vincenttuan.app_livedata_room;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.edu.vincenttuan.app_livedata_room.po.Num;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textNumber;
    private MainActivityViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.createNum();
            }
        });

        textNumber = findViewById(R.id.textNumber);

        model = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        model.getAllNumsLiveData().observe(this, new Observer<List<Num>>() {
            @Override
            public void onChanged(@Nullable List<Num> nums) {
                textNumber.setText(nums.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_update:
                model.updateNum();
                return true;
            case R.id.action_delete:
                model.deleteNum();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
