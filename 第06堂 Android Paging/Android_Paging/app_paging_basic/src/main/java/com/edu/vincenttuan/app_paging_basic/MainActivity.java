package com.edu.vincenttuan.app_paging_basic;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.edu.vincenttuan.app_paging_basic.dao.LottoDao;
import com.edu.vincenttuan.app_paging_basic.model.Lotto;
import com.edu.vincenttuan.app_paging_basic.room.LottoDatabase;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private LottoDatabase lottoDatabase;
    private LottoDao lottoDao;
    private RecyclerView recyclerView;
    private LottoAdapter lottoAdapter;

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
                Lotto lotto = new Lotto(getLottoNums());
                lottoDao.insert(lotto);

            }
        });

        lottoDatabase = Room.inMemoryDatabaseBuilder(this, LottoDatabase.class)
                .allowMainThreadQueries()
                .build();
        lottoDao = lottoDatabase.lottoDao();

        recyclerView = findViewById(R.id.recyclerView);

        lottoAdapter = new LottoAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(lottoAdapter);

        /*
            pageSize：設定每頁載入的數量
            prefetchDistance：距底部還有幾條資料時，載入下一頁資料，預設為 pagesize
            initialLoadSizeHint：初始化資料時載入的數量，預設為 pageSize*3
            enablePlaceholders：當item為null是否使用PlaceHolder展示
        */

        LiveData<PagedList<Lotto>> liveData =  new LivePagedListBuilder(
                lottoDao.getAll(),
                new PagedList.Config.Builder()
                        .setPageSize(30)
                        .setPrefetchDistance(20)
                        .setInitialLoadSizeHint(40)
                        .setEnablePlaceholders(false)
                        .build()
        ).build();


        liveData.observe(this, new Observer<PagedList<Lotto>>() {
            @Override
            public void onChanged(@Nullable PagedList<Lotto> lottos) {
                lottoAdapter.submitList(lottos);
            }
        });

        lottoAdapter.setOnItemClickListener(new LottoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Lotto lotto) {
                lotto.setNums(getLottoNums());
                lottoDao.update(lotto);
            }
        });

        lottoAdapter.setOnItemLongClickListener(new LottoAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, Lotto lotto) {
                lottoDao.delete(lotto);
            }
        });

    }

    private String getLottoNums() {
        Set<Integer> set = new HashSet<>();
        while (set.size() < 6) {
            set.add(new Random().nextInt(49) + 1);
        }
        return set.toString();
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
