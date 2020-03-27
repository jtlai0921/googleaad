package com.google.developers.mojimaster2.paging;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.google.developers.mojimaster2.data.Smiley;
import com.google.developers.mojimaster2.data.DataRepository;

/**
 * Store and manage data for SmileyListActivity and AddSmileyActivity activity.
 */
public class SmileyViewModel extends ViewModel {

    private final DataRepository mRepository;
    private final MutableLiveData<String> mUnicode = new MutableLiveData<>();
    public static int PAGE_SIZE = 30;
    public static boolean PLACEHOLDERS = true;

    public SmileyViewModel(DataRepository repository) {
        mRepository = repository;
    }

    @SuppressWarnings("unchecked")
    public LiveData<PagedList<Smiley>> getAllSmileys() {
        return null;
    }

    public MutableLiveData<String> getUnicode() {
        return mUnicode;
    }

    public void emojiChanged(String text) {
        mUnicode.setValue(getStringsHex(text));
    }

    public void save(String emoji, String name) {
        Smiley smiley = new Smiley(getStringsHex(emoji), name, emoji);
        save(smiley);
    }

    public void save(Smiley smiley) {

    }

    public void delete(Smiley smiley) {
        mRepository.delete(smiley);
    }

    /**
     * Returns a Unicode U+1F609.
     * Get the code point of a String, then get the hex value of it.
     *
     * @param text string of any characters
     * @return hex string of the code point
     */
    private String getStringsHex(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        return "U+" + Long.toHexString(text.codePointAt(0)).toUpperCase();
    }

}
