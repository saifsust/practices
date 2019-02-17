package com.example.intenttest;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SharedActivity extends AppCompatActivity {


    private Intent receiverIntent;
    private TextView messageView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_activity);
        messageView = (TextView) findViewById(R.id.messageView);

        receiverIntent = getIntent();

        Bundle reciever = getIntent().getExtras();

        if (reciever == null) return;

        String message = reciever.getString("message");

        messageView.setText(message);


    }
}
