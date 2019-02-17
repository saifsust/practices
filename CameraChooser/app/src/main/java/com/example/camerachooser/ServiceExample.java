package com.example.camerachooser;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class ServiceExample extends Service {


    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this,"Service is created ! ",Toast.LENGTH_LONG).show();
        mediaPlayer = MediaPlayer.create(this,R.raw.song);
        mediaPlayer.setLooping(false);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Toast.makeText(this,"Service is started !",Toast.LENGTH_LONG).show();
        mediaPlayer.start();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Service is Destroy",Toast.LENGTH_LONG).show();
        mediaPlayer.stop();
    }


}
