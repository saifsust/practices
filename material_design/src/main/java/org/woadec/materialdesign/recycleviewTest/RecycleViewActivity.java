package org.woadec.materialdesign.recycleviewTest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import org.woadec.materialdesign.R;
import org.woadec.materialdesign.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewActivity extends AppCompatActivity {


    private Intent intent;

    private MovieAdapter movieAdapter;
    private RecyclerView recyclerView;

    private List<Movie> movies = new ArrayList<>();

    /**
     * all Activity method are implemented.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_main_activity);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview_display);


        data();

        movieAdapter = new MovieAdapter(movies, new ItemClickListener() {
            @Override
            public void onClick(View view, int position, String link) {

                Toast.makeText(getApplicationContext(),"short press : "+link+" "+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongPress(View view, int position, String link) {
                Toast.makeText(getApplicationContext(),"Long press "+link+" "+position,Toast.LENGTH_SHORT).show();

            }
        });

        //LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLinearLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());

       // recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //recyclerView.addItemDecoration(new MovieItemDecorator(LinearLayoutManager.VERTICAL,this,30));

        recyclerView.setAdapter(movieAdapter);

        recyclerView.addOnItemTouchListener(new TouchListener(getApplicationContext(),recyclerView,new TouchListener.ClickListener(){

            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));




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
     *
     *
     */


    private void data() {
        String describe="Hello my friend nad Family Hello my friend nad Family Hello my friend nad Family Hello my friend nad Family Hello my friend nad Family";

        movies.add(new Movie("Title 1", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2015", describe));

        movies.add(new Movie("Title 2", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2016", "Hello my friend nad Family"));

        movies.add(new Movie("Title 3", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2017", "Hello my friend nad Family"));

        movies.add(new Movie("Title 4", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2018", "Hello my friend nad Family"));

        movies.add(new Movie("Title 5", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2019", "Hello my friend nad Family"));

        movies.add(new Movie("Title 7", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2000", "Hello my friend nad Family"));

        movies.add(new Movie("Title 8", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2001", "Hello my friend nad Family"));

        movies.add(new Movie("Title 9", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2002", "Hello my friend nad Family"));
        movies.add(new Movie("Title 10", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2002", "Hello my friend nad Family"));

        movies.add(new Movie("Title 11", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2002", "Hello my friend nad Family"));

        movies.add(new Movie("Title 12", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2002", "Hello my friend nad Family"));

        movies.add(new Movie("Title 13", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2002", "Hello my friend nad Family"));

        movies.add(new Movie("Title 13", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2002", "Hello my friend nad Family"));

        movies.add(new Movie("Title 13", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2002", "Hello my friend nad Family"));

        movies.add(new Movie("Title 13", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2002", "Hello my friend nad Family"));

        movies.add(new Movie("Title 13", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2002", "Hello my friend nad Family"));

        movies.add(new Movie("Title 13", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2002", "Hello my friend nad Family"));

        movies.add(new Movie("Title 13", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2002", "Hello my friend nad Family"));

        movies.add(new Movie("Title 13", "https://www.androidhive.info/2016/01/android-working-with-recycler-view/", "2002", "Hello my friend nad Family"));

    }

}
