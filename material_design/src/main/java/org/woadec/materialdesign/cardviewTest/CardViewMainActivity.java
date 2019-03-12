package org.woadec.materialdesign.cardviewTest;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.bumptech.glide.load.resource.bitmap.StreamBitmapDataLoadProvider;

import org.woadec.materialdesign.R;
import org.woadec.materialdesign.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CardViewMainActivity extends AppCompatActivity {


    private Intent intent;
    private RecyclerView recyclerView;
    private List<Album> albums = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview_main_activity);

        recyclerView = (RecyclerView) findViewById(R.id.cardview_main_viewer);


        data();

        Thread task = new Thread(new RecycleViewerDataManupulator(recyclerView,albums,getApplicationContext()));
        task.start();

    }

    private void data() {
        albums.add(new Album("I Love You", 23, R.drawable.img));
        albums.add(new Album("I Love You", 23, R.drawable.img));
        albums.add(new Album("I Love You", 23, R.drawable.img));
        albums.add(new Album("I Love You", 23, R.drawable.img));
        albums.add(new Album("I Love You", 23, R.drawable.img));
        albums.add(new Album("I Love You", 23, R.drawable.img));
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
}
