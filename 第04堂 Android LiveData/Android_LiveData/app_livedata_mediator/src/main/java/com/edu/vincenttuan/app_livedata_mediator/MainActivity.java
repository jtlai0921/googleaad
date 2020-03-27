package com.edu.vincenttuan.app_livedata_mediator;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
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

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private MutableLiveData liveData1, liveData2;
    private TextView textNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab1 = findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                liveData1.setValue(r.nextInt(100) + "");
            }
        });

        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                liveData1.setValue(-1 * r.nextInt(100) + "");
            }
        });

        liveData1 = new MutableLiveData();
        liveData2 = new MutableLiveData();

        textNumber = findViewById(R.id.textNumber);

        final MediatorLiveData liveDataMerger = new MediatorLiveData();
        liveDataMerger.addSource(liveData1, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                liveDataMerger.setValue(o);
            }
        });
        liveDataMerger.addSource(liveData2, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                liveDataMerger.setValue(o);
            }
        });

        liveDataMerger.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                textNumber.setText(o.toString());
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
