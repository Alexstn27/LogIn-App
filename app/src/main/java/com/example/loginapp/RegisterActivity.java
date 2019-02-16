package com.example.loginapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText editText2,EtPassword,editText3;
    String username,userpass,conpass;
    Button button;
    Context ctx=this;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            editText2=(EditText)findViewById(R.id.editText2);
            EtPassword=(EditText)findViewById(R.id.EtPassword);
            editText3=(EditText)findViewById(R.id.editText3);
            button=(Button)findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    username=editText2.getText().toString();
                    userpass=EtPassword.getText().toString();
                    conpass=editText3.getText().toString();

                    if (!(userpass.equals(conpass))){
                        Toast.makeText(getBaseContext(),"password not matching",Toast.LENGTH_LONG).show();
                        EtPassword.setText("");
                        editText2.setText("");
                        editText3.setText("");
                        finish();
                    }
                    else {
                        DatabaseHelper dp=new DatabaseHelper(ctx);
                        Toast.makeText(getBaseContext(),"registration succesfull",Toast.LENGTH_LONG).show();
                        finish();

                    }

                }
            });
        }



  /*           //Add back button in toolbar
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item){
            int id = item.getItemId();

            if(id == android.R.id.home){
                this.finish();
            }

            return super.onOptionsItemSelected(item);
        }}










    //TODO: adding action event to Register NOW button (parse data filled by user and validate) - email address will contain @ and . chars


  /*  public static boolean isEmailValid(String email) {

        String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
            return true;
        else
            return false;
    } */


  private boolean validEmail(String email)
    {
        // TODO Auto-generated method stub
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPatternnew = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";
        String domain = email.substring(email.indexOf('@'), email.length());
        String last = domain.substring(domain.indexOf('.'),domain.length());
        if (email.matches(emailPattern) && (last.length() ==3 || last.length() == 4)) // check @domain.nl or @domain.com or @domain.org
        {
            return true;
        }
        else //check for @domain.co.in or @domain.co.uk
            return email.matches(emailPatternnew) && last.length() == 6 && email.charAt(email.length() - 3) == '.';
    }



    //TODO: password acceptance criteria : 8 char length



    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Please enter a given  password : ");
        String passwordhere = in.nextLine();
        System.out.print("Please re-enter the password to confirm : ");
        String confirmhere = in.nextLine();

        List<String> errorList = new ArrayList<String>();

        while (!isValid(passwordhere, confirmhere, errorList)) {
            System.out.println("The password entered here  is invalid");
            for (String error : errorList) {
                System.out.println(error);
            }

            System.out.print("Please enter a given  password : ");
            passwordhere = in.nextLine();
            System.out.print("Please re-enter the password to confirm : ");
            confirmhere = in.nextLine();
        }
        System.out.println("your password is: " + passwordhere);

    }

    public static boolean isValid(String passwordhere, String confirmhere, List<String> errorList) {

        Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        errorList.clear();

        boolean flag=true;

        if (!passwordhere.equals(confirmhere)) {
            errorList.add("password and confirm password does not match");
            flag=false;
        }
        if (passwordhere.length() < 8) {
            errorList.add("Password length must have at least 8 character !!");
            flag=false;
        }
        if (!specailCharPatten.matcher(passwordhere).find()) {
            errorList.add("Password must have atleast one specail character !!");
            flag=false;
        }
        if (!UpperCasePatten.matcher(passwordhere).find()) {
            errorList.add("Password must have atleast one uppercase character !!");
            flag=false;
        }
        if (!lowerCasePatten.matcher(passwordhere).find()) {
            errorList.add("Password must have atleast one lowercase character !!");
            flag=false;
        }
        if (!digitCasePatten.matcher(passwordhere).find()) {
            errorList.add("Password must have atleast one digit character !!");
            flag=false;
        }

        return flag;} }





// TODO: Use DatabaseHelper InsertUser method to save user credentials.





// TODO: Use getUser(id) method to validate inserting operation.


















