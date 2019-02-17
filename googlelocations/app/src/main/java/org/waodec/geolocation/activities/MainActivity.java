package org.waodec.geolocation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Intent nextIntent;
    private Button search;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        search = (Button) findViewById(R.id.search_map);
        search.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.search_map) {
            System.out.println("Hello Search");
            nextIntent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(nextIntent);
        }

    }
}
