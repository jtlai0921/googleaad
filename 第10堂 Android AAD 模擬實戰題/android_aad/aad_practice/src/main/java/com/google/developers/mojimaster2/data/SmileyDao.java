package com.google.developers.mojimaster2.data;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Room data access object.
 */
@Dao
public interface SmileyDao {

    /**
     * Returns all data in table for Paging.
     */
    @Query("SELECT * FROM smiley ORDER BY name")
    DataSource.Factory<Integer, Smiley> getAll();

    /**
     * Returns LiveData of random Smileys.
     *
     * @param limit number of return
     */
    @Query("SELECT * FROM smiley ORDER BY RANDOM() LIMIT :limit")
    LiveData<List<Smiley>> getRandom(int limit);

    /**
     * Returns a random Smiley.
     */
    @Query("SELECT * FROM smiley ORDER BY RANDOM() LIMIT 1")
    Smiley getSmiley();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Smiley... smiley);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Smiley smiley);

    @Delete
    void delete(Smiley smiley);

}
