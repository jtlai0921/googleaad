package com.edu.vincenttuan.app_viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends AndroidViewModel {
    Context context;
    private List<BMI> bmis = new ArrayList<>();
    public MyViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public List<BMI> getBmis() {
        return bmis;
    }

    public void addBmi(BMI bmi) {
        bmis.add(bmi);
    }
}
