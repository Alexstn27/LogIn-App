package com.example.loginapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText usernameEditText, passwordEditText, repeatPasswordEditText, emailEditText;
    String username,userpass,conpass, email;
    Button button;
    Context ctx=this;

    public enum StatusMessages {

        password_mismatch {
            @Override
            public String toString() {
                return "password not matching";
            }
        },
        registration_succesfull {
            @Override
            public String toString() {
                return "registration succesfull";
            }
        },
        invalid_email {
            @Override
            public String toString() {
                return "email is invalid";
            }
        },
        password_length {
            @Override
            public String toString() {
                return "Password length must have at least 8 character !!";
            }
        },
        password_special_char {
            @Override
            public String toString(){
                return "Password must have at least one special character !!";
            }
        },
        password_uppercase_char {
            @Override
            public String toString() {
                return "Password must have at least one uppercase character !!";
            }
        },
        password_lowercase_char {
            @Override
            public String toString() {
                return "Password must have at least one lowercase character !!";
            }
        },
        password_digit_char{};

        @Override
        public String toString() {
            return "Password must have at least one digit character !!";
        }



    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            usernameEditText =(EditText)findViewById(R.id.editText2);
            passwordEditText =(EditText)findViewById(R.id.EtPassword);
            repeatPasswordEditText =(EditText)findViewById(R.id.editText3);
            emailEditText = (EditText) findViewById(R.id.editText4);
            button=(Button)findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    username= usernameEditText.getText().toString();
                    userpass= passwordEditText.getText().toString();
                    conpass= repeatPasswordEditText.getText().toString();
                    email= emailEditText.getText().toString();

                    List<StatusMessages> errorList = new ArrayList<StatusMessages>();

                    if (!isValid(userpass, conpass, errorList)){
                          customToast(errorList.get(0));
//                        customToast(StatusMessages.password_mismatch);
                          passwordEditText.setText("");
                          repeatPasswordEditText.setText("");
                    }
                    else {


                        if (validEmail(email) == false) // if(!validEmail(email))
                        {
                            customToast(StatusMessages.invalid_email);
                            emailEditText.setText("");

                        } else {

                            DatabaseHelper dp=new DatabaseHelper(ctx);
                            long id = dp.insertUser(username, email, userpass);
                            // acest id trebuie folosit pe o metoda dp.getUser(id) -> usermodel
                            //trebuie sa compari usermodel.username cu username
                            customToast(StatusMessages.registration_succesfull);
                            finish();

                        }


                    }

                }
            });}




             //Add back button in toolbar

     private void customToast (StatusMessages message){
         Toast toast =  Toast.makeText(getBaseContext(),message.toString(),Toast.LENGTH_LONG);
         toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 40);
         toast.show();
     }


  private boolean validEmail(String email)
    {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPatternnew = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";
        String domain, last;
        try {
                domain = email.substring(email.indexOf('@'), email.length());
                last = domain.substring(domain.indexOf('.'),domain.length());

             } catch (IndexOutOfBoundsException e) {

                return false;
            }

        if (email.matches(emailPattern) && (last.length() ==3 || last.length() == 4)) // check @domain.nl or @domain.com or @domain.org
        {
            return true;
        }
        else //check for @domain.co.in or @domain.co.uk
            return email.matches(emailPatternnew) && last.length() == 6 && email.charAt(email.length() - 3) == '.';
    }



    //TODO: password acceptance criteria : 8 char length

    private boolean isValid(String passwordhere, String confirmhere, List<StatusMessages> errorList) { // refactoring List<StatusMessage> and add all error messages into enum structure

        Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");

        boolean flag = true;

        if (!passwordhere.equals(confirmhere)) {
            errorList.add(StatusMessages.password_mismatch);
            //customToast(StatusMessages.password_mismatch);
            flag = false;
        }
        if (passwordhere.length() < 8) {
            errorList.add(StatusMessages.password_length);
            flag = false;
        }
        if (!specailCharPatten.matcher(passwordhere).find()) {
            errorList.add(StatusMessages.password_special_char);
            flag = false;
        }
        if (!UpperCasePatten.matcher(passwordhere).find()) {
            errorList.add(StatusMessages.password_uppercase_char);
            flag = false;
        }
        if (!lowerCasePatten.matcher(passwordhere).find()) {
            errorList.add(StatusMessages.password_lowercase_char);
            flag =  false;
        }
        if (!digitCasePatten.matcher(passwordhere).find()) {
            errorList.add(StatusMessages.password_digit_char);
            flag = false;
        }

        return flag;
    }
}




// TODO: Use getUser(id) method to validate inserting operation.














