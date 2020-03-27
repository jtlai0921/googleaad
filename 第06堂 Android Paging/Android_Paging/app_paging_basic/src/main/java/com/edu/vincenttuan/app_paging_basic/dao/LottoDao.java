package com.edu.vincenttuan.app_paging_basic.dao;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.edu.vincenttuan.app_paging_basic.model.Lotto;

import java.util.List;

@Dao
public interface LottoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Lotto lotto);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void update(Lotto lotto);

    @Delete
    public void delete(Lotto lotto);

    @Query("select * from lotto")
    public DataSource.Factory<Integer, Lotto> getAll();

    @Query("select * from lotto")
    public List<Lotto> getAllTest();
}
