package com.google.developers.mojimaster2;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.TextInputEditText;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;
import android.support.text.emoji.widget.EmojiAppCompatEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.developers.mojimaster2.paging.SmileyViewModel;
import com.google.developers.mojimaster2.paging.ViewModelFactory;

/**
 * Saves a Smiley to local data source and shows a hex value of character.
 */
public class AddSmileyActivity extends AppCompatActivity implements TextWatcher {

    private SmileyViewModel mViewModel;
    private EmojiAppCompatEditText mEmojiEditText;
    private TextInputEditText mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelFactory viewModelFactory = ViewModelFactory.createFactory(this);
        mViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(SmileyViewModel.class);

        EmojiCompat.init(new BundledEmojiCompatConfig(this));
        setContentView(R.layout.activity_add_smiley);

        mEmojiEditText = findViewById(R.id.emoji_char);
        mEmojiEditText.addTextChangedListener(this);
        TextView mUnicode = findViewById(R.id.emoji_unicode);
        mName = findViewById(R.id.emoji_name);

        Button mSave = findViewById(R.id.save);
        mSave.setOnClickListener(this::saveOnClick);
    }

    public void saveOnClick(View view) {
        String emoji = mEmojiEditText.getText().toString().trim();
        String name = mName.getText().toString();

        if (emoji.length() == 0) {
            mEmojiEditText.setError(getString(R.string.missing_input));
            return;
        }

        finish();
    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        mViewModel.emojiChanged(text);
    }

    /**
     * No action needed.
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    /**
     * No action needed.
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

}
