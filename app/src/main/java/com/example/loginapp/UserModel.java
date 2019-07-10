package com.example.loginapp;

public class UserModel {
    public static final String TABLE_NAME = "usermodel";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASS = "pass";
    private static final String TABLE_USER = "users";

    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + "(" + KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT, "
            + KEY_PASS + " TEXT, "
            + KEY_EMAIL + " TEXT );";


    private int id;
    private String name;
    private String email;
    private String pass;

    public UserModel() {
    }

    public UserModel(int id, String name, String email, String pass) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email  = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
