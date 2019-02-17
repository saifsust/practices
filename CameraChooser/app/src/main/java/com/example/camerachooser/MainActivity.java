package com.example.camerachooser;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {


    private Intent explicity;
    private Button camera_capture;
    private ButtonClickAction clickAction = new ButtonClickAction();

    private File imageFile;
    private String image_current_path;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        camera_capture = (Button) findViewById(R.id.capture);
        camera_capture.setOnClickListener(clickAction);

    }

    private void capture_picture() {


        explicity = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivity(explicity);


    }

    private String createInmagePath() {


        String timeStem = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        return timeStem;
    }


    private class ButtonClickAction implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (v == findViewById(R.id.capture)) {

                //  capture_picture();

                System.out.println(createInmagePath());

            }

        }
    }
}



