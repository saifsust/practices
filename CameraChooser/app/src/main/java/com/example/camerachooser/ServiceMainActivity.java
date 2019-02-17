package com.example.camerachooser;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class ServiceMainActivity extends Activity {


    private Button startButton, stopButton, nextButton;
    private ButtonActionListner buttonActionListner = new ButtonActionListner();
    private Intent openIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_main_activity);

        startButton = (Button) findViewById(R.id.start_service);
        stopButton = (Button) findViewById(R.id.stop_service);
        nextButton = (Button) findViewById(R.id.next_page);

        startButton.setOnClickListener(buttonActionListner);
        stopButton.setOnClickListener(buttonActionListner);
        nextButton.setOnClickListener(buttonActionListner);

    }

    @Override
    public ComponentName startService(Intent service) {
        return super.startService(service);
    }


    private class ButtonActionListner implements View.OnClickListener {
        @Override
        public void onClick(View v


        ) {

            switch (v.getId()) {
                case R.id.start_service:
                    openIntent = new Intent(ServiceMainActivity.this, ServiceExample.class);
                    startService(openIntent);
                    break;
                case R.id.stop_service:
                    openIntent = new Intent(ServiceMainActivity.this,ServiceExample.class);
                    stopService(openIntent);
                    break;
                case R.id.next_page:
                    openIntent = new Intent(ServiceMainActivity.this,NextPageActivity.class);
                    startActivity(openIntent);
                    break;

                default:
                    return;

            }
        }
    }

}
