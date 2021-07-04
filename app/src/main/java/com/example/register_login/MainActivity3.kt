package com.example.register_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val pin = findViewById<TextInputEditText>(R.id.pincode)
        val nextBtn =findViewById<Button>(R.id.doneBtn)
        nextBtn.setOnClickListener{
            val pinstr=pin.text.toString()
            val intent = Intent(this,Broadcast::class.java)
            intent.putExtra("pin",pinstr);
            startActivity(intent)
        }

    }
}