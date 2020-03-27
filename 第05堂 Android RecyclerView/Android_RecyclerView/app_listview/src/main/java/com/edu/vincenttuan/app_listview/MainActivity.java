package com.edu.vincenttuan.app_listview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private List<Map<String, String>> foodList = new ArrayList<>();
    private String[] foodNames;
    private ListView listView;
    private BaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        foodNames = getResources().getStringArray(R.array.foodNames);
        //adapter = new FoodAdapter(this, foodList);
        adapter = new FoodAdapterHolder(this, foodList);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(String foodName : foodNames) {
                    Map<String, String> food = new HashMap<>();
                    food.put("id", "" + getResources().getIdentifier(foodName, "drawable", getPackageName()));
                    food.put("name", foodName.substring(0, 1).toUpperCase() + foodName.substring(1).toLowerCase());
                    foodList.add(food);
                }
                adapter.notifyDataSetChanged();
                setTitle(foodList.size() + " 筆");

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
