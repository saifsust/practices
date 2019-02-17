package com.example.intenttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button sendmessage;
    private Intent sendIntent;
    private View.OnClickListener buttonClickListner;

    private final String message = "Hello I am Saiful Islam. I want to be your Friend.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
         *
         * resourse set for view the app's user interface
         *
         * */


        setContentView(R.layout.main_activity);

        sendmessage = (Button) findViewById(R.id.sendMessage);


        /**
         * Implements the send Message Button Action
         *
         * */


        buttonClickListner = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (v == findViewById(R.id.sendMessage)) {


                    try {

                        sendMessage();

                    } catch (Exception ex) {
                        System.out.println("Main Activity Send Message Exception : " + ex.getMessage());
                    }
                }
            }
        };


        sendmessage.setOnClickListener(buttonClickListner);


    }

    /*
     * Send message one activity to another activity  using intent class
     * */

    private boolean sendMessage() {


       // sendIntent = new Intent();
        sendIntent = new Intent(this,SharedActivity.class);
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,message);
        sendIntent.setType("text/plain");
        sendIntent.putExtra("message",message);

        startActivity(sendIntent);

       /* if(sendIntent.resolveActivity(getPackageManager()) != null){
            startActivity(sendIntent);
        }*/

        return true;
    }

}
