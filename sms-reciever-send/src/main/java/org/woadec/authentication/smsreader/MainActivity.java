package org.woadec.authentication.smsreader;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.woadec.authentication.R;
import org.woadec.authentication.activities.LoginActivity;
import org.woadec.authentication.sms_app.SmsMainActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Receiver {

    private Intent intent;
    private Button readButton, verification, permissionButton, smsAppButton, facebookAccountKitButton;
    private TextView tv;
    private IncomingSMSReader br;

    public static int APP_REQUEST_CODE = 99;

    private int RESOLVE_HINT = 2;

    private GoogleApiClient apiClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        readButton = (Button) findViewById(R.id.reader);

        readButton.setOnClickListener(this);
        TextView tv = (TextView) findViewById(R.id.sms_view);

        verification = (Button) findViewById(R.id.verification);

        permissionButton = (Button) findViewById(R.id.permission);

        facebookAccountKitButton = (Button) findViewById(R.id.facebook_account_kit_button);
        smsAppButton = (Button) findViewById(R.id.sms_app_button);

        permissionButton.setOnClickListener(this);

        verification.setOnClickListener(this);

        facebookAccountKitButton.setOnClickListener(this);
        smsAppButton.setOnClickListener(this);

      /*  SmsManager smsManager = SmsManager.getDefault();

        String token = smsManager.createAppSpecificSmsToken(buildPendingIntent());

        Log.e(getClass().getSimpleName(), token);

        tv.setText(token);*/


    }


    /**
     * Google phone number verification
     *
     * @throws Exception
     */

    private void requestHint() throws Exception {
        HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();


        apiClient = new GoogleApiClient.Builder(getApplicationContext()).addApi(Auth.CREDENTIALS_API).build();


        PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(
                apiClient, hintRequest);
        startIntentSenderForResult(intent.getIntentSender(),
                RESOLVE_HINT, null, 0, 0, 0);
    }


    private void startSMSListener() {
        SmsRetrieverClient client = SmsRetriever.getClient(this /* context */);

        Task<Void> task = client.startSmsRetriever();

        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {


                IntentFilter intentFilter = new IntentFilter();

                intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);

                GoogleBroadcastReceiver receiver = new GoogleBroadcastReceiver();
                getApplicationContext().registerReceiver(receiver, intentFilter);


                Toast.makeText(getApplicationContext(), "SMS Retriever starts", Toast.LENGTH_LONG).show();

            }

        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    // Obtain the phone number from the result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (requestCode == RESOLVE_HINT) {
            if (resultCode == RESULT_OK) {
                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                // credential.getId();  <-- will need to process phone number string
            }
        }*/
    }


    /**
     * Google phone number veification is ended
     *
     * @param view
     */


    /**
     * Facebook authentication
     */

    private void onLogin(final LoginType loginType) {
        // create intent for the Account Kit activity


        final Intent intent = new Intent(this, AccountKitActivity.class);

        // configure login type and response type
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        loginType,
                        AccountKitActivity.ResponseType.TOKEN
                );
        final AccountKitConfiguration configuration = configurationBuilder.build();

        // launch the Account Kit activity
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configuration);
        startActivityForResult(intent, APP_REQUEST_CODE);
    }

    /**
     * Faceook authentication end
     *
     * @param view
     */


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.reader:
                br = new IncomingSMSReader();
                IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
                //filter.addAction(Intent.I);
                this.registerReceiver(br, filter);
                break;

            case R.id.verification:
                try {

                    AppEventsLogger logger = AppEventsLogger.newLogger(getApplicationContext());
                    logger.logEvent("onPhoneLogin");
                    onLogin(LoginType.PHONE);


                    // requestHint();
                    startSMSListener();


                } catch (Exception ex) {

                    Log.e(getClass().getSimpleName(), ex.getMessage());
                }

                break;

            case R.id.permission:


                break;


            case R.id.facebook_account_kit_button:

                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                break;
            case R.id.sms_app_button:

                intent = new Intent(MainActivity.this, SmsMainActivity.class);
                startActivity(intent);
                finish();
                break;
        }


    }

    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder sb = new StringBuilder();
        sb.append("Action: " + intent.getAction() + "\n");
        sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
        String log = sb.toString();
        Log.d(getClass().getSimpleName(), log);
        Toast.makeText(context, log, Toast.LENGTH_LONG).show();
    }


    private PendingIntent buildPendingIntent() {
        return PendingIntent.getActivity(this, 1337, new Intent(this, ResultActivity.class), 0);
    }


    @Override
    protected void onPause() {
        super.onPause();
        try{
            if (br != null)
                unregisterReceiver(br);

        }catch (Exception ex){
            Log.e(getClass().getSimpleName(),ex.getMessage());
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        try{
            if (br != null)
                unregisterReceiver(br);

        }catch (Exception ex){
            Log.e(getClass().getSimpleName(),ex.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {

            if (br != null)
                unregisterReceiver(br);
        } catch (Exception ex) {
            Log.e(getClass().getSimpleName(), ex.getMessage());
        }
    }
}
