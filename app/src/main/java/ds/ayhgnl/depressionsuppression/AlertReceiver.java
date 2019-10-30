package ds.ayhgnl.depressionsuppression;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlertReceiver extends BroadcastReceiver {
    public static final String CHANNEL_1_ID = "channel";
    private NotificationManagerCompat notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        String notifTitle = "Depression Suppression!";
        String notifDesc = "Come take your weekly survey";
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());


    }
}
