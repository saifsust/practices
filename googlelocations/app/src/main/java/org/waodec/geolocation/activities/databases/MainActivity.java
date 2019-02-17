package org.waodec.geolocation.activities.databases;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.waodec.geolocation.activities.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText userMail, userName;
    private Button submit, view;
    private DatabaseExperiment db;
    private Intent next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_activity);
        userName = (EditText) findViewById(R.id.user_name);
        userMail = (EditText) findViewById(R.id.user_mail);
        submit = (Button) findViewById(R.id.submit);
        view = (Button) findViewById(R.id.view);
        submit.setOnClickListener(this);
        view.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.submit:
                String name = userName.getText().toString();
                String mail = userMail.getText().toString();
                save(name, mail);
                break;
            case R.id.view:
                next = new Intent(MainActivity.this, ViewDataActivity.class);
                startActivity(next);
                break;

            default:
                break;

        }
    }


    private void save(final String name, final String mail) {


        class SaveTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                User user = new User();
                user.setUser_name(name);
                user.setUser_mail(mail);
                DbSingleTon.getInstance(getApplicationContext()).getDbExperiment().dbQuery().insert(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                //next = new Intent(MainActivity.this,ViewDataActivity.class);
                //startActivity(next);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "SAVE", Toast.LENGTH_LONG).show();
            }
        }


        SaveTask st = new SaveTask();
        st.execute();


        //startActivity(next);

    }


}
