package com.edu.vincenttuan.app_room.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.edu.vincenttuan.app_room.dao.DiceDao;
import com.edu.vincenttuan.app_room.po.Dice;

@Database(entities = {Dice.class}, version = 1)
public abstract class DiceDatabase extends RoomDatabase {
    public abstract DiceDao diceDao();
}
