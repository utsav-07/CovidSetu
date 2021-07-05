package com.example.register_login

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.register_login.dataStorage.SessionManager

class target : AppCompatActivity() {
    var session: SessionManager? = null

    var name = ""
    var number:kotlin.String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_target)
        session = SessionManager(applicationContext)

        val user = session!!.userDetails
        name = user[SessionManager.KEY_NAME]!!

        // email

        // email
        number = user[SessionManager.KEY_NUMBER]
        println(name + number)
        var x : String? = intent.getStringExtra("title")
        var y : String? = intent.getStringExtra("msg")
        var name:String? = intent.getStringExtra("name")
        var latitude:String? = intent.getStringExtra("latitude")
        var longitude:String? = intent.getStringExtra("longitude")
        var mob:String? = intent.getStringExtra("mob")
        val textTitle = findViewById<TextView>(R.id.tvTitle)
        val textDescp = findViewById<TextView>(R.id.tvDescription)
        val textPhone = findViewById<TextView>(R.id.mobile)
        val textName = findViewById<TextView>(R.id.client_name)
        val txtloc= findViewById<TextView>(R.id.location)
        textTitle.text= x.toString()
        textDescp.text= y.toString()
        txtloc.text=latitude+" "+longitude
        textName.text=name.toString();
        textPhone.text=mob.toString();
        Log.d("Target","$latitude");
    }
}