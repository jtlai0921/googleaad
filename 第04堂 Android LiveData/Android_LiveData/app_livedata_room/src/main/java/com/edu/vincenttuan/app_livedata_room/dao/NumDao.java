package com.edu.vincenttuan.app_livedata_room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.edu.vincenttuan.app_livedata_room.po.Num;

import java.util.List;

@Dao
public interface NumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insert(Num num);

    @Query("select * from Num order by Random() LIMIT 1")
    public Num get();

    @Query("select * from Num")
    public LiveData<List<Num>> getAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void update(Num num);

    @Delete
    public void delete(Num num);
}
