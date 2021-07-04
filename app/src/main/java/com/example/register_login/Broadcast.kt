package com.example.register_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Broadcast : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        var pin: String? = intent.getStringExtra("pin")
        Log.d("MAIN","$pin")
        var Topic = "/topics/$pin"
        FirebaseMessaging.getInstance().subscribeToTopic(Topic)
        var broadCastBtn=findViewById<Button>(R.id.btnBroadCast)
        var tit = findViewById<TextInputEditText>(R.id.edTitleTxt)
        var msg = findViewById<TextInputEditText>(R.id.edtxtMsg)
        broadCastBtn.setOnClickListener {
            val title = tit.text.toString()
            val message = msg.text.toString()
            //TODO:work to be done
            val mob:String="9258441169"
            val name:String="Ajay Singh"
            val latitude:Double=1.043.toDouble()
            val longitude:Double=3.44.toDouble()

            if(title.isNotEmpty()&&message.isNotEmpty())
            {
                pushNotification(NotificationData(title,message,mob,name,latitude,longitude), Topic).also {
                    Log.d("DEB","$title"+" "+"$mob")
                    sendNotification(it)
                }
            }

        }
    }
    private fun sendNotification(notification:pushNotification)= CoroutineScope(Dispatchers.IO).launch {
        try{
            val response = Retrofitinstance.api.postNotification(notification)
        }catch (e:Exception){
            Log.i("Main",e.toString())
        }
    }


}
