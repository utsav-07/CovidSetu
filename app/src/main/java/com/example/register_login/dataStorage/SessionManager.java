package com.example.register_login.dataStorage;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {


    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";



    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_NUMBER = "number";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void insertdata(String name, String number){
        // Storing login value as TRUE

        // Storing name in pref
        editor.putString(KEY_NAME, name);


        // Storing email in pref

        editor.putString(KEY_NUMBER, number);

        // commit changes
        editor.commit();
    }


    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_NUMBER, pref.getString(KEY_NUMBER, null));

        // return user
        return user;
    }

    public  void clerData(){
        editor.clear();
        editor.commit();
    }

}
