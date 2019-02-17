package com.example.broatcastexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button send_ordered_broadcast, send_normal_broadcast, abort_ordered_broadcast;
    private ButtonActonListner actionListner = new ButtonActonListner();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        send_normal_broadcast = (Button) findViewById(R.id.send_normal_broadcast);
        send_ordered_broadcast = (Button) findViewById(R.id.send_ordered_broadcast);
        abort_ordered_broadcast = (Button) findViewById(R.id.abort_ordered_broadcast);

        send_normal_broadcast.setOnClickListener(actionListner);
        send_ordered_broadcast.setOnClickListener(actionListner);
        abort_ordered_broadcast.setOnClickListener(actionListner);
    }


    private class ButtonActonListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {



            switch (v.getId()){
                case R.id.send_normal_broadcast:
                    break;
                case R.id.send_ordered_broadcast:
                    break;
                case R.id.abort_ordered_broadcast:
                    break;
                default: return;
            }
        }
    }

}
