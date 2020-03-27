package com.edu.vincenttuan.app_paging_viewmodel.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.edu.vincenttuan.app_paging_viewmodel.dao.LottoDao;
import com.edu.vincenttuan.app_paging_viewmodel.model.Lotto;

@Database(entities = {Lotto.class}, version = 1)
public abstract class LottoDatabase extends RoomDatabase {
    public abstract LottoDao lottoDao();
}
