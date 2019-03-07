package org.woadec.authentication.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReader extends BroadcastReceiver {

    private static MessageListener mListener;


    @Override
    public void onReceive(Context context, Intent intent) {


        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = null;
        if (bundle != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            }
        }

        Log.e(getClass().getSimpleName(), "receiver called");
        for (int i = 0; i < messages.length; i++) {

            String message = messages[i].getMessageBody();

            Log.e(getClass().getSimpleName(), message);

            mListener.messageReceived(message);
        }

    }

    public static void bindListener(MessageListener listener) {
        mListener = listener;
    }

}
