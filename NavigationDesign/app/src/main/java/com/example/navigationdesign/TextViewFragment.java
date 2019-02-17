package com.example.navigationdesign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TextViewFragment extends Fragment {


    private TextView textView;
    private Button button;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Fragment fragment;


    private String text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_view_fragment, container, false);
       textView = (TextView)view.findViewById(R.id.text_view);


       for(int i=0;i<10;++i){
           text+=" *** "+text;
       }
       textView.setText(text);

       button = (Button) view.findViewById(R.id.backClick);

       View.OnClickListener listener = new View.OnClickListener(){
           @Override
           public void onClick(View v) {

               manager = getFragmentManager();
               transaction = manager.beginTransaction();
               fragment = new ListViewFragment();
               transaction.replace(R.id.fragment_id_100,fragment);
               transaction.commit();



           }
       };


       button.setOnClickListener(listener);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
