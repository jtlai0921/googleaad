package com.google.developers.mojimaster2.data;

import android.support.annotation.NonNull;

/**
 * A Model class that holds information about the emoji.
 * Class defines a table for the Room database with primary key the {@see #mCode}.
 */
public class Smiley {

    private String mCode;

    private String mName;

    private String mEmoji;

    public Smiley(@NonNull String code, String name, String emoji) {
        this.mCode = code;
        this.mName = name;
        this.mEmoji = emoji;
    }

    @NonNull
    public String getCode() {
        return mCode;
    }

    public String getName() {
        return mName;
    }

    public String getEmoji() {
        return mEmoji;
    }

}
