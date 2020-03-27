package com.google.developers.mojimaster2.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

/**
 * Room data access object.
 */
public interface SmileyDao {

    /**
     * Returns all data in table for Paging.
     */
    Smiley getAll();

    /**
     * Returns LiveData of random Smileys.
     *
     * @param limit number of return
     */
    Smiley getRandom(int limit);

    /**
     * Returns a random Smiley.
     */
    Smiley getSmiley();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Smiley... smiley);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Smiley smiley);

    @Delete
    void delete(Smiley smiley);

}
