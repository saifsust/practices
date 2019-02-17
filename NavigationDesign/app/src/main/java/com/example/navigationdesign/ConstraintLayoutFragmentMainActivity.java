package com.example.navigationdesign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ConstraintLayoutFragmentMainActivity extends AppCompatActivity {


    private Button button;
    private FragmentManager manager = getSupportFragmentManager();
    private FragmentTransaction transaction;
    private Fragment fragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_fragment_activity);

        button = (Button) findViewById(R.id.view_button);

        View.OnClickListener listner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    transaction = manager.beginTransaction();
                    fragment = new ListViewFragment();
                    transaction.replace(R.id.fragment_id_100, fragment);
                    transaction.commit();
                } catch (Exception ex) {
                    System.out.println("ConstraintLayout Exception : " + ex.getMessage());
                }


            }
        };

        button.setOnClickListener(listner);


    }
}
