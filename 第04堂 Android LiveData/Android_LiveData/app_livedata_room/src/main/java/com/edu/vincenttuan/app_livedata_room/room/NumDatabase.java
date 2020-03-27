package com.edu.vincenttuan.app_livedata_room.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.edu.vincenttuan.app_livedata_room.dao.NumDao;
import com.edu.vincenttuan.app_livedata_room.po.Num;

@Database(entities = {Num.class}, version = 1)
public abstract class NumDatabase extends RoomDatabase {
    public abstract NumDao numDao();
}
