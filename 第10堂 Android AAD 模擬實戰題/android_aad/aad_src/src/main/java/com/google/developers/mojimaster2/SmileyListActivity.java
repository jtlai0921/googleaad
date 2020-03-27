package com.google.developers.mojimaster2;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;
import android.support.v7.app.AppCompatActivity;
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
