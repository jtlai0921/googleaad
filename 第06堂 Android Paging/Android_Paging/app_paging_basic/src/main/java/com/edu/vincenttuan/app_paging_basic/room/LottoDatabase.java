package com.edu.vincenttuan.app_paging_basic.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.edu.vincenttuan.app_paging_basic.dao.LottoDao;
import com.edu.vincenttuan.app_paging_basic.model.Lotto;

@Database(entities = {Lotto.class}, version = 1)
public abstract class LottoDatabase extends RoomDatabase {
    public abstract LottoDao lottoDao();
}
