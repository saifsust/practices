package org.waodec.geolocation.activities.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.waodec.geolocation.activities.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button click;
    private Intent nextInttent;
    private PendingIntent pendingIntent;
    private NotificationCompat.Builder builder;
    private NotificationManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_main_activity);

        click = (Button) findViewById(R.id.show_notification);
        click.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.show_notification) {

            System.out.println("Hello Bangladesh");
            sendNotification(view);
        }
    }


    private void sendNotification(View view) {

        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_sound_on);
        builder.setContentTitle("Hello Saiful Islam");
        builder.setContentText("I am developing myself......");


        //  nextInttent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));


        nextInttent = new Intent(MainActivity.this, NotificationBroadcastReceiver.class);

        //nextInttent.setAction(Intent.ACTION_VIEW);
        nextInttent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        nextInttent.putExtra("Hello SAIFUL ISLAM LITON", 0);

        pendingIntent = PendingIntent.getBroadcast(this, 0, nextInttent, 0);
        //pendingIntent = PendingIntent.getActivity(this, 0, nextInttent, 0);
       // builder.setContentIntent(pendingIntent);
        builder.addAction(R.drawable.common_google_signin_btn_icon_light,"BroadCast",pendingIntent);
        builder.setPriority(1000);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(001, builder.build());


    }


}
