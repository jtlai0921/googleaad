package com.google.developers.mojimaster2;

import android.app.AlarmManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.developers.mojimaster2.service.NotificationJobService;

public class SettingActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    public static final int JOB_ID = 28;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        final String notifyKey = getString(R.string.pref_key_notification);
        if (key.equals(notifyKey)) {
            boolean on = sharedPreferences.getBoolean(notifyKey, false);
            JobScheduler jobScheduler =
                    (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

            if (jobScheduler == null) {
                return;
            }

            // 設置 JobInfo
            if(on) {
                long interval_day = AlarmManager.INTERVAL_DAY;
                ComponentName componentName = new ComponentName(this, NotificationJobService.class);
                JobInfo info = new JobInfo.Builder(JOB_ID, componentName)
                        .setPersisted(true)
                        .setPeriodic(interval_day)
                        .build();
                int requestCode = jobScheduler.schedule(info);
                if(requestCode == JobScheduler.RESULT_SUCCESS) {
                    Toast.makeText(this, "JobScheduler SUCCESS", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "JobScheduler ERROR", Toast.LENGTH_SHORT).show();
                }

            } else {
                jobScheduler.cancel(JOB_ID);
                Toast.makeText(this, "JobScheduler Cancel", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public static class SettingsFragment extends PreferenceFragment {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.preferences);
        }
    }

}
