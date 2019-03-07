package org.woadec.authentication.smsreader;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class IncomingSMSReader extends BroadcastReceiver {


    public Receiver getmReceiver() {
        return mReceiver;
    }

    public void setmReceiver(Receiver mReceiver) {
        this.mReceiver = mReceiver;
    }

    private Receiver mReceiver;

    @Override
    public void onReceive(Context context, Intent intent) {
    /*    StringBuilder sb = new StringBuilder();
        sb.append("Action: " + intent.getAction() + "\n");
        sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
        String log = sb.toString();
        Log.d(getClass().getSimpleName(), log);
        Toast.makeText(context, log, Toast.LENGTH_LONG).show();*/

       

        ArrayList sms = new ArrayList();

        Uri uriSms = Uri.parse("content://sms/inbox");
        Cursor cursor = context.getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"}, null, null, null);

        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            String address = cursor.getString(1);
            String body = cursor.getString(3);

            Log.e(getClass().getSimpleName(), address);
            Log.e(getClass().getSimpleName(), body);

            sms.add("Address=&gt; " + address + "n SMS =&gt; " + body);
        }


    }
}
