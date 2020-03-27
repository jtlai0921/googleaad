package com.edu.vincenttuan.app_room;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.edu.vincenttuan.app_room.dao.DiceDao;
import com.edu.vincenttuan.app_room.po.Dice;
import com.edu.vincenttuan.app_room.room.DiceDatabase;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private DiceDatabase diceDatabase;
    private DiceDao diceDao;
    private Context context;
    private TextView textView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        diceDatabase = Room.inMemoryDatabaseBuilder(context, DiceDatabase.class)
        //diceDatabase = Room.databaseBuilder(context, DiceDatabase.class, "DiceDatabase")
                //.allowMainThreadQueries()
                .build();
        diceDao = diceDatabase.diceDao();

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                Dice dice = new Dice(
                        r.nextInt(6) + 1,
                        r.nextInt(6) + 1,
                        r.nextInt(6) + 1,
                        r.nextInt(6) + 1);
                Executors.newSingleThreadExecutor().execute(()->{
                    diceDao.insert(dice);
                    List<Dice> list = diceDao.getAll();
                    list.stream().forEach(d -> d.setSum(d.getD1() + d.getD2() + d.getD3() + d.getD4()));
                    runOnUiThread(() -> {
                        setTitle(list.size() + "");
                        textView.setText(list.toString());
                    });
                });
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        diceDatabase.close();
    }

    public void update(View view) {
        Random r = new Random();
        long id = Long.parseLong(editText.getText().toString());
        Dice dice = new Dice(
                r.nextInt(6) + 1,
                r.nextInt(6) + 1,
                r.nextInt(6) + 1,
                r.nextInt(6) + 1);
        dice.setId(id);
        Executors.newSingleThreadExecutor().execute(() -> {
            diceDao.update(dice);
            List<Dice> list = diceDao.getAll();
            list.stream().forEach(d -> d.setSum(d.getD1() + d.getD2() + d.getD3() + d.getD4()));
            runOnUiThread(() -> {
                textView.setText(list.toString());
            });

        });
    }

    public void delete(View view) {
        long id = Long.parseLong(editText.getText().toString());
        Dice dice = new Dice();
        dice.setId(id);
        Executors.newSingleThreadExecutor().execute(() -> {
            diceDao.delete(dice);
            List<Dice> list = diceDao.getAll();
            list.stream().forEach(d -> d.setSum(d.getD1() + d.getD2() + d.getD3() + d.getD4()));
            runOnUiThread(() -> {
                textView.setText(list.toString());
            });

        });
    }
}
