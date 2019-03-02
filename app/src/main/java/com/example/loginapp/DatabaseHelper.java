package com.example.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static  String DATABASE_NAME = "table_user";
    public static final int DATABASE_VERSION = 1;
    //TODO: delete table_user string from DatabaseHelper and use it from UserModel
    private static final String TABLE_USER = "usermodel";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserModel.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");
        onCreate(db);
    }

    public long insertUser(String username, String email, String pass) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserModel.KEY_NAME, username);
        values.put(UserModel.KEY_EMAIL, email);
        values.put(UserModel.KEY_PASS, pass);
        //
        long id = db.insert(UserModel.TABLE_NAME, null, values);
        db.close();

        // return newly inserted row id
        return id;
    }

    public UserModel getUser(long id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(UserModel.TABLE_NAME,
                new String[]{
                        UserModel.KEY_ID, UserModel.KEY_NAME, UserModel.KEY_EMAIL, UserModel.KEY_PASS},
                UserModel.KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare usermodel object
        UserModel user = new UserModel(
                cursor.getInt(cursor.getColumnIndex(UserModel.KEY_ID)),
                cursor.getString(cursor.getColumnIndex(UserModel.KEY_NAME)),
                cursor.getString(cursor.getColumnIndex(UserModel.KEY_EMAIL)),
                cursor.getString(cursor.getColumnIndex(UserModel.KEY_PASS)));

        // close the db connection
        cursor.close();

        return user;
    }


    public UserModel getUsername(String username) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(UserModel.TABLE_NAME,
                new String[]{ UserModel.KEY_ID, UserModel.KEY_NAME, UserModel.KEY_EMAIL, UserModel.KEY_PASS},
                UserModel.KEY_NAME + "=?", new String[]{username},null, null, null, null);
                //



        UserModel user = new UserModel();

        if (cursor.getCount() < 1)
            return user;
        else{

            cursor.moveToFirst();

            user = new UserModel(
                    cursor.getInt(cursor.getColumnIndex(UserModel.KEY_ID)),
                    cursor.getString(cursor.getColumnIndex(UserModel.KEY_NAME)),
                    cursor.getString(cursor.getColumnIndex(UserModel.KEY_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(UserModel.KEY_PASS)));

            // close the db connection
            cursor.close();

            return user;
        }

    }




    public List<UserModel> getAllUsers() {
        List<UserModel> users = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + UserModel.TABLE_NAME + " ORDER BY " +
                UserModel.KEY_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserModel user = new UserModel();
                user.setId(cursor.getInt(cursor.getColumnIndex(UserModel.KEY_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(UserModel.KEY_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(UserModel.KEY_EMAIL)));
                user.setPass(cursor.getString(cursor.getColumnIndex(UserModel.KEY_PASS)));

                users.add(user);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return users list
        return users;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + UserModel.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }
    }
