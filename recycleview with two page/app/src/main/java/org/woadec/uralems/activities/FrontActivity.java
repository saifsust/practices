package org.woadec.uralems.activities;

import android.os.Bundle;

import org.woadec.uralems.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FrontActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.front_layout_activity);
    }
}
