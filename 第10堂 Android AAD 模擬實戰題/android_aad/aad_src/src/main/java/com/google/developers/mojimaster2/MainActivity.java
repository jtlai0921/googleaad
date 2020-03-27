package com.google.developers.mojimaster2;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;
import android.support.text.emoji.widget.EmojiAppCompatTextView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.developers.mojimaster2.data.Smiley;
import com.google.developers.mojimaster2.game.AnswersView;
import com.google.developers.mojimaster2.game.GameViewModel;
import com.google.developers.mojimaster2.game.GameFactory;
import com.google.developers.mojimaster2.game.Result;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EmojiAppCompatTextView mQuestionView;
    private AnswersView mAnswersView;
    private TextView mResult;
    private GameViewModel mGameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameFactory viewModelFactory = GameFactory.createFactory(this);
        mGameViewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel.class);

        EmojiCompat.Config config = new BundledEmojiCompatConfig(this);
        EmojiCompat.init(config);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mQuestionView = findViewById(R.id.question);
        mResult = findViewById(R.id.result);

        mGameViewModel.getCurrentAnswer().observe(this, this::updateContent);
        mGameViewModel.getResults().observe(this, this::showResults);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this::loadNewGame);
    }

    /**
     * Listener on answer selection.
     */
    private void onAnswersChange(String answer) {
        mGameViewModel.updateResult(answer);
    }

    /**
     * Loads new game and updates observer.
     */
    private void loadNewGame(@Nullable View view) {
        mResult.setText(null);
        mGameViewModel.resetGame();
    }

    /**
     * Load answers for the next round.
     */
    private void loadRound(List<Smiley> smileys) {
        mAnswersView.loadAnswers(smileys);
        mGameViewModel.startNewGameRound();
    }

    /**
     * Show results of each round.
     */
    private void showResults(Result result) {
        if (result == null) {
            return;
        }
        mResult.setTextColor(getColor(result.getColor()));
        mResult.setText(getString(result.getResult()));

        if (!result.getEnableAnswersView()) {
            mAnswersView.setEnabled(false);
        }
    }

    private void updateContent(Smiley smiley) {
        if (smiley == null) {
            return;
        }
        mQuestionView.setText(smiley.getEmoji());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_list:
                Intent listIntent = new Intent(this, SmileyListActivity.class);
                startActivity(listIntent);
                return true;
            case R.id.action_add:
                Intent addIntent = new Intent(this, AddSmileyActivity.class);
                startActivity(addIntent);
                return true;
            case R.id.action_settings:
                Intent settingIntent = new Intent(this, SettingActivity.class);
                startActivity(settingIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
