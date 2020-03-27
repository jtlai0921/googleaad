package com.edu.vincenttuan.app_paging_viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.edu.vincenttuan.app_paging_viewmodel.dao.LottoDao;
import com.edu.vincenttuan.app_paging_viewmodel.model.Lotto;
import com.edu.vincenttuan.app_paging_viewmodel.room.LottoDatabase;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private LottoViewModel model;
    private RecyclerView recyclerView;
    private LottoAdapter lottoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        model = ViewModelProviders.of(this).get(LottoViewModel.class);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1.新增
                model.addLotto();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);

        lottoAdapter = new LottoAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(lottoAdapter);

        model.getLottos().observe(this, new Observer<PagedList<Lotto>>() {
            @Override
            public void onChanged(@Nullable PagedList<Lotto> lottos) {
                lottoAdapter.submitList(lottos);
            }
        });

        lottoAdapter.setOnItemClickListener(new LottoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Lotto lotto) {
                // 3.修改
                model.updateLotto(lotto);
            }
        });

        lottoAdapter.setOnItemLongClickListener(new LottoAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, Lotto lotto) {
                // 4.刪除
                model.deleteLotto(lotto);
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
