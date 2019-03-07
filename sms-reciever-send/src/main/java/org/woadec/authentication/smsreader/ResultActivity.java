package org.woadec.authentication.smsreader;

import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;

import org.woadec.authentication.R;

public class ResultActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        tv = (TextView) findViewById(R.id.sms_view);
       /* for (SmsMessage sms : Telephony.Sms.Intents.getMessagesFromIntent(getIntent())) {
            tv.append(sms.getMessageBody());

            Log.e(getClass().getSimpleName(), sms.getMessageBody());

        }*/
    }
}
