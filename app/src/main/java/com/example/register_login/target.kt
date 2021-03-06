package com.example.register_login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.register_login.dataStorage.SessionManager

class target : AppCompatActivity() {

    lateinit var longitude:String
    lateinit var latitude:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_target)


        // email

        // email


        var x : String? = intent.getStringExtra("title")
        var y : String? = intent.getStringExtra("msg")
        var name:String? = intent.getStringExtra("name")
        latitude = intent.getStringExtra("latitude").toString()
         longitude= intent.getStringExtra("longitude").toString()
        var mob:String? = intent.getStringExtra("mob")
        val textTitle = findViewById<TextView>(R.id.tvTitle)
        val textDescp = findViewById<TextView>(R.id.tvDescription)
        val textPhone = findViewById<TextView>(R.id.mobile)
        val textName = findViewById<TextView>(R.id.client_name)

        textTitle.text= x.toString()
        textDescp.text= y.toString()

        textName.text=name.toString();
        textPhone.text=mob.toString();

    }

    fun locOnMap(view: View) {
        val intent = Intent(this,MapsActivity::class.java)
      intent.putExtra("latitude",latitude)
        intent.putExtra("longitude",longitude)
        Log.d("Target","${latitude}")

        startActivity(intent)
    }
}