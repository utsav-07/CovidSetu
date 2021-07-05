package com.example.register_login

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.register_login.dataStorage.SessionManager
import com.google.android.gms.location.*
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import java.util.jar.Manifest

class Broadcast : AppCompatActivity() {
lateinit var fusedLocationProviderClient: FusedLocationProviderClient
var area:String = "abs"
    var session: SessionManager? = null

    var name = "Suraj"
    var number:kotlin.String? = "9258441169"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        session = SessionManager(applicationContext)

        val user = session!!.userDetails
        name = user[SessionManager.KEY_NAME]!!

        // email

        // email
        number = user[SessionManager.KEY_NUMBER]
        if(name.isEmpty())
        {
            name="Suraj"
            number="9258441169"
        }

    //    println(name + number)
        var pin: String? = intent.getStringExtra("pin")
        Log.d("MAIN","$pin")
        var Topic = "/topics/$pin"
        FirebaseMessaging.getInstance().subscribeToTopic(Topic)
        var broadCastBtn=findViewById<Button>(R.id.btnBroadCast)
        val tit = findViewById<TextInputEditText>(R.id.edTitleTxt)
        val msg = findViewById<TextInputEditText>(R.id.edtxtMsg)
      fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this)

        broadCastBtn.setOnClickListener {

            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),1)
            }
            else
            {//getgeoLocation()
                var randomLoc:String ="area"
                fusedLocationProviderClient.lastLocation?.addOnSuccessListener {

                    if(it==null)
                    {
                        Toast.makeText(this,"Sorry cant't get location",Toast.LENGTH_SHORT);
                    }
                    else it.apply{
                        val lat = it.latitude;
                        val longi= it.longitude;
                        area=""+latitude+" "+longitude
                        val title = tit.text.toString()
                        val message = msg.text.toString()
                        //TODO:work to be done
                        Log.d("BroadCast",area);

                        val mob:String=number.toString()
                        val name:String=name.toString()
                        val latitude:String = it.latitude.toString()
                        val longitude:String=it.longitude.toString()

                        if(title.isNotEmpty()&&message.isNotEmpty()&&area.isNotEmpty())
                        {
                            pushNotification(NotificationData(title,message,mob,name,latitude,longitude), Topic).also {
                                Log.d("DEB","$title"+" "+"$latitude")
                                sendNotification(it)
                            }
                        }

                    }

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
