package com.example.register_login.Dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.register_login.MainActivity2;
import com.example.register_login.MainActivity3;
import com.example.register_login.R;
import com.example.register_login.dataStorage.SessionManager;

import java.util.HashMap;

public class Dashboard extends AppCompatActivity {

//    SessionManager session;
//    String name = "", email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//        session = new SessionManager(getApplicationContext());
//        HashMap<String, String> user = session.getUserDetails();
//         name = user.get(SessionManager.KEY_NAME);
//
//        // email
//         email = user.get(SessionManager.KEY_EMAIL);
//        System.out.println(name  + email);


    }

    public void stats(View view) {
        //TODO: move to new Intent

        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(intent);
    }

    public void sos_alert(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
        startActivity(intent);

    }
}