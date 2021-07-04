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
            Log.d("DEB","$title"+" "+"$message");
            if(title.isNotEmpty()&&message.isNotEmpty())
            {
                pushNotification(NotificationData(title, message), Topic).also {
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
