package com.example.loginapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class SecondActivity extends AppCompatActivity {

    private EditText userName, userPassword ;
    private Button regButton ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
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
        Context context = getApplicationContext();
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_logout:
                //showToastMenuItem("Logout Item pressed");
                SharedPreferences  prefs = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("IS_LOGGED",false);
                editor.commit();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                showToastMenuItem("Settings Item pressed");
                return true;
        }

        return false;//super.onOptionsItemSelected(item);
    }

    private  void showToastMenuItem(CharSequence text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    private void setSupportActionBar(Toolbar toolbar) {

    }


    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.etName) ;
        userPassword = (EditText)findViewById(R.id.etPassword);
        regButton = (Button)findViewById(R.id.btnLogin);

    }
}
