package com.example.camerachooser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class ImageViewActivity extends AppCompatActivity {

    private ImageView imageView;
    private Intent receiver;
    private Bundle receiverBundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_view_activity);
        imageView = (ImageView) findViewById(R.id.imageView);
    }


    private void imageViewer() {
        receiver = getIntent();

        receiverBundle = receiver.getExtras();


        //Bitmap imageBitMap = receiver


    }


}
