package com.edu.vincenttuan.app_livedata_viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Random;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<String> numLiveData;

    public MainActivityViewModel() {
        if(numLiveData == null) {
            numLiveData = new MutableLiveData<>();
            createNum();
        }
    }

    public MutableLiveData<String> getNumLiveData() {
        return numLiveData;
    }

    public void createNum() {
        Random r = new Random();
        numLiveData.setValue(r.nextInt(100) + "");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
