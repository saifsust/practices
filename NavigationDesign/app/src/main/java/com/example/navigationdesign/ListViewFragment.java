package com.example.navigationdesign;

import android.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListViewFragment extends Fragment {


    private static String data[];
    private static final int SIZE = 100;

    private FragmentManager manager ;//= getFragmentManager();
    private FragmentTransaction transaction;
    private Fragment fragment;

    private ListView listView;
    private Button button;
    private Bundle bundle;



    static {
        data = new String[SIZE];
        for (int i = 0; i < SIZE; ++i) {
            data[i] = "List View-" + i;
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.list_view_fragment, container, false);

        listView = (ListView) view.findViewById(R.id.list_view);
        button = (Button) view.findViewById(R.id.list_back_button);




        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);


        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                manager = getFragmentManager();
               transaction = manager.beginTransaction();
               fragment = new TextViewFragment();
               transaction.replace(R.id.fragment_id_100,fragment);
               transaction.commit();

            }
        };

        listView.setOnItemClickListener(listener);



        View.OnClickListener buttonListner = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                manager = getFragmentManager();
                transaction = manager.beginTransaction();
                fragment = new EmptyFragment();
                transaction.replace(R.id.fragment_id_100,fragment);
                transaction.commit();
            }
        };


        button.setOnClickListener(buttonListner);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
