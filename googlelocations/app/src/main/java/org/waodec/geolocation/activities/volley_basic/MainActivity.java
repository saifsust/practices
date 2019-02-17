package org.waodec.geolocation.activities.volley_basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.waodec.geolocation.activities.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String URL = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1";
    private static final String TAG = "VOLLEY BASIC TAG";
    private StringRequest stringRequest;
    private RequestQueue requestQueue;

    private Button download;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley_main_activity);
        download = (Button) findViewById(R.id.button_download);
        download.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.button_download) {
            sendRequest();
        }

    }


    private void sendRequest() {

        requestQueue = Volley.newRequestQueue(this);

        stringRequest = new StringRequest(Request.Method.GET, URL, new ResponseListner(), new ResponseListner());

        requestQueue.add(stringRequest);

    }

    private class ResponseListner implements Response.Listener<String>, Response.ErrorListener {

        @Override
        public void onResponse(String response) {
            Toast.makeText(getApplicationContext(), "RESPONSE : " + response.toString(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.i(TAG, "ERRO " + error.toString());
        }
    }


}
