package org.woadec.authentication.sms_app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.woadec.authentication.R;
import org.woadec.authentication.smsreader.MainActivity;

import java.security.Permission;

public class SmsMainActivity extends AppCompatActivity {


    private SmsManager smsManager;

    private static final int SEND_SMS_REQUEST_PERMISSION = 1;

    private Button sendSMS;
    private TextView viewSMS;
    private EditText body, phoneNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_app_activity);


        //send_sms_Permission();


        sendSMS = (Button) findViewById(R.id.sms_send);
        sendSMS.setOnClickListener(new SendButtonListener());

        viewSMS = (TextView) findViewById(R.id.sms_view);
        body = (EditText) findViewById(R.id.sms_typer);

        phoneNum = (EditText) findViewById(R.id.phone_number);


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case SEND_SMS_REQUEST_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();

                }
                return;

        }

    }

    private class SendButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {


            try {
                send_sms_Permission();

                if (isEmpty())
                    Toast.makeText(getApplicationContext(), "Please,enter text and phone number", Toast.LENGTH_LONG).show();
                else {

                    smsSendBySMSManager();
                }

                //sendSmsByIntentView();
                Log.i(getClass().getSimpleName(), "successfully granted");
            } catch (Exception ex) {
                Log.e(getClass().getSimpleName(), ex.getMessage());
            }


        }
    }


    private boolean isEmpty() {
        return phoneNum.getText().toString().isEmpty() || body.getText().toString().isEmpty();
    }


    private void smsSendBySMSManager() {
        smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNum.getText().toString(), null, body.getText().toString(), null, null);
        Toast.makeText(getApplicationContext(), "SMS sent.",
                Toast.LENGTH_LONG).show();


    }


    private void send_sms_Permission() {
        if (!is_send_sms_permission_granted()) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)) {
                    Toast.makeText(getApplicationContext(), "Please allow permission!", Toast.LENGTH_SHORT).show();
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.requestPermissions(new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_REQUEST_PERMISSION);
            }
            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_REQUEST_PERMISSION);
        }

    }

    /**
     * sendSmsByIntentView is a  method of
     *
     * @throws Exception
     */

    private void sendSmsByIntentView() throws Exception {


        Intent sendIntent = new Intent(Intent.ACTION_VIEW);

        sendIntent.setData(Uri.parse("smsto:"));
        sendIntent.setType("vnd.android-dir/mms-sms");
        sendIntent.putExtra("address", phoneNum.getText());
        sendIntent.putExtra("", body.getText());
        startActivity(Intent.createChooser(sendIntent, "Send sms via:"));


    }


    /**
     * send sms permission granted or not
     *
     * @return true if granted
     */
    private boolean is_send_sms_permission_granted() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * receive sms permission granted or not
     *
     * @return true if  permission is granted
     */

    private boolean is_receive_sms_permission_granted() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * read sms permission is granted or not
     *
     * @return true  if permsission is granted
     */
    private boolean is_read_sms_permission_granted() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Intent changed called for farther action
     *
     * @param keyCode
     * @param event
     * @return
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(SmsMainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onKeyDown(keyCode, event);
    }
}
