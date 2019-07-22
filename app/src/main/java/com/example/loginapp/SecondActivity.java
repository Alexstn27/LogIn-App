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
         // setSupportActionBar(toolbar);
       // getActionBar().setDisplayHomeAsUpEnabled(false);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupUIViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;//super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;//super.onOptionsItemSelected(item);
    }

    private void setSupportActionBar(Toolbar toolbar) {

    }


    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.etName) ;
        userPassword = (EditText)findViewById(R.id.etPassword);
        regButton = (Button)findViewById(R.id.btnLogin);

    }
}
