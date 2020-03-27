package com.google.developers.mojimaster2;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.google.developers.mojimaster2.data.Smiley;
import com.google.developers.mojimaster2.paging.SmileyAdapter;
import com.google.developers.mojimaster2.paging.SmileyViewModel;
import com.google.developers.mojimaster2.paging.ViewModelFactory;

/**
 * Activity lists all Smileys in RecyclerView using Paging library.
 */
public class SmileyListActivity extends AppCompatActivity {

    private SmileyViewModel mViewModel;
    private RecyclerView mRecycler;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelFactory viewModelFactory = ViewModelFactory.createFactory(this);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(SmileyViewModel.class);

        EmojiCompat.init(new BundledEmojiCompatConfig(this));
        setContentView(R.layout.activity_smileys);

        SmileyAdapter adapter = new SmileyAdapter();

        // 取得 View Model
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(SmileyViewModel.class);

        // 取得 LiveData 資料
        LiveData<PagedList<Smiley>> liveData = mViewModel.getAllSmileys();

        // 訂閱
        liveData.observe(this, new Observer<PagedList<Smiley>>() {
            @Override
            public void onChanged(@Nullable PagedList<Smiley> smilies) {
                adapter.submitList(smilies);
            }
        });

        // 建立 RecyclerView 實體
        mRecycler = findViewById(R.id.recyclerView);

        // 取得 LayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // 設定 LayoutManager 管理 RecyclerView
        mRecycler.setLayoutManager(layoutManager);

        // 設定適配器
        mRecycler.setAdapter(adapter);

        initAction();

        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddSmileyActivity.class);
            startActivity(intent);
        });
    }

    public void initAction() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            @Override
            public int getMovementFlags(RecyclerView recyclerView,
                                        RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, ItemTouchHelper.RIGHT);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Smiley smiley = ((SmileyAdapter.SmileyViewHolder) viewHolder).getSmiley();
                mViewModel.delete(smiley);

                String text = getString(R.string.undo_deleted, smiley.getEmoji());
                Snackbar.make(mFab, text, Snackbar.LENGTH_LONG)
                        .setAction("Undo", view -> mViewModel.save(smiley)).show();
            }
        });

        itemTouchHelper.attachToRecyclerView(mRecycler);
    }

}
