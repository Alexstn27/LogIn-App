package com.example.loginapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.EditTextPreference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText Name ;
    private EditText Password;
    private Button Login;
    private Button Register ;
    private View toastView;
    SharedPreferences prefs;

    @Override
    protected void onResume() {
        super.onResume ();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy ();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean isSaved = CheckCredentials();

        if (isSaved) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Name.requestFocus();
        Login = (Button) findViewById(R.id.btnLogin);
        Register = (Button) findViewById(R.id.btnRegister);


        Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });

        Button button = (Button) findViewById(R.id.btnRegister);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                //TODO: create one new activity using navigation controller stack

               // Intent intent = new Intent( view.getContext (), RegisterActivity.class);
                Intent intent = new Intent( MainActivity.this, RegisterActivity.class);

                startActivity(intent);
            }
        });


    }

    private boolean CheckCredentials() {
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        String name = prefs.getString ( "USERNAME", "" );
        String password = prefs.getString ( "PASSWORD", "" );

        if(name != null && !name .isEmpty() && password != null && !password .isEmpty()) {
            return true;
        }

        return false;

    }

    private void validate(String userName , String userPassword) {
        DatabaseHelper dp = new DatabaseHelper(this);
        //List<UserModel> allUsersFromDatabase = dp.getAllUsers();

        UserModel userFromDatabase = dp.getUsername(userName);
        String username = "";
        String password = "";
        if (userFromDatabase.getPass() != null) {
            password = userFromDatabase.getPass();
        }

        if (userFromDatabase.getName() != null) {
            username = userFromDatabase.getName();
        }
        if (username.equals(userName) && password.equals(userPassword)) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("USERNAME",userName);
            editor.putString("PASSWORD",userPassword);

            editor.commit();

            startActivity(intent);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Wrong Name or Password";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }
        //TODO:hide keyboard when click outside of EditText

        public void hideKeyboard(View view) {
            InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }



        //TODO: adding toast message login error :)
      /*  Context context = getApplicationContext();
        CharSequence text = "Wrong Name or Password";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show(); */



}
