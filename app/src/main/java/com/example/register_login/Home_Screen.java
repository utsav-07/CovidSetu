package com.example.register_login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cuberto.liquid_swipe.LiquidPager;
import com.example.register_login.Pager.ViewPager;

public class Home_Screen extends AppCompatActivity {


    LiquidPager pager;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__screen);
        pager = findViewById(R.id.pager);
        viewPager = new ViewPager(getSupportFragmentManager() , 1);
        pager.setAdapter(viewPager);
    }
}