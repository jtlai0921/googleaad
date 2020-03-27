package com.edu.vincenttuan.app_paging_viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import com.edu.vincenttuan.app_paging_viewmodel.dao.LottoDao;
import com.edu.vincenttuan.app_paging_viewmodel.model.Lotto;
import com.edu.vincenttuan.app_paging_viewmodel.room.LottoDatabase;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LottoViewModel extends AndroidViewModel {
    private LottoDao lottoDao;

    public LottoViewModel(@NonNull Application application) {
        super(application);
        LottoDatabase database = Room.inMemoryDatabaseBuilder(
                application.getApplicationContext(),
                LottoDatabase.class
            ).allowMainThreadQueries().build();
        lottoDao = database.lottoDao();
    }

    public LiveData<PagedList<Lotto>> getLottos() {
        LiveData<PagedList<Lotto>> liveData =  new LivePagedListBuilder(
                lottoDao.getAll(),
                new PagedList.Config.Builder()
                        .setPageSize(30)
                        .setPrefetchDistance(20)
                        .setInitialLoadSizeHint(40)
                        .setEnablePlaceholders(false)
                        .build()
        ).build();
        return liveData;
    }

    public void addLotto() {
        lottoDao.insert(new Lotto(getLottoNums()));
    }

    public void updateLotto(Lotto lotto) {
        lotto.setNums(getLottoNums());
        lottoDao.update(lotto);
    }

    public void deleteLotto(Lotto lotto) {
        lottoDao.delete(lotto);
    }

    private String getLottoNums() {
        Set<Integer> set = new HashSet<>();
        while (set.size() < 6) {
            set.add(new Random().nextInt(49) + 1);
        }
        return set.toString();
    }
}
