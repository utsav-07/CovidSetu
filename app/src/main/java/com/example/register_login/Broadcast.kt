package com.example.register_login

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Broadcast : AppCompatActivity() {

     lateinit var location: Location
    lateinit var  fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        var pin: String? = intent.getStringExtra("pin")
        Log.d("MAIN","$pin")
        var Topic = "/topics/$pin"
        FirebaseMessaging.getInstance().subscribeToTopic(Topic)
        var broadCastBtn=findViewById<Button>(R.id.btnBroadCast)
        val tit = findViewById<TextInputEditText>(R.id.edTitleTxt)
        val msg = findViewById<TextInputEditText>(R.id.edtxtMsg)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        broadCastBtn.setOnClickListener {
            fetchLocation()

            val title = tit.text.toString()
            val message = msg.text.toString()
            //TODO:work to be done
            var longitude:String="1"
            var latitude:String="2"
            if(location.latitude!=null) {
                latitude = location.latitude.toString()
                longitude = location.longitude.toString()
            }
            Log.d("BroadCast","${location.longitude.toString()} ,${location.longitude.toString()}");


            val mob:String="9258441169"
            val name:String="Ajay Singh"


            if(title.isNotEmpty()&&message.isNotEmpty())
            {
                pushNotification(NotificationData(title,message,mob,name,latitude,longitude), Topic).also {
                    Log.d("DEB","$title"+" "+"$mob")
                    sendNotification(it)
                }
            }

        }
    }

    private fun fetchLocation() {
        val task = fusedLocationProviderClient.lastLocation

       if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=
           PackageManager.PERMISSION_GRANTED&&ActivityCompat
               .checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
       {
           ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
           return
       }
       task.addOnSuccessListener {
           if(it!=null)
           {
               Toast.makeText(applicationContext,"${it.latitude}  ${it.longitude}",Toast.LENGTH_SHORT).show()


           }

       }
       location=task.result
    }

    private fun sendNotification(notification:pushNotification)= CoroutineScope(Dispatchers.IO).launch {
        try{
            val response = Retrofitinstance.api.postNotification(notification)
        }catch (e:Exception){
            Log.i("Main",e.toString())
        }
    }


}
