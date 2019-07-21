package com.example.loginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

public class SecondActivity extends AppCompatActivity {

    private EditText userName, userPassword ;
    private Button regButton ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar =(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
       // getActionBar().setDisplayHomeAsUpEnabled(false);
        setupUIViews();
    }

<<<<<<< HEAD
=======
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void setSupportActionBar(Toolbar toolbar) {

    }


>>>>>>> 48d3c42... Log out button added on toolbar
    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.etName) ;
        userPassword = (EditText)findViewById(R.id.etPassword);
        regButton = (Button)findViewById(R.id.btnLogin);

    }
}
