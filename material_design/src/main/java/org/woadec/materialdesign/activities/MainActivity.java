package org.woadec.materialdesign.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.woadec.materialdesign.R;
import org.woadec.materialdesign.appbarlayout.AppBarLayoutActivity;
import org.woadec.materialdesign.cardviewTest.CardViewMainActivity;
import org.woadec.materialdesign.muslimpro.MuslimMainActivity;
import org.woadec.materialdesign.recycleviewTest.RecycleViewActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Intent intent;
    private Button muslinProButton, recycleViewButton, cardviewButton, appBarButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        muslinProButton = (Button) findViewById(R.id.muslim_pro_viewer);
        recycleViewButton = (Button) findViewById(R.id.recycleview_viewer);
        cardviewButton = (Button) findViewById(R.id.cardview_viewer);

        appBarButton = (Button) findViewById(R.id.appbar_viewer);

        muslinProButton.setOnClickListener(this);
        recycleViewButton.setOnClickListener(this);
        cardviewButton.setOnClickListener(this);
        appBarButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.muslim_pro_viewer:
                intent = new Intent(this, MuslimMainActivity.class);
                startActivity(intent);
                finish();
                return;
            case R.id.recycleview_viewer:
                intent = new Intent(this, RecycleViewActivity.class);
                startActivity(intent);
                finish();
                return;

            case R.id.cardview_viewer:
                intent = new Intent(this, CardViewMainActivity.class);
                startActivity(intent);
                finish();
                return;

            case R.id.appbar_viewer:
                intent = new Intent(this, AppBarLayoutActivity.class);
                startActivity(intent);
                finish();
                return;
        }

    }
}
