package com.example.loginapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Name ;
    private EditText Password;
    private Button Login;
    private Button Register ;
    private View toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Name.requestFocus();
        Login = (Button) findViewById(R.id.btnLogin);
        Register = (Button) findViewById(R.id.btnRegister);

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
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
    private void validate(String userName , String userPassword){
        if ( (userName.equals("Admin")) && (userPassword.equals("Admin")) ) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class );
            startActivity(intent);
        }else{
            Context context = getApplicationContext();
            CharSequence text = "Wrong Name or Password";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }


        //TODO: adding toast message login error
      /*  Context context = getApplicationContext();
        CharSequence text = "Wrong Name or Password";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show(); */

    }

}
