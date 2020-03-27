package com.google.developers.mojimaster2.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.google.developers.mojimaster2.MainActivity;
import com.google.developers.mojimaster2.R;
import com.google.developers.mojimaster2.data.DataRepository;
import com.google.developers.mojimaster2.data.Smiley;

public class NotificationJobService extends JobService {
    public static final int INTENT_ID = 11;
    public static final int NOTIFICATION_ID = 18;
    public static final String CHANNEL_ID = "notify-smiley";

    @Override
    public boolean onStartJob(JobParameters params) {
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        DataRepository repository = DataRepository.getInstance(getApplication());
        Smiley smiley = repository.getSmiley();

        if (notificationManager == null | smiley == null) {
            Log.i(NotificationJobService.class.getName(), "Failed to create notification");
            return false;
        }

        // 定義 Notification
        // 點擊後要執行的 Activity
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                INTENT_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_mood)
                .setContentTitle(String.format("%1$s : %2$s", smiley.getEmoji(), smiley.getCode()))
                .setContentText(smiley.getName())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setPriority(Notification.PRIORITY_MAX);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.notification_channel_name);
            String description = getString(R.string.notification_channel_description);

            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    name,
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(description);
            channel.enableLights(true);
            channel.enableVibration(true);

            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(CHANNEL_ID);
        }

        notificationManager.notify(NOTIFICATION_ID, builder.build());
        Toast.makeText(this, "onStartJob", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Toast.makeText(this, "onStopJob", Toast.LENGTH_SHORT).show();
        return true;
    }

}
