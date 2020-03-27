package com.google.developers.mojimaster2.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * A Model class that holds information about the emoji.
 * Class defines a table for the Room database with primary key the {@see #mCode}.
 */
@Entity(tableName = DataSmileyName.TABLE_NAME)
public class Smiley {

    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = DataSmileyName.COL_UNICODE)
    private String mCode;

    @ColumnInfo(name = DataSmileyName.COL_NAME)
    private String mName;

    @ColumnInfo(name = DataSmileyName.COL_EMOJI)
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

    @Override
    public String toString() {
        return "Smiley{" +
                "mCode='" + mCode + '\'' +
                ", mName='" + mName + '\'' +
                ", mEmoji='" + mEmoji + '\'' +
                '}';
    }
}
