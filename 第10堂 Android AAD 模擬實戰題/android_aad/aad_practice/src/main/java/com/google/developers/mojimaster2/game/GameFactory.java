package com.google.developers.mojimaster2.game;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.google.developers.mojimaster2.R;
import com.google.developers.mojimaster2.data.DataRepository;

import java.lang.reflect.InvocationTargetException;

/**
 * Factory for GameViewModel.
 */
public class GameFactory implements ViewModelProvider.Factory {

    private final DataRepository mRepository;
    private final int mAnswerLimit;
    private static final String DEFAULT_ANSWER_LIMIT = "4";

    public static GameFactory createFactory(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Not yet attached to Application");
        }

        SharedPreferences defaultPreferences = PreferenceManager
                .getDefaultSharedPreferences(application.getApplicationContext());

        String key = application.getString(R.string.pref_key_answers);
        int limit = Integer.parseInt(defaultPreferences.getString(key, DEFAULT_ANSWER_LIMIT));

        return new GameFactory(DataRepository.getInstance(application), limit);
    }

    public GameFactory(DataRepository repository, int limit) {
        mRepository = repository;
        mAnswerLimit = limit;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(DataRepository.class, int.class)
                    .newInstance(mRepository, mAnswerLimit);
        } catch (NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }
    }
}
