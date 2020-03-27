package com.edu.vincenttuan.app_livedata_room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import com.edu.vincenttuan.app_livedata_room.dao.NumDao;
import com.edu.vincenttuan.app_livedata_room.po.Num;
import com.edu.vincenttuan.app_livedata_room.room.NumDatabase;

import java.util.List;
import java.util.Random;

public class MainActivityViewModel extends AndroidViewModel {
    private LiveData<List<Num>> allNumsLiveData;
    private NumDao numDao;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        NumDatabase numDatabase = Room.inMemoryDatabaseBuilder(getApplication().getApplicationContext(), NumDatabase.class)
                .allowMainThreadQueries()
                .build();
        numDao = numDatabase.numDao();
        allNumsLiveData = numDao.getAll();
    }

    public LiveData<List<Num>> getAllNumsLiveData() {
        return allNumsLiveData;
    }

    public void createNum() {
        Random r = new Random();
        numDao.insert(new Num(r.nextInt(100)));
    }

    public void updateNum() {
        Num num = numDao.get();
        if(num != null) {
            Random r = new Random();
            num.setN1(r.nextInt(100));
            numDao.update(num);
        }
    }

    public void deleteNum() {
        Num num = numDao.get();
        if(num != null) {
            numDao.delete(num);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
