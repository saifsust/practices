package org.woadec.materialdesign.muslimpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import org.woadec.materialdesign.R;
import org.woadec.materialdesign.activities.MainActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MuslimMainActivity extends AppCompatActivity {


    private Intent intent;


    /**
     * Activity's all methods are implemented.
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_view_activity);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     *
     */

}
