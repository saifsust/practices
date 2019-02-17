package org.waodec.geolocation.activities.databases;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.waodec.geolocation.activities.R;

import java.util.List;

public class ViewDataActivity extends AppCompatActivity {

    private ListView listView;

    private List<User> users;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_view_activity);
        listView = (ListView) findViewById(R.id.list_view);
        view();


    }


    private void view() {
        UserViewerTask task = new UserViewerTask();
        task.execute();
    }


    private class UserViewerTask extends AsyncTask<Void, Void, String[]> {
        @Override
        protected String[] doInBackground(Void... voids) {
            users = DbSingleTon.getInstance(getApplicationContext()).getDbExperiment().dbQuery().retrieveAll();
            String[] data = new String[users.size()];

            for (int i = 0; i < users.size(); ++i) {
                User user = users.get(i);
                data[i] = "USER_ID : " + user.getId() + " NAME : " + user.getUser_name() + " Mail : " + user.getUser_mail();
                System.out.println(data[i]);
            }

            return data;
        }

        @Override
        protected void onPostExecute(String[] data) {
            super.onPostExecute(data);
            adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_activated_1,data);
            listView.setAdapter(adapter);


            // startActivity(new Intent(getApplicationContext(), ViewDataActivity.class));
        }
    }
}
