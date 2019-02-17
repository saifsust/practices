package org.waodec.geolocation.activities.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationBroadcastReceiver extends BroadcastReceiver {

    private final String TAG = "Broadcast Receiver ";
    private StringBuffer sBuilder;


    @Override
    public void onReceive(Context context, Intent intent) {

        sBuilder = new StringBuffer();

        System.out.println("Helllo Receiver");

        Toast.makeText(context, "Hello Notification", Toast.LENGTH_LONG).show();

    }
}
