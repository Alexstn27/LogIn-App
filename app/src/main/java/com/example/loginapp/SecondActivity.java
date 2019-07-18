package com.example.loginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private EditText userName, userPassword ;
    private Button regButton ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
       // getActionBar().setDisplayHomeAsUpEnabled(false);
        setupUIViews();
    }


    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.etName) ;
        userPassword = (EditText)findViewById(R.id.etPassword);
        regButton = (Button)findViewById(R.id.btnLogin);

    }
}
