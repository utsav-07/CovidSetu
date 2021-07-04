package com.example.register_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class target : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)
        var x : String? = intent.getStringExtra("title")
        var y : String? = intent.getStringExtra("msg")

        var name:String? = intent.getStringExtra("name")
        var longitude:String? = intent.getStringExtra("longitude")
        var latitude:String? = intent.getStringExtra("latitude")
        var mob:String? = intent.getStringExtra("mob")

        val textTitle = findViewById<TextView>(R.id.tvTitle)
        val textDescp = findViewById<TextView>(R.id.tvDescription)
        val textPhone = findViewById<TextView>(R.id.mobile)
        val textName = findViewById<TextView>(R.id.client_name)
        val txtloc= findViewById<TextView>(R.id.location)
        textTitle.text= x.toString()
        textDescp.text= y.toString()
        txtloc.text=latitude.toString()+" "+longitude.toString()
        textName.text=name.toString();
        textPhone.text=mob.toString();
        Log.d("Target","$mob");
    }
}