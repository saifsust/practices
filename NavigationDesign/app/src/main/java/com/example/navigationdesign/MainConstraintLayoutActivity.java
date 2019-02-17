package com.example.navigationdesign;

import android.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainConstraintLayoutActivity extends AppCompatActivity {


    private Button login;
    private EditText mail, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        login = (Button) findViewById(R.id.login_button);
        mail = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.password);


        View.OnClickListener listner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(mail.getText() + " " + password.getText());

                mail.setText(null);
                password.setText(null);
            }
        };

        login.setOnClickListener(listner);




    }


}
