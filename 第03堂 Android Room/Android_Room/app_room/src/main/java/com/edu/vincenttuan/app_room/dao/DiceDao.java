package com.edu.vincenttuan.app_room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.edu.vincenttuan.app_room.po.Dice;

import java.util.List;

@Dao
public interface DiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insert(Dice dice);

    @Query("select * from dice")
    public List<Dice> getAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void update(Dice dice);

    @Delete
    public void delete(Dice dice);
}
